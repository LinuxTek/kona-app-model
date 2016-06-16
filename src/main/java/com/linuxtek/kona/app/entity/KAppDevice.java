package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAppDevice extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppPlatformId();

	void setAppPlatformId(Long appPlatformId);

	Long getAppId();

	void setAppId(Long appId);

	String getDeviceUuid();

	void setDeviceUuid(String deviceUuid);

	Long getUserId();

	void setUserId(Long userId);

	String getPushToken();

	void setPushToken(String pushToken);

	String getPushEndpoint();

	void setPushEndpoint(String pushEndpoint);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}