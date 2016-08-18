package com.linuxtek.kona.app.messaging.entity;

import java.util.Date;

public class KBaseEmailEvent implements KEmailEvent {
    private Long id;
    private Long typeId;
    private Long emailId;
    private String target;
    private String error;
    private String hostname;
    private String browser;
    private Date eventDate;
    private Date createdDate;
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
	public Long getTypeId() {
        return typeId;
    }

    @Override
	public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
	public Long getEmailId() {
        return emailId;
    }

    @Override
	public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    @Override
	public String getTarget() {
        return target;
    }

    @Override
	public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    @Override
	public String getError() {
        return error;
    }

    @Override
	public void setError(String error) {
        this.error = error == null ? null : error.trim();
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
	public Date getEventDate() {
        return eventDate;
    }

    @Override
	public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", emailId=").append(emailId);
        sb.append(", target=").append(target);
        sb.append(", error=").append(error);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", eventDate=").append(eventDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
