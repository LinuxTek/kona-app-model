package com.linuxtek.kona.app.messaging.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.messaging.entity.KEmailGroupAddress;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractEmailGroupAddressService<EMAIL_GROUP_ADDRESS extends KEmailGroupAddress,
													    EMAIL_GROUP_ADDRESS_EXAMPLE>
		extends KAbstractService<EMAIL_GROUP_ADDRESS,EMAIL_GROUP_ADDRESS_EXAMPLE>
		implements KEmailGroupAddressService<EMAIL_GROUP_ADDRESS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEmailGroupAddressService.class);
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(EMAIL_GROUP_ADDRESS emailGroupAddress) {
		if (emailGroupAddress.getCreatedDate() == null) {
			emailGroupAddress.setCreatedDate(new Date());
		}
		emailGroupAddress.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------

	@Override
	public EMAIL_GROUP_ADDRESS fetchByGroupIdAndAddressId(Long groupId, Long addressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		filter.put("addressId", addressId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------

	@Override
	public List<EMAIL_GROUP_ADDRESS> fetchByGroupId(Long groupId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------

	@Override
	public List<EMAIL_GROUP_ADDRESS> fetchByAddressId(Long addressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("addressId", addressId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}

