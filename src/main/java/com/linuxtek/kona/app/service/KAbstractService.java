/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.dao.KMyBatisDao;
import com.linuxtek.kona.data.entity.KEntityObject;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.util.KResultList;

public abstract class KAbstractService<T extends KEntityObject,E> implements KDataService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractService.class);
    
    protected abstract <D extends KMyBatisDao<T,E>> D getDao();
    protected abstract E getExampleObjectInstance(Integer startRow, Integer resultSize,
			String[] sortOrder, Map<String, Object> filter, boolean distinct);
    
	// ----------------------------------------------------------------------------
    
	@Override @Transactional
	public T add(T t) {
		validate(t);
		getDao().insert(t);
		return t;
	}
	
	// ----------------------------------------------------------------------------

	@Override @Transactional
	public T update(T t) {
		validate(t);
		getDao().updateByPrimaryKey(t);
		return t;
	}
	
	// ----------------------------------------------------------------------------

	@Override @Transactional
	public void remove(T t) {
		getDao().deleteByPrimaryKey(t.getId());
	}
	
	// ----------------------------------------------------------------------------

	@Override @Transactional
	public void removeById(Long id) {
		T t = fetchById(id);
		remove(t);
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public void validate(T t) {
	}
    
	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByCriteria(Integer startRow, Integer resultSize,
			String[] sortOrder, Map<String, Object> filter, boolean distinct) {
		logger.debug("AccountServiceImpl fetch(): called");

		E example = getExampleObjectInstance(startRow, resultSize, sortOrder, filter, distinct);

		List<T> list = getDao().selectByExample(example);

		KResultList<T> resultList = new KResultList<T>();
		resultList.setStartIndex(startRow);
		resultList.setTotalSize(list.size());
		resultList.setEndIndex(startRow + list.size());

		logger.debug("fetch(): record count: " + list.size());

		for (T account : list) {
			resultList.add(account);
		}

		return resultList;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	public T fetchById(Long id) {
		return getDao().selectByPrimaryKey(id);
	}
	
	// ----------------------------------------------------------------------------
	
	protected String uuid() {
		return KUtil.uuid();
	}

}
