package com.linuxtek.kona.app.comm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KApp;
import com.linuxtek.kona.app.core.entity.KAppUser;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KAppService;
import com.linuxtek.kona.app.core.service.KAppUserService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.app.comm.entity.KPushNotification;
import com.linuxtek.kona.app.comm.entity.KPushNotificationDevice;
import com.linuxtek.kona.app.comm.entity.KPushNotificationMessage;
import com.linuxtek.kona.app.comm.service.KPushService;
import com.linuxtek.kona.app.util.KPositionUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KStringUtil;



public abstract class KAbstractPushNotificationMessageService<T extends KPushNotificationMessage,EXAMPLE,
															 A extends KApp,
															 U extends KUser,
															 AU extends KAppUser,
															 N extends KPushNotification,
															 D extends KPushNotificationDevice> 
		extends KAbstractService<T,EXAMPLE>
		implements KPushNotificationMessageService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPushNotificationMessageService.class);

	// ----------------------------------------------------------------------------

	protected abstract T getNewObject();
	
	protected abstract <S extends KAppService<A>> S getAppService();
	
	protected abstract <S extends KUserService<U>> S getUserService();
	
	protected abstract <S extends KAppUserService<AU>> S getAppUserService();
	
	protected abstract <S extends KPushNotificationDeviceService<D>> S getPushNotificationDeviceService();
    
	protected abstract <S extends KPushService> S getPushService();


	// ----------------------------------------------------------------------------

	@Override
	public void validate(T pushNotificationMessage) {
		if (pushNotificationMessage.getCreatedDate() == null) {
			pushNotificationMessage.setCreatedDate(new Date());
		}

		pushNotificationMessage.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------
	
	
	@Override
	public T broadcast(T message, List<Long> userIds, Double latitude, Double longitude, Double radius, boolean sandbox) {
		
		if (radius != null) {
			radius = KPositionUtil.toMeters(radius);
		}
		
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("userIds", userIds);
		filter.put("latitude", latitude);
		filter.put("longitude", longitude);
		filter.put("radius", radius);
		return broadcast(message, filter, sandbox);
	}
	
	// ----------------------------------------------------------------------------

	
	@Override 
	// filter currently only supports: latitude, longitude, radius, appId, userIds
	public T broadcast(Long appId, String title, String messageBody, 
			String imageUrl, String actionUrl, Map<String, Object> filter, boolean sandbox) {
		
		T message = getNewObject();
		message.setAppId(appId);
		message.setSandbox(sandbox);
		message.setTitle(title);
		message.setMessage(messageBody);
		message.setImageUrl(imageUrl);
		message.setActionUrl(actionUrl);
		message.setDeviceCount(0); // initialize count
		return broadcast(message, filter, sandbox);
	}
	
	// ----------------------------------------------------------------------------
	
	@Override 
	public T broadcast(final T message, Map<String, Object> filter, boolean sandbox) {
		
		message.setId(null); // make sure we're creating a new object
		message.setSandbox(sandbox);
		message.setDeviceCount(0); // initialize count
		message.setFilter(KStringUtil.toJson(filter));
		add(message);
		
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					logger.debug("blast thread running ...");
					blast(message);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		};
		
		t.start();
		
		return message;
	}
	
	// ----------------------------------------------------------------------------
	
	protected Map<String, Object> createPayLoadData(T message) {
		A app = getAppService().fetchById(message.getAppId());
		
		Map<String, Object> data = new HashMap<String,Object>();
		
		data.put("source", app.getName());
		
		if (message.getTitle() != null) {
			data.put("title", message.getTitle());
		}
		
		if (message.getActionUrl() != null) {
			data.put("action_url", message.getActionUrl());
		}
		
		if (message.getImageUrl() != null) {
			data.put("image_url", message.getImageUrl());
		}
		
		return data;
	}
	
	// ----------------------------------------------------------------------------
	
	protected void blast(T message) {
		logger.debug("blast called for message: " + message);
		
		Map<String,Object> filter = KStringUtil.toMap(message.getFilter());
		Long affinityAppId = null;
		
		Map<String, Object> data = createPayLoadData(message);
		
		List<D> deviceList = getDeviceList(filter, affinityAppId, message.isSandbox());
		
		int sendCount = 0;
		List<String> blastErrors = new ArrayList<String>();
		List<String> deviceIdList = new ArrayList<String>();
		
		for (D device : deviceList) {
			logger.debug("blast push device: " + device);
			
			KPushService.Platform platform = getPushService().getPushPlatform(device.getPlatformName(), device.isSandbox());
			
			String endpoint = device.getPushEndpoint();
			
			if (platform == null || endpoint == null) {
				continue;
			}
			
			try {
				getPushService().publish(platform, endpoint, message.getMessage(), data);
				
				logger.debug("\n--------------------------\n"
						+ "\npush message:"
						+ "\nplatform: " + platform
						+ "\nendpoint: " + endpoint
						+ "\nmessage: " + message
						+ "\ndata: " + data 
						+ "\n--------------------------\n"
				);
				
				sendCount += 1;
				
				deviceIdList.add(device.getId().toString());
				
			} catch (Exception e) {
				blastErrors.add(e.getMessage());
			}
		}
		
		logger.debug("blast: updating sendCount: " + sendCount); 
		
		if (blastErrors.size() > 0) {
			logger.warn("Broadcast blast errors" + KStringUtil.join(blastErrors, "\n\n"));
		}
		
		message.setDeviceCount(sendCount);
		
		
		// FIXME: this won't save since it's a blob type and underlying framework needs to support it
		message.setDevices(KStringUtil.join(deviceIdList,",").getBytes());
		
		update(message);
	}
	
	// ----------------------------------------------------------------------------
	
	private List<D> getDeviceList(Map<String,Object> filter, Long affinityAppId, boolean sandbox) {
		logger.debug("getDeviceList called for filter: " + KStringUtil.toJson(filter));
		
		List<Long> userIdList = getUserIdList(filter);
		
		if (userIdList == null) {
			logger.debug("getDeviceList: getUserIdList returned null");
			return null;
		}
		
		List<D> result = getPushNotificationDeviceService().fetchByUserIds(userIdList, true, sandbox, affinityAppId);
		
		logger.debug("getDeviceList: fetchByUserIds: result size: " + result.size());
		
		return result;
	}
	
	// ----------------------------------------------------------------------------
	
	
	@SuppressWarnings("unchecked")
	private List<Long> getUserIdList(Map<String,Object> filter) {
		logger.debug("getUserIdList called for filter: " + KStringUtil.toJson(filter));
		
		List<Long> userIdList = null;
		Long appId = null;
		Double latitude = null;
		Double longitude = null;
		Double radius = null; // in meters
		
		// NOTE: filter is serialized as Map<String,String> so explicitly convert types
		if (filter != null) {
			Object value = filter.get("appId");
			if (value != null) {
				appId = Long.valueOf(value.toString());
			}
			
			value = filter.get("latitude");
			if (value != null) {
				latitude = Double.valueOf(value.toString());
			}
			
			value = filter.get("longitude");
			if (value != null) {
				longitude = Double.valueOf(value.toString());
			}
			
			value = filter.get("radius");
			if (value != null) {
				radius = Double.valueOf(value.toString());
			}
			
			value = filter.get("userIds");
			if (value != null) {
				userIdList = new ArrayList<Long>();
				
				List<Object> list = (List<Object>) value;
				
				for (Object o : list) {
					userIdList.add(Long.valueOf(o.toString()));
				}
			}
		}
		
		if (userIdList == null) {
			userIdList = new ArrayList<Long>();
		}
		
		// first see if we have a geofence
		List<U> userList = null;
		
		if (latitude != null && longitude != null && radius != null) {
			userList = getUserService().fetchProximate(latitude, longitude, radius);
			logger.debug("getUserIdList: haveGeoFence: matched userList count: " + userList.size());
		} else {
			userList = getUserService().fetchAllRegistered(null);
		}
		
		if (userList != null) {
			for (U user : userList) {
				userIdList.add(user.getId());
			}
		}
		
		
		// if appId is set, the filter on its value
		List<Long> result = null;
		
		if (appId != null) {
			result =  new ArrayList<Long>();
			
			List<AU> appUserList = getAppUserService().fetchByAppId(appId);
			
			for (AU appUser : appUserList) {
				if (userIdList.contains(appUser.getUserId())) {
					result.add(appUser.getId());
				}
			}
			
			logger.debug("getUserIdList: have appId: filtered userIdList count: " + result.size());
		} else {
			result = userIdList;
		}
		
		return result;
	}
}
