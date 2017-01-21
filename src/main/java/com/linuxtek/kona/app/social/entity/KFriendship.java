package com.linuxtek.kona.app.social.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KFriendship extends KEntityObject {

	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	Long getFriendId();

	void setFriendId(Long friendId);

	Long getStatusId();

	void setStatusId(Long statusId);

	Long getCircleId();

	void setCircleId(Long circleId);

	/** Flag to indicate if a friend request was made by this user to the friend.  If false, the user simply followed the friend. */
	boolean isFriendshipRequested();

	void setFriendshipRequested(boolean friendshipRequested);
	
	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);



}