/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAppUser;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractAppUserService<T extends KAppUser,EXAMPLE> 
extends KAbstractService<T,EXAMPLE>
implements KAppUserService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppUserService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract T getNewObject();
	
	// ----------------------------------------------------------------------------

	@Override
	public void validate(T appUser) {
		if (appUser.getCreatedDate() == null) {
			appUser.setCreatedDate(new Date());
		}

		appUser.setUpdatedDate(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByAppIdAndUserId(Long appId, Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("userId", userId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public T create(Long appId, Long userId, Long tokenId, String appUserId) {
		T appUser = getNewObject();
		appUser.setAppId(appId);
		appUser.setUserId(userId);
		appUser.setTokenId(tokenId);
		appUser.setAppUserId(appUserId);
		appUser.setCreatedDate(new Date());
		return add(appUser);
	}
}

