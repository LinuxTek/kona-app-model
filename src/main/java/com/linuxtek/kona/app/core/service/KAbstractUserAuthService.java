/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.entity.KUserAuth;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.encryption.KEncryptUtil;
import com.linuxtek.kona.util.KPassGen;

public abstract class KAbstractUserAuthService<UA extends KUserAuth,EXAMPLE,U extends KUser> 
		extends KAbstractService<UA,EXAMPLE>
		implements KUserAuthService<UA,U> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserAuthService.class);

	// ----------------------------------------------------------------------------
	protected abstract <S extends KUserService<U>> S getUserService();

	protected abstract UA getNewObject();

	protected abstract void sendPasswordResetEmail(U user, String password);
	
	// ----------------------------------------------------------------------------
	
	@Override
	public UA fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public UA resetPassword(Long userId) {
		String password = KPassGen.generatePassword(8);
		UA userAuth = setPlainPassword(userId, password);
		U user = getUserService().fetchById(userId);
		sendPasswordResetEmail(user, password);
		return userAuth;
	}
	
	// ----------------------------------------------------------------------------

	@Override 
	public UA setEncryptedPassword(Long userId, String encryptedPassword) {
		UA userAuth = fetchByUserId(userId);
		if (userAuth == null) {
			userAuth = getNewObject();
			userAuth.setUserId(userId);
			userAuth.setPassword(encryptedPassword);
			userAuth.setCreatedDate(new Date());
			userAuth = add(userAuth);

			// if user didn't have a password, user is most likely
			// not active. set user to active once password is set.
			U user = getUserService().fetchById(userId);
			if (!user.isActive()) {
				user.setActive(true);
				user = getUserService().update(user);
			}
		} else {
			userAuth.setPassword(encryptedPassword);
			userAuth = update(userAuth);
		}

		return userAuth;
	}
	
	// ----------------------------------------------------------------------------

	@Override 
	public UA setPlainPassword(Long userId, String password) {
		try {
			String passwordx = KEncryptUtil.SHA1(password);
			return setEncryptedPassword(userId, passwordx);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new KAuthException("Error creating encrypted password", KAuthException.Type.INVALID_PASSWORD, e);
		}
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public U validateCredentials(String username, String password) throws KAuthException {
		// first check if username is valid
		logger.debug("validating credentials for: "
				+ "username: " + username
				+ "password: " + password);

		U user = getUserService().fetchByUsername(username);
		if (user == null) {
			throw new KAuthException("Username not found: " + username, KAuthException.Type.INVALID_USERNAME);
		}

		// next check if we have an auth record for this user
		UA userAuth = fetchByUserId(user.getId());
		if (userAuth == null || userAuth.getPassword() == null) {
			// if no auth record/password and password is null
			// assume user doesn't require a password
			if (password == null) {
				return user;
			}

			throw new KAuthException("Auth record not found for: " + username, KAuthException.Type.INVALID_PASSWORD);
		}

		// next check if password is valid
		String passwordx;
		try {
			passwordx = KEncryptUtil.SHA1(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new KAuthException("Error validating password: " + password, KAuthException.Type.INVALID_PASSWORD);
		}
		
		if (!passwordx.equals(userAuth.getPassword())) {
			throw new KAuthException("Invalid password for username: " + username, KAuthException.Type.INVALID_PASSWORD);
		}

		return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(UA userAuth) {
    	if (userAuth.getCreatedDate() == null) {
			userAuth.setCreatedDate(new Date());
		}
    	
    	userAuth.setLastUpdated(new Date());
	}
}
