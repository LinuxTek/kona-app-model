/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.app.entity.KTokenLog;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.remote.service.KServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@KServiceRelativePath(KAuthService.SERVICE_PATH)
public interface KAuthService<T extends KToken> extends KService {

    // NOTE: SERVICE_PATH must begin with rpc/ prefix
    public static final String SERVICE_PATH = "rpc/kona/AuthService";

    public T login(String clientId, String username, String password) 
            throws KAuthException;
    
    public T login(T token, KServiceClient client)
            throws KAuthException;
    
    public T login(String accessToken, KServiceClient client)
            throws KAuthException;
    
    public Long getLoginTokenTypeId();

    public void logout(T token);
    public void logout(String accessToken);
    
    /**
	 * Return a valid token for the specified accessToken.  
     * 
     * Equivalent to getToken(accessToken, true);
	 *
	 * @return A valid Token object or null if the accessToken is not valid.
	 */
    public T getToken(String accessToken);
    
    /**
	 * Return a token for the specified accessToken.  
     * 
     * If checkValid is true, first run a check to see if the token
     * is currently valid.  If not, return null. 
     * 
     * If checkValid is false, this method will return a Token
     * if one is found for this accessToken.  It's up to the calling
     * program to check if the returned token is active and 
     * has not expired
	 *
	 * @return A valid Token object or null if the accessToken is not valid.
	 */
	public T getToken(String accessToken, boolean checkValid);

    /**
     * Check if a token is valid.  The token will first be refreshed
     * with the latest attributes before it is checked.
     */
    public boolean isTokenValid(T token);
    
    /**
     * Check if a token is valid.  Set fetchFreshToken to true to 
     * first refresh the token's attributes before checking its validity.
     */
    public boolean isTokenValid(T token, boolean fetchFreshToken);

    //-------------public boolean isSystemToken(T token);

    // methodArgs is expected to be a JSON string
    public boolean logAndValidateToken(T token, String hostname, 
    		Double latitude, Double longitude, String browser, 
    		String sessionId, String requestUrl, String className, 
            String methodName, String methodArgs);
    
    public void logRequest(T token, String hostname, 
    		Double latitude, Double longitude, String browser, 
    		String sessionId, String requestUrl, String className, 
            String methodName, String methodArgs);

    
    /** Number of seconds before AccessToken expires. Allow different apps to have different values. */
    public Integer getAccessTokenTimeout(String clientId);
    
    /** Number of seconds before RefreshToken expires. Allow different apps to have different values. */
    public Integer getRefreshTokenTimeout(String clientId);
    
    public String getTokenScope(String clientId);
    
    //public String getAccessToken();
    
    // No need to expose this to clients
    //public KTokenLog addTokenLog(KTokenLog tokenLog);
    
    public T fetchTokenById(Long tokenId);
    public T fetchTokenByAccessToken(String accessToken);
    public T fetchTokenByRefreshToken(String refreshToken);
    
    public List<T> fetchTokensByCriteria(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filterCriteria, boolean distinct);
    
    public List<T> fetchTokensByFilter(Map<String,Object> filter);

    /**
     * Create a token without logging the user in.
     * This method does not check if the user already has an active token associated with this clientId.
     */
	public T createToken(String clientId, KUser user, String scope);

	public T expireToken(T token);

	
    public List<T> fetchActiveTokenListByUserId(Long userId);
    
    public T getActiveToken(String clientId, Long userId);

    public T addToken(T token);

    public T updateToken(T token);

    public <L extends KTokenLog> L addTokenLog(L tokenLog);

    public <L extends KTokenLog> L updateTokenLog(L tokenLog);
}
