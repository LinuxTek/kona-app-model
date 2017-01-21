/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBaseApp.
 */
public class KBaseApp implements KApp {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String uid;
    private Long typeId;
    private Long userId;
    private Long logoId;
    private String logoUrlPath;
    private String name;
    private String displayName;
    private String description;
    private String appUrl;
    private String companyName;
    private String companyUrl;
    private String privacyUrl;
    private boolean enabled;
    private Date createdDate;
    private Date retiredDate;
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
        this.uid = uid == null ? null : uid.trim();
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
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    @Override
	public Long getLogoId() {
        return logoId;
    }

    @Override
	public void setLogoId(Long logoId) {
        this.logoId = logoId;
    }

    @Override
	public String getLogoUrlPath() {
        return logoUrlPath;
    }

    @Override
	public void setLogoUrlPath(String logoUrlPath) {
        this.logoUrlPath = logoUrlPath == null ? null : logoUrlPath.trim();
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
	public String getDescription() {
        return description;
    }

    @Override
	public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
	public String getAppUrl() {
        return appUrl;
    }

    @Override
	public void setAppUrl(String appUrl) {
        this.appUrl = appUrl == null ? null : appUrl.trim();
    }

    @Override
	public String getCompanyName() {
        return companyName;
    }

    @Override
	public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    @Override
	public String getCompanyUrl() {
        return companyUrl;
    }

    @Override
	public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl == null ? null : companyUrl.trim();
    }

    @Override
	public String getPrivacyUrl() {
        return privacyUrl;
    }

    @Override
	public void setPrivacyUrl(String privacyUrl) {
        this.privacyUrl = privacyUrl == null ? null : privacyUrl.trim();
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
	public Date getRetiredDate() {
        return retiredDate;
    }

    @Override
	public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", userId=").append(userId);
        sb.append(", logoId=").append(logoId);
        sb.append(", logoUrlPath=").append(logoUrlPath);
        sb.append(", name=").append(name);
        sb.append(", displayName=").append(displayName);
        sb.append(", description=").append(description);
        sb.append(", appUrl=").append(appUrl);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyUrl=").append(companyUrl);
        sb.append(", privacyUrl=").append(privacyUrl);
        sb.append(", enabled=").append(enabled);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", retiredDate=").append(retiredDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
