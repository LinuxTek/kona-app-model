package com.linuxtek.kona.app.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePreOrder implements KPreOrder {

	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.uid
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.app_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.partner_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long partnerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.product_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.campaign_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long campaignId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.payment_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long paymentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.ref_app_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long refAppId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.referred_by_user_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long referredByUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.amount
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private BigDecimal amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.reconciled
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean reconciled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.proxy_payment
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean proxyPayment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.processor
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String processor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.payment_description
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String paymentDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.payment_token
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String paymentToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.payment_card_last4
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String paymentCardLast4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.payment_ref
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String paymentRef;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.first_name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String firstName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.last_name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String lastName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.email
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.mobile_number
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String mobileNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipping_address1
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String shippingAddress1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipping_address2
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String shippingAddress2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipping_city
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String shippingCity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipping_state
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String shippingState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipping_postal_code
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String shippingPostalCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipping_country
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String shippingCountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.notes
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String notes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.hostname
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String hostname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.browser
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String browser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.created_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.shipped_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date shippedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pre_order.last_updated
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date lastUpdated;

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getId()
	 */
    @Override
	public Long getId() {
        return id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setId(java.lang.Long)
	 */
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getUid()
	 */
    @Override
	public String getUid() {
        return uid;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setUid(java.lang.String)
	 */
    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getAppId()
	 */
    @Override
	public Long getAppId() {
        return appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setAppId(java.lang.Long)
	 */
    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getPartnerId()
	 */
    @Override
	public Long getPartnerId() {
        return partnerId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setPartnerId(java.lang.Long)
	 */
    @Override
	public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getProductId()
	 */
    @Override
	public Long getProductId() {
        return productId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setProductId(java.lang.Long)
	 */
    @Override
	public void setProductId(Long productId) {
        this.productId = productId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getCampaignId()
	 */
    @Override
	public Long getCampaignId() {
        return campaignId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setCampaignId(java.lang.Long)
	 */
    @Override
	public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getPaymentId()
	 */
    @Override
	public Long getPaymentId() {
        return paymentId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setPaymentId(java.lang.Long)
	 */
    @Override
	public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getRefAppId()
	 */
    @Override
	public Long getRefAppId() {
        return refAppId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setRefAppId(java.lang.Long)
	 */
    @Override
	public void setRefAppId(Long refAppId) {
        this.refAppId = refAppId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getReferredByUserId()
	 */
    @Override
	public Long getReferredByUserId() {
        return referredByUserId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setReferredByUserId(java.lang.Long)
	 */
    @Override
	public void setReferredByUserId(Long referredByUserId) {
        this.referredByUserId = referredByUserId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getAmount()
	 */
    @Override
	public BigDecimal getAmount() {
        return amount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setAmount(java.math.BigDecimal)
	 */
    @Override
	public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#isReconciled()
	 */
    @Override
	public boolean isReconciled() {
        return reconciled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setReconciled(boolean)
	 */
    @Override
	public void setReconciled(boolean reconciled) {
        this.reconciled = reconciled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#isProxyPayment()
	 */
    @Override
	public boolean isProxyPayment() {
        return proxyPayment;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setProxyPayment(boolean)
	 */
    @Override
	public void setProxyPayment(boolean proxyPayment) {
        this.proxyPayment = proxyPayment;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getProcessor()
	 */
    @Override
	public String getProcessor() {
        return processor;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setProcessor(java.lang.String)
	 */
    @Override
	public void setProcessor(String processor) {
        this.processor = processor == null ? null : processor.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getPaymentDescription()
	 */
    @Override
	public String getPaymentDescription() {
        return paymentDescription;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setPaymentDescription(java.lang.String)
	 */
    @Override
	public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription == null ? null : paymentDescription.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getPaymentToken()
	 */
    @Override
	public String getPaymentToken() {
        return paymentToken;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setPaymentToken(java.lang.String)
	 */
    @Override
	public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken == null ? null : paymentToken.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getPaymentCardLast4()
	 */
    @Override
	public String getPaymentCardLast4() {
        return paymentCardLast4;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setPaymentCardLast4(java.lang.String)
	 */
    @Override
	public void setPaymentCardLast4(String paymentCardLast4) {
        this.paymentCardLast4 = paymentCardLast4 == null ? null : paymentCardLast4.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getPaymentRef()
	 */
    @Override
	public String getPaymentRef() {
        return paymentRef;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setPaymentRef(java.lang.String)
	 */
    @Override
	public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef == null ? null : paymentRef.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getFirstName()
	 */
    @Override
	public String getFirstName() {
        return firstName;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setFirstName(java.lang.String)
	 */
    @Override
	public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getLastName()
	 */
    @Override
	public String getLastName() {
        return lastName;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setLastName(java.lang.String)
	 */
    @Override
	public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getEmail()
	 */
    @Override
	public String getEmail() {
        return email;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setEmail(java.lang.String)
	 */
    @Override
	public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getMobileNumber()
	 */
    @Override
	public String getMobileNumber() {
        return mobileNumber;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setMobileNumber(java.lang.String)
	 */
    @Override
	public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippingAddress1()
	 */
    @Override
	public String getShippingAddress1() {
        return shippingAddress1;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippingAddress1(java.lang.String)
	 */
    @Override
	public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1 == null ? null : shippingAddress1.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippingAddress2()
	 */
    @Override
	public String getShippingAddress2() {
        return shippingAddress2;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippingAddress2(java.lang.String)
	 */
    @Override
	public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2 == null ? null : shippingAddress2.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippingCity()
	 */
    @Override
	public String getShippingCity() {
        return shippingCity;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippingCity(java.lang.String)
	 */
    @Override
	public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity == null ? null : shippingCity.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippingState()
	 */
    @Override
	public String getShippingState() {
        return shippingState;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippingState(java.lang.String)
	 */
    @Override
	public void setShippingState(String shippingState) {
        this.shippingState = shippingState == null ? null : shippingState.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippingPostalCode()
	 */
    @Override
	public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippingPostalCode(java.lang.String)
	 */
    @Override
	public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode == null ? null : shippingPostalCode.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippingCountry()
	 */
    @Override
	public String getShippingCountry() {
        return shippingCountry;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippingCountry(java.lang.String)
	 */
    @Override
	public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry == null ? null : shippingCountry.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getNotes()
	 */
    @Override
	public String getNotes() {
        return notes;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setNotes(java.lang.String)
	 */
    @Override
	public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getHostname()
	 */
    @Override
	public String getHostname() {
        return hostname;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setHostname(java.lang.String)
	 */
    @Override
	public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getBrowser()
	 */
    @Override
	public String getBrowser() {
        return browser;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setBrowser(java.lang.String)
	 */
    @Override
	public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getCreatedDate()
	 */
    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setCreatedDate(java.util.Date)
	 */
    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getShippedDate()
	 */
    @Override
	public Date getShippedDate() {
        return shippedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setShippedDate(java.util.Date)
	 */
    @Override
	public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#getLastUpdated()
	 */
    @Override
	public Date getLastUpdated() {
        return lastUpdated;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPreOrder#setLastUpdated(java.util.Date)
	 */
    @Override
	public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pre_order
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
        sb.append(", uid=").append(uid);
        sb.append(", appId=").append(appId);
        sb.append(", partnerId=").append(partnerId);
        sb.append(", productId=").append(productId);
        sb.append(", campaignId=").append(campaignId);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", refAppId=").append(refAppId);
        sb.append(", referredByUserId=").append(referredByUserId);
        sb.append(", amount=").append(amount);
        sb.append(", reconciled=").append(reconciled);
        sb.append(", proxyPayment=").append(proxyPayment);
        sb.append(", processor=").append(processor);
        sb.append(", paymentDescription=").append(paymentDescription);
        sb.append(", paymentToken=").append(paymentToken);
        sb.append(", paymentCardLast4=").append(paymentCardLast4);
        sb.append(", paymentRef=").append(paymentRef);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", shippingAddress1=").append(shippingAddress1);
        sb.append(", shippingAddress2=").append(shippingAddress2);
        sb.append(", shippingCity=").append(shippingCity);
        sb.append(", shippingState=").append(shippingState);
        sb.append(", shippingPostalCode=").append(shippingPostalCode);
        sb.append(", shippingCountry=").append(shippingCountry);
        sb.append(", notes=").append(notes);
        sb.append(", hostname=").append(hostname);
        sb.append(", browser=").append(browser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", shippedDate=").append(shippedDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}