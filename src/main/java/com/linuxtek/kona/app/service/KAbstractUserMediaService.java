/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.entity.KFile;
import com.linuxtek.kona.app.entity.KUser;
import com.linuxtek.kona.app.entity.KUserMedia;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.media.util.KImageInfo;
import com.linuxtek.kona.media.util.KImageUtil;

public abstract class KAbstractUserMediaService<T extends KUserMedia, EXAMPLE, U extends KUser, F extends KFile> 
		extends KAbstractService<T,EXAMPLE>
		implements KUserMediaService<T,F> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserMediaService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract T getNewObject();
	
	protected abstract F getNewFileObject();
	
	protected abstract <S extends KUserService<U>> S getUserService();
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
	
	@Override 
    public T add(F file,Double latitude, Double longitude, Integer floor,
            String description, boolean primaryPhoto) throws IOException {
        file = getFileService().add(file);
        T userMedia = getNewObject();
        userMedia.setUserId(file.getUserId());
        userMedia.setFileId(file.getId());
        userMedia.setFileTypeId(file.getTypeId());
        userMedia.setUrlPath(file.getUrlPath());
        userMedia.setEnabled(true);
        userMedia.setPrimaryPhoto(primaryPhoto);
        userMedia.setLatitude(latitude);
        userMedia.setLongitude(longitude);
        userMedia.setFloor(floor);
        userMedia.setDescription(description);
        userMedia.setCreatedDate(new Date());

        userMedia = add(userMedia);
        
        if (primaryPhoto) {
        	getUserService().updatePrimaryPhotoUrl(file.getUserId(), file.getUrlPath());
        }
        
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
    
	protected void resizeImage(T media, Integer maxWidth, Integer maxHeight) throws IOException {
        if (!media.isResizeable()) {
        	return;
        }
        
		F file = getFileService().fetchById(media.getFileId(), true);
		
        byte[] src = file.getData();
        
        KImageUtil.Image image = KImageUtil.resizeToMaxWidthAndHeight(src, maxWidth, maxHeight);
        
		file.setData(image.data);
        file.setSize(image.size);
        
        getFileService().update(file);
        
        media.setWidth(image.width);
        media.setHeight(image.height);
        update(media);
	}
	
	// ----------------------------------------------------------------------------
    
    protected void updateImageInfo(T media) throws IOException {
		F file = getFileService().fetchById(media.getFileId(), true);
		
        byte[] data = file.getData();
        
        if (data == null) {
            logger.info("file data is null");
            return;
        }

        KImageInfo info = new KImageInfo(data);
        
        media.setWidth(info.getWidth());
        media.setHeight(info.getHeight());
        media.setBitsPerPixel(info.getBitsPerPixel());

        long dataSize = Long.valueOf(data.length);
        
        if (file.getSize() == null || !file.getSize().equals(dataSize)) {
            logger.warn("setting file size to: "
                    + dataSize + "  old value: " + file.getSize());
            file.setSize(dataSize);
        }

        String contentType = info.getMimeType();
        if (file.getContentType() == null) {
            file.setContentType(contentType);
        }

        //sanity check
        if (!contentType.equalsIgnoreCase(file.getContentType())) {
            logger.info("Content-type mismatch:"
                + "\n\tfile id: " + file.getId()
                + "\n\tfile name: " + file.getName()
                + "\n\tfile content-type: " + file.getContentType()
                + "\n\tKImageInfo content-type: " + contentType);
        }

        logger.debug("Image info: " + file.getName()
                + "\n\twidth: " + media.getWidth()
                + "\n\theight: " + media.getHeight()
                + "\n\tbitsPerPixel: " + media.getBitsPerPixel());
        
        
        getFileService().update(file);
        update(media);
    }
    
    
    
	// ----------------------------------------------------------------------------

	protected T createThumbnail(T original, Integer width, Integer height) throws IOException {
		
		F file = getFileService().fetchById(original.getFileId(), true);
		
		T thumbnail = null;
		
		F thumbnailFile = null;
		
		if (original.getThumbnailId() != null) {
			thumbnail = fetchById(original.getThumbnailId());
			thumbnailFile = getFileService().fetchById(thumbnail.getFileId());
		}
	
		if (thumbnailFile == null) {
			thumbnailFile = getNewFileObject();
			thumbnailFile.setUid(uuid());
			thumbnailFile.setTypeId(file.getTypeId());
			thumbnailFile.setParentId(file.getId());
			thumbnailFile.setUserId(file.getUserId());
			thumbnailFile.setName(file.getName());
			thumbnailFile.setContentType(file.getContentType());
			thumbnailFile.setHidden(file.isHidden());
			thumbnailFile.setEnabled(file.isEnabled());
			thumbnailFile.setActive(file.isActive());
			thumbnailFile.setCreatedDate(new Date());

			String localPath = file.getLocalPath() + "-thumbnail";
			thumbnailFile.setLocalPath(localPath);
		}
        
		byte[] thumbnailBytes = KImageUtil.resize(file.getData(), width, height);
        
		thumbnailFile.setData(thumbnailBytes);
		thumbnailFile.setSize(Long.valueOf(thumbnailBytes.length));

        if (thumbnailFile.getId() == null) {
        	thumbnailFile = getFileService().add(thumbnailFile);
        } else {
        	thumbnailFile = getFileService().update(thumbnailFile);
        }
        
        if (thumbnail == null) {
        	   thumbnail = getNewObject();
               thumbnail.setUserId(thumbnailFile.getUserId());
               thumbnail.setFileId(thumbnailFile.getId());
               thumbnail.setFileTypeId(thumbnailFile.getTypeId());
               thumbnail.setUrlPath(thumbnailFile.getUrlPath());
               thumbnail.setEnabled(true);
               thumbnail.setPrimaryPhoto(false);
               thumbnail.setCreatedDate(new Date());
               
               thumbnail = add(thumbnail);
        } else {
        	thumbnail.setFileId(thumbnailFile.getId());
        	thumbnail = update(thumbnail);
        }
        
        return thumbnail;
	}
}
