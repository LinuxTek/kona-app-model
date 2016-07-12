/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KRemoteServiceAppCreds;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractRemoteServiceAppCredsService<REMOTE_SERVICE_APP_CREDS extends KRemoteServiceAppCreds, 
											   REMOTE_SERVICE_APP_CREDS_EXAMPLE>
		extends KAbstractService<REMOTE_SERVICE_APP_CREDS,REMOTE_SERVICE_APP_CREDS_EXAMPLE>
		implements KRemoteServiceAppCredsService<REMOTE_SERVICE_APP_CREDS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRemoteServiceAppCredsService.class);

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(REMOTE_SERVICE_APP_CREDS remoteServiceAppCreds) {
		if (remoteServiceAppCreds.getCreatedDate() == null) {
			remoteServiceAppCreds.setCreatedDate(new Date());
		}

		remoteServiceAppCreds.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<REMOTE_SERVICE_APP_CREDS> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0,9999, null, filter,  false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public REMOTE_SERVICE_APP_CREDS fetchByAppIdAndRemoteServiceId(Long appId, Long remoteServiceId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("remoteServiceId", remoteServiceId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
	}

}
