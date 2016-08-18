/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBaseAuthCode.
 */
public class KBaseAuthCode implements KAuthCode {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long typeId;
    private Long appId;
    private Long userId;
    private String code;
    private boolean valid;
    private Integer useCount;
    private Integer maxUseCount;
    private Date expirationDate;
    private Date lastAccessedDate;
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
	public Long getTypeId() {
        return typeId;
    }

    @Override
	public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
	public String getCode() {
        return code;
    }

    @Override
	public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
	public boolean isValid() {
        return valid;
    }

    @Override
	public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
	public Integer getUseCount() {
        return useCount;
    }

    @Override
	public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    @Override
	public Integer getMaxUseCount() {
        return maxUseCount;
    }

    @Override
	public void setMaxUseCount(Integer maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    @Override
	public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
	public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
	public Date getLastAccessedDate() {
        return lastAccessedDate;
    }

    @Override
	public void setLastAccessedDate(Date lastAccessedDate) {
        this.lastAccessedDate = lastAccessedDate;
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
        sb.append(", userId=").append(userId);
        sb.append(", code=").append(code);
        sb.append(", valid=").append(valid);
        sb.append(", useCount=").append(useCount);
        sb.append(", maxUseCount=").append(maxUseCount);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", lastAccessedDate=").append(lastAccessedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
