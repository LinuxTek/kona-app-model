package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAppLegal;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractAppLegalService<T extends KAppLegal,EXAMPLE> 
extends KAbstractService<T,EXAMPLE>
implements KAppLegalService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppLegalService.class);

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T appLegal) {
	    if (appLegal.getCreatedDate() == null) {
	        appLegal.setCreatedDate(new Date());
	    }

	    if (appLegal.getUid() == null) {
	        appLegal.setUid(uuid());
	    }

	    appLegal.setUpdatedDate(new Date());
	    
	    if (appLegal.isActive()) {
            unsetActive(appLegal);
        }
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppId(Long appId) {
	    Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
	    return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByUid(String uid) {
	    Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
	    return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByAppIdAndTypeAndVersion(Long appId, String type, Integer version) {
	    Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
	    filter.put("type", type);
	    filter.put("version", version);
	    return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByAppIdAndType(Long appId, String type) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("type", type);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
	

	// ----------------------------------------------------------------------------

	@Override
	public T fetchActive(Long appId, String type) {
	    T active = null;

	    List<T> list = fetchByAppIdAndType(appId, type);

	    for (T item : list) {
	        if (item.isActive()) {
	            active = item;
	            break;
	        }
	    }

	    return active;
	}

	// ----------------------------------------------------------------------------

	private void unsetActive(T current) {
	    T item = fetchActive(current.getAppId(), current.getType());

        if (item != null) {
            if (current.getId() == null || !current.getId().equals(item.getId())) {
                item.setActive(false);
                getDao().updateByPrimaryKey(item);
            }
        }
    }	
}
