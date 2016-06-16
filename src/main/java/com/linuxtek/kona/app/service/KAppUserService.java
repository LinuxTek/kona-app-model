/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KAppUser;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppUserService<T extends KAppUser> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/AppUserService";
	
	public T create(Long appId, Long userId, Long tokenId, String appUserId);
	
	public T fetchByAppIdAndUserId(Long appId, Long userId);
	
	public List<T> fetchByAppId(Long appId);
	
	public List<T> fetchByUserId(Long userId);
}
