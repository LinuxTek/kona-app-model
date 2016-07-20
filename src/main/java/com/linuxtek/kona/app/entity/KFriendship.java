package com.linuxtek.kona.app.entity;

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

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}