package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 * KAccount.
 */
    
public interface KAccount extends KEntityObject {

    /** 
     * Internal object identifier. Not exported or referenced externally.
     */
    public Long getId(); 
    public void setId(Long id);
    
    /** 
     * Unique account number that can be exported or referenced externally.
     */
    public String getUid(); 
    public void setUid(String uid);
    
    public Long getOwnerId(); 
    public void setOwnerId(Long ownerId);
    
    public String getName(); 
    public void setName(String name);
    
    public String getDisplayName(); 
    public void setDisplayName(String displayName);
    
    
    /**
     * Stripe ID for payment processing.
     */
    public String getStripeUid(); 
    public void setStripeUid(String stripeUid);

  

    /**
     * Flag to indicate if this account is currently enabled.
     * This flag is set by the system to control the user's access
     * to the account.  For example, this flag may be set to false
     * if the user exceeds a specfic number of failed login attempts.
     */
    public boolean isEnabled();
    public void setEnabled(boolean enabled);

    /**
     * Flag to indicate if this account is currently active.
     * An active account is one which is not retired (deleted).
     * If set to true, getRetiredDate() should be set to null, otherwise
     * it should return the date the account was retired.
     */
    public boolean isActive();
    public void setActive(boolean active);

    /**
     * Flag to indicate if this account has passed all verification
     * requirements.  Note that an account might be simultaneously
     * enabled and not verified.
     */
    public boolean isVerified();
    public void setVerified(boolean verified);

    /**
     * Date this account was created.
     */
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);

    /**
     * Date this account was retired.
     */
    public Date getRetiredDate();
    public void setRetiredDate(Date retiredDate);

    /**
     * The date this user record was last updated.
     */
    public Date getUpdatedDate();
    public void setUpdatedDate(Date updatedDate);
}
