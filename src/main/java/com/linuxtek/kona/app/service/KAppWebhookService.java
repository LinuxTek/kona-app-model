/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KAppWebhook;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppWebhookService<T extends KAppWebhook> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/AppWebhookService";
    
    public T fetchByUid(String uid);
    
    public T fetchByAppIdAndName(Long appId, String name);
}
