package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KSupportMessage extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
    void setUid(String uid);

	Long getAppId();
	void setAppId(Long appId);

	Long getUserId();
	void setUserId(Long userId);

	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);

	String getEmail();
	void setEmail(String email);

	String getMobileNumber();
	void setMobileNumber(String mobileNumber);

	String getMessage();
	void setMessage(String message);

	String getHostname();
	void setHostname(String hostname);

	String getBrowser();
	void setBrowser(String browser);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
