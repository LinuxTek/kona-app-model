/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

@SuppressWarnings("serial")
public class KBaseFile implements KFile {
	private Long id;
	private Long parentId;
	private Long typeId;
	private Long accessId;
	private Long userId;
	private Long tokenId;
	private Long thumbId;
	private String name;
	private String contentType;
	private Long size;
	private byte[] data;
    private boolean folder;
    private boolean shared;
    private boolean archive;
    private boolean compressed;
    private boolean hidden;
    //private boolean _private;
    private boolean enabled;
    private boolean active;
    private boolean resizeable;
	

	private Integer width;
	private Integer height;
	private Integer bitsPerPixel;
	private Integer framesPerSecond;
	private String srcHostname;
	private String srcFilename;
    private String localPath;
    private String urlPath;
    private String absoluteUrl;
    private String thumbUrlPath;
    private String thumbAbsoluteUrl;
    private Long uploadTime;
	private Date createdDate;
	private Date retiredDate;
	private Date lastUpdated;

    // --------------------------------------------------
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // --------------------------------------------------

    public Long getParentId() {
        return (parentId);
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    // --------------------------------------------------

    public Long getTypeId() {
        return (typeId);
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    // --------------------------------------------------
    
    public Long getAccessId() {
        return (accessId);
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    // --------------------------------------------------

    public Long getUserId() {
        return (userId);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // --------------------------------------------------

    public Long getTokenId() {
        return (tokenId);
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    // --------------------------------------------------

    public Long getThumbId() {
        return (thumbId);
    }

    public void setThumbId(Long thumbId) {
        this.thumbId = thumbId;
    }


    // --------------------------------------------------

    public String getName() {
        return (name);
    }

    public void setName(String name) {
        this.name = name;
    }

    // --------------------------------------------------

    public String getContentType() {
        return (contentType);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    // --------------------------------------------------

    public Long getSize() {
        return (size);
    }

    public void setSize(Long size) {
        this.size = size;
    }

    // --------------------------------------------------

    public byte[] getData() {
        return (data);
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    // --------------------------------------------------

    public boolean isFolder() {
        return (folder);
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    // --------------------------------------------------

    public boolean isShared() {
        return (shared);
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    // --------------------------------------------------

    public boolean isArchive() {
        return (archive);
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    // --------------------------------------------------

    public boolean isCompressed() {
        return (compressed);
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    // --------------------------------------------------

    public boolean isHidden() {
        return (hidden);
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // --------------------------------------------------

    /*
    public boolean isPrivate() {
        return (_private);
    }

    public void setPrivate(boolean _private) {
        this._private = _private;
    }
    */

    // --------------------------------------------------

    public boolean isEnabled() {
        return (enabled);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // --------------------------------------------------

    public boolean isActive() {
        return (active);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    // --------------------------------------------------
    public boolean isResizeable() {
		return resizeable;
	}

	public void setResizeable(boolean resizeable) {
		this.resizeable = resizeable;
	}
    
    // --------------------------------------------------

    public Integer getWidth() {
        return (width);
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    // --------------------------------------------------

    public Integer getHeight() {
        return (height);
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    // --------------------------------------------------

    public Integer getBitsPerPixel() {
        return (bitsPerPixel);
    }

    public void setBitsPerPixel(Integer bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    // --------------------------------------------------

    public Integer getFramesPerSecond() {
        return (framesPerSecond);
    }

    public void setFramesPerSecond(Integer framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    // --------------------------------------------------

    public String getSrcHostname() {
        return (srcHostname);
    }

    public void setSrcHostname(String srcHostname) {
        this.srcHostname = srcHostname;
    }

    // --------------------------------------------------

    public String getSrcFilename() {
        return (srcFilename);
    }

    public void setSrcFilename(String srcFilename) {
        this.srcFilename = srcFilename;
    }

    // --------------------------------------------------

    public String getLocalPath() {
        return (localPath);
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    // --------------------------------------------------

    public String getUrlPath() {
        return (urlPath);
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    // --------------------------------------------------

    public String getAbsoluteUrl() {
        return (absoluteUrl);
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        this.absoluteUrl = absoluteUrl;
    }

    // --------------------------------------------------

    public String getThumbUrlPath() {
        return (thumbUrlPath);
    }

    public void setThumbUrlPath(String thumbUrlPath) {
        this.thumbUrlPath = thumbUrlPath;
    }

    // --------------------------------------------------

    public String getThumbAbsoluteUrl() {
        return (thumbAbsoluteUrl);
    }

    public void setThumbAbsoluteUrl(String thumbAbsoluteUrl) {
        this.thumbAbsoluteUrl = thumbAbsoluteUrl;
    }

    // --------------------------------------------------

    public Long getUploadTime() {
        return (uploadTime);
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    // --------------------------------------------------

    public Date getCreatedDate() {
        return (createdDate);
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    // --------------------------------------------------

    public Date getRetiredDate() {
        return (retiredDate);
    }

    public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
    }

    // --------------------------------------------------

    public Date getLastUpdated() {
        return (lastUpdated);
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
