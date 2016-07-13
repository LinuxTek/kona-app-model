/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KAppCreds;
import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserAuth;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KDateUtil;

public abstract class KAbstractAuthService<U extends KUser, UA extends KUserAuth, 
											T extends KToken, AC extends KAppCreds>
		implements KAuthService<U, T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAuthService.class);
    
    // ----------------------------------------------------------------------------
    
    protected abstract <S extends KTokenService<T>> S getTokenService();
    
    protected abstract <S extends KUserService<U>> S getUserService();
    
    protected abstract <S extends KUserAuthService<UA,U>> S getUserAuthService();
    
    protected abstract <S extends KAppCredsService<AC>> S getAppCredsService();

    // ----------------------------------------------------------------------------

    protected abstract T getNewObject();
    
    protected abstract Long getLoggedInPresenceId();
    
    protected abstract Long getLoggedOutPresenceId();
    
    // ----------------------------------------------------------------------------

    protected String generateToken() {
    	return KUtil.uuid();
    }
    
    // ----------------------------------------------------------------------------
    
    /**
     * Login the user and return a token.
     *  - If the credentials match, a token is returned; if not, null is returned.
     */
    @Override 
    public T login(String clientId, String username, String password) {
    	if (username == null) {
    		throw new KAuthException("Username is null");
    	}

    	// Get the user
    	U user = null;
    	
    	try {
    		user = getUserAuthService().validateCredentials(username, password);
    	} catch (KAuthException e) {
    		logger.debug(e.getMessage(), e);
    	}
        
        return login(clientId, user);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override 
    public T login(String clientId, U user) {

    	// see if we have an active token for this user and app
    	T activeToken = getTokenService().fetchActiveByClientIdAndUserId(clientId, user.getId());
    	if (activeToken != null) {

    		Long accessCount = activeToken.getAccessCount();
    		if (accessCount == null) {
    			accessCount = 1L;
    		} else {
    			accessCount += 1;
    		}
    		activeToken.setAccessCount(accessCount);
    		activeToken.setLastLoginDate(activeToken.getLoginDate());
    		activeToken.setLoginDate(new Date());

    		logger.debug("Active Token:\n" + activeToken);

    		getTokenService().update(activeToken);
            
    		loginUser(user, activeToken);
            
    		return activeToken;
    	}

    	T token = createToken(user, clientId, getClientDefaultScope(clientId));
        
    	loginUser(user, token);

    	return token;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public T createToken(U user, String clientId) {
    	return createToken(user, clientId, null);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public T createToken(U user, String clientId, String scope) {
    	String accessToken = generateToken();
    	String refreshToken = generateToken();
        
    	Date now = new Date();
        Date accessExpiration = null;
        Date refreshExpiration = null;
        
        if (scope == null) {
        	scope = getClientDefaultScope(clientId);
        }
        
        Integer timeout = getClientAccessTokenTimeout(clientId);
        if (timeout != null && timeout>0) {
            accessExpiration = KDateUtil.addSecs(new Date(), timeout);
        }
        
        timeout = getClientRefreshTokenTimeout(clientId);
        if (timeout != null && timeout>0) {
            refreshExpiration = KDateUtil.addSecs(new Date(), timeout);
        }

        T token = getNewObject();
        token.setAccessCount(1L);
        token.setCreatedDate(now);
        token.setClientId(clientId);
        token.setAppId(getAppId(clientId));
        token.setUserId(user.getId());
    	token.setTypeId(getLoginTokenTypeId());
    	token.setAccessToken(accessToken);
    	token.setRefreshToken(refreshToken);
    	token.setScope(scope);
    	token.setUsername(user.getUsername());
    	token.setLastLoginDate(null);
    	token.setLoginDate(now);
    	token.setAccessExpirationDate(accessExpiration);
    	token.setRefreshExpirationDate(refreshExpiration);
    	token.setActive(true);

    	return getTokenService().add(token);
    }
    
    // ----------------------------------------------------------------------------

    private void loginUser(U user, T token) {
        if (user == null) return;

        Date loginDate = user.getLoginDate();

        if (loginDate != null) {
            user.setLastLoginDate(loginDate);
        }

        user.setLoginDate(new Date());
        user.setLoggedIn(true);
        user.setPresenceId(getLoggedInPresenceId());

        getUserService().update(user);


    }
    
    // ----------------------------------------------------------------------------
    
    @Override 
    public T login(String accessToken, KServiceClient client) {
        T token = getTokenService().fetchByAccessToken(accessToken, false);
        return login(token, client);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override 
    public T login(T token, KServiceClient client) {
        if (token == null) {
        	return null;
        }
        
        if (!getTokenService().isValid(token, true)) {
        	return null;
        }

        Date now = new Date();
        token.setLastLoginDate(token.getLoginDate());
        token.setLoginDate(now);
        
        if (client != null) {
        	token.setHostname(client.getHostname());
        	token.setBrowser(client.getBrowser());
            token.setLatitude(client.getLatitude());
            token.setLongitude(client.getLongitude());
            token.setAppVersion(client.getAppVersion());
            token.setAppBuild(client.getAppBuild());
        }
        
        token = getTokenService().update(token);

        U user = getUserService().fetchById(token.getUserId());
        
        loginUser(user, token);
        
        return token;
    }

    // ----------------------------------------------------------------------------
    
    private void logoutUser(T token) {
        if (token == null || token.getUserId() == null) return;

        U user = getUserService().fetchById(token.getUserId());
        
        if (user == null) return;

        Date loginDate = user.getLoginDate();

        if (loginDate != null) {
            user.setLastLoginDate(loginDate);
        }
        
        user.setLoginDate(null);
        
        user.setLoggedIn(false);
        
        user.setPresenceId(getLoggedOutPresenceId());

        getUserService().update(user);
    }
    
    // ----------------------------------------------------------------------------

    @Override 
    public void logout(T token) {
        if (token == null) return;
        logger.debug("logout(token) called: token: " + token.getAccessToken());

        token.setLogoutDate(new Date());
        token.setActive(false);

        getTokenService().update(token);
        logoutUser(token);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override 
    public void logout(String accessToken) {
        T token = getTokenService().fetchByAccessToken(accessToken);
        logout(token);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public void logout(U user) {
        if (user == null) return;
    	List<T> tokenList = getTokenService().fetchActiveByUserId(user.getId());
    	for (T token : tokenList) {
    		logout(token);
    	}
    }
    
    // ----------------------------------------------------------------------------
    
    /** Number of seconds before AccessToken expires. */ 
    protected Integer getClientAccessTokenTimeout(String clientId) {
    	AC creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getAccessTokenTimeout();
    }
    
    // ----------------------------------------------------------------------------
    
    /** Number of seconds before RefreshToken expires. */
    protected Integer getClientRefreshTokenTimeout(String clientId) {
    	AC creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getRefreshTokenTimeout();
    }
    
    // ----------------------------------------------------------------------------
    
    /** Default scope assigned to this client */
    protected String getClientDefaultScope(String clientId) {
    	AC creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getScope();
    }

    // ----------------------------------------------------------------------------
    
    protected Long getAppId(String clientId) {
    	AC creds = getAppCredsService().fetchByClientId(clientId);
    	return creds.getAppId();
    }
    
    // ----------------------------------------------------------------------------
   
}
