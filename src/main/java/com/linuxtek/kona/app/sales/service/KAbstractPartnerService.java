package com.linuxtek.kona.app.sales.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KPartner;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractPartnerService<PARTNER extends KPartner, 
										   PARTNER_EXAMPLE>
		extends KAbstractService<PARTNER,PARTNER_EXAMPLE>
		implements KPartnerService<PARTNER> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPartnerService.class);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(PARTNER partner) {
		if (partner.getCreatedDate() == null) {
			partner.setCreatedDate(new Date());
		}

		if (partner.getUid() == null) {
			partner.setUid(uuid());
		}

		String name = KInflector.getInstance().slug(partner.getDisplayName());
		partner.setName(name);
	}
    
	// ----------------------------------------------------------------------------
    
	@Override 
	public PARTNER retire(PARTNER partner) {
		// fetch fresh object
		partner = fetchById(partner.getId());

		// this isn't a slip up in logic. it's possible that the app object passed in
		// is not retired but by the time this call is made and the object is refreshed
		// is has been retired.  if so, fetchById should return null.
		// return app if already retired
		if (partner == null || partner.getRetiredDate() != null) {
			return partner;
		}

		// NOTE: we need uuid here in case multiple apps with the same name are deleted.
		// first app called test is deleted, then second app created called test gets deleted.
		String prefix = "$RETIRED_" + uuid() + "_"; 
		partner.setName(prefix + partner.getName());;
		partner.setDisplayName(prefix + partner.getDisplayName());;
		partner.setEnabled(false);
		partner.setRetiredDate(new Date());
		partner = update(partner);

		return partner;
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PARTNER fetchByName(String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public PARTNER fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	public List<PARTNER> fetchByParentId(Long parentId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<PARTNER> fetchAllByParentId(Long parentId) {
		List<PARTNER> list = fetchByParentId(parentId);
		ArrayList<PARTNER> result = new ArrayList<PARTNER>();
		for (PARTNER partner : list) {
			result.add(partner);
			List<PARTNER> children = fetchAllByParentId(partner.getId());
			if (children != null && children.size() > 0) {
				result.addAll(children);
			}
		}
		return result;
	}
}
