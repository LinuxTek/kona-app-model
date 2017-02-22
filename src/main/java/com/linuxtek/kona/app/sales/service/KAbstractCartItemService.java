package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.app.sales.entity.KPurchase;
import com.linuxtek.kona.app.sales.entity.KPromo;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KDateUtil;

public abstract class KAbstractCartItemService<CART_ITEM extends KCartItem, 
										   CART_ITEM_EXAMPLE,
										   CART extends KCart,
										   USER extends KUser,
										   ACCOUNT extends KAccount,
										   PROMO extends KPromo,
										   PRODUCT extends KProduct,
										   PURCHASE extends KPurchase>
		extends KAbstractService<CART_ITEM,CART_ITEM_EXAMPLE>
		implements KCartItemService<CART_ITEM,CART> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCartItemService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract CART_ITEM getNewObject();

	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract <S extends KCartService<CART>> S getCartService();
    
	protected abstract <S extends KPromoService<PROMO,ACCOUNT,PRODUCT>> S getPromoService();
    
	protected abstract <S extends KProductService<PRODUCT>> S getProductService();
    
	protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();
    
    
	// ----------------------------------------------------------------------------

    @Override
    public void validate(CART_ITEM cartItem) {
        if (cartItem.getCreatedDate() == null) {
        	cartItem.setCreatedDate(new Date());
        }

		if (cartItem.getUid() == null) {
			cartItem.setUid(uuid());
		}
        
        cartItem.setUpdatedDate(new Date());
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public List<CART_ITEM> fetchByCartId(Long cartId) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("cartId", cartId);
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public List<CART_ITEM> getCartItemList(CART cart) {
        if (cart == null || cart.getId() == null) return null;
        return fetchByCartId(cart.getId());
    }
    
	// ----------------------------------------------------------------------------
 
    @Override 
    public CART_ITEM updateCartItem(Long itemId) {
    	CART_ITEM item = fetchById(itemId);
        return updateCartItem(item.getCartId(), itemId);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART_ITEM updateCartItem(Long cartId, Long itemId) {
        CART cart = getCartService().fetchById(cartId);
        CART_ITEM item = fetchById(itemId);
        return updateCartItem(cart, item);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART_ITEM updateCartItem(CART_ITEM item) {
        if (item == null) return null;
        CART cart = getCartService().fetchById(item.getCartId());
        return updateCartItem(cart, item);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART_ITEM updateCartItem(CART cart, CART_ITEM item) {
        if (item == null) return null;
        item = update(item);
        getCartService().updateCartTotals(cart);
        return item;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART_ITEM addCartItem(CART cart, Long userId, Long productId, Long promoId) {
        return addCartItem(cart, userId, productId, 1, promoId, null);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART_ITEM addCartItem(KServiceClient client, Long userId, 
    		Long appId, Long productId, Integer quantity,
    		Long promoId, String notes) {
    	CART cart = getCartService().getCart(userId, appId, client);
        return addCartItem(cart, userId, productId, quantity, promoId, notes);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART_ITEM addCartItem(CART cart, Long userId, 
    		Long productId, Integer quantity,
    		Long promoId, String notes) {
        
        USER user = getUserService().fetchById(userId);
        
        PRODUCT product = getProductService().fetchById(productId);
        
        if (quantity == null) quantity = 1;
        
        // first make sure we're not adding a duplicate
        /*
        List<CartItem> itemList = cartItemService.fetchByCartId(cart.getId());
        if (itemList != null && itemList.size()>0) {
        	for (CartItem i : itemList) {
        		if (i.getUserId().equals(userId) 
        				&& i.getSubscriptionId().equals(productId)) {
        			}
        	}
        }
        */
        
        PROMO promo = getPromoService().fetchById(promoId);
        //promo = getPromoService().fetchSignupDefault();
        
        Date startDate = null;
        Date endDate = null;
        if (product.isSubscription()) {
            startDate = new Date();
            endDate = getSubscriptionEndDate(user, product, quantity, promo);
        }
        
        BigDecimal unitPrice = product.getPrice();
        BigDecimal subtotal = unitPrice.multiply(new BigDecimal(quantity));
        
        BigDecimal setupFee = product.getSetupFee();
        if (setupFee != null) {
        	subtotal = subtotal.add(setupFee);
        }
        
        BigDecimal discount = getItemDiscount(product, promo);
        BigDecimal total = subtotal.subtract(discount);
        
        subtotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        String description = getItemDescription(user, product);
        String discountDescription = getItemDiscountDescription(promo);
        
        CART_ITEM item = getNewObject();
        item.setCartId(cart.getId());
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPromoId(promoId);
        item.setDescription(description);
        item.setDiscountDescription(discountDescription);
        item.setUnitPrice(unitPrice);
        item.setSubtotal(subtotal);
        item.setSetupFee(setupFee);
        item.setDiscount(discount);
        item.setTotal(total);
        item.setSubscriptionStartDate(startDate);
        item.setSubscriptionEndDate(endDate);
        item.setCreatedDate(new Date());
        
        add(item);
        getCartService().updateCartTotals(cart);
        
        logger.debug("added cart item: " + KClassUtil.toJson(item));
        return item;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public CART_ITEM removeCartItem(Long itemId) {
    	CART_ITEM item = fetchById(itemId);
        return removeCartItem(item.getCartId(), itemId);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public CART_ITEM removeCartItem(Long cartId, Long itemId) {
        CART cart = getCartService().fetchById(cartId);
        CART_ITEM item = fetchById(itemId);
        return removeCartItem(cart, item);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public CART_ITEM removeCartItem(CART cart, CART_ITEM item) {
        if (!cart.getId().equals(item.getCartId())) {
        	throw new IllegalStateException("Cart and CartItem mismatch: "
                    + "\nCart: " + KClassUtil.toJson(cart)
                    + "\nCartItem: " + KClassUtil.toJson(item));
        }
        
        remove(item);
        getCartService().updateCartTotals(cart);
        return item;
    }
    
    // ----------------------------------------------------------------------------
    
    public BigDecimal getItemDiscount(PRODUCT product, PROMO promo) {
        BigDecimal discount = null;
        if (promo == null) {
        	discount = new BigDecimal(0);
            return discount;
        }
        
        discount = promo.getDiscountAmount();
        if (discount == null || discount.compareTo(new BigDecimal(0)) == 0) {
        	Integer pct = promo.getDiscountPct();
            if (pct != null) {
                BigDecimal rate = new BigDecimal(pct);
                rate = rate.divide(new BigDecimal(100), 
                		2, BigDecimal.ROUND_HALF_UP);
            	BigDecimal price = product.getPrice();
                discount = price.multiply(rate);
            }
        }
        
        if (discount == null) {
        	discount = new BigDecimal(0);
        }
        
        // check setup fee
        BigDecimal setupFee = product.getSetupFee();
        BigDecimal promoSetupFee = promo.getSetupFee();
        
        if (setupFee != null && promoSetupFee != null
                && setupFee.compareTo(new BigDecimal(0))>0
                && promoSetupFee.compareTo(new BigDecimal(0))>0) {
            BigDecimal feeDiscount = setupFee.subtract(promoSetupFee);
            discount = discount.add(feeDiscount);
        }
        
    	return discount; 
    }
    
    // ----------------------------------------------------------------------------

    private String getItemDescription(USER user, PRODUCT product) {
        String description = product.getDescription();
    	return description;
    }
    
    // ----------------------------------------------------------------------------
    
    private String getItemDiscountDescription(PROMO promo) {
        String description = null;

        if (promo != null) {
        	description = "\n<div style='margin-top:10px;'><b>Promo:</b> [" 
        			+ promo.getDisplayName() + "] "
                    + promo.getDescription() + "</div>";
        }
        
    	return description;
    }
    
    // ----------------------------------------------------------------------------
    
    private Date getSubscriptionEndDate(USER user, PRODUCT product, Integer quantity, PROMO promo) {
        if (!product.isSubscription()) return null;

    	Date endDate = null;
    	
    	// if we have a product, then add to its endDate
    	PURCHASE purchase = getPurchase(user.getAccountId(), product.getId());
    	if (purchase != null) {
    		endDate = purchase.getExpirationDate();
    	}
        
        // If we have an endDate that already expired, then reset it to now
        Date now = new Date();
        if (endDate == null || endDate.getTime() < now.getTime()) {
        	endDate = now;
        }
        
        Integer subscriptionDays = product.getSubscriptionDays();
        if (promo != null && promo.getSubscriptionDays() != null) {
        	subscriptionDays = promo.getSubscriptionDays();
            
            // -1 indicates unlimited subscription so return null for endDate
            if (subscriptionDays == -1) {
            	return null;
            }
        }
        subscriptionDays = subscriptionDays * quantity;
        
        endDate = KDateUtil.addDays(endDate, subscriptionDays);
    	return endDate;
    }
    
    // ----------------------------------------------------------------------------
    
    private PURCHASE getPurchase(Long accountId, Long productId) {
    	return getPurchaseService().fetchByAccountIdAndProductId(accountId, productId);
    }
  
}

