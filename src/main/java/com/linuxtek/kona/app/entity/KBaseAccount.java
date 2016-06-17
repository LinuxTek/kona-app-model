/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

/**
 * KBaseAccount.
 */
public class KBaseAccount implements KAccount {
    private static final long serialVersionUID = 1L;

	private Long id;
	private String uid;
	private Long ownerId;
	private String name;
	private String displayName;
    private boolean enabled;
    private boolean active;
    private boolean verified;
    private Date createdDate;
    private Date retiredDate;
    private Date lastUpdated;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getRetiredDate() {
		return retiredDate;
	}
	public void setRetiredDate(Date retiredDate) {
		this.retiredDate = retiredDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
