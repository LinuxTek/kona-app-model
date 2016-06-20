/*
 * Copyright (C) 2013 LinuxTek, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

/**
 * KBaseMediaObject.
 */
public class KBaseMediaObject implements KMediaObject {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String uid;
	private Long parentId;
	private Long thumbnailId;
	private String urlPath;
	private Double latitude;
	private Double longitude;
	private Integer floor;
	private String description;
	private boolean enabled;
	private Integer framesPerSecond;
	private boolean resizeable;
	private Integer bitsPerPixel;
	private Integer height;
	private Integer width;


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
	public Long getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
