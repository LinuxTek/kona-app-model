/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KRegistration extends KEntityObject {
    public Long getId();
    public void setId(Long id);

    public Long getAppId();
    public void setAppId(Long appId);

    public Long getUserId();
    public void setUserId(Long userId);

    public Long getCampaignId();
    public void setCampaignId(Long campaignId);

    public Long getPartnerId();
    public void setPartnerId(Long partnerId);

    public Long getPromoId();
    public void setPromoId(Long promoId);

    public Long getReferredById();
    public void setReferredById(Long referredById);

    public String getClientId();
    public void setClientId(String clientId);

    public String getUsername(); 
    public void setUsername(String username);

    public String getHostname();
    public void setHostname(String hostname);

    public String getBrowser();
    public void setBrowser(String browser);

    public String getDeviceUuid();
    public void setDeviceUuid(String deviceUuid);

    public Long getPlatformId();
    public void setPlatformId(Long platformId);

    public String getPlatformVersion();
    public void setPlatformVersion(String platformVersion);

    public Integer getSignupTime();
    public void setSignupTime(Integer signupTime);

    public Boolean getEmailVerified();
    public void setEmailVerified(Boolean emailVerified);

    public Boolean getEmailPending();
    public void setEmailPending(Boolean emailPending);

    public Boolean getMobileVerified();
    public void setMobileVerified(Boolean mobileVerified);

    public Boolean getMobilePending();
    public void setMobilePending(Boolean mobilePending);

    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);

    public Date getRegisteredDate();
    public void setRegisteredDate(Date registeredDate);

    public Date getRetiredDate();
    public void setRetiredDate(Date retiredDate);

    public Date getLastUpdated();
    public void setLastUpdated(Date lastUpdated);
}
