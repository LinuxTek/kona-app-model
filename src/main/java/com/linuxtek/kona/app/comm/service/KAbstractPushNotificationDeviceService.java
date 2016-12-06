package com.linuxtek.kona.app.comm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.comm.entity.KPushNotification;
import com.linuxtek.kona.app.comm.entity.KPushNotificationDevice;
import com.linuxtek.kona.app.comm.service.KPushService;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractPushNotificationDeviceService<T extends KPushNotificationDevice,EXAMPLE,
															N extends KPushNotification> 
		extends KAbstractService<T,EXAMPLE>
		implements KPushNotificationDeviceService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPushNotificationDeviceService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract <S extends KPushService> S getPushService();
	
	protected abstract <S extends KPushNotificationService<N>> S getPushNotificationService();
    
	protected abstract List<T> fetchByUserIds(List<Long> userIdList, boolean sandbox);
    
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(T pushDevice) {
    	if (pushDevice.getCreatedDate() == null) {
			pushDevice.setCreatedDate(new Date());
		}
    	
    	pushDevice.setLastUpdated(new Date());
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	public List<T> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public List<T> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------
    

    @Override
    public T fetchByAppIdAndUserIdAndDeviceUuid(Long appId, Long userId, String deviceUuid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("userId", userId);
        filter.put("deviceUuid", deviceUuid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public T fetchByPushToken(String pushToken) {
    	// without this check, it would look for push_device where push_token is null
    	if (pushToken == null) return null;
		Map<String,Object> filter = KMyBatisUtil.createFilter("pushToken", pushToken);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override @Transactional
    public T save(T pushDevice) {
    	Long userId = pushDevice.getUserId();
    	Long appId = pushDevice.getAppId();
    	String deviceUuid = pushDevice.getDeviceUuid();
    	String pushToken = pushDevice.getPushToken();
    	String oldPushToken = null;
    	
    	T current = fetchByAppIdAndUserIdAndDeviceUuid(appId, userId, deviceUuid);
        
    	if (current != null) {
    		if (pushDevice.getId() == null) {
        		pushDevice.setId(current.getId());
    		} else {
    			if (!current.getId().equals(pushDevice.getId())) {
    				throw new IllegalStateException("Existing PushNotificationDevice exists: ${pushDevice}");
    			}
    		}
    		
    		oldPushToken = current.getPushToken();
    		
    		if (oldPushToken != null) {
    			if (pushToken == null) {
    				pushDevice.setPushToken(oldPushToken);
    			} else {
    				if (oldPushToken.equals(pushToken)) {
    					pushDevice.setPushEndpoint(current.getPushEndpoint());
    				} else {
    					pushDevice.setPushEndpoint(null);
    				}
    			}
    		}
    	}
        
        // sanity check
    	// see if we already have an endpoint for this token
        T device = fetchByPushToken(pushToken);
        
        // if pushToken exists and doesn't already belong to us then throw an exception
        if (device != null && (pushDevice.getId() == null || !pushDevice.getId().equals(device.getId()))) {
        	throw new IllegalStateException("PushToken already assigned to another device: " + device);
        }
        
        if (pushDevice.getId() == null) {
        	pushDevice = add(pushDevice);
        } else {
        	pushDevice = update(pushDevice);
        }
        
        if (pushDevice.getPushEndpoint() == null) {
        	pushDevice = updateEndpoint(pushDevice, oldPushToken);
        }
        
        
        return pushDevice;
    }
    
	// ----------------------------------------------------------------------------
    
    protected T updateEndpoint(T device, String oldPushToken) {
        String pushToken = device.getPushToken();
        
        if (device.getAppId() == null || pushToken == null) {
        	throw new IllegalArgumentException("AppId and PushToken must be set");
        }
        
        N notification = getPushNotificationService().fetchById(device.getPushNotificationId());
        
        String pushEndpoint = notification.getPushEndpoint();
        
        if (pushEndpoint == null) {
        	throw new IllegalStateException("AppEndPoint is null");
        }
        
        KPushService.Platform pushPlatform = 
        		getPushService().getPushPlatform(notification.getPlatformName(), notification.isSandbox());
        
        if (pushPlatform == null) {
        	throw new IllegalArgumentException("Platform is not supported for push notification: " 
        			+ notification.getPlatformName());
        }
        
        // if we have an existing endpoint, delete it first
        String endpoint = device.getPushEndpoint();
        
        if (endpoint != null) {
        	getPushService().deleteDeviceEndpoint(endpoint);
        }
        
        endpoint = getPushService().createDeviceEndpoint(pushPlatform, pushEndpoint, pushToken, null);
        
        if (endpoint == null) {
        	throw new IllegalStateException("Unable to create endpoint for device: " + device);
        }
        
        device.setPushEndpoint(endpoint);
        
        return update(device);
    }
    
	// ----------------------------------------------------------------------------

	@Override
	// unique - return 1 device per user (must figure out which device to return for multiple devices)
	// affinityAppId - hint to choose a particular device for when unque is true
	public List<T> fetchByUserIds(List<Long> userIdList, boolean unique, boolean sandbox, Long affinityAppId) {
		List<T> deviceList = fetchByUserIds(userIdList, sandbox);
        
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
	
	// ----------------------------------------------------------------------------
	
	private T chooseDevice(T prevDevice, T nextDevice, Long affinityAppId) {
		if (affinityAppId != null) {
			if (prevDevice.getAppId().equals(affinityAppId)) return prevDevice;
			if (nextDevice.getAppId().equals(affinityAppId)) return nextDevice;
		}
		
		// choose the last one created
		if (prevDevice.getCreatedDate().getTime() > nextDevice.getCreatedDate().getTime()) {
			return prevDevice;
		} else {
			return nextDevice;
		}
	}
}
