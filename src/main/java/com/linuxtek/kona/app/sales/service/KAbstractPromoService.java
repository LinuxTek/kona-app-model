package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.app.sales.entity.KPurchase;
import com.linuxtek.kona.app.sales.entity.KPromo;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.locale.KValidator;
import com.linuxtek.kona.util.KClassUtil;

public abstract class KAbstractPromoService<PROMO extends KPromo, 
										    PROMO_EXAMPLE,
										    ACCOUNT extends KAccount,
										    PRODUCT extends KProduct,
										    PURCHASE extends KPurchase>
		extends KAbstractService<PROMO,PROMO_EXAMPLE>
		implements KPromoService<PROMO,ACCOUNT,PRODUCT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPromoService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();
    
	// ----------------------------------------------------------------------------

	@Override
	public void validate(PROMO promo) {
		if (promo.getCreatedDate() == null) {
			promo.setCreatedDate(new Date());
		}
        
		promo.setLastUpdated(new Date());
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PROMO fetchByCode(String code) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("name", code);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PROMO fetchByCode(String promoCode, ACCOUNT account, PRODUCT product) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("promoCode", promoCode);
        
		PROMO promo = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
        
		if (promo != null && !isPromoValid(promo, account, product)) {
			promo = null;
		}
        
		return promo;
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	public PROMO fetchSignupDefault(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("enabled", true);
        filter.put("signupDefault", true);
        
		PROMO promo = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
        
		if (promo != null && !isPromoValid(promo, null, null)) {
			promo = null;
		}
        
		return promo;
	}
    
	// ----------------------------------------------------------------------------

	// rule variables: price,
	public boolean isPromoValid(PROMO promo, ACCOUNT account, PRODUCT product) {
		boolean valid = isValid(promo, account, product);

		if (valid && product != null) {
			String rule = promo.getValidationRule();
			if (rule != null) {
				Map<String,Object> ruleVarMap = getRuleVarMap(product);
				if (!KValidator.matchRule(ruleVarMap, rule)) {
					logger.info("Invalid Promo: rule violation: " 
							+ "\nrule: " + rule
							+ "\nvarMap: " + KClassUtil.toJson(ruleVarMap));
					valid = false;
				}
			}
		}

		return valid;
	}
    
	// ----------------------------------------------------------------------------

	private Map<String,Object> getRuleVarMap(PRODUCT product) {
		Map<String,Object> varMap = new HashMap<String,Object>();
		varMap.put("productName", product.getName());
		varMap.put("appId", product.getAppId());
		varMap.put("price", product.getPrice());
		varMap.put("setupFee", product.getSetupFee());
		varMap.put("trialDays", product.getTrialDays());
		varMap.put("subscriptionDays", product.getSubscriptionDays());
		return varMap;
	}
    
	// ----------------------------------------------------------------------------

	private boolean isValid(PROMO promo, ACCOUNT account, PRODUCT product) {
		if (promo == null) {
			logger.info("Invalid Promo: promo is null");
			return false;
		}

		if (!promo.isEnabled()) {
			logger.info("Invalid Promo: promo is disabled: " + promo.getName());
			return false;
		}

		Date now = new Date();

		Date startDate = promo.getStartDate();
		if (startDate != null && now.before(startDate)) {
			logger.info("Invalid Promo: promo start date: " + startDate);
			return false;
		}

		Date endDate = promo.getEndDate();
		if (endDate != null && now.after(endDate)) {
			logger.info("Invalid Promo: promo expired: " + endDate);
			return false;
		}

		Integer useCount = promo.getUseCount();
		Integer maxUseCount = promo.getMaxUseCount();
		if (useCount != null && maxUseCount != null 
				&& useCount >= maxUseCount) {
			logger.info("Invalid Promo: max use count: " + maxUseCount);
			return false;
		}


		if (promo.getUsePerAccount() != null && account != null && product != null) {
			List<PURCHASE> purchases = getPurchaseService().fetchByAccountId(account.getId());
			useCount = 0;
			for (PURCHASE purchase : purchases) {
				if (purchase.getPromoId() != null && purchase.getPromoId().equals(promo.getId())) {
					useCount +=1;
				}
			}
			
			if (useCount >= promo.getUsePerAccount()) {
				logger.info("Invalid Promo: account reached max use count:\naccountId: " + account.getId());
				return false;
			}
		}

		return true;
	}


}
