/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.linuxtek.kona.app.entity.KShortUrl;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KShortUrlService<T extends KShortUrl> extends KService, KDataService<T> {
    public static final String SERVICE_PATH = "rpc/kona/ShortUrlService";
    
    public T fetchByPath(String path);

    public T fetchByShortUrl(String shortUrl);

    public List<T> fetchByLongUrl(String longUrl, Long userId);

    public String shorten(Long appId, Long userId, String url);

    public String shorten(Long appId, Long userId, Long partnerId, Long promoId,
            String url, String domain, String description,
            boolean script, boolean singleMapping);

    public String explode(HttpServletRequest req, String url);

    public String explode(HttpServletRequest req, T shortUrl);
}
