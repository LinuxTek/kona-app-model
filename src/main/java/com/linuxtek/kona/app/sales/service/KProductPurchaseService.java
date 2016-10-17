package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;

import com.linuxtek.kona.app.sales.entity.KProductPurchase;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KProductPurchaseService<PRODUCT_PURCHASE extends KProductPurchase> 
		extends KService, KDataService<PRODUCT_PURCHASE> {
    
    public static final String SERVICE_PATH = "rpc/KProductPurchaseService";

	public PRODUCT_PURCHASE fetchByAccountIdAndProductId(Long accountId, Long productId);
	
	public List<PRODUCT_PURCHASE> fetchByParentId(Long parentId);
    
	public List<PRODUCT_PURCHASE> fetchByAccountId(Long accountId);
    
	public List<PRODUCT_PURCHASE> fetchByAccountIdAndAppId(Long accountId, Long appId);

	public List<PRODUCT_PURCHASE> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew);
	
    // fetch ProductPurchase that will expire (autoRenew is off) in the next "days" days
	public List<PRODUCT_PURCHASE> fetchSubscriptionsPendingExpiration(int days);
	
	public void remindSubscriptionsPendingExpiration(int days);
	
	public void expireSubscriptions();
	
	// create or update
	public PRODUCT_PURCHASE savePromoPurchase(Long userId, Long productId, Long promoId);
}
       

   
