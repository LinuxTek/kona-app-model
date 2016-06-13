/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KToken;

public abstract class KAbstractTokenService<T extends KToken> implements KTokenService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractTokenService.class);
    
    @Override
    public boolean isValid(T token) {
        return isValid(token, false);
    }
    
    
    @Override
    public boolean isValid(String accessToken) {
    	return isValid(accessToken, false);
    }
    
    
    @Override
    public boolean isValid(String accessToken, boolean fetchFreshToken) {
    	T token = fetchByAccessToken(accessToken);
    	return isValid(token, fetchFreshToken);
    }

 
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
    



    /**
     * Return token for specified accessToken.  
     *
     * @return A valid Token object or null if the accessToken is not valid.
     */
    @Override
    public T fetchByAccessToken(String accessToken) {
        return fetchByAccessToken(accessToken, false);
    }
}
