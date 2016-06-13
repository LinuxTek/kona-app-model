/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KToken;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserAuth;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;


/**
 * The client side stub for the RPC service.
 */
public interface KAuthService<U extends KUser, T extends KToken> extends KService {
    public static final String SERVICE_PATH = "rpc/kona/AuthService";

    public T login(String clientId, String username, String password) throws KAuthException;
    
    public T login(String clientId, U user);
    
    public T login(T token, KServiceClient client) throws KAuthException;
    
    public T login(String accessToken, KServiceClient client) throws KAuthException;
    
    public void logout(U user);
    
    public void logout(T token);
    
    public void logout(String accessToken);
    
    /** Return the token type issued when a user logs in.  
     *  Assumes an external reference to TokenType object that includes references to BASIC, BEARER, etc. */ 
    public Long getLoginTokenTypeId();
    
    /** Number of seconds before AccessToken expires. */ 
    public Integer getClientAccessTokenTimeout(String clientId);
    
    /** Number of seconds before RefreshToken expires. */
    public Integer getClientRefreshTokenTimeout(String clientId);
    
    /** Default scope assigned to this client */
    public String getClientDefaultScope(String clientId);
    
    /**
     * Create a token without logging the user in.
     * 
     * This method does not check if the user already has an active token associated with this clientId.
     */
	public T createToken(U user, String clientId, String scope);

}
