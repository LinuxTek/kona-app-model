package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;
import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KInvoiceItem;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KInvoiceService<INVOICE extends KInvoice,
								 INVOICE_ITEM extends KInvoiceItem,
								 CART extends KCart,
								 CART_ITEM extends KCartItem>
        extends KService, KDataService<INVOICE> {
    
    public static final String SERVICE_PATH = "rpc/KInvoiceService";

	public List<INVOICE> fetchByUserId(Long userId);
    
	public List<INVOICE> fetchAllOpen();
    
	public List<INVOICE> fetchByAccountIdAndProductId(Long accountId, Long productId);
    
	public List<INVOICE> fetchOpenByAccountId(Long accountId);
    
    public INVOICE fetchByInvoiceNo(String invoiceNo);
    
    public INVOICE createInvoice(CART cart);
    
	public INVOICE createInvoice(CART cart, List<CART_ITEM> itemList);
    
	public INVOICE createInvoice(Long appId, Long accountId, List<INVOICE_ITEM> itemList);
    
	public void updateInvoice(INVOICE invoice);
    
	public void closeInvoice(INVOICE invoice, boolean paid, BigDecimal amount,
			String paymentRef, String cardLast4, String notes);


}
