package com.linuxtek.kona.app.social.entity;

import java.util.Date;

public class KBaseFriendshipCircle implements KFriendshipCircle {
    private Long id;
    private String uid;
    private Long userId;
    private String name;
    private String displayName;
    private boolean defaultCircle;
    private Date createdDate;
    private Date updatedDate;

    private static final long serialVersionUID = 1L;

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
	public boolean isDefaultCircle() {
        return defaultCircle;
    }

    @Override
	public void setDefaultCircle(boolean defaultCircle) {
        this.defaultCircle = defaultCircle;
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
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", displayName=").append(displayName);
        sb.append(", defaultCircle=").append(defaultCircle);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
