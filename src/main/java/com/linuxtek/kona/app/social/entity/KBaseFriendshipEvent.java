/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.entity;

import java.util.Date;

public class KBaseFriendshipEvent implements KFriendshipEvent {
    private Long id;
    private Long typeId;
    private Long friendshipId;
    private Long userId;
    private Long friendId;
    private String event;
    private Date eventDate;
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
	public Long getTypeId() {
        return typeId;
    }

    @Override
	public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
	public Long getFriendshipId() {
        return friendshipId;
    }

    @Override
	public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
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
	public String getEvent() {
        return event;
    }

    @Override
	public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    @Override
	public Date getEventDate() {
        return eventDate;
    }

    @Override
	public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", friendshipId=").append(friendshipId);
        sb.append(", userId=").append(userId);
        sb.append(", friendId=").append(friendId);
        sb.append(", event=").append(event);
        sb.append(", eventDate=").append(eventDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
