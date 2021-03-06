package com.linuxtek.kona.app.comm.service;

import java.util.List;

import com.linuxtek.kona.app.comm.entity.KPushNotification;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KPushNotificationService<T extends KPushNotification> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/PushNotificationService";
    
    public T save(T notification);

    public T updateEndpoint(T notification);

    public T fetchByAppIdAndPlatformNameAndSandbox(Long appId, String platformName, boolean sandbox);

    public List<T> fetchByAppId(Long appId);

}
