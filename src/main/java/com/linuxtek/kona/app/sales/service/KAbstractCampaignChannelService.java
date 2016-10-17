package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KCampaignChannel;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractCampaignChannelService<CAMPAIGN_CHANNEL extends KCampaignChannel, 
													  CAMPAIGN_CHANNEL_EXAMPLE>
		extends KAbstractService<CAMPAIGN_CHANNEL,CAMPAIGN_CHANNEL_EXAMPLE>
		implements KCampaignChannelService<CAMPAIGN_CHANNEL> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignChannelService.class);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(CAMPAIGN_CHANNEL campaignChannel) {
		if (campaignChannel.getCreatedDate() == null) {
			campaignChannel.setCreatedDate(new Date());
		}
        
		if (campaignChannel.getUid() == null) {
			campaignChannel.setUid(uuid());
		}
	}

	// ----------------------------------------------------------------------

	@Override
	public List<CAMPAIGN_CHANNEL> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------
	
	@Override
	public CAMPAIGN_CHANNEL fetchByCampaignIdAndTypeIdAndName(Long campaignId, Long typeId, String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        filter.put("typeId", typeId);
        filter.put("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------

	@Override
	public CAMPAIGN_CHANNEL fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------

	@Override
	public CAMPAIGN_CHANNEL fetchBySmsNumber(String smsNumber) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("smsNumber", smsNumber);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------

	@Override
	public CAMPAIGN_CHANNEL fetchByUrlPathAndName(String urlPath, String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("urlPath", urlPath);
		filter.put("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
}
