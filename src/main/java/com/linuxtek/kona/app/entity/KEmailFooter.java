package com.linuxtek.kona.app.entity;

import java.io.Serializable;

public class KEmailFooter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String copyright; 
	String companyName; 
	String street1; 
	String street2; 
	String city; 
	String state; 
	String postalCode;
	String country;
	String permissionReminder;
	String unsubscribeUrl;

	public KEmailFooter() {
	}
    
	public KEmailFooter(String copyright, String companyName, String street1, String street2, String city, 
			String state, String postalCode, String country) {
		this.copyright = copyright;
		this.companyName = companyName;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}
    
	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPermissionReminder() {
		return permissionReminder;
	}

	public void setPermissionReminder(String permissionReminder) {
		this.permissionReminder = permissionReminder;
	}

	public String getUnsubscribeUrl() {
		return unsubscribeUrl;
	}

	public void setUnsubscribeUrl(String unsubscribeUrl) {
		this.unsubscribeUrl = unsubscribeUrl;
	}


}
