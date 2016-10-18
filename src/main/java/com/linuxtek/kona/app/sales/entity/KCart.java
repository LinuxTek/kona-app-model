package com.linuxtek.kona.app.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KCart extends KEntityObject {

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.id
	 *
	 * @return the value of cart.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.id
	 *
	 * @param id the value for cart.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setId(Long id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.app_id
	 *
	 * @return the value of cart.app_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getAppId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.app_id
	 *
	 * @param appId the value for cart.app_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAppId(Long appId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.user_id
	 *
	 * @return the value of cart.user_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getUserId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.user_id
	 *
	 * @param userId the value for cart.user_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setUserId(Long userId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.account_id
	 *
	 * @return the value of cart.account_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getAccountId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.account_id
	 *
	 * @param accountId the value for cart.account_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAccountId(Long accountId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.currency_id
	 *
	 * @return the value of cart.currency_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getCurrencyId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.currency_id
	 *
	 * @param currencyId the value for cart.currency_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCurrencyId(Long currencyId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.invoice_id
	 *
	 * @return the value of cart.invoice_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getInvoiceId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.invoice_id
	 *
	 * @param invoiceId the value for cart.invoice_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setInvoiceId(Long invoiceId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.session_id
	 *
	 * @return the value of cart.session_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getSessionId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.session_id
	 *
	 * @param sessionId the value for cart.session_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSessionId(String sessionId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.access_token
	 *
	 * @return the value of cart.access_token
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getAccessToken();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.access_token
	 *
	 * @param accessToken the value for cart.access_token
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setAccessToken(String accessToken);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.hostname
	 *
	 * @return the value of cart.hostname
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getHostname();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.hostname
	 *
	 * @param hostname the value for cart.hostname
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setHostname(String hostname);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.browser
	 *
	 * @return the value of cart.browser
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getBrowser();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.browser
	 *
	 * @param browser the value for cart.browser
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setBrowser(String browser);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.latitude
	 *
	 * @return the value of cart.latitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Double getLatitude();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.latitude
	 *
	 * @param latitude the value for cart.latitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLatitude(Double latitude);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.longitude
	 *
	 * @return the value of cart.longitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Double getLongitude();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.longitude
	 *
	 * @param longitude the value for cart.longitude
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLongitude(Double longitude);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.description
	 *
	 * @return the value of cart.description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getDescription();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.description
	 *
	 * @param description the value for cart.description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDescription(String description);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.notes
	 *
	 * @return the value of cart.notes
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getNotes();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.notes
	 *
	 * @param notes the value for cart.notes
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setNotes(String notes);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.default_card_last4
	 *
	 * @return the value of cart.default_card_last4
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getDefaultCardLast4();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.default_card_last4
	 *
	 * @param defaultCardLast4 the value for cart.default_card_last4
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDefaultCardLast4(String defaultCardLast4);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.subtotal
	 *
	 * @return the value of cart.subtotal
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getSubtotal();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.subtotal
	 *
	 * @param subtotal the value for cart.subtotal
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSubtotal(BigDecimal subtotal);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.discount
	 *
	 * @return the value of cart.discount
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getDiscount();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.discount
	 *
	 * @param discount the value for cart.discount
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDiscount(BigDecimal discount);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.shipping
	 *
	 * @return the value of cart.shipping
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getShipping();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.shipping
	 *
	 * @param shipping the value for cart.shipping
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setShipping(BigDecimal shipping);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.tax
	 *
	 * @return the value of cart.tax
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getTax();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.tax
	 *
	 * @param tax the value for cart.tax
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setTax(BigDecimal tax);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.total
	 *
	 * @return the value of cart.total
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getTotal();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.total
	 *
	 * @param total the value for cart.total
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setTotal(BigDecimal total);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.checked_out
	 *
	 * @return the value of cart.checked_out
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isCheckedOut();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.checked_out
	 *
	 * @param checkedOut the value for cart.checked_out
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCheckedOut(boolean checkedOut);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.invoiced
	 *
	 * @return the value of cart.invoiced
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isInvoiced();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.invoiced
	 *
	 * @param invoiced the value for cart.invoiced
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setInvoiced(boolean invoiced);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.expired
	 *
	 * @return the value of cart.expired
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	boolean isExpired();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.expired
	 *
	 * @param expired the value for cart.expired
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setExpired(boolean expired);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.created_date
	 *
	 * @return the value of cart.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getCreatedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.created_date
	 *
	 * @param createdDate the value for cart.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCreatedDate(Date createdDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.expired_date
	 *
	 * @return the value of cart.expired_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getExpiredDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.expired_date
	 *
	 * @param expiredDate the value for cart.expired_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setExpiredDate(Date expiredDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.checked_out_date
	 *
	 * @return the value of cart.checked_out_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getCheckedOutDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.checked_out_date
	 *
	 * @param checkedOutDate the value for cart.checked_out_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCheckedOutDate(Date checkedOutDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.invoiced_date
	 *
	 * @return the value of cart.invoiced_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getInvoicedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.invoiced_date
	 *
	 * @param invoicedDate the value for cart.invoiced_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setInvoicedDate(Date invoicedDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column cart.last_updated
	 *
	 * @return the value of cart.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getLastUpdated();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column cart.last_updated
	 *
	 * @param lastUpdated the value for cart.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setLastUpdated(Date lastUpdated);

}