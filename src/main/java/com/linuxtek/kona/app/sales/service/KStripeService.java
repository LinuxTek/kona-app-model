package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;
import java.util.Map;

import com.linuxtek.kona.app.core.entity.KAccount;

import com.linuxtek.kona.remote.service.KService;

import com.linuxtek.kona.stripe.entity.KCard;
import com.linuxtek.kona.stripe.entity.KCharge;
import com.linuxtek.kona.stripe.entity.KStripeException;

public interface KStripeService<ACCOUNT extends KAccount> 
		extends KService, com.linuxtek.kona.stripe.service.KStripeService {

	public String addCustomer(Long appId, ACCOUNT account);
	
	public void deleteCustomer(Long appId, ACCOUNT account);
	
	public void updateCustomer(Long appId, ACCOUNT account);
	
	public KCharge chargeCustomer(Long appId, ACCOUNT account, BigDecimal amount,
			String description, String receiptEmail,
			Map<String,Object> metadata, Map<String,Object> shipping) throws KStripeException;

	public KCard addPrimaryCard(Long appId, ACCOUNT account, KCard card);
	
	public KCard addPrimaryCard(Long appId, ACCOUNT account, String cardToken);
	
	public KCard updatePrimaryCard(Long appId, ACCOUNT account, KCard card);
	
	public KCard updatePrimaryCard(Long appId, ACCOUNT account, String cardToken);
	
	public KCard getPrimaryCardByUserId(Long appId, Long userId);
	
	public KCard getPrimaryCard(Long appId, ACCOUNT account);
	
	public String getPrimaryCardLast4ByUserId(Long appId, Long userId);

	public ACCOUNT updateAccountStripeUidByCardToken(Long appId, ACCOUNT account, String cardToken);

}
