/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.app.entity.KEmailGroupAddress;
import com.linuxtek.kona.data.service.KDataService;

public interface KEmailGroupAddressService<T extends KEmailGroupAddress> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/EmailGroupAddressService";

	public T fetchByGroupIdAndAddressId(Long groupId, Long addressId);
	
	public List<T> fetchByGroupId(Long groupId);
	
	public List<T> fetchByAddressId(Long addressId);
}
