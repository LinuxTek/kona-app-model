package com.linuxtek.kona.app.messaging.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KEmailEvent extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	Long getTypeId();

	void setTypeId(Long typeId);

	Long getEmailId();

	void setEmailId(Long emailId);

	String getTarget();

	void setTarget(String target);

	String getError();

	void setError(String error);

	String getHostname();

	void setHostname(String hostname);

	String getBrowser();

	void setBrowser(String browser);

	Date getEventDate();

	void setEventDate(Date eventDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}
