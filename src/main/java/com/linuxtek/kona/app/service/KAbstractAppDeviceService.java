/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.entity.KApp;
import com.linuxtek.kona.app.entity.KAppDevice;
import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractAppDeviceService<T extends KAppDevice,EXAMPLE,A extends KApp> 
		extends KAbstractService<T,EXAMPLE>
		implements KAppDeviceService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppDeviceService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract T getNewAppDeviceObject();
    
	protected abstract <S extends KAppService<A>> S getAppService();
    
	protected abstract List<T> fetchByUserIds(List<Long> userIdList);
    
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(T appDevice) {
    	if (appDevice.getCreatedDate() == null) {
			appDevice.setCreatedDate(new Date());
		}
    	
    	appDevice.setLastUpdated(new Date());
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	public List<T> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------
    

    @Override
    public T fetchByAppIdAndDeviceUuid(Long appId, String deviceUuid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("deviceUuid", deviceUuid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public T fetchByUserIdAndPushToken(Long userId, String pushToken) {
    	// without this check, it would look for app_device where push_token is null
    	if (userId == null || pushToken == null) return null;
    	Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
    	filter.put("pushToken", pushToken);
    	return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public List<T> fetchByPushToken(String pushToken) {
    	// without this check, it would look for app_device where push_token is null
    	if (pushToken == null) return null;
		Map<String,Object> filter = KMyBatisUtil.createFilter("pushToken", pushToken);
		return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    // --------------------------------------
    
    @Override @Transactional
    public T createOrUpdate(Long userId, Long appId, String deviceUuid, String pushToken) {
        T appDevice = getNewAppDeviceObject();
		appDevice.setAppId(appId);
		appDevice.setDeviceUuid(deviceUuid);
		appDevice.setUserId(userId);
		appDevice.setEnabled(true);
		appDevice.setPushToken(pushToken);
        
		return createOrUpdate(appDevice);
    }
    
    @Override @Transactional
    public T createOrUpdate(T appDevice) {
    	Long userId = appDevice.getUserId();
    	Long appId = appDevice.getAppId();
    	String deviceUuid = appDevice.getDeviceUuid();
    	String pushToken = appDevice.getPushToken();
        
      	// first check if we have an existing device for this user and pushToken
        T current = fetchByUserIdAndPushToken(userId, pushToken);
        
        if (current != null) {
        	// sanity check
        	checkAppDevice(current, userId, appId, deviceUuid);
        	return current;
        }
        
        if (deviceUuid != null) {
        	current = fetchByAppIdAndDeviceUuid(appId, deviceUuid);
        	if (current != null) {
        		checkAppDevice(current, userId, appId, deviceUuid);
        		appDevice.setId(current.getId());
        	}
        }
        
        // check if we already have an endpoint for this token
        List<T> list = fetchByPushToken(pushToken);
        
        if (list != null && list.size() > 0) {
        	String endpoint = list.get(0).getPushEndpoint();
        	
        	for (int i=1; i<list.size(); i++) {
        		String nextEndPoint = list.get(i).getPushEndpoint();
        		
        		if ((endpoint == null && nextEndPoint != null) 
        				|| (endpoint != null && nextEndPoint == null) 
        				|| (endpoint != null && nextEndPoint != null 
        					&& !endpoint.equals(nextEndPoint))) {
        			throw new IllegalStateException("Existing AppDevice token/endpoint mismatch:"
        					+ "\nAppDevice 1:"  + list.get(0)
        					+ "\nAppDevice 2:"  + list.get(i));
        		}
        	}
        	
        	if (endpoint != null) {
        		appDevice.setPushEndpoint(endpoint);
        	}
        }
        
        if (appDevice.getId() == null) {
        	appDevice = add(appDevice);
        } else {
        	appDevice = update(appDevice);
        }
        
        appDevice = updateDeviceEndpoint(appDevice);
        
        return appDevice;
    }
    
    
    private void checkAppDevice(T current, Long userId, Long appId, String deviceUuid) {
       	if (userId != null && !current.getUserId().equals(userId)) {
    		throw new IllegalStateException("AppDevice: UserId mismatch:  userId: " + userId + "   appDevice: " + current);
    	}
       	
    	if (deviceUuid != null && !current.getDeviceUuid().equals(deviceUuid)) {
    		throw new IllegalStateException("AppDevice: DeviceId mismatch:  deviceUuid: " + deviceUuid + "   appDevice: " + current);
    	}
    	
    	if (appId != null && !current.getAppId().equals(appId)) {
    		throw new IllegalStateException("AppDevice: AppId mismatch:  appId: " + appId + "   appDevice: " + current);
    	}
    }
    
    @Override @Transactional
    public T updateDeviceEndpoint(T device) {
        String pushToken = device.getPushToken();
        
        if (device.getAppId() == null || pushToken == null) {
        	throw new IllegalArgumentException("AppId and PushToken must be set");
        }
        
        
        AppPlatform appPlatform = appPlatformService.fetchByAppIdAndPlatformId(device.getAppId(), device.getPlatformId());
        String appEndpoint = appPlatform.getPushEndpoint();
        
        if (appEndpoint == null) {
        	throw new IllegalStateException("AppEndPoint is null");
        }
        
        PushService.Platform pushPlatform = getPushPlatform(appPlatform.getPlatformId());
        if (pushPlatform == null) {
        	throw new IllegalArgumentException("PlatformId is not supported for push notification: " 
        			+ appPlatform.getPlatformId());
        }
        
        List<T> appDeviceList = fetchByPushToken(pushToken);
        
        List<Long> userIds = new ArrayList<Long>();
        
        for (T d : appDeviceList) {
        	userIds.add(d.getUserId());
        }
        
        
        // ok, everything looks good so far.  if we have an existing endpoint, delete it first
        String endpoint = device.getPushEndpoint();
        
        if (endpoint != null) {
        	getPushService().deleteDeviceEndpoint(endpoint);
        }
        
        String deviceEndpoint = getPushService().createDeviceEndpoint(pushPlatform, appEndpoint, pushToken);
        
        if (deviceEndpoint == null) {
        	throw new IllegalStateException("Unable to create deviceEndpoint for device: " + device);
        }
        
        device.setPushEndpoint(deviceEndpoint);
        
        return update(device);
    }
    
    

	@Override
	// unique - return 1 device per user (must figure out which device to return for multiple devices)
	// affinityAppId - hint to choose a particular device for when unque is true
	public List<T> fetchByUserIds(List<Long> userIdList, boolean unique, Long affinityAppId) {
		List<T> deviceList = fetchByUserIds(userIdList);
        
		if (unique) {
			Map<Long, T> map = new HashMap<Long, T>();
            
			for (T device : deviceList) {
				if (!map.keySet().contains(device.getUserId())) {
					map.put(device.getUserId(), device);
				} else {
					T nextDevice = map.get(device.getUserId());
					nextDevice = chooseDevice(device, nextDevice, affinityAppId);
					map.put(device.getUserId(), nextDevice);
				}
			}
            
			return new ArrayList<T>(map.values());
		} else {
			return deviceList;
		}
	}
	
	private T chooseDevice(T prevDevice, T nextDevice, Long affinityAppId) {
		if (affinityAppId != null) {
			if (prevDevice.getAppId().equals(affinityAppId)) return prevDevice;
			if (nextDevice.getAppId().equals(affinityAppId)) return nextDevice;
		}
		
		// ok, they're both either partners or both public. choose the last one created
		if (prevDevice.getCreatedDate().getTime() > nextDevice.getCreatedDate().getTime()) {
			return prevDevice;
		} else {
			return nextDevice;
		}
		
	}

}
