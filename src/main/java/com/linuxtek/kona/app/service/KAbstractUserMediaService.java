/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KFile;
import com.linuxtek.kona.app.entity.KUserMedia;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractUserMediaService<T extends KUserMedia, EXAMPLE, F extends KFile> 
		extends KAbstractService<T,EXAMPLE>
		implements KUserMediaService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserMediaService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract <S extends KFileService<F>> S getFileService();

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T userMedia) {
		if (userMedia.isPrimaryPhoto()) {
			unsetPrimaryPhoto(userMedia);
		}

		if (userMedia.getCreatedDate() == null) {
			userMedia.setCreatedDate(new Date());
		}

		userMedia.setLastUpdated(new Date());

		if (userMedia.getUid() == null) {
			userMedia.setUid(uuid());
		}
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByFileId(Long fileId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("fileId", fileId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<T> fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchPrimaryPhoto(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("primaryPhoto", true);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	   
	// ----------------------------------------------------------------------------

	@Override 
	public void remove(T userMedia) {
		if (userMedia == null || userMedia.getId() == null) return;
		
		if (userMedia.getFileId() != null) {
			getFileService().removeById(userMedia.getFileId());
		}
		
		getDao().deleteByPrimaryKey(userMedia.getId());
	}

	// ----------------------------------------------------------------------------

	private void unsetPrimaryPhoto(T current) {
		T media = fetchPrimaryPhoto(current.getUserId());
		if (media != null) {
			if (current.getId() == null || !current.getId().equals(media.getId())) {
				media.setPrimaryPhoto(false);
				getDao().updateByPrimaryKey(media);
			}
		}
	}
}
