package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.app.sales.entity.KPurchase;
import com.linuxtek.kona.app.sales.entity.KPromo;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KDateUtil;

public abstract class KAbstractPurchaseService<PURCHASE extends KPurchase, 
										      PURCHASE_EXAMPLE,
										      PRODUCT extends KProduct,
										      PROMO extends KPromo,
										      USER extends KUser,
										      ACCOUNT extends KAccount>
		extends KAbstractService<PURCHASE,PURCHASE_EXAMPLE>
		implements KPurchaseService<PURCHASE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPurchaseService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract PURCHASE getNewObject();
    
	protected abstract <S extends KPromoService<PROMO,ACCOUNT,PRODUCT>> S getPromoService();
    
	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract <S extends KProductService<PRODUCT>> S getProductService();
    
	protected abstract void sendPendingProductExpirationEmail(PURCHASE purchase, int days);

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(PURCHASE purchase) {
		if (purchase.getCreatedDate() == null) {
			purchase.setCreatedDate(new Date());
		}
        
		purchase.setUpdatedDate(new Date());
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PURCHASE update(PURCHASE purchase) {
		super.update(purchase);
		
		List<PURCHASE> children = fetchByParentId(purchase.getId());
        
		if (children != null && children.size()>0) {
			for (PURCHASE child : children) {
				child.setEnabled(purchase.isEnabled());
				child.setExpirationDate(purchase.getExpirationDate());
				update(child);
			}
		}
		
		return purchase;
	}
    
	// ----------------------------------------------------------------------------
	
	@Override 
	public List<PURCHASE> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew) {
		if (endDate == null) {
			endDate = startDate;
		}
		
        // .andExpirationDateIsNotNull()
		Map<String,Object> filter = KMyBatisUtil.createFilter("!expirationDate", null);
        
        // .andExpirationDateGreaterThanOrEqualTo(startDate)
		filter.put(">=expirationDate", startDate);
        
        // .andExpirationDateLessThanOrEqualTo(endDate);
		filter.put("<=expirationDate", endDate);
        
		if (autoRenew != null) {
			filter.put("autoRenew", autoRenew);
		}
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	private List<PURCHASE> fetchExpired() {
		Date now = new Date();
        
        // .andExpirationDateIsNotNull()
		Map<String,Object> filter = KMyBatisUtil.createFilter("!expirationDate", null);
        
		// .andExpirationDateLessThanOrEqualTo(now);
		filter.put("<=expirationDate", now);
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PURCHASE fetchByAccountIdAndProductId(Long accountId, Long productId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		filter.put("productId", productId);
		//filter.put("enabled", true);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PURCHASE> fetchByAccountId(Long accountId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		//filter.put("enabled", true);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PURCHASE> fetchByParentId(Long parentId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	// NOTE: appId is optional. if set to null, then all products for account are returned.
	@Override
	public List<PURCHASE> fetchByAccountIdAndAppId(Long accountId, Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		if (appId != null) {
			filter.put("appId", appId);
		}
		//filter.put("enabled", true);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public void expireSubscriptions() {
		List<PURCHASE> result = fetchExpired();
		for (PURCHASE purchase : result) {
			logger.debug("expireProducts: removing Purchase:\n" + purchase);
			purchase.setAutoRenew(false);
			purchase.setExpirationDate(new Date());
			update(purchase);
		}
	}
    
	// ----------------------------------------------------------------------------

	@Override 
	public List<PURCHASE> fetchSubscriptionsPendingExpiration(int days) {
		Date startDate = new Date();
		Date endDate = KDateUtil.addDays(startDate, days);
		return fetchSubscriptionsByExpirationDate(startDate, endDate, false); 
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public void remindSubscriptionsPendingExpiration(int days) {
		List<PURCHASE> purchaseList = fetchSubscriptionsPendingExpiration(days);
		for (PURCHASE purchase : purchaseList) {
			if (purchase.getProductId() == null || purchase.isAutoRenew()) continue;
			try {
				sendPendingProductExpirationEmail(purchase, days);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
    
	// ----------------------------------------------------------------------------



	@Override
	public PURCHASE savePromoPurchase(Long userId, Long productId, Long promoId) {
		USER user = getUserService().fetchById(userId);
		PRODUCT product = getProductService().fetchById(productId);
		PROMO promo = getPromoService().fetchById(promoId);

		PURCHASE purchase = fetchByAccountIdAndProductId(user.getAccountId(), product.getId());

		if (purchase == null) {
			purchase = getNewObject();
			purchase.setAccountId(user.getAccountId());
			purchase.setUserId(userId);
			purchase.setAppId(product.getAppId());
			purchase.setProductId(productId);
			purchase.setCreatedDate(new Date());
		}


		purchase.setPromoId(promoId);
		purchase.setAutoRenew(false);
		purchase.setEnabled(true);

		Integer days = promo.getSubscriptionDays();

		if (days != null && days >= 0) {
			Date expirationDate = KDateUtil.addDays(new Date(), days);
			purchase.setExpirationDate(expirationDate);
		} else {
			purchase.setExpirationDate(null);
		}

		if (purchase.getId() == null) {
			purchase = add(purchase);
		} else {
			purchase = update(purchase);
		}

		return purchase;
	}
}
