package com.linuxtek.kona.app.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePayment implements KPayment {

	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.app_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.type_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.status_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long statusId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.currency_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long currencyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.user_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.account_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.invoice_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long invoiceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.promo_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long promoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.session_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String sessionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.access_token
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String accessToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.hostname
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String hostname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.browser
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String browser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.latitude
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Double latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.longitude
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Double longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.card_token
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String cardToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.card_last4
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String cardLast4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.amount
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private BigDecimal amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.amount_refunded
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private BigDecimal amountRefunded;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.processor_ref
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String processorRef;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.processor_error
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String processorError;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.processor_fee
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private BigDecimal processorFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.paid
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean paid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.refunded
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean refunded;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.disputed
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean disputed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.failed
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean failed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.created_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.paid_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date paidDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.disputed_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date disputedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.refunded_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date refundedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.failed_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date failedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.last_updated
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.processor_receipt
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String processorReceipt;

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getId()
	 */
    @Override
	public Long getId() {
        return id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setId(java.lang.Long)
	 */
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getAppId()
	 */
    @Override
	public Long getAppId() {
        return appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setAppId(java.lang.Long)
	 */
    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getTypeId()
	 */
    @Override
	public Long getTypeId() {
        return typeId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setTypeId(java.lang.Long)
	 */
    @Override
	public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getStatusId()
	 */
    @Override
	public Long getStatusId() {
        return statusId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setStatusId(java.lang.Long)
	 */
    @Override
	public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getCurrencyId()
	 */
    @Override
	public Long getCurrencyId() {
        return currencyId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setCurrencyId(java.lang.Long)
	 */
    @Override
	public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getUserId()
	 */
    @Override
	public Long getUserId() {
        return userId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setUserId(java.lang.Long)
	 */
    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getAccountId()
	 */
    @Override
	public Long getAccountId() {
        return accountId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setAccountId(java.lang.Long)
	 */
    @Override
	public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getInvoiceId()
	 */
    @Override
	public Long getInvoiceId() {
        return invoiceId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setInvoiceId(java.lang.Long)
	 */
    @Override
	public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getPromoId()
	 */
    @Override
	public Long getPromoId() {
        return promoId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setPromoId(java.lang.Long)
	 */
    @Override
	public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getSessionId()
	 */
    @Override
	public String getSessionId() {
        return sessionId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setSessionId(java.lang.String)
	 */
    @Override
	public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getAccessToken()
	 */
    @Override
	public String getAccessToken() {
        return accessToken;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setAccessToken(java.lang.String)
	 */
    @Override
	public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getHostname()
	 */
    @Override
	public String getHostname() {
        return hostname;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setHostname(java.lang.String)
	 */
    @Override
	public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getBrowser()
	 */
    @Override
	public String getBrowser() {
        return browser;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setBrowser(java.lang.String)
	 */
    @Override
	public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getLatitude()
	 */
    @Override
	public Double getLatitude() {
        return latitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setLatitude(java.lang.Double)
	 */
    @Override
	public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getLongitude()
	 */
    @Override
	public Double getLongitude() {
        return longitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setLongitude(java.lang.Double)
	 */
    @Override
	public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getCardToken()
	 */
    @Override
	public String getCardToken() {
        return cardToken;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setCardToken(java.lang.String)
	 */
    @Override
	public void setCardToken(String cardToken) {
        this.cardToken = cardToken == null ? null : cardToken.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getCardLast4()
	 */
    @Override
	public String getCardLast4() {
        return cardLast4;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setCardLast4(java.lang.String)
	 */
    @Override
	public void setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4 == null ? null : cardLast4.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getAmount()
	 */
    @Override
	public BigDecimal getAmount() {
        return amount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setAmount(java.math.BigDecimal)
	 */
    @Override
	public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getAmountRefunded()
	 */
    @Override
	public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setAmountRefunded(java.math.BigDecimal)
	 */
    @Override
	public void setAmountRefunded(BigDecimal amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getProcessorRef()
	 */
    @Override
	public String getProcessorRef() {
        return processorRef;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setProcessorRef(java.lang.String)
	 */
    @Override
	public void setProcessorRef(String processorRef) {
        this.processorRef = processorRef == null ? null : processorRef.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getProcessorError()
	 */
    @Override
	public String getProcessorError() {
        return processorError;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setProcessorError(java.lang.String)
	 */
    @Override
	public void setProcessorError(String processorError) {
        this.processorError = processorError == null ? null : processorError.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getProcessorFee()
	 */
    @Override
	public BigDecimal getProcessorFee() {
        return processorFee;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setProcessorFee(java.math.BigDecimal)
	 */
    @Override
	public void setProcessorFee(BigDecimal processorFee) {
        this.processorFee = processorFee;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#isPaid()
	 */
    @Override
	public boolean isPaid() {
        return paid;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setPaid(boolean)
	 */
    @Override
	public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#isRefunded()
	 */
    @Override
	public boolean isRefunded() {
        return refunded;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setRefunded(boolean)
	 */
    @Override
	public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#isDisputed()
	 */
    @Override
	public boolean isDisputed() {
        return disputed;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setDisputed(boolean)
	 */
    @Override
	public void setDisputed(boolean disputed) {
        this.disputed = disputed;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#isFailed()
	 */
    @Override
	public boolean isFailed() {
        return failed;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setFailed(boolean)
	 */
    @Override
	public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getCreatedDate()
	 */
    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setCreatedDate(java.util.Date)
	 */
    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getPaidDate()
	 */
    @Override
	public Date getPaidDate() {
        return paidDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setPaidDate(java.util.Date)
	 */
    @Override
	public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getDisputedDate()
	 */
    @Override
	public Date getDisputedDate() {
        return disputedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setDisputedDate(java.util.Date)
	 */
    @Override
	public void setDisputedDate(Date disputedDate) {
        this.disputedDate = disputedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getRefundedDate()
	 */
    @Override
	public Date getRefundedDate() {
        return refundedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setRefundedDate(java.util.Date)
	 */
    @Override
	public void setRefundedDate(Date refundedDate) {
        this.refundedDate = refundedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getFailedDate()
	 */
    @Override
	public Date getFailedDate() {
        return failedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setFailedDate(java.util.Date)
	 */
    @Override
	public void setFailedDate(Date failedDate) {
        this.failedDate = failedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getUpdatedDate()
	 */
    @Override
	public Date getUpdatedDate() {
        return updatedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setUpdatedDate(java.util.Date)
	 */
    @Override
	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#getProcessorReceipt()
	 */
    @Override
	public String getProcessorReceipt() {
        return processorReceipt;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPayment#setProcessorReceipt(java.lang.String)
	 */
    @Override
	public void setProcessorReceipt(String processorReceipt) {
        this.processorReceipt = processorReceipt == null ? null : processorReceipt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
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
        sb.append(", appId=").append(appId);
        sb.append(", typeId=").append(typeId);
        sb.append(", statusId=").append(statusId);
        sb.append(", currencyId=").append(currencyId);
        sb.append(", userId=").append(userId);
        sb.append(", accountId=").append(accountId);
        sb.append(", invoiceId=").append(invoiceId);
        sb.append(", promoId=").append(promoId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", cardToken=").append(cardToken);
        sb.append(", cardLast4=").append(cardLast4);
        sb.append(", amount=").append(amount);
        sb.append(", amountRefunded=").append(amountRefunded);
        sb.append(", processorRef=").append(processorRef);
        sb.append(", processorError=").append(processorError);
        sb.append(", processorFee=").append(processorFee);
        sb.append(", paid=").append(paid);
        sb.append(", refunded=").append(refunded);
        sb.append(", disputed=").append(disputed);
        sb.append(", failed=").append(failed);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", paidDate=").append(paidDate);
        sb.append(", disputedDate=").append(disputedDate);
        sb.append(", refundedDate=").append(refundedDate);
        sb.append(", failedDate=").append(failedDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", processorReceipt=").append(processorReceipt);
        sb.append("]");
        return sb.toString();
    }
}
