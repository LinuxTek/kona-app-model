package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KFriendshipCircle extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	String getName();

	void setName(String name);

	String getDisplayName();

	void setDisplayName(String displayName);

	boolean isDefaultCircle();

	void setDefaultCircle(boolean defaultCircle);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}