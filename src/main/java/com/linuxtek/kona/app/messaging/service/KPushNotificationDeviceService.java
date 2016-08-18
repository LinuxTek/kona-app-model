package com.linuxtek.kona.app.messaging.service;

import java.util.List;

import com.linuxtek.kona.app.messaging.entity.KPushNotificationDevice;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KPushNotificationDeviceService<T extends KPushNotificationDevice> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/PushNotificationDeviceService";

	public T save(T device);

    public T fetchByPushToken(String pushToken);

    // Different users using same app on same device
    public T fetchByAppIdAndUserIdAndDeviceUuid(Long appId, Long userId, String deviceUuid);
	
    public List<T> fetchByAppId(Long appId);

    public List<T> fetchByUserId(Long userId);

	public List<T> fetchByUserIds(List<Long> userIdList, boolean unique, boolean sandbox, Long affinityAppId);
}
