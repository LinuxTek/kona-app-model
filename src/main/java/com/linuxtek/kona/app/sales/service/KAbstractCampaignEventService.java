package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KCampaignEvent;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractCampaignEventService<CAMPAIGN_EVENT extends KCampaignEvent, 
													CAMPAIGN_EVENT_EXAMPLE>
		extends KAbstractService<CAMPAIGN_EVENT,CAMPAIGN_EVENT_EXAMPLE>
		implements KCampaignEventService<CAMPAIGN_EVENT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignEventService.class);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(CAMPAIGN_EVENT campaignEvent) {
		if (campaignEvent.getCreatedDate() == null) {
			campaignEvent.setCreatedDate(new Date());
		}
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<CAMPAIGN_EVENT> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	public List<CAMPAIGN_EVENT> fetchByCampaignIdAndChannelId(Long campaignId, Long channelId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		filter.put("channelId", channelId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	public CAMPAIGN_EVENT fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
}
