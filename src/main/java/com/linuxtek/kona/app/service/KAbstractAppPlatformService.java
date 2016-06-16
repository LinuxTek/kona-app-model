/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.entity.KApp;
import com.linuxtek.kona.app.entity.KAppPlatform;
import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

import io.blync.core.entity.App;
import io.blync.core.entity.AppPlatform;
import io.blync.core.entity.Platform;
import io.blync.messenger.service.PushService;


public abstract class KAbstractAppPlatformService<T extends KAppPlatform,EXAMPLE,A extends KApp> 
extends KAbstractService<T,EXAMPLE>
implements KAppPlatformService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppPlatformService.class);

	// ----------------------------------------------------------------------------

	protected abstract T getNewAppPlatformObject();

	protected abstract <S extends KAppService<A>> S getAppService();
    
	protected abstract <S extends KPushService> S getPushService();

	protected abstract List<T> fetchByUserIds(List<Long> userIdList);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T appPlatform) {
		if (appPlatform.getCreatedDate() == null) {
			appPlatform.setCreatedDate(new Date());
		}

		appPlatform.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByPlatformName(String platformName) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("platformName", platformName);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByAppIdAndPlatformName(Long appId, String platformName) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("platformName", platformName);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override @Transactional
	public T create(T platform) {
		platform = add(platform);
		return updatePlatformEndpoint(platform);
	}

	// ----------------------------------------------------------------------------

	@Override @Transactional
	public T updatePlatformEndpoint(T platform) {
		if (platform.getPushServerSecret() == null) {
			throw new IllegalArgumentException("PushServerKey must be set");
		}


		KPushService.Platform pushPlatform = getPushPlatform(platform.getPlatformName());
		if (pushPlatform == null) {
			throw new IllegalArgumentException("Platform is not supported for push notification: " 
					+ platform.getPlatformName());
		}


		// ok, everything looks good so far.  if we have an existing endpoint, delete it first
		if (platform.getPushEndpoint() != null) {
			getPushService().deleteApplicationEndpoint(platform.getPushEndpoint());
		}

		App app = appService.fetchById(platform.getAppId());

		String principal = platform.getPushServerKey();
		String credential = platform.getPushServerSecret();

		String platformEndpoint = getPushService().createApplicationEndpoint(pushPlatform, app.getName(), principal, credential);

		if (platformEndpoint == null) {
			throw new IllegalStateException("Unable to create platformEndpoint for appPlatformId: " + platform.getId());
		}

		platform.setPushEndpoint(platformEndpoint);

		return update(platform);
	}
    
	// ----------------------------------------------------------------------------
	
	// support ios and android only for now
	protected KPushService.Platform getPushPlatform(String platformName, boolean sandbox) {

		if (platformName == null) return null;
		if (sandbox) return KPushService.Platform.APNS_SANDBOX;

		KPushService.Platform pp = null;

		switch (platformName) {
		case "ios":
			pp = KPushService.Platform.APNS;
			break;

		case "android":
			pp = KPushService.Platform.GCM;
			break;

		default:
			logger.error("Unsupported platform: " + platformName);
			pp = null;
			break;
		}

		return pp;
	}

}
