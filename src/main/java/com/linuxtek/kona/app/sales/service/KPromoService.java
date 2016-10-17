package com.linuxtek.kona.app.sales.service;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.app.sales.entity.KPromo;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KPromoService<PROMO extends KPromo, ACCOUNT extends KAccount, PRODUCT extends KProduct>
        extends KService, KDataService<PROMO> {
    
    public static final String SERVICE_PATH = "rpc/KPromoService";

	public PROMO fetchByCode(String promoCode);

	public PROMO fetchByCode(String promoCode, ACCOUNT account, PRODUCT product);

	// ----------------------------------------------------------------------
	public PROMO fetchSignupDefault(Long appId);

	// rule variables: price,
	public boolean isPromoValid(PROMO promo, ACCOUNT account, PRODUCT product);
}
