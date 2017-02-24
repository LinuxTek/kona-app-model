package com.linuxtek.kona.app.core.model;

public interface KGeoLocation {

    /**
     * @return the address
     */
    String getAddress();

    /**
     * @param address the address to set
     */
    void setAddress(String address);

    /**
     * @return the latitude
     */
    double getLatitude();

    /**
     * @param latitude the latitude to set
     */
    void setLatitude(double latitude);

    /**
     * @return the longitude
     */
    double getLongitude();

    /**
     * @param longitude the longitude to set
     */
    void setLongitude(double longitude);

}
