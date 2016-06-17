package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAppWebhook extends KEntityObject {

	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getAppId();

	void setAppId(Long appId);

	String getName();

	void setName(String name);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getEvents();

	void setEvents(String events);

	String getUrl();

	void setUrl(String url);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}
