package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KCampaign;
import com.linuxtek.kona.app.sales.entity.KCampaignType;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractCampaignService<CAMPAIGN extends KCampaign, 
													CAMPAIGN_EXAMPLE>
		extends KAbstractService<CAMPAIGN,CAMPAIGN_EXAMPLE>
		implements KCampaignService<CAMPAIGN> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignService.class);

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(CAMPAIGN campaign) {
		if (campaign.getCreatedDate() == null) {
			campaign.setCreatedDate(new Date());
		}
        
		if (campaign.getUid() == null) {
			campaign.setUid(uuid());
		}
	}

	// ----------------------------------------------------------------------

	@Override
	public CAMPAIGN fetchByName(String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}


	// ----------------------------------------------------------------------

	@Override
	public List<CAMPAIGN> fetchByPartnerIdAndType(Long partnerId, KCampaignType type) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("partnerId", partnerId);
        
		filter.put("enabled", true);
        
		if (type != null) {
			filter.put("typeId", type.getId());
		}
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------

	@Override
	public CAMPAIGN fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------

}
