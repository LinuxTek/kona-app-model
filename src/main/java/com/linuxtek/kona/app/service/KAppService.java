/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KApp;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppService<A extends KApp> extends KService, KDataService<A> {
    public static final String SERVICE_PATH = "rpc/kona/AppService";
    
    public A create(A app, String apiVersion, String redirectUri, String scope);
    
    public A create(A app, String apiVersion, String redirectUri, String scope, 
            String clientId, String clientSecret);
    
    public A update(A app, String apiVersion, String redirectUri, String scope);
    
    public A fetchByName(String name);
    
    public A fetchByUid(String uid);
    
    public A retire(A app);
    
    public A getSystemApp();

    public List<A> fetchByUserId(Long userId);

    public boolean isAppNameAvailable(String name);
}
