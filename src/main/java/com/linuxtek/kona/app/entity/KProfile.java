/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * User profile.
 */
    
public interface KProfile extends KEntityObject {
//    public enum Gender implements Serializable { MALE, FEMALE }

    /** 
     * Internal object identifier. Do not exported or referenced externally.
     */
    public Long getId(); 
    public void setId(Long id);


    public Long getUserId(); 
    public void setUserId(Long userId);

    
    /**
     * Reference to the user's photo.
     */
    public Long getPhotoId(); 
    public void setPhotoId(Long photoId);


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
}
