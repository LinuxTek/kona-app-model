/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

/**
 * KBaseUserMedia.
 */
public class KBaseUserMedia extends KBaseMediaObject implements KUserMedia {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long fileId;
    private Long fileTypeId;
    private boolean enabled;
    private boolean primaryPhoto;
    private Date createdDate;
    private Date lastUpdated;


    @Override
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
	public Long getFileId() {
        return fileId;
    }

    @Override
	public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
	public Long getFileTypeId() {
        return fileTypeId;
    }

    @Override
	public void setFileTypeId(Long fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    @Override
	public boolean isEnabled() {
        return enabled;
    }

    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
	
    @Override
	public boolean isPrimaryPhoto() {
        return primaryPhoto;
    }

    @Override
	public void setPrimaryPhoto(boolean primaryPhoto) {
        this.primaryPhoto = primaryPhoto;
    }

    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
	public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
	public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", uid=").append(getUid());
        sb.append(", parentId=").append(getParentId());
        sb.append(", thumbnailId=").append(getThumbnailId());
        sb.append(", userId=").append(userId);
        sb.append(", fileId=").append(fileId);
        sb.append(", fileTypeId=").append(fileTypeId);
        sb.append(", urlPath=").append(getUrlPath());
        sb.append(", latitude=").append(getLatitude());
        sb.append(", longitude=").append(getLongitude());
        sb.append(", floor=").append(getFloor());
        sb.append(", description=").append(getDescription());
        sb.append(", enabled=").append(enabled);
        sb.append(", width=").append(getWidth());
        sb.append(", height=").append(getHeight());
        sb.append(", bitsPerPixel=").append(getBitsPerPixel());
        sb.append(", framesPerSecond=").append(getFramesPerSecond());
        sb.append(", resizeable=").append(isResizeable());
        sb.append(", primaryPhoto=").append(primaryPhoto);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }


}
