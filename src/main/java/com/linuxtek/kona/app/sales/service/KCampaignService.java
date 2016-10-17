package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCampaign;
import com.linuxtek.kona.app.sales.entity.KCampaignType;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KCampaignService<CAMPAIGN extends KCampaign>
        extends KService, KDataService<CAMPAIGN> {
    
    public static final String SERVICE_PATH = "rpc/KCampaignService";

	public CAMPAIGN fetchByUid(String uid);
    
	public CAMPAIGN fetchByName(String name);
    
	public List<CAMPAIGN> fetchByPartnerIdAndType(Long partnerId, KCampaignType type);
}
