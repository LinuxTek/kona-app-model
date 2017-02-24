package com.linuxtek.kona.app.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.linuxtek.kona.app.core.model.KBaseGeoLocation;
import com.linuxtek.kona.app.core.model.KGeoLocation;


public abstract class KAbstractGeocodingService implements KGeocodingService {
    private static Logger logger = LoggerFactory.getLogger(KAbstractGeocodingService.class);


    private GeoApiContext googleGeoApiContext = null;

    // ----------------------------------------------------------------------------

    protected abstract String getGoogleApiServerKey();

    // ----------------------------------------------------------------------------

    private GeoApiContext getGoogleContext() {

        if (googleGeoApiContext == null) {
            String apiKey = getGoogleApiServerKey();
            googleGeoApiContext = new GeoApiContext().setApiKey(apiKey);
        }

        return googleGeoApiContext;
    }

    // ----------------------------------------------------------------------------

    @Override
    public KGeoLocation geocode(String address) {
        try {
            // address = "1600 Amphitheatre Parkway Mountain View, CA 94043"
            GeocodingResult[] results =  GeocodingApi.geocode(getGoogleContext(), address).await();
            return process(results[0]);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // ----------------------------------------------------------------------------

    @Override
    public KGeoLocation geocode(double latitude, double longitude) {
        try {
            GeocodingResult[] results = GeocodingApi.newRequest(getGoogleContext())
                    .latlng(new LatLng(latitude, longitude)).await();
            return process(results[0]);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // ----------------------------------------------------------------------------

    private KGeoLocation process(GeocodingResult result) {
        KGeoLocation location = new KBaseGeoLocation();

        Geometry geometry = result.geometry;


        if (geometry == null) return null;

        LatLng latlng = geometry.location;

        if (latlng == null) return null;

        location.setAddress(result.formattedAddress);
        location.setLatitude(latlng.lat);
        location.setLongitude(latlng.lng);

        return location;
    }

    // ----------------------------------------------------------------------------

    @Override
    public KGeoLocation findNearestIntersection(String address) {
        KGeoLocation location = geocode(address);
        if (location == null) return null;
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        return findNearestIntersection(latitude, longitude);
    }

    // ----------------------------------------------------------------------------

    @Override
    public KGeoLocation findNearestIntersection(double latitude, double longitude) {
        /*
        try {
			Intersection intersection = getGeoNamesWebService().findNearestIntersection(latitude, longitude);

			Map<String,Object> map = new HashMap<String,Object>();
            map.put("distance", intersection.getDistance());
            map.put("street1", intersection.getStreet1());
            map.put("street2", intersection.getStreet2());
            map.put("latitude", intersection.getLatitude());
            map.put("longitude", intersection.getLongitude());
            map.put("postalCode", intersection.getPostalCode());

            return map;
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
		}
         */
        return null;
    }
}
