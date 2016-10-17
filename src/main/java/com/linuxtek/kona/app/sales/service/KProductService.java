package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KProductService<PRODUCT extends KProduct>
        extends KService, KDataService<PRODUCT> {
    
    public static final String SERVICE_PATH = "rpc/KProductService";

	//public Product fetchDefault(Long appId);
	public PRODUCT fetchByName(Long appId, String name);
    
	public List<PRODUCT> fetchAll(Long appId, Boolean active);
}
