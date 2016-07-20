package com.linuxtek.kona.app.entity;

import java.util.Date;

public class KBaseAppNotificationDevice implements KPushNotificationDevice {
	private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long appId;
    private Long pushNotificationId;
    private Long userId;
    private String deviceUuid;
    private String platformName;
	private String pushToken;
    private String pushEndpoint;
    private boolean enabled;
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
	public Long getPushNotificationId() {
        return pushNotificationId;
    }

    @Override
	public void setPushNotificationId(Long pushNotificationId) {
        this.pushNotificationId = pushNotificationId;
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
	public String getDeviceUuid() {
        return deviceUuid;
    }

    @Override
	public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
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
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
	public String getPushToken() {
        return pushToken;
    }

    @Override
	public void setPushToken(String pushToken) {
        this.pushToken = pushToken == null ? null : pushToken.trim();
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
	public boolean isEnabled() {
        return enabled;
    }

    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
        sb.append(", pushNotificationId=").append(pushNotificationId);
        sb.append(", appId=").append(appId);
        sb.append(", deviceUuid=").append(deviceUuid);
        sb.append(", userId=").append(userId);
        sb.append(", pushToken=").append(pushToken);
        sb.append(", pushEndpoint=").append(pushEndpoint);
        sb.append(", enabled=").append(enabled);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
