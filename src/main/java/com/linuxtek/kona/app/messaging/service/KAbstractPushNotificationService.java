package com.linuxtek.kona.app.messaging.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.core.entity.KApp;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KAppService;
import com.linuxtek.kona.app.messaging.entity.KPushNotification;
import com.linuxtek.kona.app.messaging.service.KPushService;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractPushNotificationService<T extends KPushNotification,EXAMPLE,A extends KApp> 
		extends KAbstractService<T,EXAMPLE>
		implements KPushNotificationService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPushNotificationService.class);

	// ----------------------------------------------------------------------------

	protected abstract <S extends KAppService<A>> S getAppService();
    
	protected abstract <S extends KPushService> S getPushService();

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T pushNotification) {
		if (pushNotification.getCreatedDate() == null) {
			pushNotification.setCreatedDate(new Date());
		}

		pushNotification.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByAppIdAndPlatformNameAndSandbox(Long appId, String platformName, boolean sandbox) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("platformName", platformName);
		filter.put("sandbox", sandbox);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override @Transactional
	public T save(T notification) {
		if (notification.getId() == null) {
			notification = add(notification);
		} else {
			notification = update(notification);
		}
		
		return updateEndpoint(notification);
	}

	// ----------------------------------------------------------------------------

	@Override @Transactional
	public T updateEndpoint(T notification) {
		if (notification.getPushServerSecret() == null) {
			throw new IllegalArgumentException("PushServerKey must be set");
		}

		KPushService.Platform pushPlatform = 
				getPushService().getPushPlatform(notification.getPlatformName(), notification.isSandbox());
		
		if (pushPlatform == null) {
			throw new IllegalArgumentException("Platform is not supported for push notification: " 
					+ notification.getPlatformName());
		}

		// ok, everything looks good so far.  if we have an existing endpoint, delete it first
		if (notification.getPushEndpoint() != null) {
			getPushService().deleteApplicationEndpoint(notification.getPushEndpoint());
		}

		A app = getAppService().fetchById(notification.getAppId());
		String principal = notification.getPushServerKey();
		String credential = notification.getPushServerSecret();

		String endpoint = getPushService().createApplicationEndpoint(pushPlatform, app.getName(), principal, credential);

		if (endpoint == null) {
			throw new IllegalStateException("Unable to create endpoint for pushNotificationId: " + notification.getId());
		}

		notification.setPushEndpoint(endpoint);

		return update(notification);
	}
    
	// ----------------------------------------------------------------------------
}
