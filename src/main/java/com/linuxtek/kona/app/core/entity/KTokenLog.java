/*
 * Copyright (C) 2011 LinuxTek, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KTokenLog extends KEntityObject {
    public Long getId();
    public void setId(Long id);
    
    public Long getAppId();
    public void setAppId(Long appId);

    public Long getUserId();
    public void setUserId(Long userId);
    
    public Long getTokenId();
    public void setTokenId(Long tokenId);

    public String getHostname();
    public void setHostname(String hostname);
    
    public Double getLatitude();
    public void setLatitude(Double latitude);
    
    public Double getLongitude();
    public void setLongitude(Double longitude);

    public String getBrowser();
    public void setBrowser(String browser);

    public String getRequestUrl();
    public void setRequestUrl(String requestUrl);

    public Date getUpdatedDate();
    public void setUpdatedDate(Date updatedDate);
}
