/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.app.core.model.KMedia;
import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * KMediaObject.
 */
    
public interface KMediaObject extends KEntityObject, KMedia {
	
	@Override
    public Long getId();
    public void setId(Long id);
    
    public String getUid();
    public void setUid(String uid);
    
    public Long getParentId();
    public void setParentId(Long parentId);
    
    public Long getThumbnailId();
    public void setThumbnailId(Long thumbnailId);
    
    public Long getUserId();
    public void setUserId(Long userId);
    
    public Long getAccountId();
    public void setAccountId(Long accountId);

    public Long getFileId();
    public void setFileId(Long fileId);
    
	Long getFileTypeId();
	void setFileTypeId(Long fileTypeId);
    
	/**
	 * User defined internal organization of the media objects.
	 * @return
	 */
    public String getFolderPath();
    public void setFolderPath(String folderPath);

    /**
     * User defined name of media object. May or may not be same as file name.
     */
    public String getName();
    public void setName(String name);
    
    public String getDescription();
    public void setDescription(String description);

    public String getUrlPath();
    public void setUrlPath(String urlPath);

    public String getThumbnailUrlPath();
    public void setThumbnailUrlPath(String thumbnailUrlPath);

    public String getContentType();
    public void setContentType(String contentType);
    
	boolean isEnabled();
	void setEnabled(boolean enabled);
    
    public Double getLatitude();
    public void setLatitude(Double latitude);
    
    public Double getLongitude();
    public void setLongitude(Double longitude);
    
    public Integer getFloor();
    public void setFloor(Integer floor);

    
    public Integer getWidth();
    public void setWidth(Integer width);

    public Integer getHeight();
    public void setHeight(Integer height);
    
    public Long getSize();
    public void setSize(Long size);
    
    public Integer getThumbnailWidth();
    public void setThumbnailWidth(Integer thumbnailWidth);

    public Integer getThumbnailHeight();
    public void setThumbnailHeight(Integer thumbnailHeight);

    public Long getThumbnailSize();
    public void setThumbnailSize(Long thumbnailSize);

    public Integer getBitsPerPixel();
    public void setBitsPerPixel(Integer bitPerPixel);

    public Integer getFramesPerSecond();
    public void setFramesPerSecond(Integer framesPerSecond);
    
    public boolean isResizeable();
    public void setResizeable(boolean resizeable);
    
	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
