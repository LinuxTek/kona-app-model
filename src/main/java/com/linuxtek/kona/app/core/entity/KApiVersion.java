package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KApiVersion extends KEntityObject {

	Long getId();

	void setId(Long id);

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	Date getPublishedDate();

	void setPublishedDate(Date publishedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}