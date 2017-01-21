package com.linuxtek.kona.app.sales.entity;

import java.util.Date;

public class KBasePurchase implements KPurchase {

	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.parent_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.account_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.user_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.product_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.app_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.promo_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long promoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.partner_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long partnerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.campaign_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long campaignId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.payment_type_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long paymentTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.kind
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String kind;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.invoice_no
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String invoiceNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.auto_renew
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean autoRenew;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.enabled
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean enabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.created_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.expiration_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date expirationDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_purchase.last_updated
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date updatedDate;

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getId()
	 */
    @Override
	public Long getId() {
        return id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setId(java.lang.Long)
	 */
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getParentId()
	 */
    @Override
	public Long getParentId() {
        return parentId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setParentId(java.lang.Long)
	 */
    @Override
	public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getAccountId()
	 */
    @Override
	public Long getAccountId() {
        return accountId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setAccountId(java.lang.Long)
	 */
    @Override
	public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getUserId()
	 */
    @Override
	public Long getUserId() {
        return userId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setUserId(java.lang.Long)
	 */
    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getProductId()
	 */
    @Override
	public Long getProductId() {
        return productId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setProductId(java.lang.Long)
	 */
    @Override
	public void setProductId(Long productId) {
        this.productId = productId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getAppId()
	 */
    @Override
	public Long getAppId() {
        return appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setAppId(java.lang.Long)
	 */
    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getPromoId()
	 */
    @Override
	public Long getPromoId() {
        return promoId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setPromoId(java.lang.Long)
	 */
    @Override
	public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getPartnerId()
	 */
    @Override
	public Long getPartnerId() {
        return partnerId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setPartnerId(java.lang.Long)
	 */
    @Override
	public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getCampaignId()
	 */
    @Override
	public Long getCampaignId() {
        return campaignId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setCampaignId(java.lang.Long)
	 */
    @Override
	public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getPaymentTypeId()
	 */
    @Override
	public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setPaymentTypeId(java.lang.Long)
	 */
    @Override
	public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getKind()
	 */
    @Override
	public String getKind() {
        return kind;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setKind(java.lang.String)
	 */
    @Override
	public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getInvoiceNo()
	 */
    @Override
	public String getInvoiceNo() {
        return invoiceNo;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setInvoiceNo(java.lang.String)
	 */
    @Override
	public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#isAutoRenew()
	 */
    @Override
	public boolean isAutoRenew() {
        return autoRenew;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setAutoRenew(boolean)
	 */
    @Override
	public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#isEnabled()
	 */
    @Override
	public boolean isEnabled() {
        return enabled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setEnabled(boolean)
	 */
    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getCreatedDate()
	 */
    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setCreatedDate(java.util.Date)
	 */
    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getExpirationDate()
	 */
    @Override
	public Date getExpirationDate() {
        return expirationDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setExpirationDate(java.util.Date)
	 */
    @Override
	public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#getUpdatedDate()
	 */
    @Override
	public Date getUpdatedDate() {
        return updatedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPurchase#setUpdatedDate(java.util.Date)
	 */
    @Override
	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_purchase
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", accountId=").append(accountId);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", appId=").append(appId);
        sb.append(", promoId=").append(promoId);
        sb.append(", partnerId=").append(partnerId);
        sb.append(", campaignId=").append(campaignId);
        sb.append(", paymentTypeId=").append(paymentTypeId);
        sb.append(", kind=").append(kind);
        sb.append(", invoiceNo=").append(invoiceNo);
        sb.append(", autoRenew=").append(autoRenew);
        sb.append(", enabled=").append(enabled);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
