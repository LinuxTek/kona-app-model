/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KPosition;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractPositionService<T extends KPosition, EXAMPLE, F extends KFile> 
		extends KAbstractService<T,EXAMPLE>
		implements KPositionService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPositionService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract <S extends KFileService<F>> S getFileService();
	
	// ----------------------------------------------------------------------------
	
	protected String[] getDefaultSortOrder() {
		String[] sortOrder = { "sampleNo" };
		return sortOrder;
	}

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T position) {
		if (position.getCreatedDate() == null) {
			position.setCreatedDate(new Date());
		}

		position.setUpdatedDate(new Date());
		
		if (position.getSampleNo() == null) {
			position.setSampleNo(position.getTimestamp());
		}
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByUserIdBetweenDates(Long userId, Date startDate, Date endDate) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
		if (startDate != null) {
			filter.put(">timestamp", startDate.getTime());
		}
		
		if (endDate != null) {
			filter.put("<timestamp", endDate.getTime());
		}
		
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByUserIdBetweenSampleNos(Long userId, Long startSampleNo, Long endSampleNo) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
		if (startSampleNo != null) {
			filter.put(">sampleNo", startSampleNo); 
		}
		
		if (endSampleNo != null) {
			filter.put("<sampleNo", endSampleNo);
		}
		
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}
