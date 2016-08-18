/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.List;

import com.linuxtek.kona.app.social.entity.KFriendshipEvent;

import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KFriendshipEventService<FRIENDSHIP_EVENT extends KFriendshipEvent>
        extends KService, KDataService<FRIENDSHIP_EVENT> {
    public static final String SERVICE_PATH = "rpc/kona/FriendshipEventService";
    
    public List<FRIENDSHIP_EVENT> fetchByFriendshipId(Long friendshipId);
}
