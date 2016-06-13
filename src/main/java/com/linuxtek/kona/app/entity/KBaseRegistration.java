/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

/**
 * KBaseRegistration.
 */
public class KBaseRegistration implements KRegistration {
    private Long id;
    private Long appId;
    private Long userId;
    private Long campaignId;
    private Long partnerId;
    private Long promoId;
    private Long referredById;
    private String clientId;
    private String username;
    private String hostname;
    private String browser;
    private String deviceName;
    private String deviceUuid;
    private Long platformId;
    private String platformVersion;
    private Integer signupTime;
    private Boolean emailVerified;
    private Boolean emailPending;
    private Boolean mobileVerified;
    private Boolean mobilePending;
    private Date createdDate;
    private Date remindedDate;
    private Date registeredDate;
    private Date retiredDate;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getCampaignId() {
        return campaignId;
    }

    @Override
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
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
    public Long getPromoId() {
        return promoId;
    }

    @Override
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    @Override
    public Long getReferredById() {
        return referredById;
    }

    @Override
    public void setReferredById(Long referredById) {
        this.referredById = referredById;
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
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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
    public String getDeviceUuid() {
        return deviceUuid;
    }

    @Override
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid == null ? null : deviceUuid.trim();
    }

    @Override
    public Long getPlatformId() {
        return platformId;
    }

    @Override
    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    @Override
    public String getPlatformVersion() {
        return platformVersion;
    }

    @Override
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion == null ? null : platformVersion.trim();
    }

    @Override
    public Integer getSignupTime() {
        return signupTime;
    }

    @Override
    public void setSignupTime(Integer signupTime) {
        this.signupTime = signupTime;
    }

    @Override
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    @Override
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Override
    public Boolean getEmailPending() {
        return emailPending;
    }

    @Override
    public void setEmailPending(Boolean emailPending) {
        this.emailPending = emailPending;
    }

    @Override
    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    @Override
    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    @Override
    public Boolean getMobilePending() {
        return mobilePending;
    }

    @Override
    public void setMobilePending(Boolean mobilePending) {
        this.mobilePending = mobilePending;
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
    public Date getRegisteredDate() {
        return registeredDate;
    }

    @Override
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
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
        sb.append(", userId=").append(userId);
        sb.append(", campaignId=").append(campaignId);
        sb.append(", partnerId=").append(partnerId);
        sb.append(", promoId=").append(promoId);
        sb.append(", referredById=").append(referredById);
        sb.append(", clientId=").append(clientId);
        sb.append(", username=").append(username);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", deviceUuid=").append(deviceUuid);
        sb.append(", platformId=").append(platformId);
        sb.append(", platformVersion=").append(platformVersion);
        sb.append(", signupTime=").append(signupTime);
        sb.append(", emailVerified=").append(emailVerified);
        sb.append(", emailPending=").append(emailPending);
        sb.append(", mobileVerified=").append(mobileVerified);
        sb.append(", mobilePending=").append(mobilePending);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", remindedDate=").append(remindedDate);
        sb.append(", registeredDate=").append(registeredDate);
        sb.append(", retiredDate=").append(retiredDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
