package com.linuxtek.kona.app.entity;

import java.util.Date;

public class KBaseRemoteServiceUserCreds implements KRemoteServiceUserCreds {
    private Long id;
    private Long appId;
    private Long accountId;
    private Long userId;
    private Long remoteServiceId;
    private String name;
    private String remoteServiceUserId;
    private String remoteServiceScreenName;
    private String authType;
    private String accessToken;
    private String refreshToken;
    private String tokenSecret;
    private Date expireDate;
    private Date connectedDate;
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
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
	public Long getAccountId() {
        return accountId;
    }

    @Override
	public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
	public Long getRemoteServiceId() {
        return remoteServiceId;
    }

    @Override
	public void setRemoteServiceId(Long remoteServiceId) {
        this.remoteServiceId = remoteServiceId;
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
	public String getRemoteServiceUserId() {
        return remoteServiceUserId;
    }

    @Override
	public void setRemoteServiceUserId(String remoteServiceUserId) {
        this.remoteServiceUserId = remoteServiceUserId == null ? null : remoteServiceUserId.trim();
    }

    @Override
	public String getRemoteServiceScreenName() {
        return remoteServiceScreenName;
    }

    @Override
	public void setRemoteServiceScreenName(String remoteServiceScreenName) {
        this.remoteServiceScreenName = remoteServiceScreenName == null ? null : remoteServiceScreenName.trim();
    }

    @Override
	public String getAuthType() {
        return authType;
    }

    @Override
	public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    @Override
	public String getAccessToken() {
        return accessToken;
    }

    @Override
	public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    @Override
	public String getRefreshToken() {
        return refreshToken;
    }

    @Override
	public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    @Override
	public String getTokenSecret() {
        return tokenSecret;
    }

    @Override
	public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret == null ? null : tokenSecret.trim();
    }

    @Override
	public Date getExpireDate() {
        return expireDate;
    }

    @Override
	public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
	public Date getConnectedDate() {
        return connectedDate;
    }

    @Override
	public void setConnectedDate(Date connectedDate) {
        this.connectedDate = connectedDate;
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
        sb.append(", accountId=").append(accountId);
        sb.append(", userId=").append(userId);
        sb.append(", remoteServiceId=").append(remoteServiceId);
        sb.append(", name=").append(name);
        sb.append(", remoteServiceUserId=").append(remoteServiceUserId);
        sb.append(", remoteServiceScreenName=").append(remoteServiceScreenName);
        sb.append(", authType=").append(authType);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", tokenSecret=").append(tokenSecret);
        sb.append(", expireDate=").append(expireDate);
        sb.append(", connectedDate=").append(connectedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
