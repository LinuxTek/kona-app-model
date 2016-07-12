/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.app.entity.KEmailGroupAddress;
import com.linuxtek.kona.data.service.KDataService;

public interface KEmailGroupAddressService<EMAIL_GROUP_ADDRESS extends KEmailGroupAddress> 
		extends KService, KDataService<EMAIL_GROUP_ADDRESS> {
			
    public static final String SERVICE_PATH = "rpc/kona/EmailGroupAddressService";

	public EMAIL_GROUP_ADDRESS fetchByGroupIdAndAddressId(Long groupId, Long addressId);
	
	public List<EMAIL_GROUP_ADDRESS> fetchByGroupId(Long groupId);
	
	public List<EMAIL_GROUP_ADDRESS> fetchByAddressId(Long addressId);
}
