package com.linuxtek.kona.app.entity;

import java.util.Date;

public class KBaseAppWebhook implements KAppWebhook {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long appId;
    private String name;
    private String displayName;
    private String events;
    private String url;
    private boolean enabled;
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
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
	public String getDisplayName() {
        return displayName;
    }

    @Override
	public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    @Override
	public String getEvents() {
        return events;
    }

    @Override
	public void setEvents(String events) {
        this.events = events == null ? null : events.trim();
    }

    @Override
	public String getUrl() {
        return url;
    }

    @Override
	public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        sb.append(", appId=").append(appId);
        sb.append(", events=").append(events);
        sb.append(", url=").append(url);
        sb.append(", enabled=").append(enabled);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
