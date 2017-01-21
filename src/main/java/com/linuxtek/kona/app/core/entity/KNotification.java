package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KNotification extends KEntityObject {

    @Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	String getEvent();

	void setEvent(String event);

	Date getLastViewedDate();

	void setLastViewedDate(Date lastViewedDate);

	Date getEventDate();

	void setEventDate(Date eventDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

	String toString();

}