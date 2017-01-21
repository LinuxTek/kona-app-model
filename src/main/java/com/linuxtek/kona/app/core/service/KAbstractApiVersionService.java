/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KApiVersion;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractApiVersionService<A extends KApiVersion,EXAMPLE> 
		extends KAbstractService<A,EXAMPLE>
		implements KApiVersionService<A> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractApiVersionService.class);
    
	// ----------------------------------------------------------------------------
    
    @Override
    public A fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    // ----------------------------------------------------------------------------
    
    @Override
    public A fetchLatest() {
        Map<String,Object> filter = null;

        // FIXME: do not hard code table or column anmes
        String[] sortOrder = { "published_date" };

        List<A> result = fetchByCriteria(0, 99999, sortOrder, filter, false);

        return result.get(result.size() - 1);
    }
    
    // ----------------------------------------------------------------------------
    
	@Override
	public void validate(A apiVersion) {
	   	 if (apiVersion.getCreatedDate() == null) {
    		 apiVersion.setCreatedDate(new Date());
    	 }
         apiVersion.setUpdatedDate(new Date());
	}
}

