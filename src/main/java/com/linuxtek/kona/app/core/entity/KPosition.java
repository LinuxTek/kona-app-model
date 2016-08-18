package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KPosition extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	Long getUserId();

	void setUserId(Long userId);

	Long getSampleNo();

	void setSampleNo(Long sampleNo);

	String getNetwork();

	void setNetwork(String network);

	Integer getBattery();

	void setBattery(Integer battery);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

	Integer getFloor();

	void setFloor(Integer floor);

	Double getAltitude();

	void setAltitude(Double altitude);

	Double getAccuracy();

	void setAccuracy(Double accuracy);

	Double getAltitudeAccuracy();

	void setAltitudeAccuracy(Double altitudeAccuracy);

	Double getHeading();

	void setHeading(Double heading);

	Double getSpeed();

	void setSpeed(Double speed);

	Long getTimestamp();

	void setTimestamp(Long timestamp);

	String getErrorCode();

	void setErrorCode(String errorCode);

	String getErrorMessage();

	void setErrorMessage(String errorMessage);

	Date getErrorDate();

	void setErrorDate(Date errorDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}