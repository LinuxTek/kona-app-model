/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KAccount;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAccountService<A extends KAccount> extends KService, KDataService<A> {
    public static final String SERVICE_PATH = "rpc/kona/AccountService";
    
    public A fetchByUid(String uid);
    
	public A fetchByName(String name);
    
	public A fetchByUserId(Long userId);
	
	public A retire(A account);
    
	public A createAccount(String displayName);
    
	public boolean isAccountNameAvailable(String name);
}
