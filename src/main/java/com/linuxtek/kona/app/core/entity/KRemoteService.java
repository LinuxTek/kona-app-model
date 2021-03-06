package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KRemoteService extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	String getName();

	void setName(String name);

	String getDisplayName();

	void setDisplayName(String displayName);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}