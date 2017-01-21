/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.entity;

import java.util.Date;

public class KBaseInvitation implements KInvitation {
	private Long id;
    private Long typeId;
    private Long channelId;
    private Long statusId;
    private Long userId;
    private Long addressBookId;
    private Long inviteeUserId;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String mobileNumber;
    private String invitationCode;
    private String message;
    private Integer invitedCount;
    private Date invitedDate;
    private Date viewedDate;
    private Date ignoredDate;
    private Date acceptedDate;
    private Date registeredDate;
    private Date createdDate;
    private Date updatedDate;
    
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
	public Long getChannelId() {
        return channelId;
    }

    @Override
	public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    @Override
	public Long getStatusId() {
        return statusId;
    }

    @Override
	public void setStatusId(Long statusId) {
        this.statusId = statusId;
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
	public Long getAddressBookId() {
        return addressBookId;
    }

    @Override
	public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }

    @Override
	public Long getInviteeUserId() {
        return inviteeUserId;
    }

    @Override
	public void setInviteeUserId(Long inviteeUserId) {
        this.inviteeUserId = inviteeUserId;
    }

    @Override
	public String getFirstName() {
        return firstName;
    }

    @Override
	public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    @Override
	public String getLastName() {
        return lastName;
    }

    @Override
	public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    @Override
	public String getDisplayName() {
        return displayName;
    }

    @Override
	public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    @Override
	public String getEmail() {
        return email;
    }

    @Override
	public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Override
	public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
	public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    @Override
	public String getInvitationCode() {
        return invitationCode;
    }

    @Override
	public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    @Override
	public String getMessage() {
        return message;
    }

    @Override
	public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    @Override
	public Integer getInvitedCount() {
        return invitedCount;
    }

    @Override
	public void setInvitedCount(Integer invitedCount) {
        this.invitedCount = invitedCount;
    }

    @Override
	public Date getInvitedDate() {
        return invitedDate;
    }

    @Override
	public void setInvitedDate(Date invitedDate) {
        this.invitedDate = invitedDate;
    }

    @Override
	public Date getViewedDate() {
        return viewedDate;
    }

    @Override
	public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
    }

    @Override
	public Date getIgnoredDate() {
        return ignoredDate;
    }

    @Override
	public void setIgnoredDate(Date ignoredDate) {
        this.ignoredDate = ignoredDate;
    }

    @Override
	public Date getAcceptedDate() {
        return acceptedDate;
    }

    @Override
	public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", channelId=").append(channelId);
        sb.append(", statusId=").append(statusId);
        sb.append(", userId=").append(userId);
        sb.append(", addressBookId=").append(addressBookId);
        sb.append(", inviteeUserId=").append(inviteeUserId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", displayName=").append(displayName);
        sb.append(", email=").append(email);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", invitationCode=").append(invitationCode);
        sb.append(", message=").append(message);
        sb.append(", invitedCount=").append(invitedCount);
        sb.append(", invitedDate=").append(invitedDate);
        sb.append(", viewedDate=").append(viewedDate);
        sb.append(", ignoredDate=").append(ignoredDate);
        sb.append(", acceptedDate=").append(acceptedDate);
        sb.append(", registeredDate=").append(registeredDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
