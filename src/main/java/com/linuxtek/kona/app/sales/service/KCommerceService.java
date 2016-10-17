package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KPayment;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.stripe.entity.KStripeException;


public interface KCommerceService<PAYMENT extends KPayment,
								  ACCOUNT extends KAccount,
								  CART extends KCart,
								  INVOICE extends KInvoice> 
		extends KService {
    
    public static final String SERVICE_PATH = "rpc/KCommerceService";
    
	// ----------------------------------------------------------------------
    
	public PAYMENT charge(CART cart, String cardToken, Boolean setDefaultCard,
			String paymentOption, KServiceClient client) throws KStripeException;

	// NOTE, if a payment fails while charging a cart, the generated invoice
	// is immediately closed so a payment retry does not occur.  The assumption
	// is that carts are charged for live payments and invoices are charged
	// for recurring payments.
	public PAYMENT charge(CART cart, String cardToken, KServiceClient client) throws KStripeException;

	public PAYMENT charge(INVOICE invoice, KServiceClient client) throws KStripeException;

	public PAYMENT charge(INVOICE invoice, String cardToken,
			boolean paymentRequired, KServiceClient client) throws KStripeException;
	
	public PAYMENT externalCharge(CART cart, Long paymentTypeId, BigDecimal paidAmount,
			String processorRef, BigDecimal processorFee, KServiceClient client);
    
	public PAYMENT externalCharge(INVOICE invoice, Long paymentTypeId, BigDecimal paidAmount,
			String processorRef, BigDecimal processorFee, KServiceClient client);
	
	public PAYMENT inAppPurchase(String store, Long userId, Long appId, Long subscriptionId,
			Integer quantity, String promoCode, BigDecimal paidAmount,
			String processorRef, String receipt, String notes,
			KServiceClient client);
	
	public PAYMENT processorCharge(INVOICE invoice, String cardToken, KServiceClient client);
    
	// ----------------------------------------------------------------------

//	public Date getSubscriptionEndDate(Account account, User user,
//			Subscription sub, Integer quantity, Promo promo);
//
//	public BigDecimal getItemDiscount(Subscription subscription, Promo promo);
//	public String getItemDescription(User uer, Subscription sub);
//	public String getItemDiscountDescription(Promo promo);

	public String getCardLast4(Long appId, ACCOUNT account);
	
	

	// NOTE: this method is not Transactional.  We don't want to rollback
	// payments that have already been processed.
	public void renewSubscriptions();
    
	public void retryFailedPayments();

    
	// ----------------------------------------------------------------------
}
