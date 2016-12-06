package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KPushNotificationDevice extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getPushNotificationId();

	void setPushNotificationId(Long pushNotificationId);

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
