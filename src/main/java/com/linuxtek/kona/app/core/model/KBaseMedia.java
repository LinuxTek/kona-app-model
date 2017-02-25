package com.linuxtek.kona.app.core.model;

import java.io.Serializable;
import java.util.List;

public class KBaseMedia implements Serializable, KMedia {
    private static final long serialVersionUID = 1L;

    private String url;
    private String contentType;
    private Integer width;
    private Integer height;
    private Long size;
	

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }


    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }


    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }


    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }


    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }


    /**
     * @return the size
     */
    public Long getSize() {
        return size;
    }


    /**
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", url=").append(url);
        sb.append(", contentType=").append(contentType);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", size=").append(size);
        sb.append("]");
        return sb.toString();
    }


}

