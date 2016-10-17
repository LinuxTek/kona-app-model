package com.linuxtek.kona.app.sales.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAccountService;
import com.linuxtek.kona.app.core.service.KSystemService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KInvoiceItem;
import com.linuxtek.kona.app.sales.entity.KPayment;
import com.linuxtek.kona.app.sales.entity.KPaymentStatus;
import com.linuxtek.kona.app.sales.entity.KPaymentType;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.app.sales.entity.KProductPurchase;
import com.linuxtek.kona.app.sales.entity.KPromo;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.stripe.entity.KCard;
import com.linuxtek.kona.stripe.entity.KCharge;
import com.linuxtek.kona.stripe.entity.KStripeException;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KDateUtil;
import com.linuxtek.kona.util.KStringUtil;
import com.linuxtek.kona.util.KSystemUtil;

public abstract class KAbstractCommerceService<CART_ITEM extends KCartItem, 
										   CART_ITEM_EXAMPLE,
										   CART extends KCart,
										   USER extends KUser,
										   ACCOUNT extends KAccount,
										   PAYMENT extends KPayment,
										   INVOICE extends KInvoice,
										   INVOICE_ITEM extends KInvoiceItem,
										   PROMO extends KPromo,
										   PRODUCT extends KProduct,
										   PRODUCT_PURCHASE extends KProductPurchase>
		implements KCommerceService<PAYMENT,ACCOUNT,CART,INVOICE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCartItemService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract PAYMENT getNewPaymentObject();
    
	protected abstract PRODUCT_PURCHASE getNewProductPurchaseObject();
    
	protected abstract String getGooglePackageName(Long appId);
    
	protected abstract String getAppleVerifyReceiptUrl(Long appId);
    
	protected abstract String getAppleVerifyReceiptSandboxUrl(Long appId);
    
	protected abstract String getAppleAppSharedSecret(Long appId);
    
	protected abstract void sendReceiptEmail(INVOICE invoice, boolean externalPayment);
    
	protected abstract void sendInvalidCardEmail(INVOICE invoice);
       
	protected abstract Long getSystemAppId();

	protected abstract <S extends KAccountService<ACCOUNT>> S getAccountService();
    
	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract <S extends KCartService<CART>> S getCartService();
    
	protected abstract <S extends KCartItemService<CART_ITEM,CART>> S getCartItemService();
    
	protected abstract <S extends KInvoiceService<INVOICE,CART,CART_ITEM>> S getInvoiceService();
    
	protected abstract <S extends KInvoiceItemService<INVOICE_ITEM,INVOICE,CART_ITEM>> S getInvoiceItemService();
    
	protected abstract <S extends KPromoService<PROMO,ACCOUNT,PRODUCT>> S getPromoService();
    
	protected abstract <S extends KProductService<PRODUCT>> S getProductService();
    
	protected abstract <S extends KProductPurchaseService<PRODUCT_PURCHASE>> S getProductPurchaseService();
    
	protected abstract <S extends KPaymentService<PAYMENT,INVOICE>> S getPaymentService();
    
	protected abstract <S extends KStripeService<ACCOUNT>> S getStripeService();
    
	protected abstract <S extends KGooglePlayService<PRODUCT_PURCHASE>> S getGooglePlayService();
    
	protected abstract <S extends KSystemService> S getSystemService();
    
	// ----------------------------------------------------------------------------
    
    protected ACCOUNT getAccountByUserId(Long userId) {
        if (userId == null) return null;
        
        ACCOUNT account = null;
        
        USER user = getUserService().fetchById(userId);
        
        if (user != null && user.getAccountId() != null) {
        	account = getAccountService().fetchById(user.getAccountId());
        }
        
        return account;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT charge(CART cart, String cardToken, Boolean setDefaultCard, 
            String paymentOption, KServiceClient client) throws KStripeException {
            // paymentOption: defaultCard | newCard
            // we always charge the default (active) card associated
            // with the customer.  The only situation we need to account
            // for is a paymentOption=newCard setDefaultCard=false
            //
            // To keep things simple, we always charge the new card
            // if cardToken is not null.  Therefore, we set cardToken
            // to null after setting it as the customer's default card
            // to indicate that the customer should be charged.
            if (paymentOption != null && paymentOption.equals("newCard")) {
                if (setDefaultCard) {
                    Long userId = cart.getUserId();
                    Long appId = client.getAppId();
                    ACCOUNT account = getAccountByUserId(userId);
                    getStripeService().addCustomerCard(appId, account.getStripeUid(), 
                    		cardToken);
                    cardToken = null;
                }
            }
            
            return charge(cart, cardToken, client);
    }
    
	// ----------------------------------------------------------------------------
        
    @Override
    // NOTE, if a payment fails while charging a cart, the generated invoice
    // is immediately closed so a payment retry does not occur.  The assumption
    // is that carts are charged for live payments and invoices are charged
    // for recurring payments.
    public PAYMENT charge(CART cart, String cardToken, KServiceClient client) throws KStripeException {
            if (cart == null) {
                throw new IllegalStateException("Cart is empty");
            }
            
            boolean paymentRequired = false;
            if (cart.getTotal() != null && 
            		cart.getTotal().compareTo(new BigDecimal(0))>0) {
                paymentRequired = true;
            }
            
            INVOICE invoice = getInvoiceService().createInvoice(cart);
            PAYMENT payment = charge(invoice, cardToken, paymentRequired, client);
            
            if (paymentRequired && payment != null) {
            	KPaymentStatus status = 
            			KPaymentStatus.getInstance(payment.getStatusId());
            	if (status != KPaymentStatus.SUCCESS || !payment.isPaid()) {
            		getInvoiceService().closeInvoice(invoice, false, null, null, null);
            	}
            }
            
            return payment;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT charge(INVOICE invoice, KServiceClient client)
    		throws KStripeException {
        return charge(invoice, null, true, client);
    }
    
	// ----------------------------------------------------------------------------
 
    @Override
    public PAYMENT charge(INVOICE invoice, String cardToken, 
    		boolean paymentRequired, KServiceClient client) throws KStripeException {
            PAYMENT payment = null;
            
            if (paymentRequired) {
            	payment = processorCharge(invoice, cardToken, client);
            } else {
                BigDecimal paidAmount = new BigDecimal(0);
            	getInvoiceService().closeInvoice(invoice, true, paidAmount, "INTERNAL", null);
                
            	//FIXME: assume for now that if payment is not required
            	// it is because of a promoCode
                Long promoId = getPromoIdByInvoice(invoice);
                
                Date now = new Date();
                
                payment = getNewPaymentObject();
                payment.setAppId(invoice.getAppId());
                payment.setPromoId(promoId);
                payment.setTypeId(KPaymentType.PROMO.getId());
                payment.setStatusId(KPaymentStatus.SUCCESS.getId());
        		payment.setCurrencyId(invoice.getCurrencyId());
        		payment.setUserId(invoice.getUserId());
        		payment.setAccountId(invoice.getAccountId());
        		payment.setInvoiceId(invoice.getId());
        		payment.setAccessToken(client.getAccessToken());
        		payment.setSessionId(client.getSessionId());
        		payment.setHostname(client.getHostname());
        		payment.setBrowser(client.getBrowser());
        		payment.setLatitude(client.getLatitude());
        		payment.setLongitude(client.getLongitude());
        		payment.setCardToken(cardToken);
        		payment.setCardLast4(null);
                payment.setAmount(new BigDecimal(0));
        		payment.setProcessorRef(null);
        		payment.setProcessorError(null);
                payment.setProcessorFee(new BigDecimal(0));
        		payment.setPaid(true);
        		payment.setFailed(false);
        		payment.setPaidDate(now);
        		payment.setCreatedDate(now);
        		payment = getPaymentService().add(payment);
            }
            
            if (!paymentRequired || payment.isPaid()) {
                //FIXME: if you purchase more than 1 subscription, then the 
            	// combined value of that purchase should be applied to your 
            	// account.
                
                List<INVOICE_ITEM> invoiceItemList = getInvoiceItemService().getInvoiceItemList(invoice);
                logger.debug("invoice item list size: " + invoiceItemList.size());
                
            	Long userId = invoice.getUserId();
            	ACCOUNT account = getAccountByUserId(userId);
            	for (INVOICE_ITEM item : invoiceItemList) {
            		boolean autoRenew = false;
            		KPaymentType paymentType = KPaymentType.PROMO;
            		if (paymentRequired) {
            			autoRenew = true;
            			paymentType = KPaymentType.CARD;
            		}
            		
            		addProductPurchase(account, invoice, item, paymentType, autoRenew);
            		incPromoUseCount(item.getPromoId());
            	}

				try {
					sendReceiptEmail(invoice, false);
                    /*
                    if (paymentRequired) {
                    	sendReceiptEmail(invoice);
                    }
                    */
				} catch (Exception e) {
					String body = "\ninvoice: " + KClassUtil.toString(invoice);
					getSystemService().alert("Email Error: Payment Receipt", body, e);
				}
			} 
              
            return payment;
    }
    
	// ----------------------------------------------------------------------------
   
    private PRODUCT_PURCHASE addProductPurchase(ACCOUNT account, INVOICE invoice, INVOICE_ITEM item, 
    		KPaymentType paymentType, boolean autoRenew) {
    	
    	PRODUCT_PURCHASE purchase = getProductPurchaseService()
				.fetchByAccountIdAndProductId(account.getId(), item.getProductId());
    	
    	if (purchase == null) {
    		purchase = getNewProductPurchaseObject();
    		purchase.setAccountId(account.getId());
    		purchase.setUserId(invoice.getUserId());
    		purchase.setProductId(item.getProductId());
    		
    		// IMPORTANT! invoice appId is the app that generated the cart/invoice.
    		// ProductPurchase appId is the app associated with the subscription itself.
    		PRODUCT product = getProductService().fetchById(item.getProductId());
    		purchase.setAppId(product.getAppId());
    	}
    	
    	purchase.setPromoId(item.getPromoId());
    	purchase.setAutoRenew(autoRenew);
    	purchase.setInvoiceNo(invoice.getInvoiceNo());
    	purchase.setPaymentTypeId(paymentType.getId());
    	purchase.setEnabled(true);
    	purchase.setExpirationDate(item.getSubscriptionEndDate());
    	
    	if (purchase.getId() == null) {
    		purchase = getProductPurchaseService().add(purchase);
    	} else {
    		purchase = getProductPurchaseService().update(purchase);
    	}
        
    	return purchase;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT externalCharge(CART cart, Long paymentTypeId, BigDecimal paidAmount, 
    		String processorRef, BigDecimal processorFee, KServiceClient client) {
    	INVOICE invoice = getInvoiceService().createInvoice(cart);
        return externalCharge(invoice, paymentTypeId, paidAmount, processorRef, processorFee, client);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT externalCharge(INVOICE invoice, Long paymentTypeId,
    		BigDecimal paidAmount, String processorRef, BigDecimal processorFee, 
    		KServiceClient client) {
    	PAYMENT payment = null;

    	if (processorRef == null) {
    		processorRef = "EXTERNAL PAYMENT";
    	}

    	if (processorFee == null) {
    		processorFee = new BigDecimal(0);
    	}
        
    	if (paymentTypeId == null) {
    		paymentTypeId = KPaymentType.EXTERNAL.getId();
    	}
        
        boolean paid = false;
    	if (invoice.getAmountDue() != null && paidAmount != null &&
    			invoice.getAmountDue().compareTo(paidAmount) == 0) {
            paid = true;
    	}

    	getInvoiceService().closeInvoice(invoice, paid, paidAmount, processorRef, null);

    	Long promoId = getPromoIdByInvoice(invoice);
        Date now = new Date();

    	payment = getNewPaymentObject();
    	payment.setAppId(invoice.getAppId());
    	payment.setPromoId(promoId);
    	payment.setTypeId(paymentTypeId);
    	payment.setStatusId(KPaymentStatus.SUCCESS.getId());
    	payment.setCurrencyId(invoice.getCurrencyId());
    	payment.setUserId(invoice.getUserId());
    	payment.setAccountId(invoice.getAccountId());
    	payment.setInvoiceId(invoice.getId());
    	payment.setAccessToken(client.getAccessToken());
    	payment.setSessionId(client.getSessionId());
    	payment.setHostname(client.getHostname());
    	payment.setBrowser(client.getBrowser());
    	payment.setLatitude(client.getLatitude());
    	payment.setLongitude(client.getLongitude());
    	payment.setCardToken(null);
    	payment.setCardLast4(null);
    	payment.setAmount(paidAmount);
    	payment.setProcessorRef(processorRef);
    	payment.setProcessorError(null);
    	payment.setProcessorFee(processorFee);
    	payment.setPaid(true);
    	payment.setFailed(false);
    	payment.setPaidDate(now);
    	payment.setCreatedDate(now);
    	payment = getPaymentService().add(payment);

    	Long userId = invoice.getUserId();
    	ACCOUNT account = getAccountByUserId(userId);
    	for (INVOICE_ITEM item : getInvoiceItemService().getInvoiceItemList(invoice)) {
    		addProductPurchase(account, invoice, item, KPaymentType.getInstance(paymentTypeId), true);
    		incPromoUseCount(item.getPromoId());
    	}

    	try {
    		sendReceiptEmail(invoice, true);
    		/*
                    if (paymentRequired) {
                    	sendReceiptEmail(invoice);
                    }
    		 */
    	} catch (Exception e) {
    		String body = "\ninvoice: " + KClassUtil.toString(invoice);
    		getSystemService().alert("Email Error: Payment Receipt", body, e);
    	}

    	return payment;
    }
    
	// ----------------------------------------------------------------------------
   
    private void incPromoUseCount(Long promoId) {
        if (promoId == null) return;
        PROMO promo = getPromoService().fetchById(promoId);
        Integer useCount = promo.getUseCount();
        if (useCount == null) useCount = 0;
        useCount += 1;
        promo.setUseCount(useCount);
        getPromoService().update(promo);
    }
    
	// ----------------------------------------------------------------------------
    
    private Long getPromoIdByInvoice(INVOICE invoice) {
        List<INVOICE_ITEM> items = getInvoiceItemService().fetchByInvoiceId(invoice.getId());
        Long promoId = null;
        for (INVOICE_ITEM item : items) {
            Long pid = item.getPromoId();
            if (promoId == null) {
            	promoId = pid;
            	continue;
            }
            
            if (pid != null && !pid.equals(promoId)) {
            	return null;
            }
        }
        return promoId;
    }
    
	// ----------------------------------------------------------------------------
    
    /*
    @Override
    private PAYMENT chargeCustomer(String cardToken, INVOICE invoice)
    		throws KStripeException {
        ACCOUNT account = getAccount(invoice.getAccountId());

        if (account == null) {
            throw new IllegalStateException(
                "Account not found for invoice: " + toString(invoice));
        }
        
        // see if we have a stripeUid for this customer
        if (cardToken == null && account.getStripeUid() == null) {
            throw new IllegalStateException(
                "StripeUid is null for accountId: " + account.getId());
        }
        
        BigDecimal total = invoice.getAmountDue();
        if (total == null || total.compareTo(new BigDecimal(0))<=0) {
            throw new IllegalStateException(
            		"Invoice amount due is null or negative: " + total);
        }
        
        return processorCharge(account, cardToken, invoice); 
    }
    */

	// ----------------------------------------------------------------------------
    
 

	// ----------------------------------------------------------------------------

    @Override
    public String getCardLast4(Long appId, ACCOUNT account) {
        if (account == null) return null;
        String stripeUid = account.getStripeUid();
        if (stripeUid == null) return null;
    	KCard card = getStripeService().fetchCustomerActiveCard(appId, stripeUid);
    	if (card == null) return null;
    	return card.getLast4();
    }
    
	// ----------------------------------------------------------------------------
    
	@Override
    public PAYMENT processorCharge(INVOICE invoice, String cardToken, KServiceClient client) {

        PAYMENT payment = null;
        KCharge chargeItem = null;
        String chargeDescription = getChargeDescription(invoice);
        
        invoice.setPaymentAttempted(true);
        Integer attemptCount = invoice.getPaymentAttemptCount();
        if (attemptCount == null) attemptCount = 0;
        attemptCount += 1;
        invoice.setPaymentAttemptCount(attemptCount);
        invoice.setLastPaymentAttemptDate(new Date());
        getInvoiceService().updateInvoice(invoice);
        
        ACCOUNT account = getAccountService().fetchById(invoice.getAccountId());
        KPaymentType type = KPaymentType.CARD;
        KPaymentStatus status = null;
        String error = null;

        if (account == null) {
            status = KPaymentStatus.ACCOUNT_INVALID;
            error = "Account not found for invoice: " + KClassUtil.toString(invoice);
            return getPaymentService().createPayment(type, status, cardToken, invoice, error, client);
        } 
        
        if (!account.isEnabled()) {
            status = KPaymentStatus.ACCOUNT_DISABLED;
            error = "Account disabled. accountId: " + account.getId();
            return getPaymentService().createPayment(type, status, cardToken, invoice, error, client);
        } 
        // see if we have a stripeUid for this customer
        if (cardToken == null && account.getStripeUid() == null) {
            status = KPaymentStatus.CARD_MISSING;
            error = "StripeUid is null for accountId: " + account.getId();
            return getPaymentService().createPayment(type, status, cardToken, invoice, error, client);
        } else if (cardToken == null && account.getStripeUid() != null) {
        	String cardLast4 = getStripeService().getPrimaryCardLast4ByUserId(client.getAppId(), invoice.getUserId());
        	if (cardLast4 == null) {
                Long userId = invoice.getUserId();
        		status = KPaymentStatus.CARD_MISSING;
        		error = "User does not have a default card. userId: " + userId;
        		return getPaymentService().createPayment(type, status, cardToken, invoice, error, client);
        	}
        }
        
        BigDecimal total = invoice.getAmountDue();
        if (total == null || total.compareTo(new BigDecimal(0))<=0) {
            status = KPaymentStatus.AMOUNT_INVALID;
        	error = "Invoice amount due is null, zero or negative: " + total;
            return getPaymentService().createPayment(type, status, cardToken, invoice, error, client);
        }
        
        try {
            String receiptEmail = null;
        	Map<String,Object> metadata = null;
        	Map<String,Object> shipping = null;
            
        	if (cardToken == null) {
        		chargeItem = getStripeService().chargeCustomer(client.getAppId(),
                    account, invoice.getAmountDue(), chargeDescription, 
                    receiptEmail, metadata, shipping);
        	} else {
        		chargeItem = getStripeService().chargeCard(client.getAppId(),
                    cardToken, invoice.getAmountDue(), chargeDescription, 
                    receiptEmail, metadata, shipping);
        	}
            
        	payment = getPaymentService().createPayment(KPaymentType.CARD, cardToken,
        			invoice, chargeItem, client);
        } catch (KStripeException e) {
            // FIXME: parse exception and properly set PaymentStatus
            payment = getPaymentService().createPayment(KPaymentType.CARD, cardToken, 
            		invoice, e, client);
        }
        
        return payment;
    }
    
	// ----------------------------------------------------------------------------

    private String getChargeDescription(INVOICE invoice) {
        String username = getUserService().fetchById(invoice.getUserId()).getUsername();
        
    	StringBuffer sb = new StringBuffer();
    	sb.append("invoiceId: " + invoice.getId());
    	sb.append("\naccountId: " + invoice.getAccountId());
    	sb.append("\nusername: " + username);
        return sb.toString();
    }
    
	// ----------------------------------------------------------------------------

    // NOTE: this method is not Transactional.  We don't want to rollback
    // payments that have already been processed.
    
    // FIXME: don't renew subscriptions if payment was made
    // through external processor.  Check if account has stripeUId??
    @Override
	public void renewSubscriptions() {
        logger.debug("calling renewSubscriptions()");

		// We want to get a list of all accounts that will expire in
		// 5 days or less and have autoRenew set to true.
		Date startDate = new Date();
		Date endDate = KDateUtil.addDays(startDate, 2);
        
		// in case we were down for some reason, check all past
		// subscriptions for the past 7 days
		startDate = KDateUtil.addDays(startDate, -7);

		List<PRODUCT_PURCHASE> subscriptionList = getProductPurchaseService().fetchSubscriptionsByExpirationDate(
				startDate, endDate, true);

		List<Long> accountIdList = new ArrayList<Long>();

        // only renew subscriptions for which PaymentType is CARD
        // assumes the account has a StripeID associated with a  default card.
		List<PRODUCT_PURCHASE> subscriptionRenewList = new ArrayList<PRODUCT_PURCHASE>();
		for (PRODUCT_PURCHASE purchase : subscriptionList) {
            KPaymentType paymentType = null;
            if (purchase.getPaymentTypeId() != null) {
            	paymentType = KPaymentType.getInstance(purchase.getPaymentTypeId());
            }
            
            // make sure account is still active. if not expire this subscription
            ACCOUNT account = getAccountService().fetchById(purchase.getAccountId());
            if (account.getRetiredDate() != null) {
            	purchase.setEnabled(false);
            	purchase.setAutoRenew(false);
            	purchase.setExpirationDate(new Date());
            	getProductPurchaseService().update(purchase);
            	continue;
            }
            
			if (purchase.isEnabled() && purchase.isAutoRenew()
					&& purchase.getProductId() != null
					&& paymentType != null && paymentType == KPaymentType.CARD) {
				subscriptionRenewList.add(purchase);
				if (!accountIdList.contains(purchase.getAccountId())) {
					accountIdList.add(purchase.getAccountId());
				}
			}
		}

		String summary = "";
		BigDecimal autoRenewTotal = new BigDecimal(0);
        
		List<Long> skipList = new ArrayList<Long>();

		for (Long accountId : accountIdList) {
			List<INVOICE> invoiceList = getInvoiceService().fetchOpenByAccountId(accountId);
            
            if (invoiceList != null && invoiceList.size()>0) {
                skipList.add(accountId);
                continue;
            }
            
            
            ACCOUNT account = getAccountService().fetchById(accountId);
            USER user = getUserService().fetchById(account.getOwnerId());
            Long userId = user.getId();
                        
            Long appId = getSystemAppId();
			CART cart = getCartService().createCart(userId, appId, null);
			
			for (PRODUCT_PURCHASE purchase : subscriptionRenewList) {
				if (purchase.getAccountId().equals(accountId)) {
					Long productId = purchase.getProductId();
					if (productId != null) {
						getCartItemService().addCartItem(cart, userId, productId, null);
					}
				}
			}

			if (cart.getTotal().compareTo(new BigDecimal(0)) > 0) {
				try {
					INVOICE invoice = getInvoiceService().createInvoice(cart);
					PAYMENT payment = charge(invoice, null);
                    
                    if (payment.isPaid()) {
                    	autoRenewTotal = autoRenewTotal.add(payment.getAmount());
                    	summary += "\n\nPayment: " + KClassUtil.toString(payment);
                    } else {
                    	Date nextAttempt = KDateUtil.addDays(new Date(), 3);
                    	invoice.setNextPaymentAttemptDate(nextAttempt);
                    	getInvoiceService().updateInvoice(invoice);
                        
                        processPaymentFailure(invoice, payment);
                    }
                    
                    // sleep 2s to throttle payment processor calls 
                    KSystemUtil.sleep(2000L);
				} catch (Exception e) {
					String s = "Cart: " + KClassUtil.toString(cart);
					getSystemService().alert("SYSTEM ERROR: Auto Renew Subscription", s, e);
				}
			}
		}

		summary = "Total Payments: " + autoRenewTotal 
                + "\nSkipped Accounts: " + KClassUtil.toString(skipList)
				+ "\n" + summary;
        
        if (autoRenewTotal.compareTo(new BigDecimal(0))> 0) {
        	getSystemService().alert("Auto-Renew Summary", summary);
        } 
        
        logger.info("Auto-Renew Summary:\n" + summary);
	}

	// ----------------------------------------------------------------------------

    private void processPaymentFailure(INVOICE invoice, PAYMENT payment) {
		KPaymentStatus status = null;
        
		if (payment != null) {
			status = KPaymentStatus.getInstance(payment.getStatusId());
		}
        
		String s = "\nInvoice: " + KClassUtil.toString(invoice) 
				+ "\n\nPayment: " + KClassUtil.toString(payment);
		getSystemService().alert("PAYMENT ERROR: Auto Renew Subscription", s);

		if (status != null && status == KPaymentStatus.CARD_MISSING) {
			try {
				sendInvalidCardEmail(invoice);
			} catch (Exception e) {
				String body = "\ninvoice: " + KClassUtil.toString(invoice);
				getSystemService().alert("Email Error: Invalid Card Notice", body, e);
			}
		}
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void retryFailedPayments() {
		String summary = "";
		BigDecimal autoRenewTotal = new BigDecimal(0);
		List<Long> skipList = new ArrayList<Long>();
        
    	List<INVOICE> invoiceList = getInvoiceService().fetchAllOpen();
        
        for (INVOICE invoice : invoiceList) {
            Integer attemptCount = invoice.getPaymentAttemptCount();
            if (attemptCount != null && attemptCount >= 3) {
            	getInvoiceService().closeInvoice(invoice, false, null, null, null);
				getSystemService().alert("Failed Invoice", KClassUtil.toJson(invoice));
            	continue;
            }
            
            Date now = new Date();
            Date lastAttempt = invoice.getLastPaymentAttemptDate();
            if (lastAttempt == null) {
                getSystemService().alert("Possible Invoice Sync Error", 
                		"retryFailedPayments: processing invoice with lastAttempt null: " 
                		+ KClassUtil.toJson(invoice));
                continue;
            }
            
            Date nextAttempt = invoice.getNextPaymentAttemptDate();
            if (nextAttempt != null && now.before(nextAttempt)) continue;
            if (nextAttempt == null) {
            	if (KDateUtil.diffDays(now, lastAttempt) < 3) continue;
            }
            
            // at this point we should have an invoice that has been charged
            // fewer than 3 times and which was last charged at least 3 days 
            // ago.
            
            // make sure account is still active
            ACCOUNT account = getAccountService().fetchById(invoice.getAccountId());
            if (account.getRetiredDate() != null) {
            	getInvoiceService().closeInvoice(invoice, false, null, null, null);
				getSystemService().alert("Failed Invoice: account has been retired", KClassUtil.toJson(invoice));
            	continue;
            }
            
            // only retry payment if account has a default CARD 
            List<PAYMENT> prevPayments = getPaymentService().fetchByInvoiceId(invoice.getId());
            Long paymentTypeId = null;
            if (prevPayments != null && prevPayments.size() >0) {
            	PAYMENT prevPayment = prevPayments.get(0);
            	paymentTypeId = prevPayment.getTypeId();
            }
            
            		
            KPaymentType paymentType = null;
            if (paymentTypeId != null) {
            	paymentType = KPaymentType.getInstance(paymentTypeId);
            }
            
                
            PAYMENT payment = null;
			try {
                if (paymentType != null && paymentType == KPaymentType.CARD) {
                	payment = charge(invoice, null);
                }
			} catch (Exception e) {
				String s = "Invoice: " + KClassUtil.toString(invoice);
				getSystemService().alert("SYSTEM ERROR: Auto Renew Subscription", s, e);
			}
                
			if (payment != null && payment.isPaid()) {
				autoRenewTotal = autoRenewTotal.add(payment.getAmount());
				summary += "\n\nPayment: " + KClassUtil.toString(payment);
			} else {
				nextAttempt = KDateUtil.addDays(now, 3);
				invoice.setNextPaymentAttemptDate(nextAttempt);
				getInvoiceService().updateInvoice(invoice);
				processPaymentFailure(invoice, payment);
			}

			// sleep 5s to throttle payment processor calls 
			KSystemUtil.sleep(5000L);
        }
        
		summary = "Total Payments: " + autoRenewTotal 
                + "\nSkipped Invoices: " + KClassUtil.toString(skipList)
				+ "\n" + summary;
        
        if (autoRenewTotal.compareTo(new BigDecimal(0))> 0) {
        	getSystemService().alert("Retry Failed Payments", summary);
        }
        
        logger.info("Retry Failed Payments:\n" + summary);
    	
    }
    
	// ----------------------------------------------------------------------------

    
   
    
 // One step to add item to cart and record payment for it for it
    // map should contain at least:
    //	productId
    // 	paidAmount
    
    // NOTE: 
    //  store: apple | google
    //
    // 	for apple:
    //		processorRef = transactionId
    //
    //  for google:
    //		processorRef = orderId
    //		receipt = purchaseToken
    @Override
    public PAYMENT inAppPurchase(String store, Long userId, Long appId, Long productId, Integer quantity,
    		String promoCode, BigDecimal paidAmount, String processorRef, String receipt,
    		String notes, KServiceClient client) {
        
        if (productId == null) {
            throw new IllegalArgumentException("Payment.inAppPurchase: productId is null.");
        }
        
        if (paidAmount == null) {
            throw new IllegalArgumentException("Payment.inAppPurchase: paidAmount is null.");
        }
        
        paidAmount = paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        if (processorRef != null) {
        	PAYMENT payment = getPaymentService().fetchByProcessRef(processorRef);
        	// if we already have a processorRef, most likely we're processing a purchase
        	// that was already made (typical in iOS apps).  do a sanity check then return the payment
        	if (payment != null) {
        		if (payment.getAmount().compareTo(paidAmount) != 0) {
        			getSystemService().alert("InAppPurchase Error: Payment amount mismatch", "Paid amount: " + paidAmount 
        					+ "\n\nExisting Payment: " + payment.toString());
        			throw new IllegalStateException("InAppPurchase Error: Payment amount mismatch");
        		}
        		
        		if (payment.getInvoiceId() == null) {
        			getSystemService().alert("InAppPurchase Error: Invoice not found for Payment", payment.toString());
        			throw new IllegalStateException("InAppPurchase Error: Invoice not found for Payment");
        		}
        		
        		if (!payment.getUserId().equals(userId)) {
        			getSystemService().alert("InAppPurchase Error: Payment user mismatch", "User ID: " + userId 
        					+ "\n\nExisting Payment: " + payment.toString());
        			throw new IllegalStateException("InAppPurchase Error: Payment user mismatch");
        		}
        		
        		INVOICE invoice = getInvoiceService().fetchById(payment.getInvoiceId());
        		List<INVOICE_ITEM> items = getInvoiceItemService().fetchByInvoiceId(invoice.getId());
        		boolean foundItem = false;
        		for (INVOICE_ITEM item : items) {
        			if (item.getProductId().equals(productId)) {
        				foundItem = true;
        			}
        		}
        		
        		if (!foundItem) {
        			getSystemService().alert("InAppPurchase Error: Product item not found for payment", "Product ID: " + productId 
        					+ "\n\nExisting Invoice Items: " + KClassUtil.toJson(items));
        			throw new IllegalStateException("InAppPurchase Error: product item not found for payment");
        		}
        		
        		return payment;
        	}
        	
        }
        
        Long promoId = null;
        if (promoCode != null) {
        	PROMO promo = getValidPromo(promoCode, userId, productId);
            if (promo != null) {
            	promoId = promo.getId();
            }
        }
        
        CART cart = getCartService().getCart(userId, appId, client);
    	CART_ITEM cartItem = getCartItemService().addCartItem(cart, userId,
    			productId, quantity, promoId, notes);
        
        boolean google = false;
        boolean apple = false;
        
        if (store != null && store.equalsIgnoreCase("apple")) {
            apple = true;
        } else if (store != null && store.equalsIgnoreCase("google")) {
            google = true;
        }
        
        
        if (receipt != null && processorRef != null && store != null) {
        	Date subEndDate = null;
        	if (store.equalsIgnoreCase("apple")) {
        		Map<String,Object> decodedReceipt = decodeAppStoreReceipt(appId, receipt, processorRef, false);
        		if (decodedReceipt != null) {
        			logger.debug("inAppPurchase: got receipt: " + KClassUtil.toJson(decodedReceipt));
        			if (decodedReceipt.get("expires_date_ms") != null) {
        				Long expiresDate = Long.valueOf(decodedReceipt.get("expires_date_ms").toString());
        				subEndDate = KDateUtil.getDate(expiresDate);
        			}
        		}
        	} else if (store.equalsIgnoreCase("google")) {
                //String packageName = getConfig(appId).getString("google.play.packageName");
                String packageName = getGooglePackageName(appId);
                PRODUCT product = getProductService().fetchById(productId);
                String googleProductId = product.getName();
                try {
					PRODUCT_PURCHASE purchase = getGooglePlayService().getSubscription(appId, packageName, googleProductId, receipt);
                    if (purchase == null) {
                    	logger.warn("Google Service subscription service returned null"
                    			+ "\nproductId: " + googleProductId 
                    			+ "\ntoken: " + receipt); 
                    } else {
                    	subEndDate = purchase.getExpirationDate();
                    }
				} catch (IOException e) {
                    logger.error(e.getMessage(), e);
				}
        	}

        	if (subEndDate != null) {
        		logger.debug("inAppPurchase: setting subscription end date to: " + subEndDate);
        		cartItem.setSubscriptionEndDate(subEndDate);
        		getCartItemService().updateCartItem(cartItem);
        	}
        }
        
        BigDecimal processorFee = null;
        Long paymentTypeId = null;
        if (apple) {
        	processorFee = cart.getTotal().multiply(new BigDecimal(0.3));
            paymentTypeId = KPaymentType.APPLE_APPSTORE.getId();
        } else if (google) {
        	processorFee = cart.getTotal().multiply(new BigDecimal(0.3));
            paymentTypeId = KPaymentType.GOOGLE_PLAY.getId();
        }
        
        processorFee = processorFee.setScale(2, BigDecimal.ROUND_HALF_UP);
        //paidAmount = paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        PAYMENT payment = externalCharge(cart, paymentTypeId, paidAmount, 
				processorRef, processorFee, client);
        
        // FIXME: all receipt processing should be moved to CommerceService 
        // Save the receipt since it can be used to periodically query Apple AppStore
        // for updates to the account.  (For example, will allow us to know if they 
        // cancelled a subscription.
        if (receipt != null) {
        	payment.setProcessorReceipt(receipt);
            getPaymentService().update(payment);
        }
        
        return payment;
    }
    
	// ----------------------------------------------------------------------------

    
    
    // Since in app review mode, test receipts are used, the docs say:
    // 		When validating receipts on your server, your server needs to be able to handle 
    //		a production-signed app getting its receipts from Apple’s test environment. 
    //		The recommended approach is for your production server to always validate receipts 
    //		against the production App Store first. If validation fails with the error code 
    //		“Sandbox receipt used in production”, validate against the test environment instead.
    // https://developer.apple.com/library/ios/documentation/NetworkingInternet/Conceptual/StoreKitGuide/Chapters/AppReview.html#//apple_ref/doc/uid/TP40008267-CH10-SW1
    @SuppressWarnings("unchecked")
	private Map<String,Object> decodeAppStoreReceipt(Long appId, String encodedReceipt, String transactionId, boolean sandbox) {
        Map<String,Object> result = null;
        
        //String url = getConfig(appId).getString("apple.verifyReceiptUrl");
        String url = getAppleVerifyReceiptUrl(appId);
        if (sandbox) {
        	//url = getConfig(appId).getString("apple.verifyReceiptSandboxUrl");
        	url = getAppleVerifyReceiptSandboxUrl(appId);
        }
        
        //String password = getConfig(appId).getString("apple.appSharedSecret");
        String password = getAppleAppSharedSecret(appId);
        
        Map<String,String> request = new HashMap<String,String>();
        request.put("receipt-data", encodedReceipt);
        request.put("password", password);
        
        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            template.getMessageConverters().add(new StringHttpMessageConverter());
            template.getMessageConverters().add(new ByteArrayHttpMessageConverter());
            String s = template.postForObject(url, request, String.class);
            logger.debug("######\ndecodeAppStoreReceipt: result:\n#####\n" + s);

            if (s != null) {
                result = KStringUtil.toMap(s);
            }

            logger.debug("######\nparsed result: result:\n#####\n" + KClassUtil.toJson(result));
            
            Integer status = Integer.valueOf(result.get("status").toString());
            
            /*
            https://developer.apple.com/library/ios/releasenotes/General/ValidateAppStoreReceipt/Chapters/ValidateRemotely.html#//apple_ref/doc/uid/TP40010573-CH104-SW1
            0
            	Receipt is valid.
            21000
            	The App Store could not read the JSON object you provided.
            21002
            	The data in the receipt-data property was malformed or missing.
            21003
            	The receipt could not be authenticated.
            21004
            	The shared secret you provided does not match the shared secret on file for your account.
            	Only returned for iOS 6 style transaction receipts for auto-renewable subscriptions.
            21005
            	The receipt server is not currently available.
            21006
            	This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response.
            	Only returned for iOS 6 style transaction receipts for auto-renewable subscriptions.
            21007
            	This receipt is from the test environment, but it was sent to the production environment for verification. Send it to the test environment instead.
            21008
            	This receipt is from the production environment, but it was sent to the test environment for verification. Send it to the production environment instead.
            */
            
            if (status == 21007) {
            	return decodeAppStoreReceipt(appId, encodedReceipt, transactionId, true);
            }
            
            if (status == 21008) {
            	return decodeAppStoreReceipt(appId, encodedReceipt, transactionId, false);
            }

            if (status == 0) {
                Map<String,Object> receipt = (Map<String,Object>)result.get("receipt");
                List<Map<String,Object>> inApp = (List<Map<String,Object>>)receipt.get("in_app");
                
                logger.debug("######\nin_app result:\n#####\n" + KClassUtil.toJson(inApp));

                for (Map<String,Object> purchase : inApp) {
                    logger.debug("\n\n*************\nchecking receipt:\n***********\n" + KClassUtil.toJson(purchase));
                    
                    String txnId = (String) purchase.get("transaction_id");
                    
                    logger.debug("\nchecking txnId: [" + txnId + "] = [" + transactionId + "]\n\n");
                    
                    if (txnId != null && transactionId.equals(txnId)) {
                        return purchase;
                    }
                }
                
                return null;
            } 
            
            logger.error("decodeAppStoreReceipt: Error: " + status);
            
            return null;
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    
	// ----------------------------------------------------------------------------

    
    // Return null if promoCode does not exist or if the code violates
    // some type of business rule (e.g. user already previously used the promoCode)
    private PROMO getValidPromo(String promoCode, Long userId, Long productId) {
        if (promoCode == null) return null;
        ACCOUNT account = getAccountService().fetchByOwnerId(userId);
        PRODUCT product = null;
        if (productId != null) {
            product = getProductService().fetchById(productId);
        }
        return getPromoService().fetchByCode(promoCode, account, product);
    }
    
    
    // ----------------------------------------------------------------------

}
