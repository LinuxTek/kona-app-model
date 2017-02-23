package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAppLegal extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
    void setUid(String uid);

	Long getAppId();
	void setAppId(Long appId);

	String getType();
	void setType(String env);

	String getContent();
	void setContent(String content);

	Integer getVersion();
	void setVersion(Integer version);

	boolean isActive();
	void setActive(boolean active);

	Date getPublishedDate();
	void setPublishedDate(Date publishedDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
