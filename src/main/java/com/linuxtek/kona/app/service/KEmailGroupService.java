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

public interface KEmailGroupService<T extends KEmailGroup,
								    U extends KEmailAddress,
								    V extends KEmailGroupAddress> extends KService, KDataService<T> {
	
    public static final String SERVICE_PATH = "rpc/kona/EmailGroupService";

    public T create(String groupName);
    
    public T create(String groupName, List<String> emailList);
    
    public T create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList);
    
	public T fetchByName(String name);
	
	public V addGroupAddress(String groupName, String email);
	
	public void addGroupAddressList(String groupName, List<U> address);
	
	public V removeGroupAddress(String groupName, String email);
	
    public List<V> fetchGroupAddressList(String name);
}
