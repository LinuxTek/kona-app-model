package com.linuxtek.kona.app.social.entity;

import java.util.Date;

public class KBaseAddressBook implements KAddressBook {
    private Long id;
    private Long userId;
    private Long refUserId;
    private Long photoId;
    private String photoUrlPath;
    private String firstName;
    private String lastName;
    private String displayName;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String email;
    private String mobileNumber;
    private String twitterId;
    private String twitterHandle;
    private String facebookId;
    private String facebookUsername;
    private boolean emailVerified;
    private boolean mobileVerified;
    private Date invitedDate;
    private Date registeredDate;
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
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
	public Long getRefUserId() {
        return refUserId;
    }

    @Override
	public void setRefUserId(Long refUserId) {
        this.refUserId = refUserId;
    }

    @Override
	public Long getPhotoId() {
        return photoId;
    }

    @Override
	public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Override
	public String getPhotoUrlPath() {
        return photoUrlPath;
    }

    @Override
	public void setPhotoUrlPath(String photoUrlPath) {
        this.photoUrlPath = photoUrlPath == null ? null : photoUrlPath.trim();
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
	public String getAddress() {
        return address;
    }

    @Override
	public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
	public String getTwitterId() {
        return twitterId;
    }

    @Override
	public void setTwitterId(String twitterId) {
        this.twitterId = twitterId == null ? null : twitterId.trim();
    }

    @Override
	public String getTwitterHandle() {
        return twitterHandle;
    }

    @Override
	public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle == null ? null : twitterHandle.trim();
    }

    @Override
	public String getFacebookId() {
        return facebookId;
    }

    @Override
	public void setFacebookId(String facebookId) {
        this.facebookId = facebookId == null ? null : facebookId.trim();
    }

    @Override
	public String getFacebookUsername() {
        return facebookUsername;
    }

    @Override
	public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername == null ? null : facebookUsername.trim();
    }

    @Override
	public boolean isEmailVerified() {
        return emailVerified;
    }

    @Override
	public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Override
	public boolean isMobileVerified() {
        return mobileVerified;
    }

    @Override
	public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
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
        sb.append(", userId=").append(userId);
        sb.append(", refUserId=").append(refUserId);
        sb.append(", photoId=").append(photoId);
        sb.append(", photoUrlPath=").append(photoUrlPath);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", displayName=").append(displayName);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", country=").append(country);
        sb.append(", email=").append(email);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", twitterId=").append(twitterId);
        sb.append(", twitterHandle=").append(twitterHandle);
        sb.append(", facebookId=").append(facebookId);
        sb.append(", facebookUsername=").append(facebookUsername);
        sb.append(", emailVerified=").append(emailVerified);
        sb.append(", mobileVerified=").append(mobileVerified);
        sb.append(", invitedDate=").append(invitedDate);
        sb.append(", registeredDate=").append(registeredDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
