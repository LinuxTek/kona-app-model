/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.entity;

import java.util.Date;

public class KBaseFriendship implements KFriendship {
    private Long id;
    private String uid;
    private Long userId;
    private Long friendId;
    private Long statusId;
    private Long circleId;
    private boolean friendshipRequested;
    private Date createdDate;
    private Date lastUpdated;

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
	public Long getFriendId() {
        return friendId;
    }

    @Override
	public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    @Override
	public Long getStatusId() {
        return statusId;
    }

    @Override
	public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
	public Long getCircleId() {
        return circleId;
    }

    @Override
    public void setCircleId(Long circleId) {
    	this.circleId = circleId;
    }

    @Override
    public boolean isFriendshipRequested() {
    	return friendshipRequested;
    }

    @Override
    public void setFriendshipRequested(boolean friendshipRequested) {
    	this.friendshipRequested = friendshipRequested;
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
        sb.append(", friendId=").append(friendId);
        sb.append(", statusId=").append(statusId);
        sb.append(", circleId=").append(circleId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
