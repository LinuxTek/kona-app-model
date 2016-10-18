/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * KMediaObject.
 */
    
public interface KMediaObject extends KEntityObject {
	
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
    
    public Long getFileId();
    public void setFileId(Long fileId);
    
	Long getFileTypeId();
	void setFileTypeId(Long fileTypeId);
    
    public String getUrlPath();
    public void setUrlPath(String urlPath);
    
	boolean isEnabled();
	void setEnabled(boolean enabled);
    
    public Double getLatitude();
    public void setLatitude(Double latitude);
    
    public Double getLongitude();
    public void setLongitude(Double longitude);
    
    public Integer getFloor();
    public void setFloor(Integer floor);
    
    public String getDescription();
    public void setDescription(String description);
    
    public Integer getWidth();
    public void setWidth(Integer width);

    public Integer getHeight();
    public void setHeight(Integer height);
    
    public Long getSize();
    public void setSize(Long size);

    public Integer getBitsPerPixel();
    public void setBitsPerPixel(Integer bitPerPixel);

    public Integer getFramesPerSecond();
    public void setFramesPerSecond(Integer framesPerSecond);
    
    public boolean isResizeable();
    public void setResizeable(boolean resizeable);
    
	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getLastUpdated();
	void setLastUpdated(Date lastUpdated);
}