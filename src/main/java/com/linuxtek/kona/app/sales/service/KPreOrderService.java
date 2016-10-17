package com.linuxtek.kona.app.sales.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.sales.entity.KPreOrder;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KPreOrderService<PRE_ORDER extends KPreOrder>
        extends KService, KDataService<PRE_ORDER> {
            
    public static final String SERVICE_PATH = "rpc/KPreOrderService";

	public PRE_ORDER fetchByUid(String uid);
    
	public List<PRE_ORDER> fetchByAppId(Long appId);
	
	public PRE_ORDER create(PRE_ORDER preOrder, Map<String,Object> metadata, boolean processPayment, boolean sendReceipt);
}
