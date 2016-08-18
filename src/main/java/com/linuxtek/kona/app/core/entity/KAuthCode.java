package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KAuthCode extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getTypeId();

	void setTypeId(Long typeId);

	Long getAppId();

	void setAppId(Long appId);

	Long getUserId();

	void setUserId(Long userId);

	String getCode();

	void setCode(String code);

	boolean isValid();

	void setValid(boolean valid);

	Integer getUseCount();

	void setUseCount(Integer useCount);

	Integer getMaxUseCount();

	void setMaxUseCount(Integer maxUseCount);

	Date getExpirationDate();

	void setExpirationDate(Date expirationDate);

	Date getLastAccessedDate();

	void setLastAccessedDate(Date lastAccessedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}
