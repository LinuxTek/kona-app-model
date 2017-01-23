package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseRedirect implements KRedirect {
    private Long id;
    private Long shortUrlId;
    private Long promoId;
    private String requestUrl;
    private String redirectUrl;
    private String hostname;
    private String browser;
    private String referer;
    private String locale;
    private String country;
    private String city;
    private String region;
    private Double latitiude;
    private Double longitude;
    private Date requestedDate;
    private Date redirectedDate;
    private Date createdDate;
    private Date updatedDate;

    private static final long serialVersionUID = 1L;

    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public Long getShortUrlId() {
        return shortUrlId;
    }

    @Override
	public void setShortUrlId(Long shortUrlId) {
        this.shortUrlId = shortUrlId;
    }

    @Override
	public Long getPromoId() {
        return promoId;
    }

    @Override
	public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    @Override
	public String getRequestUrl() {
        return requestUrl;
    }

    @Override
	public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    @Override
	public String getRedirectUrl() {
        return redirectUrl;
    }

    @Override
	public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    @Override
	public String getHostname() {
        return hostname;
    }

    @Override
	public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    @Override
	public String getBrowser() {
        return browser;
    }

    @Override
	public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    @Override
	public String getReferer() {
        return referer;
    }

    @Override
	public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    @Override
	public String getLocale() {
        return locale;
    }

    @Override
	public void setLocale(String locale) {
        this.locale = locale == null ? null : locale.trim();
    }

    @Override
	public String getCountry() {
        return country;
    }

    @Override
	public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    @Override
	public String getCity() {
        return city;
    }

    @Override
	public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    @Override
	public String getRegion() {
        return region;
    }

    @Override
	public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    @Override
	public Double getLatitiude() {
        return latitiude;
    }

    @Override
	public void setLatitiude(Double latitiude) {
        this.latitiude = latitiude;
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
	public Date getRequestedDate() {
        return requestedDate;
    }

    @Override
	public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    @Override
	public Date getRedirectedDate() {
        return redirectedDate;
    }

    @Override
	public void setRedirectedDate(Date redirectedDate) {
        this.redirectedDate = redirectedDate;
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
        sb.append(", shortUrlId=").append(shortUrlId);
        sb.append(", promoId=").append(promoId);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", redirectUrl=").append(redirectUrl);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", referer=").append(referer);
        sb.append(", locale=").append(locale);
        sb.append(", country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", region=").append(region);
        sb.append(", latitiude=").append(latitiude);
        sb.append(", longitude=").append(longitude);
        sb.append(", requestedDate=").append(requestedDate);
        sb.append(", redirectedDate=").append(redirectedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
