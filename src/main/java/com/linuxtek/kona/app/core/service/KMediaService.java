/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;

import com.linuxtek.kona.app.core.entity.KMediaObject;

/**
 * The client side stub for the RPC service.
 */
public interface KMediaService<T extends KMediaObject> {
	
    T createThumbnail(T mediaObject, Integer width, Integer height, boolean force) throws IOException;
}
