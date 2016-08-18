/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAppWebhook;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractAppWebhookService<A extends KAppWebhook,EXAMPLE> 
extends KAbstractService<A,EXAMPLE>
implements KAppWebhookService<A> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppWebhookService.class);

	// ----------------------------------------------------------------------------

	protected String generateUid() {
		return KUtil.uuid();
	}

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(A appWebhook) {
		if (appWebhook.getCreatedDate() == null) {
			appWebhook.setCreatedDate(new Date());
		}

		if (appWebhook.getUid() == null) {
			appWebhook.setUid(generateUid());
		}

		if (appWebhook.getDisplayName() == null) {
			appWebhook.setDisplayName(appWebhook.getUid());
		}
		
		appWebhook.setLastUpdated(new Date());

		String name = KInflector.getInstance().slug(appWebhook.getDisplayName());
		appWebhook.setName(name);
	}

	// ----------------------------------------------------------------------------
    
	@Override
	public A fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public A fetchByAppIdAndName(Long appId, String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

}
