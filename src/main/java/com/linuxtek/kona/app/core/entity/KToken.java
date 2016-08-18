/*
 * Copyright (C) 2011 LinuxTek, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * Token for user authentication
 */
public interface KToken extends KEntityObject {
    /** 
     * Internal object identifier. Do not exported or referenced externally.
     */
    public Long getId(); 
    public void setId(Long id);


    /** 
     * Token type
     */
    public Long getTypeId(); 
    public void setTypeId(Long typeId);
    
    
    /** 
     * The app accessing the system.
     */
    public Long getAppId(); 
    public void setAppId(Long appId);
    
    
    /** 
     * The clientId of the app (client) accessing the system.
     */
    public String getClientId(); 
    public void setClientId(String clientId);


    /** 
     * UserId of this system that has granted permission
     * to the app.  Token issued will be used to access 
     * this system on behalf of this user.
     */
    public Long getUserId(); 
    public void setUserId(Long userId);
    
    
    /** 
     * Token sent to app to access system on behalf of user.
     */
    public String getAccessToken(); 
    public void setAccessToken(String accessToken);
    
    
    /** 
     * Refresh token used by app to upd
     */
    public String getRefreshToken(); 
    public void setRefreshToken(String refreshToken);

    
    /** 
     * Username of the client. 
     */
    public String getUsername(); 
    public void setUsername(String username);

    
    /** 
     * Host from which user logged in. 
     */
    public String getHostname();
    public void setHostname(String hostname);
    
    public Double getLatitude();
    public void setLatitude(Double latitude);
    
    public Double getLongitude();
    public void setLongitude(Double longitude);
    
    public String getBrowser();
    public void setBrowser(String browser);

    public String getAppVersion();
    public void setAppVersion(String appVersion);
    
    public String getAppBuild();
    public void setAppBuild(String appBuild);
    
    public String getScope();
    public void setScope(String scope);
    
    /**
     * Spring security oauth2 authentication object.
     */
    public byte[] getAuthentication(); 
    public void setAuthentication(byte[] authentication);

    /** 
     * Flag to determine if token is active. A token can be
     * manually disabled by an administrator to effectively 
     * logout the current user and force a new login. 
     */
    public boolean isActive();
    public void setActive(boolean active);


    /**
     * A token can be issued but not yet approved by the user.
     * Used for OAuth2 access.
     */
    public boolean isApproved();
    public void setApproved(boolean approved);

   

    /** 
     * Number of time this token has accessed a resource. 
     */
    public Long getAccessCount(); 
    public void setAccessCount(Long accessCount);
    
    /**
     * Date created.
     */
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);
    
    public Date getRetiredDate();
    public void setRetiredDate(Date retiredDate);

    /** 
     * Date client logged in. 
     */
    public Date getLastLoginDate();
    public void setLastLoginDate(Date lastLoginDate);
    
    public Date getLoginDate();
    public void setLoginDate(Date loginDate);

    /** 
     * Date client logged out. 
     */
    public Date getLogoutDate();
    public void setLogoutDate(Date logoutDate);

    /** 
     * Date this token is set to expire. 
     */
    public Date getAccessExpirationDate();
    public void setAccessExpirationDate(Date accessExpirationDate);
    
    public Date getRefreshExpirationDate();
    public void setRefreshExpirationDate(Date refreshExpirationDate);

    /** 
     * Date this token record was last updated.
     */
    public Date getLastUpdated();
    public void setLastUpdated(Date lastUpdated);
}
