/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAppConfig;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractAppConfigService<T extends KAppConfig,EXAMPLE> 
extends KAbstractService<T,EXAMPLE>
implements KAppConfigService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppConfigService.class);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T appConfig) {
		if (appConfig.getCreatedDate() == null) {
			appConfig.setCreatedDate(new Date());
		}

		appConfig.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByAppIdAndEnvAndName(Long appId, String env, String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("env", env);
		filter.put("name", name);
		if (env == null) {
			return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
		}

		List<T> result = fetchByCriteria(0, 99999, null, filter, false);
		if (result == null || result.size() == 0) {
			return fetchByAppIdAndEnvAndName(appId, null, name);
		} else {
			return KMyBatisUtil.fetchOne(result);
		}
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppIdAndName(Long appId, String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("name", name);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppIdAndEnv(Long appId, String env) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("env", env);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public Map<String,Object> getConfig(Long appId, String env) {
		Map<String,Object> map = new HashMap<String,Object>();

		// first get all global vars
		List<T> appConfigList = fetchByAppIdAndEnv(appId, (String) null);

		for (T appConfig : appConfigList) {
			map.put(appConfig.getName(), appConfig.getValue());
		}

		// next get environment specific params
		if (env != null) {
			appConfigList = fetchByAppIdAndEnv(appId, env);
			for (T appConfig : appConfigList) {
				map.put(appConfig.getName(), appConfig.getValue());
			}
		}

		return map;
	}
}
