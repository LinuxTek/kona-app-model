/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.entity.KEmailAddress;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KEmailAddressService<T extends KEmailAddress> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/EmailAddressService";

	public T fetchByEmail(String email);
	
	public List<T> fetchAll(Boolean scrubbed, Boolean enabled);

	public List<Long> fetchAllIds(Boolean scrubbed, Boolean enabled);
	
	public List<T> fetchRandom(Long count, List<String> sourceList, List<String> excludeGroupList);
	
    public void scrub(String source, Long startId, Long endId, Date startDate, Date endDate);

	public boolean scrub(T address, boolean force);
    
	public boolean isValid(T address);

	public void importCSV(String csvFile, String errorFile, Map<String,Object> recordMap, 
			String source, boolean scrubbed, boolean skipFirstRecord) throws IOException;
}
