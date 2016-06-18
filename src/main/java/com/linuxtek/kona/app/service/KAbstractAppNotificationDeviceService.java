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

import com.linuxtek.kona.app.entity.KAppNotification;
import com.linuxtek.kona.app.entity.KAppNotificationDevice;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractAppNotificationDeviceService<T extends KAppNotificationDevice,EXAMPLE,
															N extends KAppNotification> 
		extends KAbstractService<T,EXAMPLE>
		implements KAppNotificationDeviceService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppNotificationDeviceService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract <S extends KPushService> S getPushService();
	
	protected abstract <S extends KAppNotificationService<N>> S getAppNotificationService();
    
	protected abstract List<T> fetchByUserIds(List<Long> userIdList, boolean sandbox);
    
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
    	// without this check, it would look for app_device where push_token is null
    	if (pushToken == null) return null;
		Map<String,Object> filter = KMyBatisUtil.createFilter("pushToken", pushToken);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override @Transactional
    public T save(T appDevice) {
    	Long userId = appDevice.getUserId();
    	Long appId = appDevice.getAppId();
    	String deviceUuid = appDevice.getDeviceUuid();
    	String pushToken = appDevice.getPushToken();
    	String oldPushToken = null;
    	
    	T current = fetchByAppIdAndUserIdAndDeviceUuid(appId, userId, deviceUuid);
        
    	if (current != null) {
    		if (appDevice.getId() == null) {
        		appDevice.setId(current.getId());
    		} else {
    			if (!current.getId().equals(appDevice.getId())) {
    				throw new IllegalStateException("Existing AppNotificationDevice exists: ${appDevice}");
    			}
    		}
    		
    		oldPushToken = current.getPushToken();
    		
    		if (oldPushToken != null) {
    			if (pushToken == null) {
    				appDevice.setPushToken(oldPushToken);
    			} else {
    				if (oldPushToken.equals(pushToken)) {
    					appDevice.setPushEndpoint(current.getPushEndpoint());
    				} else {
    					appDevice.setPushEndpoint(null);
    				}
    			}
    		}
    	}
        
        // sanity check
    	// see if we already have an endpoint for this token
        T device = fetchByPushToken(pushToken);
        
        // if pushToken exists and doesn't already belong to us then throw an exception
        if (device != null && (appDevice.getId() == null || !appDevice.getId().equals(device.getId()))) {
        	throw new IllegalStateException("PushToken already assigned to another device: " + device);
        }
        
        if (appDevice.getId() == null) {
        	appDevice = add(appDevice);
        } else {
        	appDevice = update(appDevice);
        }
        
        if (appDevice.getPushEndpoint() == null) {
        	appDevice = updateEndpoint(appDevice, oldPushToken);
        }
        
        
        return appDevice;
    }
    
	// ----------------------------------------------------------------------------
    
    protected T updateEndpoint(T device, String oldPushToken) {
        String pushToken = device.getPushToken();
        
        if (device.getAppId() == null || pushToken == null) {
        	throw new IllegalArgumentException("AppId and PushToken must be set");
        }
        
        N notification = getAppNotificationService().fetchById(device.getAppNotificationId());
        
        String appEndpoint = notification.getPushEndpoint();
        
        if (appEndpoint == null) {
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
        
        endpoint = getPushService().createDeviceEndpoint(pushPlatform, appEndpoint, pushToken, null);
        
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
