/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KUserMedia;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KUserMediaService<T extends KUserMedia> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/UserMediaService";
	
    public T fetchByUid(String uid);

    public T fetchByFileId(Long fileId);

    public T fetchPrimaryPhoto(Long userId);

    public List<T> fetchByUserId(Long userId);
}