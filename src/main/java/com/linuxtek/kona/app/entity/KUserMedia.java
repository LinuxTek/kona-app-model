package com.linuxtek.kona.app.entity;

import java.util.Date;

public interface KUserMedia extends KMediaObject {

	Long getUserId();
	void setUserId(Long userId);

	Long getFileId();
	void setFileId(Long fileId);

	Long getFileTypeId();
	void setFileTypeId(Long fileTypeId);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	boolean isPrimaryPhoto();
	void setPrimaryPhoto(boolean primaryPhoto);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getLastUpdated();
	void setLastUpdated(Date lastUpdated);

}