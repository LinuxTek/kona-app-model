package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KPayment;
import com.linuxtek.kona.app.sales.entity.KPaymentStatus;
import com.linuxtek.kona.app.sales.entity.KPaymentType;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.stripe.entity.KCharge;

public interface KPaymentService<PAYMENT extends KPayment, INVOICE extends KInvoice> 
        extends KService, KDataService<PAYMENT> {
    
    public static final String SERVICE_PATH = "rpc/PaymentService";
    
    public List<PAYMENT> fetchByInvoiceId(Long invoiceId);
    
    public PAYMENT fetchByProcessRef(String processorRef);
    
	public PAYMENT createPayment(KPaymentType type, KPaymentStatus status, 
			String cardToken, INVOICE invoice, String error, KServiceClient client);
	
	public PAYMENT createPayment(KPaymentType type, String cardToken, 
			INVOICE invoice, KCharge chargeItem, KServiceClient client);
	
	public PAYMENT createPayment(KPaymentType type, String cardToken, 
			INVOICE invoice, KPaymentException ex, KServiceClient client);
	
	public PAYMENT createPayment(KPaymentType type, KPaymentStatus status, String cardToken, 
			INVOICE invoice, KCharge chargeItem, String paymentError, KServiceClient client);
	
}
