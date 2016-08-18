/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.core.entity.KSetting;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KSettingService<S extends KSetting> extends KService, KDataService<S> {
	public static final String SERVICE_PATH = "rpc/kona/SettingService";
	
	   // creates or updates an setting record
    public S save(S setting);
	public void save(Long userId, Map<String, Object> config, boolean overwriteGlobal);

	public List<S> fetchGlobal();
	public List<S> fetchByUserId(Long userId);

	public S fetchGlobalByName(String name);
	public S fetchByUserIdAndName(Long userId, String name);

    public Map<String,Object> getGlobalSettings();
    public Map<String,Object> getUserSettings(Long userId);
}
