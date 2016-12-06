package com.linuxtek.kona.app.comm.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.comm.entity.KEmailAddress;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KEmailAddressService<EMAIL_ADDRESS extends KEmailAddress> 
		extends KService, KDataService<EMAIL_ADDRESS> {
			
    public static final String SERVICE_PATH = "rpc/kona/EmailAddressService";

	public EMAIL_ADDRESS fetchByEmail(String email);
	
	public List<EMAIL_ADDRESS> fetchAll(Boolean scrubbed, Boolean enabled);

	public List<Long> fetchAllIds(Boolean scrubbed, Boolean enabled);
	
	public List<EMAIL_ADDRESS> fetchRandom(Long count, List<String> sourceList, List<String> excludeGroupList);
	
    public void scrub(String source, Long startId, Long endId, Date startDate, Date endDate);

	public boolean scrub(EMAIL_ADDRESS address, boolean force);
    
	public boolean isValid(EMAIL_ADDRESS address);

	public void importCSV(String csvFile, String errorFile, Map<String,Object> recordMap, 
			String source, boolean scrubbed, boolean skipFirstRecord) throws IOException;
}
