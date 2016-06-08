/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KAccount;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@KServiceRelativePath(KAccountService.SERVICE_PATH)
public interface KAccountService<A extends KAccount> 
        extends KService, KDataService<A> {

    // NOTE: SERVICE_PATH must begin with rpc/ prefix
    public static final String SERVICE_PATH = "rpc/kona/AccountService";
    
    public A fetchByUid(String uid);

	public A retire(A account);
}
