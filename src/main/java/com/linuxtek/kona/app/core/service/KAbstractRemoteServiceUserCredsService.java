/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KRemoteServiceUserCreds;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS extends KRemoteServiceUserCreds, 
											   REMOTE_SERVICE_USER_CREDS_EXAMPLE>
		extends KAbstractService<REMOTE_SERVICE_USER_CREDS,REMOTE_SERVICE_USER_CREDS_EXAMPLE>
		implements KRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRemoteServiceUserCredsService.class);

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds) {
		if (remoteServiceUserCreds.getCreatedDate() == null) {
			remoteServiceUserCreds.setCreatedDate(new Date());
		}

		remoteServiceUserCreds.setUpdatedDate(new Date());
	}

	// ----------------------------------------------------------------------------

    @Override
    public REMOTE_SERVICE_USER_CREDS fetchByAppIdAndAccountIdAndName(Long appId, Long accountId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("accountId", accountId);
        filter.put("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public List<REMOTE_SERVICE_USER_CREDS> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0,9999, null, filter,  false);
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public REMOTE_SERVICE_USER_CREDS create(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds) {
        remoteServiceUserCreds = add(remoteServiceUserCreds);
        return remoteServiceUserCreds;
    }
    
	// ----------------------------------------------------------------------------

    
 
    @Override
    public List<REMOTE_SERVICE_USER_CREDS> fetchByRemoteServiceScreenName(Long remoteServiceId, String screenName) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("remoteServiceId", remoteServiceId);
        filter.put("remoteServiceScreenName", screenName);
        return fetchByCriteria(0,9999, null, filter,  false);
    }
}
