package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KSetting extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getUserId();

	void setUserId(Long userId);

	String getName();

	void setName(String name);

	String getValue();

	void setValue(String value);

	boolean isOverwriteGlobal();

	void setOverwriteGlobal(boolean overwriteGlobal);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}