/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.entity.KFile;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KFileService<F extends KFile> extends KService, KDataService<F> {
    public static final String SERVICE_PATH = "rpc/kona/FileService";
    
    public F fetchById(Long id, boolean withData) throws IOException;
    
    public List<F> fetchByCriteria(Integer startRow,  Integer resultSize, 
    		String[] sortOrder, Map<String, Object> filterCriteria, 
            boolean distinct, boolean withData) throws IOException;
    
}
