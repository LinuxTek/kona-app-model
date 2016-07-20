/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KFriendshipCircle;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractFriendshipCircleService<CIRCLE extends KFriendshipCircle, CIRCLE_EXAMPLE> 
		extends KAbstractService<CIRCLE,CIRCLE_EXAMPLE>
		implements KFriendshipCircleService<CIRCLE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractFriendshipCircleService.class);

	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(CIRCLE circle) {
		if (circle.getCreatedDate() == null) {
			circle.setCreatedDate(new Date());
		}

		circle.setLastUpdated(new Date());
		
		if (circle.getUid() == null) {
			circle.setUid(uuid());
		}
		
		String name = KInflector.getInstance().slug(circle.getDisplayName());
		circle.setName(name);
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<CIRCLE> fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public CIRCLE fetchDefaultByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("defaultCircle", true); 
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
}
