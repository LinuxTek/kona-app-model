package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseTokenLog implements KTokenLog {
	private static final long serialVersionUID = 1L;
    
	private Long id;
    private Long appId;
    private Long userId;
    private Long tokenId;
    private String hostname;
    private Double latitude;
    private Double longitude;
    private String browser;
    private String requestUrl;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
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
}
