/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAccount;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractAccountService<A extends KAccount,AEXAMPLE,U extends KUser> 
extends KAbstractService<A,AEXAMPLE>
implements KAccountService<A> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAccountService.class);

	// ----------------------------------------------------------------------------

	protected abstract A getNewAccountObject();

	protected abstract <S extends KUserService<U>> S getUserService();
	
	// ----------------------------------------------------------------------------
	
	protected String generateUid() {
		return KUtil.uuid();
	}

	// ----------------------------------------------------------------------------

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
		
		account.setLastUpdated(new Date());

		String name = KInflector.getInstance().slug(account.getDisplayName());
		account.setName(name);
	}

	// ----------------------------------------------------------------------------

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

		String name = KInflector.getInstance().slug(displayName);

		A account = getNewAccountObject();
		account.setUid(uid);
		account.setOwnerId(null);
		account.setDisplayName(displayName);
		account.setName(name);
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

	// ----------------------------------------------------------------------------

	@Override
	public A fetchByName(String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public A fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public A fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------


	@Override
	public A retire(A account) {
	      // fetch fresh object
        account = fetchById(account.getId());

        // return account if already retired
        if (account == null || account.getRetiredDate() != null) {
            return account;
        }

        // first check that all users associated with this account are also retired
        List<U> userList = getUserService().fetchByAccountId(account.getId());
        boolean haveActiveUser = false;
        for (U user : userList) {
            if (user.getRetiredDate() != null) {
                haveActiveUser = true;
            }
        }

        if (haveActiveUser) {
            throw new IllegalStateException("Cannot retire account with active (non-retired) users: accountId: " + account.getId());
        }



        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        account.setName(prefix + account.getName());;
        account.setEnabled(false);
        account.setActive(false);
        account.setRetiredDate(new Date());
        account = update(account);

        return account;
	}
}
