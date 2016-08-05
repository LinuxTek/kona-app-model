/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KRemoteServiceUserCreds;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS extends KRemoteServiceUserCreds> 
        extends KService, KDataService<REMOTE_SERVICE_USER_CREDS> {

    public static final String SERVICE_PATH = "rpc/kona/RemoteServiceUserCredsService";

    public List<REMOTE_SERVICE_USER_CREDS> fetchByAccountId(Long accountId);

    public REMOTE_SERVICE_USER_CREDS fetchByAppIdAndAccountIdAndName(Long appId, Long accountId, String name);

    public REMOTE_SERVICE_USER_CREDS create(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds);
}
