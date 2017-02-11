/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.core.entity.KSetting;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractSettingService<S extends KSetting,EXAMPLE> 
		extends KAbstractService<S,EXAMPLE>
		implements KSettingService<S> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSettingService.class);
    
	// ----------------------------------------------------------------------------
    
    protected abstract S getNewObject();
    
	// ----------------------------------------------------------------------------
    
	@Override
	public void validate(S setting) {
	   	 if (setting.getCreatedDate() == null) {
    		 setting.setCreatedDate(new Date());
    	 }
         setting.setUpdatedDate(new Date());
	}
	
	// ----------------------------------------------------------------------------

    @Override
    public S fetchByUserIdAndName(Long userId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    
    // ----------------------------------------------------------------------------

    @Override
    public S fetchByAccountIdAndName(Long accountId, String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	// ----------------------------------------------------------------------------
    
    @Override
	public S fetchGlobalByName(String name) {
		return fetchByUserIdAndName(null, name);
	}
    
	// ----------------------------------------------------------------------------
    
    @Override @Transactional
    public S save(S setting) {
        if (setting.getId() != null) {
            return update(setting);
        }

        // see if we have an existing record
        S s = fetchByUserIdAndName(setting.getUserId(), setting.getName());
        
        if (s == null) {
            // if this is a local value, check if the value is different from global
            if (setting.getUserId() != null) {
            	S s2 = fetchGlobalByName(setting.getName());
                
            	logger.debug("fetched global setting for name {}: {}", setting.getName(), s2);
                
            	if (s2 != null && s2.getValue().equals(setting.getValue())) {
            		
            		// if same as global and overwrite is set then add record
            		if (setting.isOverwriteGlobal()) {
            			logger.debug("global setting equal to new value: {}; overwriteGlobal not set; returning setting", setting.getValue());
            			return add(setting);
            		} else {
            			// otherwise keep global value and return current setting
            			return setting;
            		}
            	} else {
            		return add(setting);
            	}
            } else {
            	// this is a new Global setting so add it.
            	logger.debug("setting not found for userId {} and name {} .. adding new record", setting.getUserId(), setting.getName());
                
            	return add(setting);
            }
        }
        
        logger.debug("found existing setting userId {} and name {}: {}", setting.getUserId(), setting.getName(), s);
        
        // if no change in value, return existing saved value
        if (s.getValue().equals(setting.getValue())) {
        	return s;
        }
        

        logger.debug("updating: saved setting {} with new value", s, setting.getValue());
        s.setValue(setting.getValue());
        return update(s);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override @Transactional
    public void save(Long userId, Long accountId, Map<String,Object> config, boolean overwriteGlobal) {
    	for (String key : config.keySet()) {
    		S setting = null;

    		if (userId != null) {
    		    setting = fetchByUserIdAndName(userId, key);
    		} else {
    		    setting = fetchByAccountIdAndName(accountId, key);
    		}

    		if (setting == null) {
    			setting = getNewObject();
    			setting.setUserId(userId);
    			setting.setAccountId(accountId);
    			setting.setName(key);
    			setting.setValue(config.get(key).toString());
    			setting.setOverwriteGlobal(overwriteGlobal);
    			setting.setCreatedDate(new Date());
    		}

    		save(setting);
    	}
    }
    
	// ----------------------------------------------------------------------------

	@Override
	public List<S> fetchGlobal() {
		return fetchByUserId(null);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public List<S> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

    @Override
    public List<S> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------

	@Override
	public Map<String,Object> getGlobalSettings() {
		return getUserSettings(null);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public Map<String,Object> getUserSettings(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();

		// first get all global vars
		List<S> settingList = fetchGlobal();

		for (S setting : settingList) {
			map.put(setting.getName(), setting.getValue());
		}

		// next get environment specific params
		if (userId != null) {
			settingList = fetchByUserId(userId);
			for (S setting : settingList) {
				map.put(setting.getName(), setting.getValue());
			}
		}

		// MapConfiguration is not serializable
		//return new MapConfiguration(map);
		return map;
	}

    // ----------------------------------------------------------------------------

    @Override
    public Map<String,Object> getAccountSettings(Long accountId) {
        Map<String,Object> map = new HashMap<String,Object>();

        // first get all global vars
        List<S> settingList = fetchGlobal();

        for (S setting : settingList) {
            map.put(setting.getName(), setting.getValue());
        }

        // next get environment specific params
        if (accountId != null) {
            settingList = fetchByAccountId(accountId);
            for (S setting : settingList) {
                map.put(setting.getName(), setting.getValue());
            }
        }

        // MapConfiguration is not serializable
        //return new MapConfiguration(map);
        return map;
    }
}
