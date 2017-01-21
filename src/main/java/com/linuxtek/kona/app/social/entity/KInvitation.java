package com.linuxtek.kona.app.social.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KInvitation extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	Long getTypeId();

	void setTypeId(Long typeId);

	Long getChannelId();

	void setChannelId(Long channelId);

	Long getStatusId();

	void setStatusId(Long statusId);

	Long getUserId();

	void setUserId(Long userId);

	Long getAddressBookId();

	void setAddressBookId(Long addressBookId);

	Long getInviteeUserId();

	void setInviteeUserId(Long inviteeUserId);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getEmail();

	void setEmail(String email);

	String getMobileNumber();

	void setMobileNumber(String mobileNumber);

	String getInvitationCode();

	void setInvitationCode(String invitationCode);

	String getMessage();

	void setMessage(String message);

	Integer getInvitedCount();

	void setInvitedCount(Integer invitedCount);

	Date getInvitedDate();

	void setInvitedDate(Date invitedDate);

	Date getViewedDate();

	void setViewedDate(Date viewedDate);

	Date getIgnoredDate();

	void setIgnoredDate(Date ignoredDate);

	Date getAcceptedDate();

	void setAcceptedDate(Date acceptedDate);

	Date getRegisteredDate();

	void setRegisteredDate(Date registeredDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}
