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
import com.linuxtek.kona.app.entity.KRegistration;
import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserAuth;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KStringUtil;

public abstract class KAbstractUserService<U extends KUser, UEXAMPLE, UA extends KUserAuth, 
										   A extends KAccount, R extends KRegistration, T extends KToken> 
		extends KAbstractService<U,UEXAMPLE>
		implements KUserService<U> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract U getNewUserObject();
	protected abstract Long getDefaultTypeId();
	protected abstract Long getDefaultRoles();
	protected abstract Long getDefaultStatusId();
	protected abstract Long getDefaultPresenceId();
    
	protected abstract <S extends KAccountService<A>> S getAccountService();
	protected abstract <S extends KUserAuthService<U,UA>> S getUserAuthService();
	protected abstract <S extends KRegistrationService<R,U>> S getRegistrationService();
	protected abstract <S extends KTokenService<T>> S getTokenService();
    
	protected abstract void sendRegisteredUserEmail(Long appId, U user);
	
	// ----------------------------------------------------------------------------
	
	protected String generateUid() {
		return KUtil.uuid();
	}
	
	// ----------------------------------------------------------------------------
	
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
	
	// ----------------------------------------------------------------------------
	
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
        Integer signupTime = null;
        getRegistrationService().createRegistration(user, client, signupTime);

        sendRegisteredUserEmail(client.getAppId(), user);
        return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U retire(U user) {
        // fetch fresh object
        user = fetchById(user.getId());

        // return user if already retired
        if (user == null || user.getRetiredDate() != null) {
            return user;
        }

        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        user.setUsername(prefix + user.getUsername());
        user.setMobileNumber(prefix + user.getMobileNumber());
        user.setEmail(prefix + user.getEmail());
        user.setEnabled(false);
        user.setActive(false);
        user.setRetiredDate(new Date());
        user = update(user);


        // retiring the account may fail if there are other non-retired
        // users associated with the account.
        // Since account will check active users, it's imperative that
        // this object be updated first.  otherwise AccountService will
        // see this user as active and fail to retire the account.
        if (user.getParentId() == null) {
            A account = getAccountService().fetchById(user.getAccountId());
            try {
                getAccountService().retire(account);
            } catch (Throwable t) { }
        }

        return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U fetchByUsername(String username) {
        logger.debug("UserServiceImpl: fetchByUsername: username: {}", username);
        Map<String,Object> filter = KMyBatisUtil.createFilter("username", username);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U fetchByEmail(String email) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public List<U> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public U fetchByAccessToken(String accessToken, boolean validateToken) {
        U user = null;
        
        T token = getTokenService().fetchByAccessToken(accessToken, validateToken);
        
        if (token != null) {
        	user = fetchById(token.getUserId());
        }
        
        return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(U user) {
    	if (user.getCreatedDate() == null) {
			user.setCreatedDate(new Date());
		}
    	
    	user.setLastUpdated(new Date());
	}
	
}
