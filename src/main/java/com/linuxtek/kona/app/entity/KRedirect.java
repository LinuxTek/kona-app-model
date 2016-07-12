package com.linuxtek.kona.app.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KRedirect extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getShortUrlId();

	void setShortUrlId(Long shortUrlId);

	Long getPromoId();

	void setPromoId(Long promoId);

	String getRequestUrl();

	void setRequestUrl(String requestUrl);

	String getRedirectUrl();

	void setRedirectUrl(String redirectUrl);

	String getHostname();

	void setHostname(String hostname);

	String getBrowser();

	void setBrowser(String browser);

	String getReferer();

	void setReferer(String referer);

	String getLocale();

	void setLocale(String locale);

	String getCountry();

	void setCountry(String country);

	String getCity();

	void setCity(String city);

	String getRegion();

	void setRegion(String region);

	Double getLatitiude();

	void setLatitiude(Double latitiude);

	Double getLongitude();

	void setLongitude(Double longitude);

	Date getRequestDate();

	void setRequestDate(Date requestDate);

	Date getRedirectDate();

	void setRedirectDate(Date redirectDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}