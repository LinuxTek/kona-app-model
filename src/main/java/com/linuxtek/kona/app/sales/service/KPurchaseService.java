package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;

import com.linuxtek.kona.app.sales.entity.KPurchase;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KPurchaseService<PURCHASE extends KPurchase> 
		extends KService, KDataService<PURCHASE> {
    
    public static final String SERVICE_PATH = "rpc/KPurchaseService";

	public PURCHASE fetchByAccountIdAndProductId(Long accountId, Long productId);
	
	public List<PURCHASE> fetchByParentId(Long parentId);
    
	public List<PURCHASE> fetchByAccountId(Long accountId);
    
	public List<PURCHASE> fetchByAccountIdAndAppId(Long accountId, Long appId);

	public List<PURCHASE> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew);
	
    // fetch Purchase that will expire (autoRenew is off) in the next "days" days
	public List<PURCHASE> fetchSubscriptionsPendingExpiration(int days);
	
	public void remindSubscriptionsPendingExpiration(int days);
	
	public void expireSubscriptions();
	
	// create or update
	public PURCHASE savePromoPurchase(Long userId, Long productId, Long promoId);
}
       

   
