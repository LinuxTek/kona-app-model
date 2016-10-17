package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCampaignChannel;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KCampaignChannelService<CAMPAIGN_CHANNEL extends KCampaignChannel>
        extends KService, KDataService<CAMPAIGN_CHANNEL> {
            
    public static final String SERVICE_PATH = "rpc/KCampaignChannelService";

    public CAMPAIGN_CHANNEL fetchByUid(String uid);
    
    public CAMPAIGN_CHANNEL fetchByUrlPathAndName(String urlPath, String name);
    
    public CAMPAIGN_CHANNEL fetchBySmsNumber(String smsNumber);
    
    public CAMPAIGN_CHANNEL fetchByCampaignIdAndTypeIdAndName(Long campaignId, Long typeId, String name);
    
    public List<CAMPAIGN_CHANNEL> fetchByCampaignId(Long campaignId);
}
