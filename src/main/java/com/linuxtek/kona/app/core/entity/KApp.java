/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * KApp.
 */
    
public interface KApp extends KEntityObject {

    /** 
     * Internal object identifier. Not exported or referenced externally.
     */
    Long getId(); 
    void setId(Long id);
    
    /** 
     * Unique id that can be exported or referenced externally.
     */
    String getUid(); 
    void setUid(String uid);

	Long getTypeId();
	void setTypeId(Long typeId);

	Long getUserId();
	void setUserId(Long userId);

	Long getLogoId();
	void setLogoId(Long logoId);

	String getLogoUrlPath();
	void setLogoUrlPath(String logoUrlPath);

	String getName();
	void setName(String name);

	String getDisplayName();
	void setDisplayName(String displayName);

	String getDescription();
	void setDescription(String description);

	String getAppUrl();
	void setAppUrl(String appUrl);

	String getCompanyName();
	void setCompanyName(String companyName);

	String getCompanyUrl();
	void setCompanyUrl(String companyUrl);

	String getPrivacyUrl();
	void setPrivacyUrl(String privacyUrl);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getRetiredDate();
	void setRetiredDate(Date retiredDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
