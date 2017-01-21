package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KNotificationDelivery;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractNotificationDeliveryService<NotificationDelivery extends KNotificationDelivery, 
													NotificationDeliveryExample>
		extends KAbstractService<NotificationDelivery,NotificationDeliveryExample>
		implements KNotificationDeliveryService<NotificationDelivery> {
	
	private static Logger logger = LoggerFactory.getLogger(KAbstractNotificationDeliveryService.class);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(NotificationDelivery notificationDelivery) {
		if (notificationDelivery.getCreatedDate() == null) {
			notificationDelivery.setCreatedDate(new Date());
		}

		notificationDelivery.setUpdatedDate(new Date());

		if (notificationDelivery.getCode() == null) {
			notificationDelivery.setCode(uuid());
		}
	}

	// ----------------------------------------------------------------------------

	@Override
	public NotificationDelivery fetchByCode(String code) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("code", code);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<NotificationDelivery> fetchByNotificationId(Long notificationId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("notificationId", notificationId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}
