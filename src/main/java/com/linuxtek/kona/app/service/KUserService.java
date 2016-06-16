/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.data.service.KDataService;

import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;

/**
 * The client side stub for the RPC service.
 */
public interface KUserService<U extends KUser> extends KService, KDataService<U> {
	public static final String SERVICE_PATH = "rpc/kona/UserService";
	
	public U registerUser(U user, String password, KServiceClient client);

    public U fetchByUid(String uid);

    public U fetchByUsername(String username);
    
    public U fetchByEmail(String email);

    public U fetchByAccessToken(String accessToken, boolean validateToken);
    
	public U retire(U user);
	
	public List<U> fetchByAccountId(Long accountId);
	
	public List<U> fetchProximate(Double latitude, Double longitude, Double radius);
	
	public List<U> fetchAllRegistered(Map<String,Object> filter); // exclude system, test, etc users
	
	public boolean isUsernameAvailable(String name);
}
