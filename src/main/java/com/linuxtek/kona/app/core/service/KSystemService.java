/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceRelativePath;

public interface KSystemService extends KService {
    public static final String SERVICE_PATH = "rpc/kona/SystemService";
    
    public void alert(String subject, String message);
    
    public void alert(String message, Throwable t);
    
    public void alert(String subject, String message, Throwable t);
}
