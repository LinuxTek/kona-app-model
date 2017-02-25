package com.linuxtek.kona.app.core.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.linuxtek.kona.app.core.model.KBaseGeoLocation;
import com.linuxtek.kona.app.core.model.KBaseMedia;
import com.linuxtek.kona.app.core.model.KBasePlace;
import com.linuxtek.kona.app.core.model.KGeoLocation;
import com.linuxtek.kona.app.core.model.KMedia;
import com.linuxtek.kona.app.core.model.KPlace;


public abstract class KAbstractGeocodingService implements KGeocodingService {
    private static Logger logger = LoggerFactory.getLogger(KAbstractGeocodingService.class);


    private GeoApiContext googleGeoApiContext = null;

    // ----------------------------------------------------------------------------

    protected abstract String getGoogleApiKey();

    // ----------------------------------------------------------------------------

    private GeoApiContext getGoogleContext() {

        if (googleGeoApiContext == null) {
            String apiKey = getGoogleApiKey();
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

    // ----------------------------------------------------------------------------

    @Override
    public KPlace getPlace(String placeId) {
        try {
            PlaceDetails placeDetails = PlacesApi.placeDetails(getGoogleContext(), placeId).await(); 
            return toPlace(placeDetails);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // ----------------------------------------------------------------------------
    private KPlace toPlace(PlaceDetails placeDetails) {
        KPlace place = new KBasePlace();
        place.setAddress(placeDetails.formattedAddress);
        place.setPhoneNumber(placeDetails.internationalPhoneNumber);
        
        if (placeDetails.geometry != null) {
            place.setLatitude(placeDetails.geometry.location.lat);
            place.setLongitude(placeDetails.geometry.location.lng);
        }
        
        if (placeDetails.icon != null) {
            place.setIconUrl(placeDetails.icon.toString());
        }
        
        if (placeDetails.url != null) {
            place.setGoogleUrl(placeDetails.url.toString());
        }

        if (placeDetails.website != null) {
            place.setPlaceUrl(placeDetails.website.toString());
        }

        place.setName(placeDetails.name);
        place.setUtcOffset(placeDetails.utcOffset); // in minutes
        place.setPlaceId(placeDetails.placeId);
        place.setRating(Double.valueOf(placeDetails.rating));


        if (placeDetails.photos != null) {
            List<KMedia> photos = new ArrayList<KMedia>();

            for (Photo photo : placeDetails.photos) {
                KBaseMedia media = new KBaseMedia();

                //https://developers.google.com/places/web-service/photos
                String url = "https://maps.googleapis.com/maps/api/place/photo" 
                        + "?photoreference=" + photo.photoReference 
                        + "&key=" + getGoogleApiKey();

                media.setUrl(url);
                media.setWidth(photo.width);
                media.setHeight(photo.height);

                photos.add(media);
            }

            place.setPhotos(photos);
        }

        // TODO:
        //placeDetails.openingHours

        return place;
    }

    // ----------------------------------------------------------------------------

    private KPlace toPlace(PlacesSearchResult result) {
        KPlace place = new KBasePlace();

        place.setAddress(result.formattedAddress);
        
        if (result.geometry != null) {
            place.setLatitude(result.geometry.location.lat);
            place.setLongitude(result.geometry.location.lng);
        }
        
        if (result.icon != null) {
            place.setIconUrl(result.icon.toString());
        }

        place.setName(result.name);
        place.setPlaceId(result.placeId);
        place.setRating(Double.valueOf(result.rating));

        if (result.photos != null) {
            List<KMedia> photos = new ArrayList<KMedia>();

            for (Photo photo : result.photos) {
                KBaseMedia media = new KBaseMedia();

                //https://developers.google.com/places/web-service/photos
                String url = "https://maps.googleapis.com/maps/api/place/photo" 
                        + "?photoreference=" + photo.photoReference 
                        + "&key=" + getGoogleApiKey();

                media.setUrl(url);
                media.setWidth(photo.width);
                media.setHeight(photo.height);

                photos.add(media);
            }

            place.setPhotos(photos);
        }


        // TODO:
        //placeDetails.openingHours

        return place;
    }

    // ----------------------------------------------------------------------------

    @Override
    public List<KPlace> findPlaces(String query) {
        try {

            PlacesSearchResponse response = PlacesApi.textSearchQuery(getGoogleContext(), query).await(); 

            PlacesSearchResult[] results = response.results;

            List<KPlace> places = new ArrayList<KPlace>();

            for (PlacesSearchResult result : results) {
                places.add(toPlace(result));
            }

            return places;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // ----------------------------------------------------------------------------
    
    @Override
    public KPlace findPlaceDetail(String query) {
        KPlace place = null;

        List<KPlace> places = findPlaces(query);

        if (places != null) {
            place = getPlace(places.get(0).getPlaceId());
        }

        return place;
    }

    // ----------------------------------------------------------------------------
}
