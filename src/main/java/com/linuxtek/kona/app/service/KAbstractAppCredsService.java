/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAppCreds;
import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractAppCredsService<AC extends KAppCreds,ACEXAMPLE,T extends KToken> 
		extends KAbstractService<AC,ACEXAMPLE>
		implements KAppCredsService<AC> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppCredsService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract <S extends KTokenService<T>> S getTokenService();
	
	// ----------------------------------------------------------------------------
	
	@Override
	public List<AC> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public AC fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------
	
	@Override
    public void expireAppTokens(Long appId) {
        List<AC> credsList = fetchByAppId(appId);
        for (AC creds : credsList) {
        	getTokenService().expireByClientId(creds.getClientId());
        }
    }

}
