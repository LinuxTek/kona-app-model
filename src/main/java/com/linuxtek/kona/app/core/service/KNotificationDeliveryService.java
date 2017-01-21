package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KNotificationDelivery;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KNotificationDeliveryService<NotificationDelivery extends KNotificationDelivery> 
		extends KService, KDataService<NotificationDelivery> {
    
    public static final String SERVICE_PATH = "rpc/kona/NotificationDeliveryService";

    public List<NotificationDelivery> fetchByNotificationId(Long notificationId);
    
    public NotificationDelivery fetchByCode(String code);
}
