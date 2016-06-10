package com.linuxtek.kona.app.entity;

import java.util.Date;

public class KBaseToken implements KToken {
	private static final long serialVersionUID = 1L;
    
	private Long id;
    private Long typeId;
    private Long userId;
    private Long appId;
    private String clientId;
    private String accessToken;
    private String refreshToken;
    private String scope;
    private byte[] authentication;
    private String username;
    private String hostname;
    private Double latitude;
    private Double longitude;
    private String browser;
    private String appVersion;
    private String appBuild;
	private boolean active;
	private boolean approved;
    private boolean guest;
    private Long accessCount;
    private Date lastLoginDate;
    private Date loginDate;
    private Date logoutDate;
    private Date createdDate;
    private Date retiredDate;
    private Date accessExpirationDate;
    private Date refreshExpirationDate;
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
    public Long getTypeId() {
        return typeId;
    }

    @Override
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
    public String getClientId() {
        return clientId;
    }
    
    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
    public String getRefreshToken() {
        return refreshToken;
    }
    
    @Override
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
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
    public byte[] getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication; 
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
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isApproved() {
        return approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean isGuest() {
        return guest;
    }

    @Override
    public void setGuest(boolean guest) {
        this.guest = guest;
    }


    @Override
    public Long getAccessCount() {
        return accessCount;
    }

    @Override
    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }

    @Override
    public Date getLoginDate() {
        return loginDate;
    }

    @Override
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public Date getLogoutDate() {
        return logoutDate;
    }

    @Override
    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    @Override
    public Date getAccessExpirationDate() {
        return accessExpirationDate;
    }

    @Override
    public void setAccessExpirationDate(Date accessExpirationDate) {
        this.accessExpirationDate = accessExpirationDate;
    }
    
    @Override
    public Date getRefreshExpirationDate() {
        return refreshExpirationDate;
    }

    @Override
    public void setRefreshExpirationDate(Date refreshExpirationDate) {
        this.refreshExpirationDate = refreshExpirationDate;
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
	public String getBrowser() {
        return browser;
	}
    
    @Override
	public void setBrowser(String browser) {
		this.browser = browser;
	}


	@Override
	public String getAppVersion() {
		return appVersion;
	}

	@Override
	public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
	}

	@Override
	public String getAppBuild() {
		return appBuild;
	}

	@Override
	public void setAppBuild(String appBuild) {
        this.appBuild = appBuild;
		
	}

	@Override
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	@Override
	public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
	}


}
