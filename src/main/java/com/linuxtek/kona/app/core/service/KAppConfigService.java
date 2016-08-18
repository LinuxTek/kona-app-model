/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.core.entity.KAppConfig;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppConfigService<T extends KAppConfig> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/AppConfigService";
    
    public List<T> fetchByAppId(Long appId);
    public List<T> fetchByAppIdAndEnv(Long appId, String env);
    
    public List<T> fetchByAppIdAndName(Long appId, String name);
    
    // set env to null to fetch global config values
    public T fetchByAppIdAndEnvAndName(Long appId, String env, String name);
    
	public Map<String,Object> getConfig(Long appId, String env);

	
}
