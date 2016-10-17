package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KPreOrder;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.stripe.entity.KCharge;

public abstract class KAbstractPreOrderService<PRE_ORDER extends KPreOrder, 
										      PRE_ORDER_EXAMPLE,
										      ACCOUNT extends KAccount>
		extends KAbstractService<PRE_ORDER,PRE_ORDER_EXAMPLE>
		implements KPreOrderService<PRE_ORDER> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPreOrderService.class);

	// ----------------------------------------------------------------------------
    
	protected abstract void sendPreOrderReceipt(PRE_ORDER preOrder);

	protected abstract <S extends KStripeService<ACCOUNT>> S getStripeService();

	// ----------------------------------------------------------------------------
    
	@Override
	public void validate(PRE_ORDER preOrder) {
		if (preOrder.getCreatedDate() == null) {
			preOrder.setCreatedDate(new Date());
		}

		if (preOrder.getUid() == null) {
			preOrder.setUid(uuid());
		}
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PRE_ORDER> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PRE_ORDER fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PRE_ORDER create(PRE_ORDER preOrder, Map<String,Object> metadata, boolean processPayment, boolean sendReceipt) {
		if (processPayment) {
			preOrder = processPayment(preOrder, metadata);
		}

		preOrder = add(preOrder);

		if (sendReceipt) {
			if (preOrder.getEmail() != null) {
				logger.debug("sending payment receipt ...");
				sendPreOrderReceipt(preOrder);
			}
		}

		return preOrder;
	}
    
	// ----------------------------------------------------------------------------

	private PRE_ORDER processPayment(PRE_ORDER preOrder, Map<String,Object> metadata) {
		if (preOrder.getProcessor() == null) {
			throw new IllegalArgumentException("processor must be specified");
		}

		if (!preOrder.getProcessor().equalsIgnoreCase("stripe")) {
			throw new IllegalArgumentException("Only processor 'stripe' is currently supported");
		}

		if (preOrder.getPaymentToken() == null) {
			throw new IllegalArgumentException("paymentToken must be specified");
		}

		if (preOrder.getAmount() == null) {
			throw new IllegalArgumentException("amount must be specified");
		}

		KCharge charge = getStripeService().chargeCard(preOrder.getRefAppId(), preOrder.getPaymentToken(), preOrder.getAmount(), 
				preOrder.getPaymentDescription(), null, metadata, null);

		if (charge != null) {
			preOrder.setPaymentRef(charge.getId());
			if (charge.getCard() != null) {
				preOrder.setPaymentCardLast4(charge.getCard().getLast4());
			}
		}
		
		return preOrder;
	}

	// ----------------------------------------------------------------------------

}

