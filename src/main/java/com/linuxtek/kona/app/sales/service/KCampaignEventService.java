package com.linuxtek.kona.app.sales.service;

import java.util.List;

import com.linuxtek.kona.app.sales.entity.KCampaignEvent;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KCampaignEventService<CAMPAIGN_EVENT extends KCampaignEvent>
        extends KService, KDataService<CAMPAIGN_EVENT> {
            
    public static final String SERVICE_PATH = "rpc/KCampaignEventService";

    public CAMPAIGN_EVENT fetchByUid(String uid);
    
    public List<CAMPAIGN_EVENT> fetchByCampaignId(Long campaignId);
    
    public List<CAMPAIGN_EVENT> fetchByCampaignIdAndChannelId(Long campaignId, Long channelId);
}
