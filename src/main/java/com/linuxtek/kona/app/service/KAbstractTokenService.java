/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractTokenService<T extends KToken,TEXAMPLE> 
		extends KAbstractService<T,TEXAMPLE>
		implements KTokenService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractTokenService.class);
    
	// ----------------------------------------------------------------------------
    
    @Override
    public boolean isValid(T token) {
        return isValid(token, false);
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public boolean isValid(String accessToken) {
    	return isValid(accessToken, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public boolean isValid(String accessToken, boolean fetchFreshToken) {
    	T token = fetchByAccessToken(accessToken);
    	return isValid(token, fetchFreshToken);
    }
    
	// ----------------------------------------------------------------------------
 
    @Override
    public boolean isValid(T token, boolean fetchFreshToken) {

        logger.debug("KAbstractTokenService: isValid: "
                    + "token: {}  fetchFreshToken: {}", token, fetchFreshToken);

        // check if we have a token for this token string
        if (token == null || token.getAccessToken() == null) {
            return false;
        }

        if (fetchFreshToken) {
            // update the token from the database
            token = fetchByAccessToken(token.getAccessToken(), false);
        } 

        if (token == null || !token.isActive()) {
            return false;
        }

        // if we don't have an expireDate, then this token
        // never expires, so return true.
        Date d = token.getAccessExpirationDate();
        
        if (d == null) {
            return true;
        }

        Date now = new Date();
        long nowTime = now.getTime();
        long expireTime = d.getTime();

        // check if this token has expired
        if (nowTime - expireTime >= 0) {
            return false;
        }

        return true;
    }
    
	// ----------------------------------------------------------------------------

    /**
     * Return token for specified accessToken.  
     *
     * @return A valid Token object or null if the accessToken is not valid.
     */
    @Override
    public T fetchByAccessToken(String accessToken) {
        return fetchByAccessToken(accessToken, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
	public T fetchByAccessToken(String accessToken, boolean checkValid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accessToken", accessToken);
        
		T token = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
        
		if (token != null && checkValid) {
    		// NOTE: boolean argument must be FALSE
    		if (!isValid(token, false)) {
    			token = null;
    		}
    	}
        
        return token;
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public T fetchByRefreshToken(String refreshToken) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("refreshToken", refreshToken);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public T fetchActiveByClientIdAndUserId(String clientId, Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("clientId", clientId);
		filter.put("active", true);
		String[] sortOrder = { "token.last_updated desc" };

		T token = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, sortOrder, filter, false));

		// for testing, set isTokenValid(token, false)
		// for production always set isTokenValid(token, true)
		if (token == null || !isValid(token, true)) {
			return null;
		}

		return token;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchActiveByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("active", true);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByFilter(Map<String, Object> filter) {
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public T expire(T token) {
		token.setActive(false);
		token.setRetiredDate(new Date());
		token = update(token);
		return token;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void expireByClientId(String clientId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
		List<T> tokenList = fetchByCriteria(0, 99999, null, filter, false);
		for (T token : tokenList) {
			expire(token);
		}
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(T token) {
    	if (token.getCreatedDate() == null) {
			token.setCreatedDate(new Date());
		}
	}
}
