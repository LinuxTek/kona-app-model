package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

public class KBasePushNotificationMessage implements KPushNotificationMessage {
	private static final long serialVersionUID = 1L;
	
    private Long id;
    private String uid;
    private Long appId;
    private boolean sandbox;
    private String title;
    private String message;
    private String imageUrl;
    private String actionUrl;
    private String filter;
    private byte[] devices;
    private Integer deviceCount;
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
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
	public boolean isSandbox() {
        return sandbox;
    }

    @Override
	public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    @Override
	public String getTitle() {
        return title;
    }

    @Override
	public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Override
	public String getMessage() {
        return message;
    }

    @Override
	public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    @Override
	public String getImageUrl() {
        return imageUrl;
    }

    @Override
	public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    @Override
	public String getActionUrl() {
        return actionUrl;
    }

    @Override
	public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }

    @Override
	public String getFilter() {
        return filter;
    }

    @Override
	public void setFilter(String filter) {
        this.filter = filter == null ? null : filter.trim();
    }

    @Override
	public Integer getDeviceCount() {
        return deviceCount;
    }

    @Override
	public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
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
	public byte[] getDevices() {
        return devices;
    }

    @Override
	public void setDevices(byte[] devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", appId=").append(appId);
        sb.append(", sandbox=").append(sandbox);
        sb.append(", title=").append(title);
        sb.append(", message=").append(message);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", actionUrl=").append(actionUrl);
        sb.append(", filter=").append(filter);
        sb.append(", deviceCount=").append(deviceCount);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append(", devices=").append(devices);
        sb.append("]");
        return sb.toString();
    }
    
}
