/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;

import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KUserMedia;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KUserMediaService<T extends KUserMedia, F extends KFile> extends KService, KMediaService<T> {
	public static final String SERVICE_PATH = "rpc/kona/UserMediaService";
	
	/*
    public T fetchByUid(String uid);

    public T fetchByFileId(Long fileId);


    public List<T> fetchByUserId(Long userId);
    */

    public T fetchPrimaryPhoto(Long userId);
    
    public T add(F file) throws IOException;

    public T add(F file,Double latitude, Double longitude, Integer floor,
            String description, boolean primaryPhoto) throws IOException;
}
