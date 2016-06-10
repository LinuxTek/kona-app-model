/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.app.entity.KTokenLog;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.service.KAuthService;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KDateUtil;

public abstract class KAbstractAuthServiceImpl<U extends KUser, T extends KToken> implements KAuthService<U, T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAuthServiceImpl.class);

    /**
     * Login the user and return a token.
     *  - If the credentials match, a token is returned; if not, null is returned.
     */
    @Override 
    public T login(String clientId, String username, String password) {
        // if username is null, create a Guest token.  Otherwise
        // check username and password are valid.  If creds are not
        // valid then return a null token.
        //
        // NOTE: a Guest Token is not necessarily the same as a Guest User
        // which could be a normal user called guest with reduced privileges.
        U user = null;
        if (username != null) {
            // Get the user
            user = validateCredentials(username, password);

            if (user == null) {
                logger.info("Invalid username and/or password:" 
                            + "\nusername: {}\npassword: {}", username, password);
                return null;
            }

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
                return (activeToken);
            }
        }

        T token = createToken(clientId, user, getTokenScope(clientId));
        loginUser(user, token);
        
        return token;
    }
    
    
    
    
    @Override
    public T createToken(String clientId, KUser user, String scope) {
    	String accessToken = generateTokenString();
    	String refreshToken = generateTokenString();
        
    	Date now = new Date();
        Date accessExpiration = null;
        Date refreshExpiration = null;
        
        Integer timeout = getAccessTokenTimeout(clientId);
        if (timeout != null && timeout>0) {
            accessExpiration = KDateUtil.addSecs(new Date(), timeout);
        }
        
        timeout = getRefreshTokenTimeout(clientId);
        if (timeout != null && timeout>0) {
            refreshExpiration = KDateUtil.addSecs(new Date(), timeout);
        }

        T token = tokenInstance();
        token.setAccessCount(1L);
        token.setCreatedDate(now);
        token.setClientId(clientId);
        token.setAppId(getAppId(clientId));
        token.setGuest(true);
        
        if (user != null) {
        	token.setUserId(user.getId());
            token.setGuest(false);
        }
        
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

    	token = addToken(token);
        return token;
    }

    private <U extends KUser> void loginUser(U user, T token) {
        if (user == null) return;

        Date loginDate = user.getLoginDate();

        if (loginDate != null) {
            user.setLastLoginDate(loginDate);
        }

        user.setLoginDate(new Date());
        user.setLoggedIn(true);
        updateUser(user);
    }
    
    public T login(String accessToken, KServiceClient client) {
        T token = getToken(accessToken, false);
        return login(token, client);
    }
    
    @Override 
    public T login(T token, KServiceClient client) {
        if (token == null) return null;
        if (!isTokenValid(token, true)) return null;

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
        token = updateToken(token);

        KUser user = getUser(token.getUserId());
        if (user != null) {
        	user.setLastLoginDate(user.getLoginDate());
        	user.setLoginDate(now);
        	user.setLoggedIn(true);
        	updateUser(user);
        }
        return token;
    }

    private void logoutUser(T token) {
        if (token == null || token.getUserId() == null) return;

        KUser user = getUser(token.getUserId());
        if (user == null) return;

        Date loginDate = user.getLoginDate();

        if (loginDate != null) {
            user.setLastLoginDate(loginDate);
        }
        user.setLoginDate(null);
        user.setLoggedIn(false);

        updateUser(user);
    }

    @Override 
    public void logout(T token) {
        if (token == null) return;
        logger.debug("logout(token) called: token: " + token.getAccessToken());

        token.setLogoutDate(new Date());
        token.setActive(false);

        updateToken(token);
        logoutUser(token);
    }
    
    @Override 
    public void logout(String accessToken) {
        T token = getToken(accessToken, false);
        logout(token);
    }
    
    public void logoutUser(Long userId) {
    	List<T> tokenList = fetchActiveTokenListByUserId(userId);
    	for (T token : tokenList) {
    		logout(token);
    	}
    }

    @Override
    public boolean isTokenValid(T token) {
        logger.debug("isTokenValid(token) called");
        return (isTokenValid(token, true));
    }
    
    
    @Override
    public boolean isTokenValid(T t, boolean fetchFreshToken) {

        logger.debug("isTokenValid(token, fetchFreshToken) called: "
                    + "\n\ttoken: " + t
                    + "\n\tfetchFreshToken: " + fetchFreshToken);

        // check if we have a token for this token string
        if (t == null || t.getAccessToken() == null) {
            return (false);
        }

        T token = t;
        if (fetchFreshToken) {
            // update the token from the database
            token = getToken(t.getAccessToken(), false);
        } 

        if (token == null || !token.isActive()) {
            return (false);
        }

        // if we don't have an expireDate, then this token
        // never expires, so return true.
        Date d = token.getAccessExpirationDate();
        if (d == null) {
            return (true);
        }

        Date now = new Date();
        long nowTime = now.getTime();
        long expireTime = d.getTime();

        // check if this token has expired
        if (nowTime - expireTime >= 0) {
            // logging out the token is an unexpected side-effect. have
        	// an external process check for token validity.
            //logout(token); 
            return (false);
        }

        return (true);
    }
    
    public Boolean isAccessTokenValid(String accessToken) {
    	T token = getToken(accessToken);
    	return isTokenValid(token, false);
    }

    // requestMethodArgs is expected to a JSON string
    public void logRequest(T token, 
            String hostname, Double latitude, Double longitude,
            String browser, String sessionId, String requestUrl, 
            String requestClass, String requestMethod, String requestArgs) {

        logger.debug("calling logAndValidateToken: "
                + "\n\trequestClass: " + requestClass
                + "\n\trequestMethod: " + requestMethod
                + "\n\trequestMethodArgs: " + requestArgs);

        // truncate requestArgs to 250 chars (for db insertion)
        if (requestArgs != null && requestArgs.length()>250) {
            requestArgs = requestArgs.substring(0,250);
        }

        // log this token and associated request (whether or not it's valid)
        KTokenLog tokenLog = tokenLogInstance();
        tokenLog.setUserId(token.getUserId());
        tokenLog.setTokenId(token.getId());
        tokenLog.setHostname(hostname);
        tokenLog.setLatitude(latitude);
        tokenLog.setLongitude(longitude);
        tokenLog.setBrowser(browser);
        tokenLog.setRequestUrl(requestUrl);

        logger.debug("TokenLog:\n\t" + KClassUtil.toJson(tokenLog));

        addTokenLog(tokenLog);

        // hostname check: make sure the token passed in matches
        // the host from which this request originated. if the hosts 
        // don't match it could be a security threat.
        //
        // NOTE: should this logic be here or moved to the remote AuthService??
        if (token.getHostname() == null ||
                !token.getHostname().equals(hostname)) {
            //KAuthException.Type type = KAuthException.Type.INVALID_TOKEN;
            //throw new KAuthException("Token hostname error.", type);
            logger.warn("Token hostname mismatch:"
                + "\nrequest hostname: " + hostname
                + "\ntoken id: " + token.getId()
                + "\ntoken key: " + token.getAccessToken()
                + "\ntoken hostname: " + token.getHostname());
        }
    }
    
    
    // requestMethodArgs is expected to a JSON string
    public boolean logAndValidateToken(T token, 
            String hostname, Double latitude, Double longitude,
            String browser, String sessionId, String requestUrl, 
            String requestClass, String requestMethod, String requestArgs) {

        logRequest(token, hostname, latitude, longitude, browser, sessionId, 
        		requestUrl, requestClass, requestMethod, requestArgs);
        
        return (isTokenValid(token));
    }


    /**
     * Return token for specified accessToken.  
     *
     * @return A valid Token object or null if the accessToken is not valid.
     */
    @Override
    public T getToken(String accessToken) {
        return getToken(accessToken, true);
    }
    


    protected abstract T tokenInstance();

    protected abstract <L extends KTokenLog> L tokenLogInstance();

    protected abstract <U extends KUser> void updateUser(U user);

    protected abstract <U extends KUser> U getUser(Long userId);

    protected abstract <U extends KUser> U 
            validateCredentials(String username, String password);
    
    
    protected abstract Long getAppId(String clientId);
    
    protected abstract String generateTokenString();
}
