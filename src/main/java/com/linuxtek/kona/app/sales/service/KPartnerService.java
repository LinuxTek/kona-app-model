package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KPartner;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KPartnerService<PARTNER extends KPartner> 
		extends KService, KDataService<PARTNER> {
    
    public static final String SERVICE_PATH = "rpc/KPartnerService";

    public PARTNER fetchByUid(String uid);
    
    public PARTNER fetchByName(String name);
    
    public List<PARTNER> fetchAllByParentId(Long parentId);
    
	public PARTNER retire(PARTNER partner);
}
