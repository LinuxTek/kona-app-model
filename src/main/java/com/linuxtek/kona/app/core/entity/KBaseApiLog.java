/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBaseApiLog
 */
public class KBaseApiLog implements KApiLog {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String uid;
    private Long ownerId;
    private Long appId;
    private Long versionId;
    private String clientId;
    private Long userId;
    private String accessToken;
    private String endPoint;
    private String className;
    private String methodName;
    private String hostname;
    private String browser;
    private Double latitude;
    private Double longitude;
    private Date createdDate;
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
	public Long getOwnerId() {
        return ownerId;
    }

    @Override
	public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
	public Long getVersionId() {
        return versionId;
    }

   
    @Override
	public void setVersionId(Long versionId) {
        this.versionId = versionId;
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
	public Long getUserId() {
        return userId;
    }

   
    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
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
	public String getEndPoint() {
        return endPoint;
    }

    @Override
	public void setEndPoint(String endPoint) {
        this.endPoint = endPoint == null ? null : endPoint.trim();
    }

    @Override
	public String getClassName() {
        return className;
    }

   
    @Override
	public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

   
    @Override
	public String getMethodName() {
        return methodName;
    }

   
    @Override
	public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

  
    @Override
	public String getHostname() {
        return hostname;
    }

   
    @Override
	public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    @Override
	public String getBrowser() {
        return browser;
    }

   
    @Override
	public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

   
    @Override
	public Double getLatitude() {
        return latitude;
    }

    
    @Override
	public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

   
    @Override
	public Double getLongitude() {
        return longitude;
    }

    @Override
	public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
        sb.append(", uid=").append(uid);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", appId=").append(appId);
        sb.append(", versionId=").append(versionId);
        sb.append(", clientId=").append(clientId);
        sb.append(", userId=").append(userId);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", endPoint=").append(endPoint);
        sb.append(", className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
    
