/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserAuth;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@KServiceRelativePath(KUserService.SERVICE_PATH)
public interface KUserService<U extends KUser, A extends KUserAuth> 
        extends KService, KDataService<U> {

    // NOTE: SERVICE_PATH must begin with rpc/ prefix
    public static final String SERVICE_PATH = "rpc/kona/UserService";
    
	public U registerUser(Long appId, Long typeId, Long accountId, Long roles, 
			String uid, String username, String password, String firstName, 
			String lastName, String email, String mobileNumber, 
			String hostname, String browser, String accessToken, Integer signupTime);

    /*
    public <T extends KToken> void updateRegistration(T token, 
            Registration registration);
    */

    public U validateCredentials(String username, String password);

    public U fetchByUsername(String username);

    public U fetchByUid(String uid);
    
    public U fetchByAccessToken(String accessToken);

    public U fetchByEmail(String email);

    public A setPassword(Long userId, String password);
    
    public A setEncryptedPassword(Long userId, String encryptedPassword);

    public String resetPassword(Long userId);

	public U retire(U user);

}
