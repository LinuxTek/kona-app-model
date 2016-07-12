package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KRemoteServiceUserCreds extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	Long getAccountId();

	void setAccountId(Long accountId);

	Long getUserId();

	void setUserId(Long userId);

	Long getRemoteServiceId();

	void setRemoteServiceId(Long remoteServiceId);

	String getName();

	void setName(String name);

	String getRemoteServiceUserId();

	void setRemoteServiceUserId(String remoteServiceUserId);

	String getRemoteServiceScreenName();

	void setRemoteServiceScreenName(String remoteServiceScreenName);

	String getAuthType();

	void setAuthType(String authType);

	String getAccessToken();

	void setAccessToken(String accessToken);

	String getRefreshToken();

	void setRefreshToken(String refreshToken);

	String getTokenSecret();

	void setTokenSecret(String tokenSecret);

	Date getExpireDate();

	void setExpireDate(Date expireDate);

	Date getConnectedDate();

	void setConnectedDate(Date connectedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}