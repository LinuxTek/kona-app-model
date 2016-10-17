package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KSalesLead;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KSalesLeadService<SALES_LEAD extends KSalesLead>
        extends KService, KDataService<SALES_LEAD> {
    
    public static final String SERVICE_PATH = "rpc/KSalesLeadService";

	public SALES_LEAD create(SALES_LEAD salesLead);
    
	public List<SALES_LEAD> fetchByReferredByUserId(Long userId);
}
