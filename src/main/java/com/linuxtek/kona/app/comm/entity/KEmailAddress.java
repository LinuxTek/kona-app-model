package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KEmailAddress extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getEmail();

	void setEmail(String email);

	String getMobileNumber();

	void setMobileNumber(String mobileNumber);

	String getGender();

	void setGender(String gender);

	String getBirthYear();

	void setBirthYear(String birthYear);

	String getCompany();

	void setCompany(String company);

	String getTitle();

	void setTitle(String title);

	String getExtra();

	void setExtra(String extra);

	String getStreet1();

	void setStreet1(String street1);

	String getStreet2();

	void setStreet2(String street2);

	String getCity();

	void setCity(String city);

	String getState();

	void setState(String state);

	String getPostalCode();

	void setPostalCode(String postalCode);

	String getCountry();

	void setCountry(String country);

	String getSource();

	void setSource(String source);

	boolean isScrubbed();

	void setScrubbed(boolean scrubbed);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	boolean isConfirmed();

	void setConfirmed(boolean confirmed);

	Date getOptedInDate();

	void setOptedInDate(Date optedInDate);

	Date getOptedOutDate();

	void setOptedOutDate(Date optedOutDate);

	Date getBouncedDate();

	void setBouncedDate(Date bouncedDate);

	Date getComplainedDate();

	void setComplainedDate(Date complainedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);
}
