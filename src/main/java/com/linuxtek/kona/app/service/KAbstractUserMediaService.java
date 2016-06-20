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
import com.linuxtek.kona.media.util.KImageUtil;

public abstract class KAbstractUserMediaService<T extends KUserMedia, EXAMPLE, U extends KUser, F extends KFile> 
		extends KAbstractService<T,EXAMPLE>
		implements KUserMediaService<T,F> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserMediaService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract T getNewObject();
	
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
    
    protected Integer getImageMaxWidth() {
    	return -1;
    }
    protected Integer getImageMaxHeight() {
    	return -1;
    }
    
	// ----------------------------------------------------------------------------
    
	private void resizeImage(F file) throws IOException {
        if (!isResizeable()) return;
		Integer maxWidth = getImageMaxWidth();
		Integer maxHeight = getImageMaxHeight();
        byte[] src = file.getData();
        KImageUtil.Image image = KImageUtil.resizeToMaxWidthAndHeight(src, maxWidth, maxHeight);
		file.setData(image.data);
        file.setWidth(image.width);
        file.setHeight(image.height);
        file.setSize(image.size);
	}
	// ----------------------------------------------------------------------------
    
    protected void updateImageInfo(F file) throws IOException {
        byte[] data = file.getData();
        if (data == null) {
            logger.info("file data is null");
            return;
        }

        KImageInfo info = new KImageInfo(data);
        file.setWidth(info.getWidth());
        file.setHeight(info.getHeight());
        file.setBitsPerPixel(info.getBitsPerPixel());

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
                + "\n\twidth: " + file.getWidth()
                + "\n\theight: " + file.getHeight()
                + "\n\tbitsPerPixel: " + file.getBitsPerPixel());
    }
    
	// ----------------------------------------------------------------------------

    
	@Transactional
	private void createThumbnail(F original) throws IOException {
		Boolean createThumbnail =
				config.getBoolean("fileService.createThumbail", false);

		if (!createThumbnail) return;

		Integer thumbnailWidth =
				config.getInteger("fileService.thumbnailWidth", 100);

		Integer thumbnailHeight =
				config.getInteger("fileService.thumbnailHeight", 100);

		F thumbnail = fetchThumbnail(original.getId());
		if (thumbnail == null) {
			thumbnail = getNewObject();
			thumbnail.setUid(uuid());
			thumbnail.setTypeId(original.getTypeId());
			thumbnail.setParentId(original.getId());
			thumbnail.setUserId(original.getUserId());
			thumbnail.setName(original.getName());
			thumbnail.setContentType(original.getContentType());

			thumbnail.setArchive(false);
			thumbnail.setCompressed(false);
			thumbnail.setHidden(original.isHidden());
			//thumbnail.setPrivate(original.isPrivate());
			thumbnail.setEnabled(original.isEnabled());
			thumbnail.setActive(original.isActive());
			thumbnail.setCreatedDate(new Date());

			String localPath = original.getLocalPath() + "-thumbnail";
			thumbnail.setLocalPath(localPath);
			thumbnail.setUrlPath(toPublicPath(localPath));
		}
        
		byte[] thumbnailBytes = KImageUtil.resize(original.getData(),
				thumbnailWidth, thumbnailHeight);
        
		thumbnail.setData(thumbnailBytes);
		thumbnail.setSize(Long.valueOf(thumbnailBytes.length));

		KImageUtil.updateImageInfo(thumbnail);

		// first write the bytes to disk 
		saveFile(thumbnail);

		// if we get here, we can safely write the meta data to the db
        if (thumbnail.getId() == null) {
        	getDao().insert(thumbnail);
        	original.setThumbId(thumbnail.getId());
        	original.setThumbUrlPath(thumbnail.getUrlPath());
        	getDao().updateByPrimaryKey(original);
        } else {
        	getDao().updateByPrimaryKey(thumbnail);
        }
	}
}
