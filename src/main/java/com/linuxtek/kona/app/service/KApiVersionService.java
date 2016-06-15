/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.app.entity.KApiVersion;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KApiVersionService<A extends KApiVersion> extends KService, KDataService<A> {
    public static final String SERVICE_PATH = "rpc/kona/ApiVersionService";
    
    public A fetchByName(String name);

    public A fetchLatest();
    
}
