/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KRemoteService;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractRemoteServiceService<REMOTE_SERVICE extends KRemoteService, 
											   REMOTE_SERVICE_EXAMPLE>
		extends KAbstractService<REMOTE_SERVICE,REMOTE_SERVICE_EXAMPLE>
		implements KRemoteServiceService<REMOTE_SERVICE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRemoteServiceService.class);

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(REMOTE_SERVICE remoteService) {
		if (remoteService.getCreatedDate() == null) {
			remoteService.setCreatedDate(new Date());
		}

		remoteService.setLastUpdated(new Date());

		if (remoteService.getDisplayName() != null) {
			String name = KInflector.getInstance().slug(remoteService.getDisplayName());
			remoteService.setName(name);
		}

		if (remoteService.getUid() == null) {
			remoteService.setUid(uuid());
		}
	}

	// ----------------------------------------------------------------------------

	@Override
    public REMOTE_SERVICE fetchByName(String name) {
        name = name.trim().toLowerCase();
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }

	// ----------------------------------------------------------------------------
	
}
