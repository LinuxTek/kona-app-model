package com.linuxtek.kona.app.messaging.entity;

import java.util.Date;

public class KBaseEmailAddress implements KEmailAddress {
    private Long id;
    private String uid;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String gender;
    private String birthYear;
    private String company;
    private String title;
    private String extra;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String source;
    private boolean scrubbed;
    private boolean enabled;
    private boolean confirmed;
    private Date optedInDate;
    private Date optedOutDate;
    private Date bouncedDate;
    private Date complainedDate;
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
	public String getUid() {
        return uid;
    }

    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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
	public String getGender() {
        return gender;
    }

    @Override
	public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Override
	public String getBirthYear() {
        return birthYear;
    }

    @Override
	public void setBirthYear(String birthYear) {
        this.birthYear = birthYear == null ? null : birthYear.trim();
    }

    @Override
	public String getCompany() {
        return company;
    }

    @Override
	public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    @Override
	public String getTitle() {
        return title;
    }

    @Override
	public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Override
	public String getExtra() {
        return extra;
    }

    @Override
	public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    @Override
	public String getStreet1() {
        return street1;
    }

    @Override
	public void setStreet1(String street1) {
        this.street1 = street1 == null ? null : street1.trim();
    }

    @Override
	public String getStreet2() {
        return street2;
    }

    @Override
	public void setStreet2(String street2) {
        this.street2 = street2 == null ? null : street2.trim();
    }

    @Override
	public String getCity() {
        return city;
    }

    @Override
	public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    @Override
	public String getState() {
        return state;
    }

    @Override
	public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @Override
	public String getPostalCode() {
        return postalCode;
    }

    @Override
	public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    @Override
	public String getCountry() {
        return country;
    }

    @Override
	public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    @Override
	public String getSource() {
        return source;
    }

    @Override
	public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    @Override
	public boolean isScrubbed() {
        return scrubbed;
    }

    @Override
	public void setScrubbed(boolean scrubbed) {
        this.scrubbed = scrubbed;
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
	public boolean isConfirmed() {
        return confirmed;
    }

    @Override
	public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
	public Date getOptedInDate() {
        return optedInDate;
    }

    @Override
	public void setOptedInDate(Date optedInDate) {
        this.optedInDate = optedInDate;
    }

    @Override
	public Date getOptedOutDate() {
        return optedOutDate;
    }

    @Override
	public void setOptedOutDate(Date optedOutDate) {
        this.optedOutDate = optedOutDate;
    }

    @Override
	public Date getBouncedDate() {
        return bouncedDate;
    }

    @Override
	public void setBouncedDate(Date bouncedDate) {
        this.bouncedDate = bouncedDate;
    }

    @Override
	public Date getComplainedDate() {
        return complainedDate;
    }

    @Override
	public void setComplainedDate(Date complainedDate) {
        this.complainedDate = complainedDate;
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
        sb.append(", uid=").append(uid);
        sb.append(", userId=").append(userId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", gender=").append(gender);
        sb.append(", birthYear=").append(birthYear);
        sb.append(", company=").append(company);
        sb.append(", title=").append(title);
        sb.append(", extra=").append(extra);
        sb.append(", street1=").append(street1);
        sb.append(", street2=").append(street2);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", country=").append(country);
        sb.append(", source=").append(source);
        sb.append(", scrubbed=").append(scrubbed);
        sb.append(", enabled=").append(enabled);
        sb.append(", confirmed=").append(confirmed);
        sb.append(", optedInDate=").append(optedInDate);
        sb.append(", optedOutDate=").append(optedOutDate);
        sb.append(", bouncedDate=").append(bouncedDate);
        sb.append(", complainedDate=").append(complainedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
