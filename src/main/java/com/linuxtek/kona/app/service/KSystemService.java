/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceRelativePath;

@KServiceRelativePath(KSystemService.SERVICE_PATH)
public interface KSystemService extends KService {
    public static final String SERVICE_PATH = "rpc/kona/SystemService";
    
    public void alert(String subject, String message);
}
