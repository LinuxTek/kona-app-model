/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;

import com.linuxtek.kona.app.core.entity.KFile;

/**
 * Interface that is concerned with managing the data (byte[]) attribute
 * of a KFile object.
 */
public interface KFileManager<F extends KFile> {
    /**
     * Save the data to the local path defined by KFile.getLocalPath().
     */
    public void saveFile(F file) throws IOException;

    /**
     * Delete the file defined by KFile.getLocalPath().
     */
    public void deleteFile(F file) throws IOException;
    
    /**
     * Return a KFile object with the data (byte[]) array populated.
     */
    public F fetchFile(F file) throws IOException;

    /**
     * Return a KFile object with the data (byte[]) array populated.
     */
    public F fetchFileByUrlPath(String urlPath) throws IOException;
    
    public boolean isAuthorized(F file, String clientId, String accessToken);
}
