/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KAppPlatform;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppPlatformService<T extends KAppPlatform> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/AppPlatformService";
    
    public T create(T platform);

    public T updatePlatformEndpoint(T platform);

    public T fetchByAppIdAndPlatformName(Long appId, String platformName);

    public List<T> fetchByAppId(Long appId);

    public List<T> fetchByPlatformName(String platformName);
}
