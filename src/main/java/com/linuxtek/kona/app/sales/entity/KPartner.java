package com.linuxtek.kona.app.sales.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KPartner extends KEntityObject {

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.id
	 *
	 * @return the value of partner.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.id
	 *
	 * @param id the value for partner.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setId(Long id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.uid
	 *
	 * @return the value of partner.uid
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getUid();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.uid
	 *
	 * @param uid the value for partner.uid
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setUid(String uid);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.parent_id
	 *
	 * @return the value of partner.parent_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getParentId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.parent_id
	 *
	 * @param parentId the value for partner.parent_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setParentId(Long parentId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.name
	 *
	 * @return the value of partner.name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getName();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.name
	 *
	 * @param name the value for partner.name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setName(String name);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.display_name
	 *
	 * @return the value of partner.display_name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getDisplayName();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.display_name
	 *
	 * @param displayName the value for partner.display_name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDisplayName(String displayName);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.description
	 *
	 * @return the value of partner.description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getDescription();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.description
	 *
	 * @param description the value for partner.description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDescription(String description);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.url
	 *
	 * @return the value of partner.url
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getUrl();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.url
	 *
	 * @param url the value for partner.url
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setUrl(String url);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.logo_url
	 *
	 * @return the value of partner.logo_url
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getLogoUrl();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.logo_url
	 *
	 * @param logoUrl the value for partner.logo_url
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLogoUrl(String logoUrl);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.facebook_url
	 *
	 * @return the value of partner.facebook_url
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getFacebookUrl();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.facebook_url
	 *
	 * @param facebookUrl the value for partner.facebook_url
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setFacebookUrl(String facebookUrl);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.twitter_handle
	 *
	 * @return the value of partner.twitter_handle
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getTwitterHandle();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.twitter_handle
	 *
	 * @param twitterHandle the value for partner.twitter_handle
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setTwitterHandle(String twitterHandle);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.first_name
	 *
	 * @return the value of partner.first_name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getFirstName();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.first_name
	 *
	 * @param firstName the value for partner.first_name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setFirstName(String firstName);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.last_name
	 *
	 * @return the value of partner.last_name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getLastName();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.last_name
	 *
	 * @param lastName the value for partner.last_name
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLastName(String lastName);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.phone_number
	 *
	 * @return the value of partner.phone_number
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getPhoneNumber();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.phone_number
	 *
	 * @param phoneNumber the value for partner.phone_number
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPhoneNumber(String phoneNumber);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.email
	 *
	 * @return the value of partner.email
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getEmail();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.email
	 *
	 * @param email the value for partner.email
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setEmail(String email);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.street1
	 *
	 * @return the value of partner.street1
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getStreet1();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.street1
	 *
	 * @param street1 the value for partner.street1
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setStreet1(String street1);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.street2
	 *
	 * @return the value of partner.street2
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getStreet2();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.street2
	 *
	 * @param street2 the value for partner.street2
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setStreet2(String street2);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.city
	 *
	 * @return the value of partner.city
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getCity();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.city
	 *
	 * @param city the value for partner.city
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCity(String city);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.state
	 *
	 * @return the value of partner.state
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getState();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.state
	 *
	 * @param state the value for partner.state
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setState(String state);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.postal_code
	 *
	 * @return the value of partner.postal_code
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getPostalCode();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.postal_code
	 *
	 * @param postalCode the value for partner.postal_code
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPostalCode(String postalCode);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.country
	 *
	 * @return the value of partner.country
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getCountry();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.country
	 *
	 * @param country the value for partner.country
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCountry(String country);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.latitude
	 *
	 * @return the value of partner.latitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Double getLatitude();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.latitude
	 *
	 * @param latitude the value for partner.latitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLatitude(Double latitude);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.longitude
	 *
	 * @return the value of partner.longitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Double getLongitude();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.longitude
	 *
	 * @param longitude the value for partner.longitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLongitude(Double longitude);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.population
	 *
	 * @return the value of partner.population
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Integer getPopulation();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.population
	 *
	 * @param population the value for partner.population
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPopulation(Integer population);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.enabled
	 *
	 * @return the value of partner.enabled
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isEnabled();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.enabled
	 *
	 * @param enabled the value for partner.enabled
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setEnabled(boolean enabled);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.created_date
	 *
	 * @return the value of partner.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getCreatedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.created_date
	 *
	 * @param createdDate the value for partner.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCreatedDate(Date createdDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.retired_date
	 *
	 * @return the value of partner.retired_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getRetiredDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.retired_date
	 *
	 * @param retiredDate the value for partner.retired_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setRetiredDate(Date retiredDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column partner.last_updated
	 *
	 * @return the value of partner.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getLastUpdated();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column partner.last_updated
	 *
	 * @param lastUpdated the value for partner.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLastUpdated(Date lastUpdated);

}