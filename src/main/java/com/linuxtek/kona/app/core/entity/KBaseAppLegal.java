package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseAppLegal implements KAppLegal {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long appId;
    private String type;
    private String content;
    private Integer version;
    private boolean active;
    private Date publishedDate;
    private Date createdDate;
    private Date updatedDate;

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
        this.uid = uid;
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
	public String getType() {
        return type;
    }

    @Override
	public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    @Override
	public String getContent() {
        return content;
    }

    @Override
	public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
	public Integer getVersion() {
        return version;
    }

    @Override
	public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
	public boolean isActive() {
        return active;
    }

    @Override
	public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
	public Date getPublishedDate() {
        return publishedDate;
    }

    @Override
	public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", appId=").append(appId);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", version=").append(version);
        sb.append(", active=").append(active);
        sb.append(", publishedDate=").append(publishedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
