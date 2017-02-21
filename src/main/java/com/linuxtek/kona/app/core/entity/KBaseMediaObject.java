/*
 * Copyright (C) 2013 LinuxTek, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBaseMediaObject.
 */
public class KBaseMediaObject implements KMediaObject {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long accountId;
    private Long parentId;
    private Long thumbnailId;
    private Long fileId;
    private Long fileTypeId;

    private String folderPath;
    private String name;
    private String description;

    private String urlPath;
    private String thumbnailUrlPath;
    private String contentType;


    private Double latitude;
    private Double longitude;
    private Integer floor;
    private boolean enabled;
    private Integer framesPerSecond;
    private boolean resizeable;
    private Integer bitsPerPixel;

    private Integer width;
    private Integer height;
    private Long size;

    private Integer thumbnailWidth;
    private Integer thumbnailHeight;
    private Long thumbnailSize;

    private Date updatedDate;

    private Date createdDate;


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
    public Long getAccountId() {
        return (accountId);
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
    public Long getThumbnailId() {
        return thumbnailId;
    }

    @Override
    public void setThumbnailId(Long thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    @Override
    public String getFolderPath() {
        return folderPath;
    }

    @Override
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath == null ? null : folderPath.trim();
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
    public String getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
    public Integer getWidth() {
        return width;
    }

    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public Integer getBitsPerPixel() {
        return bitsPerPixel;
    }

    @Override
    public void setBitsPerPixel(Integer bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    @Override
    public Integer getFramesPerSecond() {
        return framesPerSecond;
    }

    @Override
    public void setFramesPerSecond(Integer framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    @Override
    public boolean isResizeable() {
        return resizeable;
    }

    @Override
    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
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
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }


    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String getThumbnailUrlPath() {
        return thumbnailUrlPath;
    }

    @Override
    public void setThumbnailUrlPath(String thumbnailUrlPath) {
        this.thumbnailUrlPath = thumbnailUrlPath;
    }

    @Override
    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    @Override
    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    @Override
    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    @Override
    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    @Override
    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    @Override
    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", parentId=").append(parentId);
        sb.append(", thumbnailId=").append(thumbnailId);
        sb.append(", urlPath=").append(urlPath);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", floor=").append(floor);
        sb.append(", description=").append(description);
        sb.append(", enabled=").append(enabled);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", bitsPerPixel=").append(bitsPerPixel);
        sb.append(", framesPerSecond=").append(framesPerSecond);
        sb.append(", resizeable=").append(resizeable);
        sb.append("]");
        return sb.toString();
    }



}
