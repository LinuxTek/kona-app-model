/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KAppNotification;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppNotificationService<T extends KAppNotification> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/AppNotificationService";
    
    public T save(T notification);

    public T updateEndpoint(T notification);

    public T fetchByAppIdAndPlatformNameAndSandbox(Long appId, String platformName, boolean sandbox);

    public List<T> fetchByAppId(Long appId);

}
