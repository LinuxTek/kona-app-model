/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.List;

import com.linuxtek.kona.app.core.entity.KMediaObject;
import com.linuxtek.kona.data.service.KDataService;

public interface KMediaService<T extends KMediaObject> extends KDataService<T> {
    
    T fetchByUid(String uid);

    T fetchByFileId(Long fileId);

    List<T> fetchByUserId(Long userId);

    List<T> fetchByAccountId(Long accountId);

    List<T> fetchByFolderPath(Long accountId, String folder);

    T fetchByName(Long accountId, String folder, String name);

    T createThumbnail(T mediaObject, Integer width, Integer height, boolean force) throws IOException;
}
