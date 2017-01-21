package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KPushNotification extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	String getPlatformName();

	void setPlatformName(String platformName);

	String getPushServerKey();

	void setPushServerKey(String pushServerKey);

	String getPushServerSecret();

	void setPushServerSecret(String pushServerSecret);

	String getPushEndpoint();

	void setPushEndpoint(String pushEndpoint);

    boolean isSandbox();

	void setSandbox(boolean sandbox);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}
