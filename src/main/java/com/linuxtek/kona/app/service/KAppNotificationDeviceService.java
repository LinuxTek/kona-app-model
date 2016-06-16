/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KAppNotificationDevice;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppNotificationDeviceService<T extends KAppNotificationDevice> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/AppNotificationDeviceService";

	public T save(T device);

    public T fetchByPushToken(String pushToken);

    // Different users using same app on same device
    public T fetchByAppIdAndUserIdAndDeviceUuid(Long appId, Long userId, String deviceUuid);
	
    public List<T> fetchByAppId(Long appId);

    public List<T> fetchByUserId(Long userId);

	public List<T> fetchByUserIds(List<Long> userIdList, boolean unique, boolean sandbox, Long affinityAppId);
}
