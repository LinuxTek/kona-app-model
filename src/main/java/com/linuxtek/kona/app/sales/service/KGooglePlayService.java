package com.linuxtek.kona.app.sales.service;

import java.io.IOException;

import com.linuxtek.kona.app.sales.entity.KProductPurchase;
import com.linuxtek.kona.remote.service.KService;


public interface KGooglePlayService<PRODUCT_PURCHASE extends KProductPurchase> extends KService {
    public static final String SERVICE_PATH = "rpc/KGooglePlayService";

	public PRODUCT_PURCHASE getSubscription(Long appId, String packageName, String productId,
			String token) throws IOException;

	public PRODUCT_PURCHASE getSubscription(Long productId, String token)
			throws IOException;
    
    public void cancelSubscription(Long appId, String packageName, String productId,
			String token) throws IOException;
    
	public void cancelSubscription(Long productId, String token)
			throws IOException;
	
	public PRODUCT_PURCHASE getSubscription(Long accountId, Long productId) throws IOException;

	public void cancelSubscription(Long accountId, Long productId) throws IOException;

}
