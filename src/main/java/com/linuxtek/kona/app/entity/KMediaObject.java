/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * KMediaObject.
 */
    
public interface KMediaObject extends KEntityObject {
    private static final long serialVersionUID = 1L;
    
    public String getUid();
    public String getUrlPath();
    public Double getLatitude();
    public Double getLongitude();
    public Integer getFloor();
    public String getDescription();
}
