package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KInvoiceItem;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractInvoiceItemService<INVOICE_ITEM extends KInvoiceItem, 
										   		  INVOICE_ITEM_EXAMPLE,
										   		  INVOICE extends KInvoice,
										   		  CART_ITEM extends KCartItem>
		extends KAbstractService<INVOICE_ITEM,INVOICE_ITEM_EXAMPLE>
		implements KInvoiceItemService<INVOICE_ITEM,INVOICE,CART_ITEM> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractInvoiceItemService.class);
    
	// ----------------------------------------------------------------------------

	protected abstract INVOICE_ITEM getNewObject();
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void validate(INVOICE_ITEM invoiceItem) {
        if (invoiceItem.getCreatedDate() == null) {
        	invoiceItem.setCreatedDate(new Date());
        }
        
        invoiceItem.setUpdatedDate(new Date());
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public List<INVOICE_ITEM> fetchByInvoiceId(Long invoiceId) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceId", invoiceId);
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public INVOICE_ITEM createInvoiceItem(INVOICE invoice, CART_ITEM cartItem) {
    	INVOICE_ITEM item = getNewObject();
        item.setInvoiceId(invoice.getId());
        item.setProductId(cartItem.getProductId());
        item.setDescription(cartItem.getDescription());
        item.setDiscountDescription(cartItem.getDiscountDescription());
        item.setQuantity(cartItem.getQuantity());
        item.setSubtotal(cartItem.getSubtotal());
        item.setTotal(cartItem.getTotal());
        item.setCreatedDate(invoice.getCreatedDate());
        item.setSubscriptionStartDate(cartItem.getSubscriptionStartDate());
        item.setSubscriptionEndDate(cartItem.getSubscriptionEndDate());
        item = add(item);
        return item;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public List<INVOICE_ITEM> getInvoiceItemList(INVOICE invoice) {
        if (invoice == null || invoice.getId() == null) return null;
        return fetchByInvoiceId(invoice.getId());
    }
    
	// ----------------------------------------------------------------------------
    

    @Override 
    public void updateInvoiceItem(INVOICE_ITEM item) {
        if (item == null) return;
        update(item);
    }

}
