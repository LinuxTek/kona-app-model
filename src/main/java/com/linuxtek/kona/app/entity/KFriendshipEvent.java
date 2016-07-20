package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KFriendshipEvent extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getTypeId();

	void setTypeId(Long typeId);

	Long getFriendshipId();

	void setFriendshipId(Long friendshipId);

	Long getUserId();

	void setUserId(Long userId);

	Long getFriendId();

	void setFriendId(Long friendId);

	String getEvent();

	void setEvent(String event);

	Date getEventDate();

	void setEventDate(Date eventDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}