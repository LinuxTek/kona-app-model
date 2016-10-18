package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KInvoiceItem;
import com.linuxtek.kona.app.sales.entity.KPayment;
import com.linuxtek.kona.app.sales.entity.KPaymentStatus;
import com.linuxtek.kona.app.sales.entity.KPaymentType;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.stripe.entity.KCharge;
import com.linuxtek.kona.stripe.entity.KStripeException;

public abstract class KAbstractPaymentService<PAYMENT extends KPayment, 
										      PAYMENT_EXAMPLE,
										      INVOICE extends KInvoice,
										      INVOICE_ITEM extends KInvoiceItem,
										      CART extends KCart,
										      CART_ITEM extends KCartItem>
		extends KAbstractService<PAYMENT,PAYMENT_EXAMPLE>
		implements KPaymentService<PAYMENT,INVOICE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPaymentService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract PAYMENT getNewPaymentObject();
    
	protected abstract <S extends KInvoiceService<INVOICE,INVOICE_ITEM,CART,CART_ITEM>> S getInvoiceService();
    
	// ----------------------------------------------------------------------------
    
    protected BigDecimal toBigDecimal(Integer cents) {
        if (cents == null) return null;
        BigDecimal result = new BigDecimal(cents);
        result = result.divide(new BigDecimal(100));
        return result;
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public void validate(PAYMENT payment) {
        if (payment.getCreatedDate() == null) {
        	payment.setCreatedDate(new Date());
        }
        
        payment.setLastUpdated(new Date());
    }
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PAYMENT> fetchByInvoiceId(Long invoiceId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceId", invoiceId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PAYMENT fetchByProcessRef(String processorRef) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("processorRef", processorRef);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

    @Override
    public PAYMENT createPayment(KPaymentType type, KPaymentStatus status,
    		String cardToken, INVOICE invoice, String error, 
    		KServiceClient client) {
        return createPayment(type, status, cardToken, invoice, null, error, client);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT createPayment(KPaymentType type, String cardToken,
    		INVOICE invoice, KCharge chargeItem, KServiceClient client) {
        
        return createPayment(type, KPaymentStatus.SUCCESS, 
        		cardToken, invoice, chargeItem, null, client);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT createPayment(KPaymentType type, String cardToken,
    		INVOICE invoice, KPaymentException ex, KServiceClient client) {
        
        String error = ex.getMessage();
        
        return createPayment(type, KPaymentStatus.PROCESSOR_ERROR, 
        		cardToken, invoice, null, error, client);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PAYMENT createPayment(KPaymentType type, KPaymentStatus status,
    		String cardToken, INVOICE invoice, KCharge chargeItem, 
    		String paymentError, KServiceClient client) {
        
    	PAYMENT payment = getNewPaymentObject();
        
        /*
        String accessToken = client.getAccessToken();
        String sessionId = client.getSessionId();
        
        String accessToken = getSessionAccessToken();
        if (accessToken != null) {
            if (!getAuthService().isAccessTokenValid(accessToken)) {
            	accessToken = null;
            }
        }
        String sessionId = getSessionId();
        */
        
        boolean paid = false;
        boolean failed = true;
        Date now = new Date();
        
        String paymentRef = null;
        String cardLast4 = null;
        BigDecimal amountPaid = null;
        BigDecimal fee = null;
        
        if (chargeItem != null) {
        	paymentRef = chargeItem.getId();
        	cardLast4 = chargeItem.getCard().getLast4();
        	amountPaid = toBigDecimal(chargeItem.getAmount());
        	fee = toBigDecimal(chargeItem.getFee());
            if (paymentError == null) {
            	paymentError = chargeItem.getFailureMessage();
            }
        }
        
        
        if (chargeItem != null && chargeItem.isPaid()) {
            paid = true;
            failed = false;
            /*
            invoice.setClosed(true);
            invoice.setClosedDate(now);
        	invoice.setPaymentRef(paymentRef);
            invoice.setPaymentCardLast4(cardLast4);
            invoice.setPaid(paid);
            invoice.setPaidDate(now);
            invoice.setAmountPaid(amountPaid);
            invoiceService.update(invoice);
            */
            getInvoiceService().closeInvoice(invoice, true, amountPaid, paymentRef, cardLast4);
            
            payment.setPaidDate(now);
        } else {
        	payment.setFailedDate(now);
        	
        }
        
        payment.setTypeId(type.getId());
        payment.setStatusId(status.getId());
		payment.setCurrencyId(invoice.getCurrencyId());
		payment.setUserId(invoice.getUserId());
		payment.setAppId(invoice.getAppId());
		payment.setAccountId(invoice.getAccountId());
		payment.setInvoiceId(invoice.getId());
		if (client != null) {
			payment.setAccessToken(client.getAccessToken());
			payment.setSessionId(client.getSessionId());
			payment.setHostname(client.getHostname());
			payment.setBrowser(client.getBrowser());
			payment.setLatitude(client.getLatitude());
			payment.setLongitude(client.getLongitude());
		}
		payment.setCardToken(cardToken);
		payment.setCardLast4(cardLast4);
        payment.setAmount(amountPaid);
		payment.setProcessorRef(paymentRef);
		payment.setProcessorError(paymentError);
        payment.setProcessorFee(fee);
		payment.setPaid(paid);
		payment.setFailed(failed);
		payment.setCreatedDate(new Date());

		payment = add(payment);
        
        return payment;
    }
}
