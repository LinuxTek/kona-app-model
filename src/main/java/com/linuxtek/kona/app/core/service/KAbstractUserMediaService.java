/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.entity.KUserMedia;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractUserMediaService<T extends KUserMedia, EXAMPLE, U extends KUser, F extends KFile> 
		extends KAbstractMediaObjectService<T,EXAMPLE,F>
		implements KUserMediaService<T,F> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserMediaService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract <S extends KUserService<U>> S getUserService();

	// ----------------------------------------------------------------------------

	@Override
	public void validate(T userMedia) {
        super.validate(userMedia);
        
		if (userMedia.isPrimaryPhoto()) {
			unsetPrimaryPhoto(userMedia);
		}
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchPrimaryPhoto(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("primaryPhoto", true);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	   
	// ----------------------------------------------------------------------------

    public T add(T userMedia) {
        userMedia = super.add(userMedia);

        if (userMedia.isPrimaryPhoto()) {
            getUserService().updatePrimaryPhoto(userMedia.getUserId(), userMedia.getId(), 
                    userMedia.getUrlPath(), userMedia.getThumbnailUrlPath());
        }

        return userMedia;
    }

	// ----------------------------------------------------------------------------

    public T update(T userMedia) {
        userMedia = super.update(userMedia);

        if (userMedia.isPrimaryPhoto()) {
            getUserService().updatePrimaryPhoto(userMedia.getUserId(), userMedia.getId(), 
                    userMedia.getUrlPath(), userMedia.getThumbnailUrlPath());
        }

        return userMedia;
    }

	// ----------------------------------------------------------------------------

    public void remove(T userMedia) {
        super.remove(userMedia);

        if (userMedia.isPrimaryPhoto()) {
            getUserService().updatePrimaryPhoto(userMedia.getUserId(), null, null, null);
        }
    }

	// ----------------------------------------------------------------------------
	
	
	@Override 
    public T add(F file,Double latitude, Double longitude, Integer floor,
            String description, boolean primaryPhoto) throws IOException {
        
        T userMedia = add(file, latitude, longitude, floor, description);

        userMedia.setPrimaryPhoto(primaryPhoto);

        userMedia = update(userMedia);
        
        return userMedia;
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
    
	// ----------------------------------------------------------------------------
}
