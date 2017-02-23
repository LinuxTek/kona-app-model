package com.linuxtek.kona.app.comm.model;

import java.io.Serializable;

public class KEmailMedia implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String contentType;
    private byte[] data;
    
    public KEmailMedia() {
        
    }
    
    public KEmailMedia(String name, String contentType, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
