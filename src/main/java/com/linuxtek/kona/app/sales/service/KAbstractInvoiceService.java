package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KAccountService;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KCurrency;
import com.linuxtek.kona.app.sales.entity.KInvoice;
import com.linuxtek.kona.app.sales.entity.KInvoiceItem;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.sequence.flake.KFlake;

public abstract class KAbstractInvoiceService<INVOICE extends KInvoice, 
										   	  INVOICE_EXAMPLE,
										   	  INVOICE_ITEM extends KInvoiceItem,
										   	  CART extends KCart,
										   	  CART_ITEM extends KCartItem,
										   	  ACCOUNT extends KAccount>
		extends KAbstractService<INVOICE,INVOICE_EXAMPLE>
		implements KInvoiceService<INVOICE,INVOICE_ITEM,CART,CART_ITEM> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractInvoiceService.class);
    
	// ----------------------------------------------------------------------------

	protected abstract INVOICE getNewInvoiceObject();
    
	protected abstract INVOICE_ITEM getNewInvoiceItemObject();
    
	protected abstract <S extends KAccountService<ACCOUNT>> S getAccountService();
    
	protected abstract <S extends KCartService<CART>> S getCartService();
    
	protected abstract <S extends KCartItemService<CART_ITEM,CART>> S getCartItemService();
    
	protected abstract <S extends KInvoiceItemService<INVOICE_ITEM,INVOICE,CART_ITEM>> S getInvoiceItemService();

	// ----------------------------------------------------------------------------

    @Override
    public void validate(INVOICE invoice) {
        if (invoice.getCreatedDate() == null) {
        	invoice.setCreatedDate(new Date());
        }
        
        invoice.setLastUpdated(new Date());
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public List<INVOICE> fetchByUserId(Long userId) {
       	Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public List<INVOICE> fetchByAccountIdAndProductId(Long accountId, Long productId) {
       	Map<String,Object> filter = KMyBatisUtil.filter()
       			.and("accountId", accountId)
       			.and("productId", productId)
       			.build();
           
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public List<INVOICE> fetchAllOpen() {
    	return fetchOpenByAccountId(null);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public List<INVOICE> fetchOpenByAccountId(Long accountId) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("closed", false);
        
        if (accountId != null) {
        	filter.put("accountId", accountId);
        }
        
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public INVOICE fetchByInvoiceNo(String invoiceNo) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("invoiceNo", invoiceNo);
    	return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	// ----------------------------------------------------------------------------
    
    @Override
    public INVOICE createInvoice(CART cart, List<CART_ITEM> itemList) {
        Date now = new Date();
        //String invoiceNo = sequence.getHexNo("invoiceNo", 9);
        String invoiceNo = KFlake.getId();

        INVOICE invoice = getNewInvoiceObject();
        invoice.setUserId(cart.getUserId());
        invoice.setAppId(cart.getAppId());
        invoice.setAccountId(cart.getAccountId());
        invoice.setCurrencyId(cart.getCurrencyId());
        invoice.setInvoiceNo(invoiceNo);
        invoice.setSubtotal(cart.getSubtotal());
        invoice.setTax(cart.getTax());
        invoice.setShipping(cart.getShipping());
        invoice.setDiscount(cart.getDiscount());
        invoice.setTotal(cart.getTotal());
        invoice.setAmountDue(cart.getTotal());
        invoice.setNotes(cart.getNotes());
        invoice.setPaid(false);
        invoice.setClosed(false);
        invoice.setPaidDate(null);
        invoice.setDueDate(now);
        invoice.setInvoiceDate(now);
        invoice.setCreatedDate(now);

        invoice = add(invoice);
        Long invoiceId = invoice.getId();

        for (CART_ITEM cartItem : itemList) {
            // sanity check
        	if (!cart.getId().equals(cartItem.getCartId())) {
                throw new IllegalStateException("Invalid CartItem cartId");
        	}
            
            INVOICE_ITEM item = getNewInvoiceItemObject();
            item.setInvoiceId(invoiceId);
            item.setProductId(cartItem.getProductId());
            item.setPromoId(cartItem.getPromoId());
            item.setDescription(cartItem.getDescription());
            item.setDiscountDescription(cartItem.getDiscountDescription());
            item.setQuantity(cartItem.getQuantity());
            item.setUnitPrice(cartItem.getUnitPrice());
            item.setSetupFee(cartItem.getSetupFee());
            item.setSubtotal(cartItem.getSubtotal());
            item.setDiscount(cartItem.getDiscount());
            item.setTotal(cartItem.getTotal());
            item.setCreatedDate(now);
            item.setSubscriptionStartDate(cartItem.getSubscriptionStartDate());
            item.setSubscriptionEndDate(cartItem.getSubscriptionEndDate());
            item = getInvoiceItemService().add(item);
        }

        return invoice;
    }
    
    @Override
    public INVOICE createInvoice(Long appId, Long accountId, List<INVOICE_ITEM> itemList) {
        Date now = new Date();
        //String invoiceNo = sequence.getHexNo("invoiceNo", 9);
        String invoiceNo = KFlake.getId();
        
        ACCOUNT account = getAccountService().fetchById(accountId);
        
        BigDecimal zero = new BigDecimal(0);

        // first create an empty invoice
        INVOICE invoice = getNewInvoiceObject();
        invoice.setAppId(appId);
        invoice.setUserId(account.getOwnerId());
        invoice.setAccountId(account.getId());
        invoice.setCurrencyId(KCurrency.USD.getId());
        invoice.setInvoiceNo(invoiceNo);
        invoice.setSubtotal(zero);
        invoice.setTax(zero);
        invoice.setShipping(zero);
        invoice.setDiscount(zero);
        invoice.setTotal(zero);
        invoice.setAmountDue(zero);
        invoice.setNotes(null);
        invoice.setPaid(false);
        invoice.setClosed(false);
        invoice.setPaidDate(null);
        invoice.setDueDate(now);
        invoice.setInvoiceDate(now);
        invoice.setCreatedDate(now);

        invoice = add(invoice);
        Long invoiceId = invoice.getId();

        BigDecimal total = new BigDecimal(0);
        
        for (INVOICE_ITEM item : itemList) {
            item.setInvoiceId(invoiceId);
            item.setCreatedDate(now);
            
            item = getInvoiceItemService().update(item);
            
            total = total.add(item.getTotal());
        }
        
        invoice.setSubtotal(total);
        invoice.setTotal(total);
        invoice.setAmountDue(total);
        invoice = update(invoice);

        return invoice;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public void updateInvoice(INVOICE invoice) {
        if (invoice == null) return;
        update(invoice);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public INVOICE createInvoice(CART cart) {
        List<CART_ITEM> itemList = getCartItemService().getCartItemList(cart);
        INVOICE invoice = createInvoice(cart, itemList);
        
        // check out the cart before creating an invoice
        if (!cart.isCheckedOut()) {
        	cart.setCheckedOutDate(new Date());
        	cart.setCheckedOut(true);
        }
        cart.setInvoiceId(invoice.getId());
        cart.setInvoiced(true);
        cart.setInvoicedDate(new Date());
        getCartService().update(cart);
        
        return invoice;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public void closeInvoice(INVOICE invoice, boolean paid, 
    		BigDecimal amount, String paymentRef, String cardLast4) {
    	Date now = new Date();
        Date paidDate = null;
        if (paid) paidDate = now;
        
        invoice.setClosed(true);
        invoice.setClosedDate(now);
        invoice.setPaymentRef(paymentRef);
        invoice.setPaymentCardLast4(cardLast4);
        invoice.setPaid(paid);
        invoice.setPaidDate(paidDate);
        invoice.setAmountPaid(amount);
        update(invoice);
    }
}

