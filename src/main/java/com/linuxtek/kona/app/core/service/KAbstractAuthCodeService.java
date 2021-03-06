/*
 * Copyright (C) 2011 LinuxTek, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAuthCode;
import com.linuxtek.kona.app.core.entity.KAuthCodeType;
import com.linuxtek.kona.app.core.entity.KRegistration;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractAuthCodeService<T extends KAuthCode,EXAMPLE,
											   U extends KUser,
											   R extends KRegistration> 
		extends KAbstractService<T,EXAMPLE>
		implements KAuthCodeService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAuthCodeService.class);
    
	// ----------------------------------------------------------------------------
    
    protected abstract T getNewObject();
    
    protected abstract <S extends KUserService<U>> S getUserService();
    
    protected abstract <S extends KRegistrationService<R,U>> S getRegistrationService();
    
	// ----------------------------------------------------------------------------
    
	protected abstract String getAuthCodeUrl(Long typeId, Long appId, Long userId, String code);
    
    protected abstract void sendAuthCode(Long typeId, Long appId, Long userId, String authCodeUrl); 
    
    protected abstract Date getAuthCodeExpirationDate(Long typeId, Long appId, Long userId);
    
	// ----------------------------------------------------------------------------

    protected Integer getAuthCodeMaxUseCount(Long typeId, Long appId, Long userId) {
        return null;
    }

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
    	
    	authCode.setUpdatedDate(new Date());
        
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
    
	private List<T> fetchByUserIdAndTypeId(Long userId, Long typeId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
        if (typeId != null) {
        	filter.put("typeId", typeId);
        }
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------

	@Override
	public T accessCode(String code) {
		if (code == null) return null;
        
		T authCode = fetchByCode(code);
		
		if (authCode == null || !isActive(authCode)) return null;
		
		Integer useCount = authCode.getUseCount() + 1;
        
		authCode.setUseCount(useCount);
        
		authCode.setLastAccessedDate(new Date());

        // check type 
        //String type = getAuthCodeType(authCode.getTypeId());
        
        KAuthCodeType type = KAuthCodeType.getInstance(authCode.getTypeId());
        
        R registration = null;
        
        if (type == KAuthCodeType.EMAIL_CONFIRMATION) {
            registration = getRegistrationService().fetchByUserId(authCode.getUserId());
            registration.setEmailPending(false);
            registration.setEmailVerified(true);
            getRegistrationService().update(registration);
        } else if (type == KAuthCodeType.MOBILE_CONFIRMATION) {
            registration = getRegistrationService().fetchByUserId(authCode.getUserId());
            registration.setMobilePending(false);
            registration.setMobileVerified(true);
            getRegistrationService().update(registration);
        } else if (type == KAuthCodeType.PASSWORD_RESET) {

        }
            
		return update(authCode);
	}

	// ----------------------------------------------------------------------
	
	// check to see if a (valid) authCode code has already been sent to this user
	// if so and resend flag is set, invalidate existing authCodes and return true. 
	private boolean canSendAuthCode(Long userId, long typeId, boolean resend) {
		List<T> authCodes = fetchByUserIdAndTypeId(userId, typeId);

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
	public void requestAuthCode(Long typeId, Long appId, Long userId, boolean resend) {
		U user = getUserService().fetchById(userId);

		logger.debug("requestAuthCode: user:\n" + user);

		if (!canSendAuthCode(userId, typeId, resend)) return;

		String code = generateAuthCode(typeId, appId, userId);
        
		String authCodeUrl = getAuthCodeUrl(typeId, appId, userId, code);

		KAuthCodeType type = KAuthCodeType.getInstance(typeId);

		R registration = null;

		if (type == KAuthCodeType.EMAIL_CONFIRMATION) {
			registration = getRegistrationService().fetchByUserId(userId);
			registration.setEmailPending(true);
			registration.setEmailVerified(false);
			getRegistrationService().update(registration);
		} else if (type == KAuthCodeType.MOBILE_CONFIRMATION) {
			registration = getRegistrationService().fetchByUserId(userId);
			registration.setMobilePending(true);
			registration.setMobileVerified(false);
			getRegistrationService().update(registration);
		} else if (type == KAuthCodeType.PASSWORD_RESET) {
		}

		sendAuthCode(typeId, appId, userId, authCodeUrl);
	}
	
	// ----------------------------------------------------------------------------

	@Override 
	public void requestAuthCodes(Long[] typeIds, Long appId, Long userId, boolean resend) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					for (Long typeId : typeIds) {
						logger.debug("calling requestAuthCode for typeId: " + typeId);
						requestAuthCode(typeId, appId, userId, resend);
					}
				} catch (Exception e) {
					logger.error("Error processing verification requests for userId: " + userId, e);
				}
			}
		};
		t.start();

	}
		
	// ----------------------------------------------------------------------------
	
	protected String generateAuthCode(Long typeId, Long appId, Long userId) {
		String code = generateAccessCode();

		Date now = new Date();
		
		T authCode = getNewObject();
		authCode.setTypeId(typeId);
		authCode.setAppId(appId);
		authCode.setUserId(userId);
		authCode.setCode(code);
		authCode.setUseCount(0);
		authCode.setCreatedDate(now);
		authCode.setValid(true);

		Integer maxUseCount = getAuthCodeMaxUseCount(typeId, appId, userId);
		authCode.setMaxUseCount(maxUseCount);
		
		Date expirationDate = getAuthCodeExpirationDate(typeId, appId, userId);
		//authCode.setExpirationDate(KDateUtil.addMins(now, 30)); // expire in 30 mins
		
		authCode.setExpirationDate(expirationDate);

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
        
		if (authCode.getMaxUseCount() != null && useCount >= authCode.getMaxUseCount()) {
			logger.debug("authCode useCount exceeds maxUseCount: " + useCount);
			return false;
		}
		
		Date expirationDate = authCode.getExpirationDate();
        
		Date now = new Date();
		if (expirationDate != null && now.getTime() > expirationDate.getTime()) {
			logger.debug("authCode expired: " + expirationDate);
			return false;
		}
        
		logger.debug("authCode: {}  isActive: {}", authCode, true);
		
		return true;
	}
}
