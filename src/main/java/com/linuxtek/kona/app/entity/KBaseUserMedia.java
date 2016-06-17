/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

/**
 * KBaseUserMedia.
 */
public class KBaseUserMedia implements KUserMedia {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long fileId;
    private Long fileTypeId;
    private String urlPath;
    private Double latitude;
    private Double longitude;
    private Integer floor;
    private String description;
    private boolean enabled;
    private boolean primaryPhoto;
    private Date createdDate;
    private Date lastUpdated;


    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public String getUid() {
        return uid;
    }

    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

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
	public String getUrlPath() {
        return urlPath;
    }

    @Override
	public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
    }

    @Override
	public Double getLatitude() {
        return latitude;
    }

    @Override
	public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
	public Double getLongitude() {
        return longitude;
    }

    @Override
	public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
	public Integer getFloor() {
        return floor;
    }

    @Override
	public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Override
	public String getDescription() {
        return description;
    }

    @Override
	public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", userId=").append(userId);
        sb.append(", fileId=").append(fileId);
        sb.append(", fileTypeId=").append(fileTypeId);
        sb.append(", urlPath=").append(urlPath);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", floor=").append(floor);
        sb.append(", description=").append(description);
        sb.append(", enabled=").append(enabled);
        sb.append(", primaryPhoto=").append(primaryPhoto);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
