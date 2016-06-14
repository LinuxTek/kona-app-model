/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAccount;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractAccountService<A extends KAccount> implements KAccountService<A> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAccountService.class);
    
    protected abstract A getNewAcountObject();
    protected abstract String generateUid();
    
	@Override 
	public void validate(A account) {
    	if (account.getCreatedDate() == null) {
			account.setCreatedDate(new Date());
		}
        
    	if (account.getUid() == null) {
			account.setUid(generateUid());
		}
    	
    	if (account.getDisplayName() == null) {
    		account.setDisplayName(account.getUid());
    	}
        
		String name = KInflector.getInstance().slug(account.getDisplayName());
		account.setName(name);
	}
    
    @Override
	public A createAccount(String displayName) {
		
		String uid = generateUid();
        
        if (displayName == null) {
        	displayName = uid;
        }
		
		// clean up displayName as much as possible before
		displayName = displayName.trim().replaceAll("\\p{Punct}", "");

		if (!isAccountNameAvailable(displayName)) {
			throw new IllegalArgumentException("Account name already exists: " + displayName);
		}

		A account = getNewAcountObject();
		account.setUid(uid);
		account.setOwnerId(null);
		account.setDisplayName(displayName);
		account.setEnabled(true);
		account.setActive(true);
		account.setVerified(false);
		account.setCreatedDate(new Date());

		/* -- STRIPE --
        String email = uid; 
        // If Stripe is enabled
        if (email != null && !account.getName().equalsIgnoreCase("guest")) {
            KCustomer customer = stripeService.addCustomer(email,
                    "Name: " + account.getDisplayName());
            String stripeUid = customer.getId();
            account.setStripeUid(stripeUid);
            account.setPaymentTypeId(PaymentType.CARD.getId());
        }
		 */

		return add(account);
	}
}
