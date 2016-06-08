/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KFile extends KEntityObject {
	public static final Integer MAX_DATA_SIZE = 500*1024*1024; // 500MB

    public Long getId();
    public void setId(Long id);

    public Long getTypeId();
    public void setTypeId(Long typeId);

    public Long getAccessId();
    public void setAccessId(Long accessId); // for ex. user, app, public

    public Long getParentId(); //for multi-part docs such as email w/attachments
    public void setParentId(Long parentId);

    public Long getUserId();
    public void setUserId(Long userId);

    public Long getTokenId();
    public void setTokenId(Long tokenId);

    public Long getThumbId();
    public void setThumbId(Long thumbId);

	public String getName();
	public void setName(String name);

	public String getContentType();
	public void setContentType(String contentType);

	public Long getSize();
	public void setSize(Long bytes);

	public byte[] getData();
	public void setData(byte[] data);

    public boolean isFolder();
    public void setFolder(boolean folder);

    public boolean isShared();
    public void setShared(boolean shared);

    public boolean isArchive();
    public void setArchive(boolean archive);

    public boolean isCompressed();
    public void setCompressed(boolean compressed);
    
    public boolean isResizeable();
    public void setResizeable(boolean resizeable);

    /**
     * Flag to indicate if this file should be hidden from the user's
     * normal view.  The flag is controlled by the user.
     */
    public boolean isHidden();
    public void setHidden(boolean hidden);

    /*
     * Flag to ensure that the file is never presented to anyone 
     * other than the user.
     */
    //public boolean isPrivate();
    //public void setPrivate(boolean private);

    /**
     * Flag to indicate if this file is currently enabled.
     * This flag is set by the system to control the user's  access
     * to the file.  For example, this flag may be set to false
     * if the file is pending some type of review.
     */
    public boolean isEnabled();
    public void setEnabled(boolean enabled);

    /**
     * Flag to indicate if this file is currently active.
     * An active file is one which is not retired (deleted).
     * If set to true, getRetiredDate() should be set to null, otherwise
     * it should return the date the file was retired.
     */
    public boolean isActive();
    public void setActive(boolean active);

    public Integer getWidth();
    public void setWidth(Integer width);

    public Integer getHeight();
    public void setHeight(Integer width);

    public Integer getBitsPerPixel();
    public void setBitsPerPixel(Integer bitPerPixel);

    public Integer getFramesPerSecond();
    public void setFramesPerSecond(Integer framesPerSecond);

	public String getSrcHostname();
	public void setSrcHostname(String srcHostName);

	public String getSrcFilename();
	public void setSrcFilename(String srcFilename);

    /**
     * Absolute filesystem path of where the file is stored locally.
     */
    public String getLocalPath();
    public void setLocalPath(String localPath);

    /**
     * URL path of the file relative to some absolute URL.
     * This value is stored in the database.
     */
    public String getUrlPath();
    public void setUrlPath(String publicPath);

    /**
     * Absolute URL for this file.
     * This value is generated at runtime based on config parameters.
     */
    public String getAbsoluteUrl();
    public void setAbsoluteUrl(String absoluteUrl);

    /**
     * Relative URL path for the thumbnail.
     * This value is stored in the database.
     */
    public String getThumbUrlPath();
    public void setThumbUrlPath(String thumbUrlPath);

    /**
     * Absolute URL for the thumbnail.
     * This value is generated at runtime based on config parameters.
     */
    public String getThumbAbsoluteUrl();
    public void setThumbAbsoluteUrl(String thumbAbsoluteUrl);

    public Long getUploadTime();
    public void setUploadTime(Long uploadTime);

	public Date getCreatedDate();
	public void setCreatedDate(Date createdDate);

	public Date getRetiredDate();
	public void setRetiredDate(Date retiredDate);

    public Date getLastUpdated();
    public void setLastUpdated(Date lastUpdated);
}
