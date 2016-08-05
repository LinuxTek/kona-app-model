/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KRemoteServiceAppCreds;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KRemoteServiceAppCredsService<REMOTE_SERVICE_APP_CREDS extends KRemoteServiceAppCreds> 
        extends KService, KDataService<REMOTE_SERVICE_APP_CREDS> {

    public static final String SERVICE_PATH = "rpc/kona/RemoteServiceAppCredsService";

    public List<REMOTE_SERVICE_APP_CREDS> fetchByAppId(Long appId);
    
    public REMOTE_SERVICE_APP_CREDS fetchByAppIdAndRemoteServiceId(Long appId, Long remoteServiceId);

}
