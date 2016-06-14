/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAppCreds extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	Long getApiVersionId();

	void setApiVersionId(Long apiVersionId);

	String getName();

	void setName(String name);

	String getClientId();

	void setClientId(String clientId);

	String getClientSecret();

	void setClientSecret(String clientSecret);

	String getRedirectUri();

	void setRedirectUri(String redirectUri);

	String getScope();

	void setScope(String scope);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	Integer getAccessTokenTimeout();

	void setAccessTokenTimeout(Integer accessTokenTimeout);

	Integer getRefreshTokenTimeout();

	void setRefreshTokenTimeout(Integer refreshTokenTimeout);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getRetiredDate();

	void setRetiredDate(Date retiredDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}