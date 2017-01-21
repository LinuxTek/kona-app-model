package com.linuxtek.kona.app.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KInvoiceItem extends KEntityObject {

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.id
	 *
	 * @return the value of invoice_item.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.id
	 *
	 * @param id the value for invoice_item.id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setId(Long id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.invoice_id
	 *
	 * @return the value of invoice_item.invoice_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getInvoiceId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.invoice_id
	 *
	 * @param invoiceId the value for invoice_item.invoice_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setInvoiceId(Long invoiceId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.product_id
	 *
	 * @return the value of invoice_item.product_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getProductId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.product_id
	 *
	 * @param productId the value for invoice_item.product_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setProductId(Long productId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.promo_id
	 *
	 * @return the value of invoice_item.promo_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Long getPromoId();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.promo_id
	 *
	 * @param promoId the value for invoice_item.promo_id
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setPromoId(Long promoId);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.description
	 *
	 * @return the value of invoice_item.description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getDescription();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.description
	 *
	 * @param description the value for invoice_item.description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDescription(String description);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.discount_description
	 *
	 * @return the value of invoice_item.discount_description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	String getDiscountDescription();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.discount_description
	 *
	 * @param discountDescription the value for invoice_item.discount_description
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDiscountDescription(String discountDescription);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.unit_price
	 *
	 * @return the value of invoice_item.unit_price
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getUnitPrice();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.unit_price
	 *
	 * @param unitPrice the value for invoice_item.unit_price
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setUnitPrice(BigDecimal unitPrice);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.setup_fee
	 *
	 * @return the value of invoice_item.setup_fee
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getSetupFee();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.setup_fee
	 *
	 * @param setupFee the value for invoice_item.setup_fee
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSetupFee(BigDecimal setupFee);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.quantity
	 *
	 * @return the value of invoice_item.quantity
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Integer getQuantity();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.quantity
	 *
	 * @param quantity the value for invoice_item.quantity
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setQuantity(Integer quantity);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.subtotal
	 *
	 * @return the value of invoice_item.subtotal
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getSubtotal();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.subtotal
	 *
	 * @param subtotal the value for invoice_item.subtotal
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSubtotal(BigDecimal subtotal);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.discount
	 *
	 * @return the value of invoice_item.discount
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getDiscount();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.discount
	 *
	 * @param discount the value for invoice_item.discount
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setDiscount(BigDecimal discount);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.total
	 *
	 * @return the value of invoice_item.total
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	BigDecimal getTotal();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.total
	 *
	 * @param total the value for invoice_item.total
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setTotal(BigDecimal total);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.subscription_start_date
	 *
	 * @return the value of invoice_item.subscription_start_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getSubscriptionStartDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.subscription_start_date
	 *
	 * @param subscriptionStartDate the value for invoice_item.subscription_start_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSubscriptionStartDate(Date subscriptionStartDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.subscription_end_date
	 *
	 * @return the value of invoice_item.subscription_end_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getSubscriptionEndDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.subscription_end_date
	 *
	 * @param subscriptionEndDate the value for invoice_item.subscription_end_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setSubscriptionEndDate(Date subscriptionEndDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.created_date
	 *
	 * @return the value of invoice_item.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getCreatedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.created_date
	 *
	 * @param createdDate the value for invoice_item.created_date
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setCreatedDate(Date createdDate);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column invoice_item.last_updated
	 *
	 * @return the value of invoice_item.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	Date getUpdatedDate();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column invoice_item.last_updated
	 *
	 * @param updatedDate the value for invoice_item.last_updated
	 *
	 * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
	 */
	void setUpdatedDate(Date updatedDate);

}