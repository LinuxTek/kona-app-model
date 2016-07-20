/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;

import com.linuxtek.kona.app.entity.KPosition;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KPositionService<T extends KPosition> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/PositionService";
	
    public List<T> fetchByUserIdBetweenDates(Long userId, Date startDate, Date endDate);
    
    public List<T> fetchByUserIdBetweenSampleNos(Long userId, Long startSampleNo, Long endSampleNo);
}
