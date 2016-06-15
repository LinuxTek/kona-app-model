/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KTokenService<T extends KToken> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/TokenService";

	/**
	 * Return a valid token for the specified accessToken.  
	 * 
	 * Equivalent to fetchByAccessToken(accessToken, false);
	 *
	 * @return A valid Token object or null if the accessToken is not valid.
	 */
	public T fetchByAccessToken(String accessToken);
    
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
	public T fetchByAccessToken(String accessToken, boolean checkValid);

	public T fetchByRefreshToken(String refreshToken);

	public T fetchActiveByClientIdAndUserId(String clientId, Long userId);

	public List<T> fetchActiveByUserId(Long userId);
	
	public List<T> fetchByClientId(String clientId);

	public List<T> fetchByFilter(Map<String,Object> filter);

	public T expire(T token);
	
	public void expireByClientId(String clientId);

	/**
	 * Check if a token is valid.  The token will first be refreshed
	 * with the latest attributes before it is checked.
	 */
	public boolean isValid(T token);
    
	public boolean isValid(String accessToken);
    

	/**
	 * Check if a token is valid.  Set fetchFreshToken to true to 
	 * first refresh the token's attributes before checking its validity.
	 */
	public boolean isValid(T token, boolean fetchFreshToken);
    
	public boolean isValid(String accessToken, boolean fetchFreshToken);

}
