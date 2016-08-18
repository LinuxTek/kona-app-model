package com.linuxtek.kona.app.social.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAddressBook extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	Long getUserId();

	void setUserId(Long userId);

	Long getRefUserId();

	void setRefUserId(Long refUserId);

	Long getPhotoId();

	void setPhotoId(Long photoId);

	String getPhotoUrlPath();

	void setPhotoUrlPath(String photoUrlPath);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getDisplayName();

	void setDisplayName(String displayName);

	String getAddress();

	void setAddress(String address);

	String getCity();

	void setCity(String city);

	String getState();

	void setState(String state);

	String getPostalCode();

	void setPostalCode(String postalCode);

	String getCountry();

	void setCountry(String country);

	String getEmail();

	void setEmail(String email);

	String getMobileNumber();

	void setMobileNumber(String mobileNumber);

	String getTwitterId();

	void setTwitterId(String twitterId);

	String getTwitterHandle();

	void setTwitterHandle(String twitterHandle);

	String getFacebookId();

	void setFacebookId(String facebookId);

	String getFacebookUsername();

	void setFacebookUsername(String facebookUsername);

	boolean isEmailVerified();

	void setEmailVerified(boolean emailVerified);

	boolean isMobileVerified();

	void setMobileVerified(boolean mobileVerified);

	Date getInvitedDate();

	void setInvitedDate(Date invitedDate);

	Date getRegisteredDate();

	void setRegisteredDate(Date registeredDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}
