package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KRemoteServiceAppCreds extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	Long getRemoteServiceId();

	void setRemoteServiceId(Long remoteServiceId);

	String getAuthorizeUri();

	void setAuthorizeUri(String authorizeUri);

	String getTokenUri();

	void setTokenUri(String tokenUri);

	String getScope();

	void setScope(String scope);

	String getClientKey();

	void setClientKey(String clientKey);

	String getClientSecret();

	void setClientSecret(String clientSecret);

	String getRedirectUri();

	void setRedirectUri(String redirectUri);

	String getNamespace();

	void setNamespace(String namespace);

	String getRegion();

	void setRegion(String region);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}