/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KStringUtil;

public abstract class KAbstractUserService<U extends KUser> implements KUserService<U> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract U getNewUserObject();
	protected abstract String generateUid();
	protected abstract Long getDefaultTypeId();
	protected abstract Long getDefaultRoles();
	protected abstract Long getDefaultStatusId();
	protected abstract Long getDefaultPresenceId();
	
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
	public U registerUser(U user, KServiceClient client) {
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
        
        if (client == null) {
        	client = new KServiceClient();
        }

        if (client.getHostname() == null) {
            client.setHostname("127.0.0.1"); // hostname cannot be null in registration table
        }


        Account account = null;
        
        user.setActive(true);
        user.setCreatedDate(new Date());

        String displayName = KStringUtil.createFullName(user.getFirstName(), user.getLastName());
        if (displayName == null || displayName.length() == 0) {
            displayName = user.getUsername();
        }
        user.setDisplayName(displayName);


        // First get or create an account
        if (accountId == null) {
            account = accountService.createAccount(null);
            accountId = account.getId();
        } else {
            account = accountService.fetchById(accountId);
        }

        user.setAccountId(accountId);
        userDao.insert(user);

        // sanity check
        if (user.getId() == null) {
            throw new IllegalStateException(
                    "Error inserting record: " + user);
        }

        // update account record
        if (account.getUserId() == null) {
            account.setUserId(user.getId());
            accountService.update(account);
        }

        // if a user is created with a null password then the 
        // the account is set to isActive = false
        if (password != null) {
            // encrypt the password
            String passwordx = KStringUtil.encryptSHA1(password);

            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(user.getId());
            userAuth.setPassword(passwordx);
            userAuth.setCreatedDate(new Date());
            userAuthDao.insert(userAuth);

            // sanity check
            if (userAuth.getId() == null) {
                throw new IllegalStateException(
                        "Error inserting record: " + userAuth);
            }
        }

        // log registration record
        Registration reg = new Registration();
        reg.setAppId(appId);
        reg.setUserId(user.getId());
        reg.setUsername(username);
        reg.setHostname(hostname);
        reg.setBrowser(browser);
        reg.setSessionId(sessionId);
        reg.setAccessToken(accessToken);
        reg.setSignupTime(signupTime);
        reg.setCreatedDate(new Date());
        registrationDao.insert(reg);

        //sendWelcomeEmail(appId, user, betaTester);
        return user;
	}
	
}
