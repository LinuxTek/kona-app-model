/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KRegistration;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;

public abstract class KAbstractRegistrationService<R extends KRegistration, EXAMPLE, U extends KUser> 
		extends KAbstractService<R,EXAMPLE>
		implements KRegistrationService<R,U> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRegistrationService.class);
	
	// ----------------------------------------------------------------------------

	protected abstract R getNewObject();
	
	// ----------------------------------------------------------------------------

    @Override
	public R createRegistration(U user, KServiceClient client, Integer signupTime) {

		R reg = getNewObject();
		reg.setAppId(client.getAppId());
		reg.setUserId(user.getId());
		reg.setUsername(user.getUsername());
		reg.setHostname(client.getHostname());
		reg.setBrowser(client.getBrowser());
        reg.setClientId(client.getClientId());
        reg.setDeviceUuid(client.getDeviceUuid());
		reg.setSignupTime(signupTime);
		reg.setCreatedDate(new Date());
        return add(reg);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public void validate(R registration) {
    	if (registration.getCreatedDate() == null) {
			registration.setCreatedDate(new Date());
		}
    	
    	registration.setLastUpdated(new Date());
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public R fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
}
