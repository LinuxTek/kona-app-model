package com.linuxtek.kona.app.entity;

import java.util.Date;

public class KBaseAppUser implements KAppUser {
	private static final long serialVersionUID = 1L;
	
    private Long id;
    private Long userId;
    private Long appId;
    private Long tokenId;
    private String appUserId;
    private Date createdDate;
    private Date revokedDate;
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
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
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
	public Long getTokenId() {
        return tokenId;
    }

    @Override
	public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    @Override
	public String getAppUserId() {
        return appUserId;
    }

    @Override
	public void setAppUserId(String appUserId) {
        this.appUserId = appUserId == null ? null : appUserId.trim();
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
	public Date getRevokedDate() {
        return revokedDate;
    }

    @Override
	public void setRevokedDate(Date revokedDate) {
        this.revokedDate = revokedDate;
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
        sb.append(", userId=").append(userId);
        sb.append(", appId=").append(appId);
        sb.append(", tokenId=").append(tokenId);
        sb.append(", appUserId=").append(appUserId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", revokedDate=").append(revokedDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
    
}
