package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAppUser extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getUserId();

	void setUserId(Long userId);

	Long getAppId();

	void setAppId(Long appId);

	Long getTokenId();

	void setTokenId(Long tokenId);

	String getAppUserId();

	void setAppUserId(String appUserId);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getRevokedDate();

	void setRevokedDate(Date revokedDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}