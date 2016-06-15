/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KApiLog;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KApiLogService<A extends KApiLog> extends KService, KDataService<A> {
    public static final String SERVICE_PATH = "rpc/kona/ApiLogService";
    
    public A fetchByUid(String uid);
    public List<A> fetchByOwnerId(Long ownerId);
    public List<A> fetchByUserId(Long userId);
    public List<A> fetchByAppId(Long appId);
    public List<A> fetchByClientId(String clientId);
}
