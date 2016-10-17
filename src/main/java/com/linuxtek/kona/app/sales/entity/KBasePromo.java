package com.linuxtek.kona.app.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePromo implements KPromo {

	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.app_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.product_id
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Long productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.display_name
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String displayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.description
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.enabled
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean enabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.visible
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean visible;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.signup_default
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private boolean signupDefault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.created_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.start_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date startDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.end_date
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.use_count
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer useCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.use_per_account
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer usePerAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.max_use_count
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer maxUseCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.discount_pct
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer discountPct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.discount_amount
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private BigDecimal discountAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.setup_fee
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private BigDecimal setupFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.trial_days
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer trialDays;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.subscription_days
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Integer subscriptionDays;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.validation_rule
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private String validationRule;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.last_updated
     *
     * @mbggenerated Thu Apr 07 17:04:20 MDT 2016
     */
    private Date lastUpdated;

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getId()
	 */
    @Override
	public Long getId() {
        return id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setId(java.lang.Long)
	 */
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getAppId()
	 */
    @Override
	public Long getAppId() {
        return appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setAppId(java.lang.Long)
	 */
    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getProductId()
	 */
    @Override
	public Long getProductId() {
        return productId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setProductId(java.lang.Long)
	 */
    @Override
	public void setProductId(Long productId) {
        this.productId = productId;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getName()
	 */
    @Override
	public String getName() {
        return name;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setName(java.lang.String)
	 */
    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getDisplayName()
	 */
    @Override
	public String getDisplayName() {
        return displayName;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setDisplayName(java.lang.String)
	 */
    @Override
	public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getDescription()
	 */
    @Override
	public String getDescription() {
        return description;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setDescription(java.lang.String)
	 */
    @Override
	public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#isEnabled()
	 */
    @Override
	public boolean isEnabled() {
        return enabled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setEnabled(boolean)
	 */
    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#isVisible()
	 */
    @Override
	public boolean isVisible() {
        return visible;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setVisible(boolean)
	 */
    @Override
	public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#isSignupDefault()
	 */
    @Override
	public boolean isSignupDefault() {
        return signupDefault;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setSignupDefault(boolean)
	 */
    @Override
	public void setSignupDefault(boolean signupDefault) {
        this.signupDefault = signupDefault;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getCreatedDate()
	 */
    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setCreatedDate(java.util.Date)
	 */
    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getStartDate()
	 */
    @Override
	public Date getStartDate() {
        return startDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setStartDate(java.util.Date)
	 */
    @Override
	public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getEndDate()
	 */
    @Override
	public Date getEndDate() {
        return endDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setEndDate(java.util.Date)
	 */
    @Override
	public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getUseCount()
	 */
    @Override
	public Integer getUseCount() {
        return useCount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setUseCount(java.lang.Integer)
	 */
    @Override
	public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getUsePerAccount()
	 */
    @Override
	public Integer getUsePerAccount() {
        return usePerAccount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setUsePerAccount(java.lang.Integer)
	 */
    @Override
	public void setUsePerAccount(Integer usePerAccount) {
        this.usePerAccount = usePerAccount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getMaxUseCount()
	 */
    @Override
	public Integer getMaxUseCount() {
        return maxUseCount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setMaxUseCount(java.lang.Integer)
	 */
    @Override
	public void setMaxUseCount(Integer maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getDiscountPct()
	 */
    @Override
	public Integer getDiscountPct() {
        return discountPct;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setDiscountPct(java.lang.Integer)
	 */
    @Override
	public void setDiscountPct(Integer discountPct) {
        this.discountPct = discountPct;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getDiscountAmount()
	 */
    @Override
	public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setDiscountAmount(java.math.BigDecimal)
	 */
    @Override
	public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getSetupFee()
	 */
    @Override
	public BigDecimal getSetupFee() {
        return setupFee;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setSetupFee(java.math.BigDecimal)
	 */
    @Override
	public void setSetupFee(BigDecimal setupFee) {
        this.setupFee = setupFee;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getTrialDays()
	 */
    @Override
	public Integer getTrialDays() {
        return trialDays;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setTrialDays(java.lang.Integer)
	 */
    @Override
	public void setTrialDays(Integer trialDays) {
        this.trialDays = trialDays;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getSubscriptionDays()
	 */
    @Override
	public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setSubscriptionDays(java.lang.Integer)
	 */
    @Override
	public void setSubscriptionDays(Integer subscriptionDays) {
        this.subscriptionDays = subscriptionDays;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getValidationRule()
	 */
    @Override
	public String getValidationRule() {
        return validationRule;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setValidationRule(java.lang.String)
	 */
    @Override
	public void setValidationRule(String validationRule) {
        this.validationRule = validationRule == null ? null : validationRule.trim();
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#getLastUpdated()
	 */
    @Override
	public Date getLastUpdated() {
        return lastUpdated;
    }

    /* (non-Javadoc)
	 * @see com.linuxtek.kona.app.sales.entity.KPromo#setLastUpdated(java.util.Date)
	 */
    @Override
	public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
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
        sb.append(", productId=").append(productId);
        sb.append(", name=").append(name);
        sb.append(", displayName=").append(displayName);
        sb.append(", description=").append(description);
        sb.append(", enabled=").append(enabled);
        sb.append(", visible=").append(visible);
        sb.append(", signupDefault=").append(signupDefault);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", useCount=").append(useCount);
        sb.append(", usePerAccount=").append(usePerAccount);
        sb.append(", maxUseCount=").append(maxUseCount);
        sb.append(", discountPct=").append(discountPct);
        sb.append(", discountAmount=").append(discountAmount);
        sb.append(", setupFee=").append(setupFee);
        sb.append(", trialDays=").append(trialDays);
        sb.append(", subscriptionDays=").append(subscriptionDays);
        sb.append(", validationRule=").append(validationRule);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
