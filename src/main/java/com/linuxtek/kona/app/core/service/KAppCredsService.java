/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KAppCreds;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppCredsService<AC extends KAppCreds> extends KService, KDataService<AC> {
    public static final String SERVICE_PATH = "rpc/kona/AppCredsService";
    
    public List<AC> fetchByAppId(Long appId);
    
    public AC fetchByClientId(String clientId);
    
    public void expireAppTokens(Long appId);
}
