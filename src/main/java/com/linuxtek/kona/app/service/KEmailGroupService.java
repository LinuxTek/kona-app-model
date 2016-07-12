/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KEmailAddress;
import com.linuxtek.kona.app.entity.KEmailGroup;
import com.linuxtek.kona.app.entity.KEmailGroupAddress;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KEmailGroupService<EMAIL_GROUP extends KEmailGroup,
								    EMAIL_ADDRESS extends KEmailAddress,
								    EMAIL_GROUP_ADDRESS extends KEmailGroupAddress> 
		extends KService, KDataService<EMAIL_GROUP> {
	
    public static final String SERVICE_PATH = "rpc/kona/EmailGroupService";

    public EMAIL_GROUP create(String groupName);
    
    public EMAIL_GROUP create(String groupName, List<String> emailList);
    
    public EMAIL_GROUP create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList);
    
	public EMAIL_GROUP fetchByName(String name);
	
	public EMAIL_GROUP_ADDRESS addGroupAddress(String groupName, String email);
	
	public void addGroupAddressList(String groupName, List<EMAIL_ADDRESS> address);
	
	public EMAIL_GROUP_ADDRESS removeGroupAddress(String groupName, String email);
	
    public List<EMAIL_GROUP_ADDRESS> fetchGroupAddressList(String name);
}
