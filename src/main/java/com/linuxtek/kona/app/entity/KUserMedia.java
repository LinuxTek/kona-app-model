package com.linuxtek.kona.app.entity;

import java.util.Date;

public interface KUserMedia extends KMediaObject {

	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	Long getFileId();

	void setFileId(Long fileId);

	Long getFileTypeId();

	void setFileTypeId(Long fileTypeId);

	String getUrlPath();

	void setUrlPath(String urlPath);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

	Integer getFloor();

	void setFloor(Integer floor);

	String getDescription();

	void setDescription(String description);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	boolean isPrimaryPhoto();

	void setPrimaryPhoto(boolean primaryPhoto);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}