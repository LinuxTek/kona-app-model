package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KEntityNameRule extends KEntityObject {

	Long getId();

	void setId(Long id);

	String getPattern();

	void setPattern(String pattern);

	boolean isBlackListed();

	void setBlackListed(boolean blackListed);

	boolean isReserved();

	void setReserved(boolean reserved);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);
}