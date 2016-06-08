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

    /** 
     * The user's publicly visible identifier.
     */
    public String getUid(); 
    public void setUid(String uid);

    public Long getUserId(); 
    public void setUserId(Long userId);


    /**
     * The a reference to the user's photo.
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

}
