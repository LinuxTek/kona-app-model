package com.linuxtek.kona.app.core.model;

import java.io.Serializable;

public class KBaseGeoLocation implements Serializable, KGeoLocation {
    private static final long serialVersionUID = 1L;

    private String address;
    private double latitude;
    private double longitude;
    
	
	/* (non-Javadoc)
     * @see com.linuxtek.kona.app.core.entity.KGeoLocation#getAddress()
     */
    @Override
    public String getAddress() {
        return address;
    }


    /* (non-Javadoc)
     * @see com.linuxtek.kona.app.core.entity.KGeoLocation#setAddress(java.lang.String)
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    /* (non-Javadoc)
     * @see com.linuxtek.kona.app.core.entity.KGeoLocation#getLatitude()
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /* (non-Javadoc)
     * @see com.linuxtek.kona.app.core.entity.KGeoLocation#setLatitude(double)
     */
    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /* (non-Javadoc)
     * @see com.linuxtek.kona.app.core.entity.KGeoLocation#getLongitude()
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /* (non-Javadoc)
     * @see com.linuxtek.kona.app.core.entity.KGeoLocation#setLongitude(double)
     */
    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", address=").append(address);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append("]");
        return sb.toString();
    }


}

