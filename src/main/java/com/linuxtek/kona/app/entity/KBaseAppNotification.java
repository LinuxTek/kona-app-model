package com.linuxtek.kona.app.entity;

import java.util.Date;

public class KBaseAppNotification implements KPushNotification {
	private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long appId;
    private String platformName;
    private String pushServerKey;
    private String pushServerSecret;
    private String pushEndpoint;
    private boolean sandbox;
    private Date createdDate;
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
	public String getPlatformName() {
        return platformName;
    }

    @Override
	public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @Override
	public String getPushServerKey() {
        return pushServerKey;
    }

    @Override
	public void setPushServerKey(String pushServerKey) {
        this.pushServerKey = pushServerKey == null ? null : pushServerKey.trim();
    }

    @Override
	public String getPushServerSecret() {
        return pushServerSecret;
    }

    @Override
	public void setPushServerSecret(String pushServerSecret) {
        this.pushServerSecret = pushServerSecret == null ? null : pushServerSecret.trim();
    }

    @Override
	public String getPushEndpoint() {
        return pushEndpoint;
    }

    @Override
	public void setPushEndpoint(String pushEndpoint) {
        this.pushEndpoint = pushEndpoint == null ? null : pushEndpoint.trim();
    }

    @Override
	public boolean isSandbox() {
        return sandbox;
    }

    @Override
	public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
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
        sb.append(", platformName=").append(platformName);
        sb.append(", pushServerKey=").append(pushServerKey);
        sb.append(", pushServerSecret=").append(pushServerSecret);
        sb.append(", pushEndpoint=").append(pushEndpoint);
        sb.append(", sandbox=").append(sandbox);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
    
}
