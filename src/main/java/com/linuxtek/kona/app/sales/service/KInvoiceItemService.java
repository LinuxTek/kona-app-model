package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KInvoiceItem;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KInvoiceItemService<INVOICE_ITEM extends KInvoiceItem, 
									 INVOICE extends KInvoice, 
									 CART_ITEM extends KCartItem>
        extends KService, KDataService<INVOICE_ITEM> {
    
    public static final String SERVICE_PATH = "rpc/KInvoiceItemService";

	public List<INVOICE_ITEM> fetchByInvoiceId(Long invoiceId);
	
	public List<INVOICE_ITEM> getInvoiceItemList(INVOICE invoice);
    
	public INVOICE_ITEM createInvoiceItem(INVOICE invoice, CART_ITEM cartItem);
    
	public void updateInvoiceItem(INVOICE_ITEM item);

}
