package com.linuxtek.kona.app.core.service;

import com.linuxtek.kona.app.core.model.KGeoLocation;
import com.linuxtek.kona.remote.service.KService;


public interface KGeocodingService extends KService {

	public KGeoLocation geocode(String address);

	public KGeoLocation geocode(double latitude, double longitude);
    
	public KGeoLocation findNearestIntersection(String address);
    
	public KGeoLocation findNearestIntersection(double latitude, double longitude);
}
