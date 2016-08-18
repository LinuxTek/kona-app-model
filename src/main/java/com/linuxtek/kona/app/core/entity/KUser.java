package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * Core User object for framework interaction.
 * Note that some attributes refer to the User itself
 * (e.g. firstName, lastName, etc.) while others refer to the
 * User's account (e.g.  enabled, active, etc.). 
 */
    
public interface KUser extends KEntityObject {
    /** 
     * Internal object identifier. Do not exported or referenced externally.
     */
    public Long getId(); 
    public void setId(Long id);
    
    /** 
     * The user's publicly visible identifier.
     */
    public String getUid(); 
    public void setUid(String uid);
    
    public Long getParentId(); 
    public void setParentId(Long parentId);

    /**
     * The a reference to the user's type.
     */
    public Long getTypeId(); 
    public void setTypeId(Long typeId);
    
    /**
     * Roles is meant to be a bit field
     * e.g. 1 = SYSTEM, 2 = ADMIN, 4 = USER, 
     * so a value of 3 would mean SYSTEM,ADMIN
     * @return
     */
    public Long getRoles(); 
    public void setRoles(Long roles);
    
    public Long getAccountId(); 
    public void setAccountId(Long accountId);
    
    
    public Long getStatusId();
    public void setStatusId(Long statusId);

    public Long getPresenceId();
    public void setPresenceId(Long presenceId);
    
    
    public String getPhotoUrl();
    public void setPhotoUrl(String photoUrl);
    
    
    /** 
     * The user's username which is used to login to the system. 
     */
    public String getUsername(); 
    public void setUsername(String username);

    /**
     * The user's first name.
     */
    public String getFirstName();
    public void setFirstName(String firstName);

    /**
     * The user's last name.
     */
    public String getLastName();
    public void setLastName(String lastName);

    /**
     * The user's preferred display name.  This is the publicly
     * visible name of this user to other users in the system.
     */
    public String getDisplayName();
    public void setDisplayName(String displayName);

    /**
     * The user's email address.
     */
    public String getEmail();
    public void setEmail(String email);

    /**
     * The users's mobile number.
     */
    public String getMobileNumber();
    public void setMobileNumber(String mobileNumber);

    
    /**
     * The user's gender.
     */
    public String getGender();
    public void setGender(String gender);

    /**
     * The user's date of birth.
     */
    public Date getBirthDate();
    public void setBirthDate(Date birthDate);


    /** 
     * The user's default locale.
     */
    public String getLocale();
    public void setLocale(String locale);

    /**
     * The users' default time zone.
     */
    public String getTimeZone();
    public void setTimeZone(String timeZone);

    /**
     * The latitude of the user's current geolocation.
     */
    public Double getLatitude();
    public void setLatitude(Double latitude);

    /**
     * The longitude of the user's current geolocation.
     */
    public Double getLongitude();
    public void setLongitude(Double longitude);
    
    /**
     * The floor of the user's current geolocation.
     */
    public Integer getFloor();
    public void setFloor(Integer floor);
    
    /**
     * Flag to indicate if this user account is currently enabled.
     * This flag is set by the system to control the user's access
     * to the account.  For example, this flag may be set to false
     * if the user exceeds a specfic number of failed login attempts.
     */
    public boolean isEnabled();
    public void setEnabled(boolean enabled);

    /**
     * Flag to indicate if this user account is currently active.
     * An active account is one which is not retired (deleted).
     * If set to true, getRetiredDate() should be set to null, otherwise
     * it should return the date the account was retired.
     *
     * <p>
     * NOTE: If this flag is set to false, then this user account
     * should be completely invisible to every other user in the
     * system.
     * </p>
     */
    public boolean isActive();
    public void setActive(boolean active);



    /**
     * Flag to indicate if this user is currently logged in. If 
     * this flag is true, then getLoginDate() should return the
     * date the user logged in.  If this flag is false, then
     * getLoginDate() should return null.
     */
    public boolean isLoggedIn();
    public void setLoggedIn(boolean loggedIn);

    /**
     * Flag to indicate if this user is available online. An online
     * user may be able to interact with the system (and other users).
     *
     * <p>
     * NOTE: This flag may (should) be set by the user to indicate
     * his/her online presence. 
     * </p>
     *
     * <p>
     * NOTE: If this flag is set to false, the user's presence may
     * be one of many other states such as busy, away, etc. and does
     * not necessarily indicate that the user is offline.
     * </p>
     */
    public boolean isOnline();
    public void setOnline(boolean online);

    /**
     * The date this user account was created.
     */
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);

    /**
     * The date this user account was retired.
     */
    public Date getRetiredDate();
    public void setRetiredDate(Date retiredDate);

    /**
     * The login date of this user's current session.  If the user 
     * is not online
     */
    public Date getLoginDate();
    public void setLoginDate(Date loginDate);

    /**
     * The date the user last logged in prior to his or her current session.
     */
    public Date getLastLoginDate();
    public void setLastLoginDate(Date lastLoginDate);

    /**
     * The date this user record was last updated.
     */
    public Date getLastUpdated();
    public void setLastUpdated(Date lastUpdated);
}
