package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseSupportMessage implements KSupportMessage {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long appId;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String message;
    private String hostname;
    private String browser;
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
        this.uid = uid;
    }

    @Override
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

   

    /**
     * @return the userId
     */
    @Override
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the firstName
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobileNumber
     */
    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the hostname
     */
    @Override
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the browser
     */
    @Override
    public String getBrowser() {
        return browser;
    }

    /**
     * @param browser the browser to set
     */
    @Override
    public void setBrowser(String browser) {
        this.browser = browser;
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
        sb.append(", appId=").append(appId);
        sb.append(", userId=").append(userId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", message=").append(message);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
