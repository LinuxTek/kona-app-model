/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBaseAppCreds.
 */
public class KBaseAppCreds implements KAppCreds {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long appId;
    private Long apiVersionId;
    private String name;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private boolean enabled;
    private Integer accessTokenTimeout;
    private Integer refreshTokenTimeout;
    private Date createdDate;
    private Date retiredDate;
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
	public Long getApiVersionId() {
        return apiVersionId;
    }

    @Override
	public void setApiVersionId(Long apiVersionId) {
        this.apiVersionId = apiVersionId;
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
	public String getClientId() {
        return clientId;
    }

    @Override
	public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    @Override
	public String getClientSecret() {
        return clientSecret;
    }

    @Override
	public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    @Override
	public String getRedirectUri() {
        return redirectUri;
    }

    @Override
	public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri == null ? null : redirectUri.trim();
    }

    @Override
	public String getScope() {
        return scope;
    }

    @Override
	public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
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
	public Integer getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    @Override
	public void setAccessTokenTimeout(Integer accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    @Override
	public Integer getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    @Override
	public void setRefreshTokenTimeout(Integer refreshTokenTimeout) {
        this.refreshTokenTimeout = refreshTokenTimeout;
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
        sb.append(", apiVersionId=").append(apiVersionId);
        sb.append(", name=").append(name);
        sb.append(", clientId=").append(clientId);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", redirectUri=").append(redirectUri);
        sb.append(", scope=").append(scope);
        sb.append(", enabled=").append(enabled);
        sb.append(", accessTokenTimeout=").append(accessTokenTimeout);
        sb.append(", refreshTokenTimeout=").append(refreshTokenTimeout);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", retiredDate=").append(retiredDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
    
}
