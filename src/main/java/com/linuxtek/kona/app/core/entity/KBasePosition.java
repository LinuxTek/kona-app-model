/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBasePosition.
 */
public class KBasePosition implements KPosition {
	private static final long serialVersionUID = 1L;
	
    private Long id;
    private Long appId;
    private Long userId;
    private Long sampleNo;
    private String network;
    private Integer battery;
    private Double latitude;
    private Double longitude;
    private Integer indoorFloor;
    private String indoorDetail;
    private Double altitude;
    private Double accuracy;
    private Double altitudeAccuracy;
    private Double heading;
    private Double speed;
    private Long timestamp;
    private String errorCode;
    private String errorMessage;
    private Date errorDate;
    private Date createdDate;
    private Date updatedDate;

    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
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
	public Long getSampleNo() {
        return sampleNo;
    }

    @Override
	public void setSampleNo(Long sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Override
	public String getNetwork() {
        return network;
    }

    @Override
	public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    @Override
	public Integer getBattery() {
        return battery;
    }

    @Override
	public void setBattery(Integer battery) {
        this.battery = battery;
    }

    @Override
	public Double getLatitude() {
        return latitude;
    }

    @Override
	public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
	public Double getLongitude() {
        return longitude;
    }

    @Override
	public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
	public Integer getIndoorFloor() {
        return indoorFloor;
    }

    @Override
	public void setIndoorFloor(Integer indoorFloor) {
        this.indoorFloor = indoorFloor;
    }
    
    @Override
	public String getIndoorDetail() {
        return indoorDetail;
    }
    
    @Override
	public void setIndoorDetail(String indoorDetail) {
        this.indoorDetail = indoorDetail;
    }

    @Override
	public Double getAltitude() {
        return altitude;
    }

    @Override
	public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    @Override
	public Double getAccuracy() {
        return accuracy;
    }

    @Override
	public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    @Override
	public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    @Override
	public void setAltitudeAccuracy(Double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    @Override
	public Double getHeading() {
        return heading;
    }

    @Override
	public void setHeading(Double heading) {
        this.heading = heading;
    }

    @Override
	public Double getSpeed() {
        return speed;
    }

    @Override
	public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
	public Long getTimestamp() {
        return timestamp;
    }

    @Override
	public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
	public String getErrorCode() {
        return errorCode;
    }

    @Override
	public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    @Override
	public String getErrorMessage() {
        return errorMessage;
    }
    
    @Override
	public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    @Override
	public Date getErrorDate() {
        return errorDate;
    }

    @Override
	public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
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
	public Date getUpdatedDate() {
        return updatedDate;
    }
    
    @Override
	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appId=").append(appId);
        sb.append(", userId=").append(userId);
        sb.append(", sampleNo=").append(sampleNo);
        sb.append(", network=").append(network);
        sb.append(", battery=").append(battery);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", indoorFloor=").append(indoorFloor);
        sb.append(", indoorDetail=").append(indoorDetail);
        sb.append(", altitude=").append(altitude);
        sb.append(", accuracy=").append(accuracy);
        sb.append(", altitudeAccuracy=").append(altitudeAccuracy);
        sb.append(", heading=").append(heading);
        sb.append(", speed=").append(speed);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", errorCode=").append(errorCode);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", errorDate=").append(errorDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
