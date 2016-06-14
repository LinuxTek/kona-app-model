/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KApp;
import com.linuxtek.kona.app.entity.KAppCreds;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractAppService<A extends KApp,AEXAMPLE,AC extends KAppCreds> 
		extends KAbstractService<A,AEXAMPLE>
		implements KAppService<A> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract A getNewAppObject();
	
	protected abstract <S extends KAppCredsService<AC>> S getAppCredsService();
	
	// ----------------------------------------------------------------------------
	
	@Override
	public A fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public A fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------
	
	@Override
	public List<A> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

    @Override 
    public A retire(A app) {
        // fetch fresh object
        app = fetchById(app.getId());

        // this isn't a slip up in logic. it's possible that the app object passed in
        // is not retired but by the time this call is made and the object is refreshed
        // is has been retired.  if so, fetchById should return null.
        // return app if already retired
        if (app == null || app.getRetiredDate() != null) {
            return app;
        }

        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        app.setName(prefix + app.getName());;
        app.setDisplayName(prefix + app.getDisplayName());;
        app.setEnabled(false);
        app.setRetiredDate(new Date());
        app = update(app);

        getAppCredsService().expireAppTokens(app.getId());
        return app;
    }
}
