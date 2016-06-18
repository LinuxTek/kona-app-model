/*
 * Copyright (C) 2011 LinuxTek, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAuthCode;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KDateUtil;

public abstract class KAbstractAuthCodeService<T extends KAuthCode,EXAMPLE,U extends KUser> 
		extends KAbstractService<T,EXAMPLE>
		implements KAuthCodeService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAuthCodeService.class);
    
	// ----------------------------------------------------------------------------
    
    protected abstract T getNewObject();
    
    protected abstract <S extends KUserService<U>> S getUserService();
    
	// ----------------------------------------------------------------------------
    
	protected abstract String getPasswordResetUrl(Long appId, Long userId, String code);
        /*
	protected abstract String getPasswordResetUrl(Long appId, Long userId, String code) {
		//urlTemplate.passwordReset = http://example.com/account/passsword/{code}
        String url = getPasswordResetUrl(code);
		url = shortUrlService.shorten(appId, userId, null, null, url, null, null, false, true);
		return url;
	}
    	*/
    
    protected abstract void sendRequestPasswordEmail(Long appId, U user, String passwordResetUrl); 
    
	// ----------------------------------------------------------------------------
    
	protected String generateAccessCode() {
		return uuid();
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public void validate(T authCode) {
    	if (authCode.getCreatedDate() == null) {
			authCode.setCreatedDate(new Date());
		}
    	
    	authCode.setLastUpdated(new Date());
        
		if (authCode.getMaxUseCount() == null) {
			authCode.setMaxUseCount(1);
		}
		
		if (authCode.getUseCount() == null) {
			authCode.setUseCount(0);
		}
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public T fetchByCode(String code) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("code", code);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------

	private List<T> fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------

	@Override
	public List<T> expireByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("confirmedDate", null);
        
		List<T> result = fetchByCriteria(0, 99999, null, filter, false);
        
		List<T> expiredList = new ArrayList<T>();
        
		for (T authCode : result) {
			authCode.setValid(false);
			authCode.setExpirationDate(new Date());
            
			authCode = update(authCode);
            
			expiredList.add(authCode);
		}
        
		return expiredList;
	}
	
	// ----------------------------------------------------------------------

	@Override
	public T accessCode(String code,  KServiceClient client) {
		if (code == null) return null;
        
		T authCode = fetchByCode(code);
        
		if (authCode == null || !isActive(authCode)) return null;

		Integer useCount = authCode.getUseCount() + 1;
        
		authCode.setUseCount(useCount);
        
		authCode.setLastAccessedDate(new Date());
		
		return update(authCode);
	}

	// ----------------------------------------------------------------------
	
	// check to see if a (valid) authCode code has already been sent to this user
	// if so and resend flag is set, invalidate existing authCodes and return true. 
	private boolean canSendAuthCode(Long userId, boolean resend) {
		List<T> authCodes = fetchByUserId(userId);

		if (authCodes == null || authCodes.size()==0) return true;
        
		boolean haveActive = false;
        
		for (T authCode : authCodes) {
			if (isActive(authCode)) {
				haveActive = true;
				if (resend) {
					authCode.setValid(false);
					update(authCode);
				}
			}
		}
        
		if (!haveActive) return true;
        
		if (haveActive && resend) return true;
        
		return false;
	}
    
	// ----------------------------------------------------------------------------

	@Override 
	public void requestPasswordReset(Long userId, Long appId, boolean resend) {
		U user = getUserService().fetchById(userId);
		
		logger.debug("requestUserEmailAuthCode: user:\n" + user);

		if (user.getEmail() == null) {
			throw new NullPointerException("requestPasswordReset: User email is null");
		}

		if (!canSendAuthCode(userId, resend)) return;
        
        String code = generateAuthCode(appId, userId);
		String passwordResetUrl = getPasswordResetUrl(appId, userId, code);
        
		sendRequestPasswordEmail(appId, user, passwordResetUrl);
	}
    
	// ----------------------------------------------------------------------------
	
	protected String generateAuthCode(Long appId, Long userId) {
		String code = generateAccessCode();

		Date now = new Date();
		
		T authCode = getNewObject();
		authCode.setAppId(appId);
		authCode.setUserId(userId);
		authCode.setCode(code);
		authCode.setUseCount(0);
		authCode.setMaxUseCount(1);
		authCode.setCreatedDate(now);
		authCode.setValid(true);
		authCode.setExpirationDate(KDateUtil.addMins(now, 30)); // expire in 30 mins

		add(authCode);
		return code;
	}

	// ----------------------------------------------------------------------------
    
	protected boolean isActive(T authCode) {
		if (!authCode.isValid()) {
			logger.debug("authCode is not valid: " + authCode);
			return false;
		}

		Integer useCount = authCode.getUseCount();
        
		if (useCount >= authCode.getMaxUseCount()) {
			return false;
		}
		
		Date expirationDate = authCode.getExpirationDate();
        
		Date now = new Date();
		if (expirationDate != null && now.getTime() > expirationDate.getTime()) {
			return false;
		}
        
		return true;
	}
}
