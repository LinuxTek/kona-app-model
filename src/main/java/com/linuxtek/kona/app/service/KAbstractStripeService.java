/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAccount;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.stripe.entity.KCard;
import com.linuxtek.kona.stripe.entity.KCharge;
import com.linuxtek.kona.stripe.entity.KCustomer;
import com.linuxtek.kona.stripe.entity.KStripeException;

public abstract class KAbstractStripeService<ACCOUNT extends KAccount,
											 USER extends KUser> 
        extends com.linuxtek.kona.stripe.service.KAbstractStripeService
        implements KStripeService<ACCOUNT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractStripeService.class);

	// ----------------------------------------------------------------------------

	protected abstract <S extends KUserService<USER>> S getUserService();

	protected abstract <S extends KAccountService<ACCOUNT>> S getAccountService();

	protected abstract void sendPrimaryCardUpdateEmail(ACCOUNT account, KCard card);

	// ----------------------------------------------------------------------------

    private ACCOUNT getAccountByUserId(Long userId) {
        USER user = getUserService().fetchById(userId);
        if (user == null || user.getAccountId() == null) return null;
        ACCOUNT account = getAccountService().fetchById(user.getAccountId());
        return account;
    }
    
	// ----------------------------------------------------------------------------

	private String getUserDescription(USER user) {
		String s = "Username: " + user.getUsername();
		/*
        s += "\nFirst Name: " + user.getFirstName();
        s += "\nLast Name: " + user.getLastName();
        s += "\nCompany: " + user.getCompany();
        s += "\nEmail: " + user.getEmail();
		 */
		return s;
	}

	// ----------------------------------------------------------------------------

    @Override 
    public String addCustomer(Long appId, ACCOUNT account) {
        USER user = getUserService().fetchById(account.getOwnerId());

        KCustomer customer = addCustomer(appId, user.getEmail(), 
            getUserDescription(user));

        return customer.getId();
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public void deleteCustomer(Long appId, ACCOUNT account) {
        deleteCustomer(appId, account.getStripeUid());
    }
    
	// ----------------------------------------------------------------------------

    @Override 
    public void updateCustomer(Long appId, ACCOUNT account) {
        KCustomer customer = fetchCustomerById(appId, account.getStripeUid());
        
        USER user = getUserService().fetchById(account.getOwnerId());
        
        customer.setEmail(user.getEmail());
        
        customer.setDescription(getUserDescription(user));
        
        updateCustomer(appId, customer);
    }
    
	// ----------------------------------------------------------------------------
	
    @Override
	public ACCOUNT updateAccountStripeUidByCardToken(Long appId, ACCOUNT account, String cardToken)  {
        USER user = getUserService().fetchById(account.getOwnerId());
        String email = user.getEmail();
        String description = user.getUsername();
    	KCustomer customer = addCustomer(appId, email, description, cardToken);
    	account.setStripeUid(customer.getId());
    	return getAccountService().update(account);
	}
    
	// ----------------------------------------------------------------------------

    @Override 
    public KCharge chargeCustomer(Long appId, ACCOUNT account, BigDecimal amount, 
            String description, String receiptEmail, Map<String,Object> metadata,
            Map<String,Object> shipping) throws KStripeException {
    	
        logger.debug("calling chargeCustomer");
        
        if (account == null || account.getStripeUid() == null) {
            String s = "Stripe UID is null.";
            
            if (account != null) {
                s += "\nuserId: " + account.getOwnerId()
                  + "\naccountId: " + account.getId();
            }
            
            throw new KStripeException(s);
        }

        return chargeCustomer(appId, account.getStripeUid(), amount, description, 
        		receiptEmail, metadata, shipping);
    }
    
    
    // ----------------------------------------------------------------------
    
    @Override
    public KCard addPrimaryCard(Long appId, ACCOUNT account, KCard card) {
        String stripeUid = account.getStripeUid();
        card = addCustomerCard(appId, stripeUid, card);
        return card;
    }
    
    // ----------------------------------------------------------------------

    @Override
    public KCard addPrimaryCard(Long appId, ACCOUNT account, String cardToken) {
        String stripeUid = account.getStripeUid();
        KCard card = addCustomerCard(appId, stripeUid, cardToken);
        return card;
    }
    
    // ----------------------------------------------------------------------

    @Override 
    public KCard updatePrimaryCard(Long appId, ACCOUNT account, KCard card) {
    	card = addPrimaryCard(appId, account, card);
    	sendPrimaryCardUpdateEmail(account, card);
    	return card;
    }
    
    // ----------------------------------------------------------------------

    @Override
    public KCard updatePrimaryCard(Long appId, ACCOUNT account, String cardToken) {
    	KCard card = addPrimaryCard(appId, account, cardToken);
    	sendPrimaryCardUpdateEmail(account, card);
    	return card;
    }
    
    // ----------------------------------------------------------------------
    
    @Override 
    public KCard getPrimaryCardByUserId(Long appId, Long userId) {
        if (userId == null) return null;
        ACCOUNT account = getAccountByUserId(userId);
        return getPrimaryCard(appId, account);
    }
    
    // ----------------------------------------------------------------------
    
    @Override
    public KCard getPrimaryCard(Long appId, ACCOUNT account) {
        if (account == null) return null;
        String stripeUid = account.getStripeUid();
        if (stripeUid == null) return null;
    	return fetchCustomerActiveCard(appId, stripeUid);
    }
    
    // ----------------------------------------------------------------------
    
    @Override 
    public String getPrimaryCardLast4ByUserId(Long appId, Long userId) {
        if (userId == null) return null;
    	KCard card = getPrimaryCardByUserId(appId, userId);
        if (card == null) return null;
        return card.getLast4();
    }
    
    // ----------------------------------------------------------------------


}
