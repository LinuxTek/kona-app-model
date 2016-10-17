package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;


public interface KCartItemService<CART_ITEM extends KCartItem, CART extends KCart>
        extends KService, KDataService<CART_ITEM> {
            
    public static final String SERVICE_PATH = "rpc/KCartItemService";

	public List<CART_ITEM> fetchByCartId(Long cartId);
	
	// ----------------------------------------------------------------------
	public List<CART_ITEM> getCartItemList(CART cart);
    
	public CART_ITEM addCartItem(CART cart, Long userId, 
			Long subscriptionId, Long promoId);

	public CART_ITEM addCartItem(KServiceClient client, Long userId, 
			Long appId, Long subscriptionId, Integer quantity, 
			Long promoId, String notes);

	public CART_ITEM addCartItem(CART cart, Long userId, 
			Long subscriptionId, Integer quantity, 
			Long promoId, String notes);
	
	public CART_ITEM removeCartItem(Long itemId);
    
	public CART_ITEM removeCartItem(Long cartId, Long itemId);
    
	public CART_ITEM removeCartItem(CART cart, CART_ITEM item);
	
	public CART_ITEM updateCartItem(Long itemId);
    
	public CART_ITEM updateCartItem(Long cartId, Long itemId);
    
	public CART_ITEM updateCartItem(CART cart, CART_ITEM item);
    
	public CART_ITEM updateCartItem(CART_ITEM item);
}
