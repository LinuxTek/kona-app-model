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
import com.linuxtek.kona.app.sales.entity.KProductPurchase;
import com.linuxtek.kona.app.sales.entity.KPromo;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KDateUtil;

public abstract class KAbstractProductPurchaseService<PRODUCT_PURCHASE extends KProductPurchase, 
										      PRODUCT_PURCHASE_EXAMPLE,
										      PRODUCT extends KProduct,
										      PROMO extends KPromo,
										      USER extends KUser,
										      ACCOUNT extends KAccount>
		extends KAbstractService<PRODUCT_PURCHASE,PRODUCT_PURCHASE_EXAMPLE>
		implements KProductPurchaseService<PRODUCT_PURCHASE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractProductPurchaseService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract PRODUCT_PURCHASE getNewObject();
    
	protected abstract <S extends KPromoService<PROMO,ACCOUNT,PRODUCT>> S getPromoService();
    
	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract <S extends KProductService<PRODUCT>> S getProductService();
    
	protected abstract void sendPendingProductExpirationEmail(PRODUCT_PURCHASE productPurchase, int days);

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(PRODUCT_PURCHASE productPurchase) {
		if (productPurchase.getCreatedDate() == null) {
			productPurchase.setCreatedDate(new Date());
		}
        
		productPurchase.setLastUpdated(new Date());
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PRODUCT_PURCHASE update(PRODUCT_PURCHASE productPurchase) {
		super.update(productPurchase);
		
		List<PRODUCT_PURCHASE> children = fetchByParentId(productPurchase.getId());
        
		if (children != null && children.size()>0) {
			for (PRODUCT_PURCHASE child : children) {
				child.setEnabled(productPurchase.isEnabled());
				child.setExpirationDate(productPurchase.getExpirationDate());
				update(child);
			}
		}
		
		return productPurchase;
	}
    
	// ----------------------------------------------------------------------------
	
	@Override 
	public List<PRODUCT_PURCHASE> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew) {
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

	private List<PRODUCT_PURCHASE> fetchExpired() {
		Date now = new Date();
        
        // .andExpirationDateIsNotNull()
		Map<String,Object> filter = KMyBatisUtil.createFilter("!expirationDate", null);
        
		// .andExpirationDateLessThanOrEqualTo(now);
		filter.put("<=expirationDate", now);
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PRODUCT_PURCHASE fetchByAccountIdAndProductId(Long accountId, Long productId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		filter.put("productId", productId);
		//filter.put("enabled", true);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PRODUCT_PURCHASE> fetchByAccountId(Long accountId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		//filter.put("enabled", true);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PRODUCT_PURCHASE> fetchByParentId(Long parentId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	// NOTE: appId is optional. if set to null, then all products for account are returned.
	@Override
	public List<PRODUCT_PURCHASE> fetchByAccountIdAndAppId(Long accountId, Long appId) {
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
		List<PRODUCT_PURCHASE> result = fetchExpired();
		for (PRODUCT_PURCHASE purchase : result) {
			logger.debug("expireProducts: removing ProductPurchase:\n" + purchase);
			purchase.setAutoRenew(false);
			purchase.setExpirationDate(new Date());
			update(purchase);
		}
	}
    
	// ----------------------------------------------------------------------------

	@Override 
	public List<PRODUCT_PURCHASE> fetchSubscriptionsPendingExpiration(int days) {
		Date startDate = new Date();
		Date endDate = KDateUtil.addDays(startDate, days);
		return fetchSubscriptionsByExpirationDate(startDate, endDate, false); 
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public void remindSubscriptionsPendingExpiration(int days) {
		List<PRODUCT_PURCHASE> productPurchaseList = fetchSubscriptionsPendingExpiration(days);
		for (PRODUCT_PURCHASE purchase : productPurchaseList) {
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
	public PRODUCT_PURCHASE savePromoPurchase(Long userId, Long productId, Long promoId) {
		USER user = getUserService().fetchById(userId);
		PRODUCT product = getProductService().fetchById(productId);
		PROMO promo = getPromoService().fetchById(promoId);

		PRODUCT_PURCHASE purchase = fetchByAccountIdAndProductId(user.getAccountId(), product.getId());

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
