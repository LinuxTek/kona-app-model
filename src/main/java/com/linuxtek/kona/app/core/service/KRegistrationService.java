/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import com.linuxtek.kona.app.core.entity.KRegistration;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;


/**
 * The client side stub for the RPC service.
 */
public interface KRegistrationService<R extends KRegistration, U extends KUser> 
		extends KService, KDataService<R> {
    
    public static final String SERVICE_PATH = "rpc/kona/RegistrationService";
    
	public R fetchByUserId(Long userId);
    
	public R createRegistration(U user, KServiceClient client, Integer signupTime);
}
