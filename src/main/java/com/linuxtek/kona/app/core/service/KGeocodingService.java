package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.model.KGeoLocation;
import com.linuxtek.kona.app.core.model.KPlace;
import com.linuxtek.kona.remote.service.KService;


public interface KGeocodingService extends KService {

	KGeoLocation geocode(String address);

	KGeoLocation geocode(double latitude, double longitude);
    
	KGeoLocation findNearestIntersection(String address);
    
	KGeoLocation findNearestIntersection(double latitude, double longitude);


    KPlace getPlace(String placeId);

	List<KPlace> findPlaces(String query);

    KPlace findPlaceDetail(String query);
}
