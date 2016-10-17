package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;

public interface KCartService<CART extends KCart>
        extends KService, KDataService<CART> {
    public static final String SERVICE_PATH = "rpc/KCartService";

	public List<CART> fetchByUserId(Long userId);

	// NOTE: a session could have many carts
	public List<CART> fetchBySessionId(String sessionId);
    
	public CART fetchActiveBySessionId(String sessionId);
    
	public CART fetchActiveByAccessToken(String accessToken);
    
	public CART fetchActiveByUserIdAndAppId(Long userId, Long appId);
	
	
	public CART getActiveCartByUserIdAndAppId(Long userId, Long appId);
    
	public CART getCart(Long userId, Long appId);
    
	public CART getCart(Long appId, KServiceClient client);
    
	public CART getCart(Long userId, Long appId, KServiceClient client);
    
	public CART createCart(Long userId, Long appId, KServiceClient client);
    
	public CART checkout(CART cart);
    
	public CART updateCartTotals(CART cart);
    
	public void updateCart(CART cart);
    
	public void expireCart(Long cartId);
    
	public void expireCart(CART cart);
}
