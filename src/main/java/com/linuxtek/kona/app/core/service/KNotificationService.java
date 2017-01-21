package com.linuxtek.kona.app.core.service;


import java.util.Date;
import java.util.List;

import com.linuxtek.kona.app.core.entity.KNotification;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KNotificationService<Notification extends KNotification> extends KService, KDataService<Notification> {
    public static final String SERVICE_PATH = "rpc/kona/NotificationService";

    public Notification fetchByUid(String uid);

	public List<Notification> fetchByUserIdSinceUid(Long userId, String uid, Integer limit);

	public void notifyEvent(Long userId, String event, Date eventDate);
}
