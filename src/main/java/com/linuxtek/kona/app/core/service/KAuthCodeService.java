/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KAuthCode;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAuthCodeService<T extends KAuthCode> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/AuthCodeService";
    
	public T fetchByCode(String code);
	
	// indicate code has been accessed
	public T accessCode(String code);
    
    //public void requestPasswordReset(Long userId, Long appId, boolean resend);
    
	public void requestAuthCode(Long typeId, Long appId, Long userId, boolean resend);
	
	public void requestAuthCodes(Long[] typeId, Long appId, Long userId, boolean resend);

	public List<T> expireByUserId(Long userId);
    
}
