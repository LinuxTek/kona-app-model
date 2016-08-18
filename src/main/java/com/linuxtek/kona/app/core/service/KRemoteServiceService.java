/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.linuxtek.kona.app.core.entity.KRemoteService;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KRemoteServiceService<REMOTE_SERVICE extends KRemoteService> 
        extends KService, KDataService<REMOTE_SERVICE> {

    public static final String SERVICE_PATH = "rpc/kona/RemoteServiceService";

    public REMOTE_SERVICE fetchByName(String name);
}
