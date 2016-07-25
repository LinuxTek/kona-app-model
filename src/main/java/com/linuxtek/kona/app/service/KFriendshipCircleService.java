/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KFriendshipCircle;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KFriendshipCircleService<CIRCLE extends KFriendshipCircle> extends KService, KDataService<CIRCLE> {
	public static final String SERVICE_PATH = "rpc/kona/FriendshipCircleService";
	
    public CIRCLE fetchByUid(String uid);
	
    public List<CIRCLE> fetchByUserId(Long userId);

    public CIRCLE fetchDefaultByUserId(Long userId);
}
