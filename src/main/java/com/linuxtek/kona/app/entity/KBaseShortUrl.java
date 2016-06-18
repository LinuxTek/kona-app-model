/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

/**
 * KBaseShortUrl.
 */
public class KBaseShortUrl implements KShortUrl {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long appId;
    private Long userId;
    private Long promoId;
    private Long partnerId;
    private String domain;
    private String path;
    private String shortUrl;
    private String longUrl;
    private String description;
    private boolean script;
    private boolean enabled;
    private Date createdDate;
    private Date expiredDate;
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
	public Long getPromoId() {
        return promoId;
    }

    @Override
	public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    @Override
	public Long getPartnerId() {
        return partnerId;
    }

    @Override
	public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    @Override
	public String getDomain() {
        return domain;
    }

    @Override
	public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    @Override
	public String getPath() {
        return path;
    }

    @Override
	public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    @Override
	public String getShortUrl() {
        return shortUrl;
    }

    @Override
	public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl == null ? null : shortUrl.trim();
    }

    @Override
	public String getLongUrl() {
        return longUrl;
    }

    @Override
	public void setLongUrl(String longUrl) {
        this.longUrl = longUrl == null ? null : longUrl.trim();
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
	public boolean isScript() {
        return script;
    }

    @Override
	public void setScript(boolean script) {
        this.script = script;
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
	public Date getExpiredDate() {
        return expiredDate;
    }

    @Override
	public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
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
        sb.append(", promoId=").append(promoId);
        sb.append(", partnerId=").append(partnerId);
        sb.append(", domain=").append(domain);
        sb.append(", path=").append(path);
        sb.append(", shortUrl=").append(shortUrl);
        sb.append(", longUrl=").append(longUrl);
        sb.append(", description=").append(description);
        sb.append(", script=").append(script);
        sb.append(", enabled=").append(enabled);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", expiredDate=").append(expiredDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}

