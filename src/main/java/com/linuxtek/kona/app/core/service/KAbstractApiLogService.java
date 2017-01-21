/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KApiLog;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractApiLogService<A extends KApiLog,EXAMPLE> 
		extends KAbstractService<A,EXAMPLE>
		implements KApiLogService<A> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractApiLogService.class);
    

	// ----------------------------------------------------------------------------
    
    @Override
    public A fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public List<A> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
   // ----------------------------------------------------------------------------
    
    @Override
    public List<A> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public List<A> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    public List<A> fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    // ----------------------------------------------------------------------------
    
	@Override
	public void validate(A apiLog) {
	   	 if (apiLog.getCreatedDate() == null) {
    		 apiLog.setCreatedDate(new Date());
    	 }
	   	 
         apiLog.setUpdatedDate(new Date());
         
         if (apiLog.getUid() == null) {
        	 apiLog.setUid(uuid());
         }
	}
}

