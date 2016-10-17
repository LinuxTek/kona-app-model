package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KSalesLead;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractSalesLeadService<SALES_LEAD extends KSalesLead, 
										   SALES_LEAD_EXAMPLE>
		extends KAbstractService<SALES_LEAD,SALES_LEAD_EXAMPLE>
		implements KSalesLeadService<SALES_LEAD> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractSalesLeadService.class);
    
	// ----------------------------------------------------------------------------
    
	protected abstract void sendNotification(SALES_LEAD lead);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(SALES_LEAD salesLead) {
		if (salesLead.getCreatedDate() == null) {
			salesLead.setCreatedDate(new Date());
		}

		if (salesLead.getUid() == null) {
			salesLead.setUid(uuid());
		}
		
		if (salesLead.getChannel() == null) {
			salesLead.setChannel("website");
		}
	}
    
	// ----------------------------------------------------------------------------
    
	@Override 
	public SALES_LEAD create(SALES_LEAD salesLead) {
        salesLead = add(salesLead);
        sendNotification(salesLead);
		return salesLead;
	}
    
	// ----------------------------------------------------------------------------

	public List<SALES_LEAD> fetchByReferredByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("referredByUserId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

}

