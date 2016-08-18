/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.linuxtek.kona.app.core.entity.KRedirect;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KRedirectService<REDIRECT extends KRedirect> 
        extends KService, KDataService<REDIRECT> {

    public static final String SERVICE_PATH = "rpc/kona/RedirectService";

    public List<REDIRECT> fetchByShortUrlId(Long shortUrlId);
    public List<REDIRECT> fetchByHostname(String hostname);
    public REDIRECT buildRedirect(HttpServletRequest req, String path) throws IOException;
}
