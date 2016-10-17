package com.linuxtek.kona.app.sales.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KToken;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KTokenService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.app.sales.entity.KCart;
import com.linuxtek.kona.app.sales.entity.KCartItem;
import com.linuxtek.kona.app.sales.entity.KCurrency;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KClassUtil;

public abstract class KAbstractCartService<CART extends KCart, 
										   CART_EXAMPLE,
										   CART_ITEM extends KCartItem,
										   USER extends KUser,
										   TOKEN extends KToken>
		extends KAbstractService<CART,CART_EXAMPLE>
		implements KCartService<CART> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCartService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract CART getNewObject();

	protected abstract <S extends KCartItemService<CART_ITEM,CART>> S getCartItemService();
    
	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract <S extends KTokenService<TOKEN>> S getTokenService();
    
	// ----------------------------------------------------------------------------

    @Override
    public void validate(CART cart) {
        if (cart.getCreatedDate() == null) {
        	cart.setCreatedDate(new Date());
        }
        
        cart.setLastUpdated(new Date());
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public List<CART> fetchByUserId(Long userId) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
    	return fetchByCriteria(0, 99999, null, filter, false);
    }

	// ----------------------------------------------------------------------------
    
    // NOTE: a session could have many carts
    @Override
    public List<CART> fetchBySessionId(String sessionId) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("sessionId", sessionId);
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART fetchActiveBySessionId(String sessionId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("sessionId", sessionId)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();
                
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART fetchActiveByAccessToken(String accessToken) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("accessToken", accessToken)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();
                
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------

    @Override
    public CART fetchActiveByUserIdAndAppId(Long userId, Long appId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("userId", userId)
                .and("appId", appId)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();
                
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART getActiveCartByUserIdAndAppId(Long userId, Long appId) {
        if (userId == null || appId == null) return null;
        return fetchActiveByUserIdAndAppId(userId, appId);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART getCart(Long userId, Long appId) {
    	return getCart(userId, appId, null);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART getCart(Long appId, KServiceClient client) {
        return getCart(null, appId, client);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART getCart(Long userId, Long appId, KServiceClient client) {
    	CART cart = null;
        TOKEN token = null;
        String accessToken = null;
        String sessionId = null;
        
        if (client != null) {
        	accessToken = client.getAccessToken();
        	sessionId = client.getSessionId();
        }
        
        if (userId != null) {
        	cart = getActiveCartByUserIdAndAppId(userId, appId);
        	if (cart != null) {
        		String s = cart.getSessionId();
        		if (s == null && sessionId != null) {
        			cart.setSessionId(sessionId);
        			update(cart);
        		}

        		if (s != null && sessionId != null
        				&& !s.toLowerCase().equals(sessionId.toLowerCase())) {
                    expireCart(cart);
        			cart = null;
        		}
            }
        }
        
        //String accessToken = KServletUtil.getAccessToken(req);
        if (accessToken != null) {
            token = getTokenService().fetchByAccessToken(accessToken);
            
            if (token == null || !getTokenService().isValid(token, false)) {
                accessToken = null;
                token = null;
            }
        }
        
        if (userId != null && token != null && token.getUserId() != null) {
        	if (!token.getUserId().equals(userId)) {
                
                logger.warn("getCart(): userId and token userId mismatch:"
                        + "\nuserId: " + userId
                        + "\ntoken: " + KClassUtil.toJson(token));
                
        		accessToken = null;
        		token = null;
        	}
        }

        if (userId == null && token != null) {
            userId = token.getUserId();
        }
        
        //String sessionId = KServletUtil.getSessionId(req);
        
        // first see if we have a cart for this token key
        if (cart == null && accessToken != null) {
        	cart = fetchActiveByAccessToken(accessToken);
            if (cart != null) {
                if (userId != null && (cart.getUserId() == null 
                            || !cart.getUserId().equals(userId))) {
                    expireCart(cart);
                    cart = null;
                }
            }

            if (cart != null) {
				String s = cart.getSessionId();
				if (s == null && sessionId != null) {
					cart.setSessionId(sessionId);
					update(cart);
				}

				if (s != null && sessionId != null
						&& !s.toLowerCase().equals(sessionId.toLowerCase())) {
                    expireCart(cart);
					cart = null;
				}
            }
        }
        
        // next see if we have a cart for this session id
        if (cart == null && sessionId != null) {
        	cart = fetchActiveBySessionId(sessionId);
            if (cart != null) {
                if (userId != null && (cart.getUserId() == null 
                            || !cart.getUserId().equals(userId))) {
                    expireCart(cart);
                    cart = null;
                }

            }
            if (cart != null) {
				if (accessToken != null && cart.getAccessToken() == null) {
					cart.setAccessToken(accessToken);
                    /*
                    if (cart.getDefaultCardLast4() == null) {
                    	Account account = getAccountByUserId(token.getUserId());
                    	String cardLast4 = getCardLast4(account);
                    	cart.setDefaultCardLast4(cardLast4);
                    }
                    */
					update(cart);
				}

				if (accessToken != null && cart.getAccessToken() != null
						&& !cart.getAccessToken().equals(accessToken)) {
					logger.warn("Invalid accessToken for cart:"
							+ "\ncurrent accessToken: " + accessToken
							+ "\nactive cart: " + KClassUtil.toJson(cart));
                    expireCart(cart);
					cart = null;
				}
            }
        }
            
        // if we don't have a cart, create one
        if (cart == null) {
            cart = createCart(userId, appId, client);
        }
        
        logger.debug("getCart(): cart:\n" + KClassUtil.toJson(cart));
    	return cart;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public CART createCart(Long userId, Long appId, KServiceClient client) {
        
    	String accessToken = null;
    	String sessionId = null;
        String hostname = null;
        String browser = null;
        Double latitude = null;
        Double longitude = null;
        
		Long accountId = null;
		//String cardLast4 = null;
        
    	if (client != null) {
    		accessToken = client.getAccessToken();
    		sessionId = client.getSessionId();
    		hostname = client.getHostname();
			browser = client.getBrowser();
			latitude = client.getLatitude();
			longitude = client.getLatitude();
    	}
    	
        USER user = null;
        
		if (userId != null) {
            user = getUserService().fetchById(userId);
			accountId = user.getAccountId();
		}
        
		CART cart = getNewObject();
		cart.setUserId(userId);
		cart.setAppId(appId);
		cart.setAccountId(accountId);
        cart.setCurrencyId(KCurrency.USD.getId());
		cart.setAccessToken(accessToken);
		cart.setSessionId(sessionId);
		cart.setHostname(hostname);
		cart.setBrowser(browser);
		cart.setLatitude(latitude);
		cart.setLongitude(longitude);
		//cart.setDefaultCardLast4(cardLast4);
		cart.setSubtotal(new BigDecimal(0));
		cart.setDiscount(new BigDecimal(0));
		cart.setShipping(new BigDecimal(0));
		cart.setTax(new BigDecimal(0));
		cart.setTotal(new BigDecimal(0));
		cart.setCheckedOut(false);
		cart.setInvoiced(false);
		cart.setCreatedDate(new Date());

		cart = add(cart);

		return cart;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public void updateCart(CART cart) {
        if (cart == null) return;
        update(cart);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public void expireCart(Long cartId) {
        if (cartId == null) return;
        CART cart = fetchById(cartId);
        expireCart(cart);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void expireCart(CART cart) {
        if (cart == null) return;
        cart.setExpired(true);
        cart.setExpiredDate(new Date());
        update(cart);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART checkout(CART cart) {
        if (cart == null) return null;
        cart.setCheckedOutDate(new Date());
        cart.setCheckedOut(true);
        update(cart);
        return cart;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public CART updateCartTotals(CART cart) {
    	BigDecimal subtotal = new BigDecimal(0);
    	BigDecimal discount = new BigDecimal(0);
    	BigDecimal total = new BigDecimal(0);
        
    	List<CART_ITEM> itemList = getCartItemService().getCartItemList(cart);
        
    	for (CART_ITEM item : itemList) {
    		if (item.getTotal() != null) {
    			total = total.add(item.getTotal());
    		}
        
    		if (item.getSubtotal() != null) {
    			subtotal = subtotal.add(item.getSubtotal());
    		}
        
    		if (item.getDiscount() != null) {
    			discount = discount.add(item.getDiscount());
    		} 
        
    	}
        
        cart.setSubtotal(subtotal);
        cart.setDiscount(discount);
        cart.setTotal(total);
        
        update(cart);
        
    	return cart;	
    }
}
