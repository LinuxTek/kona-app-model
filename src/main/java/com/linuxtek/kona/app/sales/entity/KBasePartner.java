package com.linuxtek.kona.app.sales.entity;

import java.util.Date;

public class KBasePartner implements KPartner {
  
	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.uid
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.parent_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.display_name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String displayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.description
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.url
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.logo_url
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String logoUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.facebook_url
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String facebookUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.twitter_handle
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String twitterHandle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.first_name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String firstName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.last_name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String lastName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.phone_number
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String phoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.email
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.street1
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String street1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.street2
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String street2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.city
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.state
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.postal_code
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String postalCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.country
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String country;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.latitude
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Double latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.longitude
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Double longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.population
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer population;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.enabled
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean enabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.created_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.retired_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date retiredDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column partner.last_updated
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date updatedDate;

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getId()
	 */
    @Override
	public Long getId() {
        return id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setId(java.lang.Long)
	 */
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getUid()
	 */
    @Override
	public String getUid() {
        return uid;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setUid(java.lang.String)
	 */
    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getParentId()
	 */
    @Override
	public Long getParentId() {
        return parentId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setParentId(java.lang.Long)
	 */
    @Override
	public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getName()
	 */
    @Override
	public String getName() {
        return name;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setName(java.lang.String)
	 */
    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getDisplayName()
	 */
    @Override
	public String getDisplayName() {
        return displayName;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setDisplayName(java.lang.String)
	 */
    @Override
	public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getDescription()
	 */
    @Override
	public String getDescription() {
        return description;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setDescription(java.lang.String)
	 */
    @Override
	public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getUrl()
	 */
    @Override
	public String getUrl() {
        return url;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setUrl(java.lang.String)
	 */
    @Override
	public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getLogoUrl()
	 */
    @Override
	public String getLogoUrl() {
        return logoUrl;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setLogoUrl(java.lang.String)
	 */
    @Override
	public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getFacebookUrl()
	 */
    @Override
	public String getFacebookUrl() {
        return facebookUrl;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setFacebookUrl(java.lang.String)
	 */
    @Override
	public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl == null ? null : facebookUrl.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getTwitterHandle()
	 */
    @Override
	public String getTwitterHandle() {
        return twitterHandle;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setTwitterHandle(java.lang.String)
	 */
    @Override
	public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle == null ? null : twitterHandle.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getFirstName()
	 */
    @Override
	public String getFirstName() {
        return firstName;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setFirstName(java.lang.String)
	 */
    @Override
	public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getLastName()
	 */
    @Override
	public String getLastName() {
        return lastName;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setLastName(java.lang.String)
	 */
    @Override
	public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getPhoneNumber()
	 */
    @Override
	public String getPhoneNumber() {
        return phoneNumber;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setPhoneNumber(java.lang.String)
	 */
    @Override
	public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getEmail()
	 */
    @Override
	public String getEmail() {
        return email;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setEmail(java.lang.String)
	 */
    @Override
	public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getStreet1()
	 */
    @Override
	public String getStreet1() {
        return street1;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setStreet1(java.lang.String)
	 */
    @Override
	public void setStreet1(String street1) {
        this.street1 = street1 == null ? null : street1.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getStreet2()
	 */
    @Override
	public String getStreet2() {
        return street2;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setStreet2(java.lang.String)
	 */
    @Override
	public void setStreet2(String street2) {
        this.street2 = street2 == null ? null : street2.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getCity()
	 */
    @Override
	public String getCity() {
        return city;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setCity(java.lang.String)
	 */
    @Override
	public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getState()
	 */
    @Override
	public String getState() {
        return state;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setState(java.lang.String)
	 */
    @Override
	public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getPostalCode()
	 */
    @Override
	public String getPostalCode() {
        return postalCode;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setPostalCode(java.lang.String)
	 */
    @Override
	public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getCountry()
	 */
    @Override
	public String getCountry() {
        return country;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setCountry(java.lang.String)
	 */
    @Override
	public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getLatitude()
	 */
    @Override
	public Double getLatitude() {
        return latitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setLatitude(java.lang.Double)
	 */
    @Override
	public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getLongitude()
	 */
    @Override
	public Double getLongitude() {
        return longitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setLongitude(java.lang.Double)
	 */
    @Override
	public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getPopulation()
	 */
    @Override
	public Integer getPopulation() {
        return population;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setPopulation(java.lang.Integer)
	 */
    @Override
	public void setPopulation(Integer population) {
        this.population = population;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#isEnabled()
	 */
    @Override
	public boolean isEnabled() {
        return enabled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setEnabled(boolean)
	 */
    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getCreatedDate()
	 */
    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setCreatedDate(java.util.Date)
	 */
    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getRetiredDate()
	 */
    @Override
	public Date getRetiredDate() {
        return retiredDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setRetiredDate(java.util.Date)
	 */
    @Override
	public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#getUpdatedDate()
	 */
    @Override
	public Date getUpdatedDate() {
        return updatedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPartner#setUpdatedDate(java.util.Date)
	 */
    @Override
	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table partner
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", displayName=").append(displayName);
        sb.append(", description=").append(description);
        sb.append(", url=").append(url);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", facebookUrl=").append(facebookUrl);
        sb.append(", twitterHandle=").append(twitterHandle);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", email=").append(email);
        sb.append(", street1=").append(street1);
        sb.append(", street2=").append(street2);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", country=").append(country);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", population=").append(population);
        sb.append(", enabled=").append(enabled);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", retiredDate=").append(retiredDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
