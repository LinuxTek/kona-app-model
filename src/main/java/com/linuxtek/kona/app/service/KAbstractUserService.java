/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAccount;
import com.linuxtek.kona.app.entity.KRegistration;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserAuth;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KStringUtil;

public abstract class KAbstractUserService<U extends KUser, UA extends KUserAuth, 
										   A extends KAccount, R extends KRegistration> 
		implements KUserService<U> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract U getNewUserObject();
	protected abstract String generateUid();
	protected abstract Long getDefaultTypeId();
	protected abstract Long getDefaultRoles();
	protected abstract Long getDefaultStatusId();
	protected abstract Long getDefaultPresenceId();
    
	protected abstract <S extends KAccountService<A>> S getAccountService();
	protected abstract <S extends KUserAuthService<U,UA>> S getUserAuthService();
	protected abstract <S extends KRegistrationService<R>> S getRegistrationService();
	
	protected void checkUserExists(String username, String email) {
		if (username == null) {
			throw new KAuthException("Username is null");
		}

		if (!isUsernameAvailable(username)) {
			throw new KAuthException("Username not available: " + username);
		}

		if (email != null) {
			U user = fetchByEmail(email);
			if (user != null) {
				throw new IllegalArgumentException("Email exists: " + email);
			}
		}
	}
	
	@Override
	public U registerUser(U user, String password, KServiceClient client) {
        if (user.getUid() == null) {
            user.setUid(generateUid());
        }

        if (user.getUsername() == null) {
            user.setUsername(user.getUid());
        }

        checkUserExists(user.getUsername(), user.getEmail());

        if (user.getTypeId() == null) {
            user.setTypeId(getDefaultTypeId());
        }

        if (user.getRoles() == null) {
            user.setRoles(getDefaultRoles());
        }
        
        if (user.getStatusId() == null) {
            user.setStatusId(getDefaultStatusId());
        }
        
        if (user.getPresenceId() == null) {
            user.setPresenceId(getDefaultPresenceId());
        }
        
        
        String displayName = KStringUtil.createFullName(user.getFirstName(), user.getLastName());
        if (displayName == null || displayName.length() == 0) {
            displayName = user.getUsername();
        }

        
        A account = null;
        if (user.getAccountId() == null) {
            account = getAccountService().createAccount(null);
            user.setAccountId(account.getId());
        } 
        
        user.setDisplayName(displayName);
        user.setActive(true);
        user.setCreatedDate(new Date());

        user = add(user);

        // update account record
        if (account == null) {
            account = getAccountService().createAccount(null);
        }

        if (account.getOwnerId() == null) {
            account.setOwnerId(user.getId());
            getAccountService().update(account);
        }
        
    
        // update auth record
        if (password != null) {
        	getUserAuthService().setPlainPassword(user.getId(), password);
        }

        if (client == null) {
        	client = new KServiceClient();
        }

        if (client.getHostname() == null) {
            client.setHostname("127.0.0.1"); // hostname cannot be null in registration table
        }
        
        // log registration record
     

        //sendWelcomeEmail(appId, user, betaTester);
        return user;
	}
	
}
