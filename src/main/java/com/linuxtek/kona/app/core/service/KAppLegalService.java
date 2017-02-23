/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KAppLegal;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppLegalService<T extends KAppLegal> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/AppLegalService";
    
    public T fetchByUid(String uid);

    public List<T> fetchByAppId(Long appId);

    public List<T> fetchByAppIdAndType(Long appId, String type);
    
    public T fetchByAppIdAndTypeAndVersion(Long appId, String type, Integer version);

    public T fetchActive(Long appId, String type);
}
