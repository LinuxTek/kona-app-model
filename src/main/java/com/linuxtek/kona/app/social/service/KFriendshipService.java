/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.List;

import com.linuxtek.kona.app.social.entity.KFriendship;

import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KFriendshipService<FRIENDSHIP extends KFriendship> extends KService, KDataService<FRIENDSHIP> {
    public static final String SERVICE_PATH = "rpc/kona/FriendshipService";

    public List<FRIENDSHIP> fetchByUserIdAndStatusId(Long userId, Long statusId);
    public List<FRIENDSHIP> fetchByFriendIdAndStatusId(Long friendId, Long statusId);
    
	public List<FRIENDSHIP> fetchFollowerList(Long userId);
	public List<FRIENDSHIP> fetchFollowingList(Long userId);
	public List<FRIENDSHIP> fetchFriendList(Long userId);

	public List<FRIENDSHIP> fetchByCircleId(Long circleId);
    
    public FRIENDSHIP fetchByUserIdAndFriendId(Long userId, Long friendId);
    public FRIENDSHIP fetchByUid(String uid);
    
    public Integer getFollowingCount(Long userId);
    public Integer getFollowerCount(Long userId);
    
	public FRIENDSHIP follow(Long userId, Long friendId, Long circleId);
	public FRIENDSHIP block(Long userId, Long friendId);
	public FRIENDSHIP unfollow(Long userId, Long friendId);
	public FRIENDSHIP unblock(Long userId, Long friendId);
	
	public FRIENDSHIP requestFriendship(Long userId, Long friendId, Long circleId);
	public FRIENDSHIP acceptFriendship(Long userId, Long friendId);
	public FRIENDSHIP rejectFriendship(Long userId, Long friendId);
	public FRIENDSHIP revokeFriendship(Long userId, Long friendId);
	
	// request and accept friendship in single call without notifications
	public FRIENDSHIP createFriendship(Long userId, Long friendId, Long circleId, boolean notifyUser);
    
}
