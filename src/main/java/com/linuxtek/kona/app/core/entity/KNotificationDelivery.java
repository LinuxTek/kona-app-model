package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KNotificationDelivery extends KEntityObject {

    @Override
	Long getId();

	void setId(Long id);

	Long getNotificationId();

	void setNotificationId(Long notificationId);

	Long getChannelId();

	void setChannelId(Long channelId);

	String getCode();

	void setCode(String code);

	Date getDeliveredDate();

	void setDeliveredDate(Date deliveredDate);

	Date getViewedDate();

	void setViewedDate(Date viewedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

	String toString();

}