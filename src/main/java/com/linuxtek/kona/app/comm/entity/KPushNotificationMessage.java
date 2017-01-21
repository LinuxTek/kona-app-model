package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KPushNotificationMessage extends KEntityObject {

	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getAppId();

	void setAppId(Long appId);

	boolean isSandbox();

	void setSandbox(boolean sandbox);

	String getTitle();

	void setTitle(String title);

	String getMessage();

	void setMessage(String message);

	String getImageUrl();

	void setImageUrl(String imageUrl);

	String getActionUrl();

	void setActionUrl(String actionUrl);

	String getFilter();

	void setFilter(String filter);

	Integer getDeviceCount();

	void setDeviceCount(Integer deviceCount);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

	byte[] getDevices();

	void setDevices(byte[] devices);

}
