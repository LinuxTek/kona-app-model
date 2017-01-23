/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KFileService<F extends KFile> extends KService, KDataService<F>, KFileManager<F> {
    public static final String SERVICE_PATH = "rpc/kona/FileService";

    public F fetchByUid(String uid, boolean withData) throws IOException;

    public F fetchById(Long id, boolean withData) throws IOException;
    
    public List<F> fetchByParentId(Long parentId, boolean withData) throws IOException;
   
    public List<F> fetchByUserId(Long userId, boolean withData) throws IOException;
    
    public List<F> fetchTempFiles(boolean withData) throws IOException;
    
    public List<F> fetchByCriteria(Integer startRow,  Integer resultSize, 
    		String[] sortOrder, Map<String, Object> filterCriteria, 
            boolean distinct, boolean withData) throws IOException;

    public String toAbsoluteUrl(String publicPath);
    
    /*
     * Get the path of this file resource on the local server.
     * 
     * Use with care.  Provided for rare cases when knowledge of file path on the local server is required.
     * 
     * @param publicPath
     * @return
    public String toServerLocalPath(String publicPath);
     */
    
}
