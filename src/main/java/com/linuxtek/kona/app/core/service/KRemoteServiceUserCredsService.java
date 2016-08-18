/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KRemoteServiceUserCreds;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS extends KRemoteServiceUserCreds> 
        extends KService, KDataService<REMOTE_SERVICE_USER_CREDS> {

    public static final String SERVICE_PATH = "rpc/kona/RemoteServiceUserCredsService";

    public List<REMOTE_SERVICE_USER_CREDS> fetchByAccountId(Long accountId);
    
    
    /**
     * Return the list of credentials that are associated with a remote service and screenName.
     * Generally this should return just one value but it's possible for a user to have multiple accounts
     * in our app and connect the same social media account to each local account.  In this case, the same
     * remote service and screenName will be associated with multiple credential records.
     * 
     * @param remoteServiceId
     * @param screenName
     * @return
     */
    public List<REMOTE_SERVICE_USER_CREDS> fetchByRemoteServiceScreenName(Long remoteServiceId, String screenName);

    public REMOTE_SERVICE_USER_CREDS fetchByAppIdAndAccountIdAndName(Long appId, Long accountId, String name);

    public REMOTE_SERVICE_USER_CREDS create(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds);
}
