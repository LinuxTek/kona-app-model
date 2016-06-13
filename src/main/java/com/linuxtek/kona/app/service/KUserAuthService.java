/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserAuth;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KUserAuthService<U extends KUser, UA extends KUserAuth> extends KService, KDataService<UA> {
	public static final String SERVICE_PATH = "rpc/kona/UserAuthService";
	
    public UA fetchByUserId(Long userId);

    public UA setPlainPassword(Long userId, String password);
    
    public UA setEncryptedPassword(Long userId, String encryptedPassword);

    public UA resetPassword(Long userId);
    
    public U validateCredentials(String username, String password);
}
