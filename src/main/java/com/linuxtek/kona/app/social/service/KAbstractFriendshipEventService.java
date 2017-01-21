/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.social.entity.KFriendshipEvent;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractFriendshipEventService<FRIENDSHIP_EVENT extends KFriendshipEvent, FRIENDSHIP_EVENT_EXAMPLE>
		extends KAbstractService<FRIENDSHIP_EVENT,FRIENDSHIP_EVENT_EXAMPLE>
		implements KFriendshipEventService<FRIENDSHIP_EVENT> {
			
	private static Logger logger = LoggerFactory.getLogger(KAbstractFriendshipEventService.class);


	// ----------------------------------------------------------------------------

    @Override
	public void validate(FRIENDSHIP_EVENT friendshipEvent) {
    	if (friendshipEvent.getCreatedDate() == null) {
			friendshipEvent.setCreatedDate(new Date());
		}
    	
    	friendshipEvent.setUpdatedDate(new Date());
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	public List<FRIENDSHIP_EVENT> fetchByFriendshipId(Long friendshipId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("friendshipId", friendshipId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}
