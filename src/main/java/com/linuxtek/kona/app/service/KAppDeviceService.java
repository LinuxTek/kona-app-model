/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KAppDevice;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppDeviceService<T extends KAppDevice> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/AppDeviceService";
    
    public T fetchByAppIdAndDeviceUuid(Long appId, String deviceUuid);
    
    public T fetchByUserIdAndPushToken(Long userId, String pushToken);
    
	public T createOrUpdate(T device);
    
	public T createOrUpdate(Long userId, Long appId, String deviceUuid, String pushToken);
	
	public T updateDeviceEndpoint(T device);
	
	public List<T> fetchByUserIds(List<Long> userIdList, boolean unique, Long affinityAppId);
    
    public List<T> fetchByPushToken(String pushToken);
    
    public List<T> fetchByUserId(Long userId);
    
    public List<T> fetchByAppId(Long appId);
}
