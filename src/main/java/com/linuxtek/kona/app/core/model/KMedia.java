package com.linuxtek.kona.app.core.model;

public interface KMedia {
    public String getUrl();
    public void setUrl(String url);

    public String getContentType();
    public void setContentType(String contentType);

    public Integer getWidth();
    public void setWidth(Integer width);

    public Integer getHeight();
    public void setHeight(Integer height);

    public Long getSize();
    public void setSize(Long size);
}
