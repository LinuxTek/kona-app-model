/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KFileType;
import com.linuxtek.kona.app.core.entity.KMediaObject;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.media.model.KImage;
import com.linuxtek.kona.media.util.KImageInfo;
import com.linuxtek.kona.media.util.KImageUtil;

public abstract class KAbstractMediaObjectService<T extends KMediaObject, EXAMPLE, F extends KFile> 
extends KAbstractService<T,EXAMPLE> 
implements KMediaService<T> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractMediaObjectService.class);

    // ----------------------------------------------------------------------------

    protected abstract T getNewMediaObject();

    protected abstract F getNewFileObject();

    protected abstract <S extends KFileService<F>> S getFileService();
    
    protected abstract Integer getThumbnailWidth();
    protected abstract Integer getThumbnailHeight();
    protected abstract boolean autoGenerateThumbnail();

    // ----------------------------------------------------------------------------

    @Override
    public void validate(T media) {
        if (media.getCreatedDate() == null) {
            media.setCreatedDate(new Date());
        }

        media.setUpdatedDate(new Date());

        if (media.getUid() == null) {
            media.setUid(uuid());
        }
    }

    // ----------------------------------------------------------------------------
    @Override
    public T add(T mediaObject) {
        mediaObject = super.add(mediaObject);

        KFileType type = KFileType.getInstance(mediaObject.getFileTypeId());

        if (autoGenerateThumbnail() && getThumbnailWidth() != null && getThumbnailHeight() != null && type == KFileType.IMAGE) {
            try  {
                mediaObject = createThumbnail(mediaObject, getThumbnailWidth(), getThumbnailHeight(), true);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return mediaObject;
    }

    // ----------------------------------------------------------------------------


    public T add(F file,Double latitude, Double longitude, 
            Integer floor, String description) throws IOException {
        file = getFileService().add(file);

        T media = getNewMediaObject();

        media.setUserId(file.getUserId());
        media.setAccountId(file.getAccountId());
        media.setFileId(file.getId());
        media.setFileTypeId(file.getTypeId());
        media.setUrlPath(file.getUrlPath());
        media.setName(file.getName());
        media.setEnabled(true);
        media.setLatitude(latitude);
        media.setLongitude(longitude);
        media.setFloor(floor);
        media.setDescription(description);
        media.setCreatedDate(new Date());

        return add(media);
    }

    // ----------------------------------------------------------------------------

    @Override 
    public void remove(T media) {
        if (media == null || media.getId() == null) return;

        if (media.getFileId() != null) {
            getFileService().removeById(media.getFileId());
        }

        getDao().deleteByPrimaryKey(media.getId());
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
    public List<T> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    // ----------------------------------------------------------------------------

    // TODO: Return all subfolder meta-data as well
    @Override
    public List<T> fetchByFolderPath(Long accountId, String folderPath) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", folderPath);
        filter.put("folderPath", folderPath);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    // ----------------------------------------------------------------------------

    protected void resizeImage(T media, Integer maxWidth, Integer maxHeight) throws IOException {
        if (!media.isResizeable()) {
            return;
        }

        F file = getFileService().fetchById(media.getFileId(), true);

        byte[] src = file.getData();

        KImage image = KImageUtil.resizeToMaxWidthAndHeight(src, maxWidth, maxHeight);

        file.setData(image.getData());
        file.setSize(image.getSize());

        getFileService().update(file);

        media.setWidth(image.getWidth());
        media.setHeight(image.getHeight());
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

    @Override
    public T createThumbnail(T media, Integer width, Integer height, boolean force) throws IOException {

        // check if we have a thumbnail for this media file 
        if (media.getThumbnailId() != null) {
            if (!force) {
                return media;
            }

            // remove existing thumbnail file
            getFileService().removeById(media.getThumbnailId());


        }

        F file = getFileService().fetchById(media.getFileId(), true);

        if (file == null) {
            throw new IllegalArgumentException("Media object file is null: " + media);
        }

        F thumbnailFile = getNewFileObject();
        thumbnailFile.setUid(uuid());
        thumbnailFile.setTypeId(file.getTypeId());
        thumbnailFile.setAccessId(file.getAccessId());
        thumbnailFile.setParentId(file.getId());
        thumbnailFile.setTokenId(file.getTokenId());
        thumbnailFile.setUserId(file.getUserId());
        thumbnailFile.setAccountId(file.getAccountId());
        thumbnailFile.setName(file.getName() + "-thumbnail");
        thumbnailFile.setContentType(file.getContentType());
        thumbnailFile.setHidden(file.isHidden());
        thumbnailFile.setEnabled(file.isEnabled());
        thumbnailFile.setActive(file.isActive());
        thumbnailFile.setCreatedDate(new Date());

        String localPath = file.getLocalPath() + "-thumbnail";
        thumbnailFile.setLocalPath(localPath);

        KImage thumbnailResult = KImageUtil.resize(file.getData(), width, height);

        byte[] thumbnailBytes = thumbnailResult.getData();
        Integer thumbnailWidth = thumbnailResult.getWidth();
        Integer thumbnailHeight = thumbnailResult.getHeight();

        thumbnailFile.setData(thumbnailBytes);

        thumbnailFile.setSize(Long.valueOf(thumbnailBytes.length));

        thumbnailFile = getFileService().add(thumbnailFile);

        media.setThumbnailId(thumbnailFile.getId());
        media.setThumbnailUrlPath(thumbnailFile.getUrlPath());
        media.setThumbnailSize(thumbnailFile.getSize());
        media.setThumbnailWidth(thumbnailWidth);
        media.setThumbnailHeight(thumbnailHeight);

        media = update(media);


        return media;
    }

    // ----------------------------------------------------------------------------

}
