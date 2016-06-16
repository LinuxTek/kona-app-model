package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAppNotificationDevice extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppNotificationId();

	void setAppNotificationId(Long appNotificationId);

	Long getAppId();

	void setAppId(Long appId);

	String getDeviceUuid();

	void setDeviceUuid(String deviceUuid);

	String getPlatformName();

	void setPlatformName(String platformName);

	Long getUserId();

	void setUserId(Long userId);

	String getPushToken();

	void setPushToken(String pushToken);

	String getPushEndpoint();

	void setPushEndpoint(String pushEndpoint);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	boolean isSandbox();

	void setSandbox(boolean sandbox);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}
