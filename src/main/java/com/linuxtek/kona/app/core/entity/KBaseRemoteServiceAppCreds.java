package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseRemoteServiceAppCreds implements KRemoteServiceAppCreds {
    private Long id;
    private Long appId;
    private Long remoteServiceId;
    private String authorizeUri;
    private String tokenUri;
    private String scope;
    private String clientKey;
    private String clientSecret;
    private String redirectUri;
    private String namespace;
    private String region;
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
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
	public Long getRemoteServiceId() {
        return remoteServiceId;
    }

    @Override
	public void setRemoteServiceId(Long remoteServiceId) {
        this.remoteServiceId = remoteServiceId;
    }

    @Override
	public String getAuthorizeUri() {
        return authorizeUri;
    }

    @Override
	public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri == null ? null : authorizeUri.trim();
    }

    @Override
	public String getTokenUri() {
        return tokenUri;
    }

    @Override
	public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri == null ? null : tokenUri.trim();
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
	public String getClientKey() {
        return clientKey;
    }

    @Override
	public void setClientKey(String clientKey) {
        this.clientKey = clientKey == null ? null : clientKey.trim();
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
	public String getNamespace() {
        return namespace;
    }

    @Override
	public void setNamespace(String namespace) {
        this.namespace = namespace == null ? null : namespace.trim();
    }

    @Override
	public String getRegion() {
        return region;
    }

    @Override
	public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
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
        sb.append(", appId=").append(appId);
        sb.append(", remoteServiceId=").append(remoteServiceId);
        sb.append(", authorizeUri=").append(authorizeUri);
        sb.append(", tokenUri=").append(tokenUri);
        sb.append(", scope=").append(scope);
        sb.append(", clientKey=").append(clientKey);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", redirectUri=").append(redirectUri);
        sb.append(", namespace=").append(namespace);
        sb.append(", region=").append(region);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
