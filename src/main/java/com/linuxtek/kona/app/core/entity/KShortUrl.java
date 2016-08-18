package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KShortUrl extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	Long getUserId();

	void setUserId(Long userId);

	Long getPromoId();

	void setPromoId(Long promoId);

	Long getPartnerId();

	void setPartnerId(Long partnerId);

	String getDomain();

	void setDomain(String domain);

	String getPath();

	void setPath(String path);

	String getShortUrl();

	void setShortUrl(String shortUrl);

	String getLongUrl();

	void setLongUrl(String longUrl);

	String getDescription();

	void setDescription(String description);

	boolean isScript();

	void setScript(boolean script);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getExpiredDate();

	void setExpiredDate(Date expiredDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}