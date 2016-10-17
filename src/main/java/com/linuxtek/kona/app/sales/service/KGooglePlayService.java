package com.linuxtek.kona.app.sales.service;

import java.io.IOException;

import com.linuxtek.kona.app.sales.entity.KPurchase;
import com.linuxtek.kona.remote.service.KService;


public interface KGooglePlayService<PURCHASE extends KPurchase> extends KService {
    public static final String SERVICE_PATH = "rpc/KGooglePlayService";

	public PURCHASE getSubscription(Long appId, String packageName, String productId,
			String token) throws IOException;

	public PURCHASE getSubscription(Long productId, String token)
			throws IOException;
    
    public void cancelSubscription(Long appId, String packageName, String productId,
			String token) throws IOException;
    
	public void cancelSubscription(Long productId, String token)
			throws IOException;
	
	public PURCHASE getSubscription(Long accountId, Long productId) throws IOException;

	public void cancelSubscription(Long accountId, Long productId) throws IOException;

}
