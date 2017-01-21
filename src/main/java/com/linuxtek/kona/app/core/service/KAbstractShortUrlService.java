/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KShortUrl;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.sequence.flake.KFlake;
import com.linuxtek.kona.util.KPassGen;
import com.linuxtek.kona.util.KStringUtil;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public abstract class KAbstractShortUrlService<T extends KShortUrl,EXAMPLE> 
extends KAbstractService<T,EXAMPLE>
implements KShortUrlService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractShortUrlService.class);

	// ----------------------------------------------------------------------------

	protected abstract T getNewObject();

    protected abstract String getDefaultVanityDomain();
    
    protected abstract boolean useHttps();

	// ----------------------------------------------------------------------------
    
	protected String generateUid() {
		return KUtil.uuid();
	}
    
	// ----------------------------------------------------------------------------

	@Override 
	public void validate(T shortUrl) {
		if (shortUrl.getCreatedDate() == null) {
			shortUrl.setCreatedDate(new Date());
		}

		shortUrl.setUpdatedDate(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public T fetchByShortUrl(String shortUrl) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrl", shortUrl);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	public T fetchByPath(String path) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("path", path);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	public List<T> fetchByLongUrl(String longUrl, Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("longUrl", longUrl);
        if (userId != null) {
        	filter.put("userId", userId);
        }
        return fetchByCriteria(0,9999, null, filter,  false);
	}
    
	// ----------------------------------------------------------------------------
    
    @Override 
	public String shorten(Long appId, Long userId, String longUrl) {
        return shorten(appId, userId, null, null, longUrl, null, null, false, false);
    }
    
	// ----------------------------------------------------------------------------

    @Override 
	public String shorten(Long appId, Long userId, Long partnerId, 
			Long promoId, String longUrl, String vanityDomain, 
			String description, boolean script, boolean singleMapping) {
        
    	if (vanityDomain == null) {
    		vanityDomain = getDefaultVanityDomain();
    	}
    	
    	T shortUrl = null;
    	
    	if (singleMapping) {
    		List<T> shortUrlList = fetchByLongUrl(longUrl, userId);
            
    		if (shortUrlList != null) { 
    			if (shortUrlList.size()>1) {
    				throw new IllegalStateException(
    						"Multiple ShortUrl mappings exist and singleMapping enforced for url: " + longUrl
    						);
    			}
                
    			if (shortUrlList.size() == 1) {
    				shortUrl = shortUrlList.get(0);
                    
    				if (shortUrl != null && !shortUrl.getDomain().equals(vanityDomain)) {
    					throw new IllegalStateException("ShortUrl exists for requested longUrl but for different domain: "
    							+ "\nlongUrl: " + longUrl
    							+ "\nvanityDomain: " + vanityDomain
    							+ "\nShortUrl: " + KStringUtil.toJson(shortUrl));
    				}
    			}
    		}
    	}
        
        if (shortUrl == null) {
        	Date now = new Date();
            
            // use the following apache directive to match this shorturl
            // ProxyPassMatch ^/(0[A-Z0-9]*)$ ajp://localhost:8009/redirect/$1
            
            //Long pathId = sequence.getNextNo("shortUrl.path");
            //String path = "0" + Long.toString(pathId, 36).toUpperCase();
            
            String path = generatePath();
            
            if (path == null) {
            	throw new RuntimeException("Error generating random path for short url.");
            }
            
            String surl = "http://";
            if (useHttps()) {
            	surl= "https://";
            }
            
            surl += vanityDomain + "/" + path;
            
            shortUrl = getNewObject();
            shortUrl.setAppId(appId);
            shortUrl.setUserId(userId);
            shortUrl.setPartnerId(partnerId);
            shortUrl.setPromoId(promoId);
        	shortUrl.setDomain(vanityDomain);
            shortUrl.setPath(path);
            shortUrl.setShortUrl(surl);
        	shortUrl.setLongUrl(longUrl);
        	shortUrl.setDescription(description);
            shortUrl.setScript(script);
        	shortUrl.setEnabled(true);
        	shortUrl.setCreatedDate(now);
        	shortUrl.setUpdatedDate(now);
            
            shortUrl = add(shortUrl);
        }
        
        return shortUrl.getShortUrl();
	}
    
	// ----------------------------------------------------------------------------
    
    private String generatePath() {
    	KPassGen pass = new KPassGen(KPassGen.LOWERCASE_LETTERS_AND_NUMBERS_ALPHABET);
        int i=0;
        String path = null;
        while (i<10) {
        	path = "0" + pass.getPass(5);
        	T s = fetchByPath(path);
            if (s == null)  break;
            path = null;
            i += 1;
        }
        
        if (path == null) {
            //Long pathId = sequence.getNextNo("shortUrl.path");
            Long pathId = KFlake.getIdAsLong();
            path = "0" + Long.toString(pathId, 36).toUpperCase();
        	T s = fetchByPath(path);
            if (s != null)  path = null;
        }
        
        return path;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override @SuppressWarnings("unused")
    public String explode(HttpServletRequest req, String url) {
    	String longUrl = null;
    	T shortUrl = null;
    	try {
    		URL u = new URL(url);
    		shortUrl = fetchByShortUrl(url);
    	} catch (MalformedURLException e) {
    		shortUrl = fetchByPath(url);
    	}

    	if (shortUrl != null) {
    		if (shortUrl.isScript()) {
    			longUrl = evalScript(req, shortUrl.getLongUrl());

    		} else {
    			longUrl = shortUrl.getLongUrl();
    		}
    	}
    	return longUrl;
    }
    
	// ----------------------------------------------------------------------------
    
    @Override 
    public String explode(HttpServletRequest req, T shortUrl) {
    	String longUrl = null;
    	if (shortUrl != null) {
    		if (shortUrl.isScript()) {
    			longUrl = evalScript(req, shortUrl.getLongUrl());

    		} else {
    			longUrl = shortUrl.getLongUrl();
    		}
    	}
    	return longUrl;
    }
    
	// ----------------------------------------------------------------------------
    
	private String evalScript(HttpServletRequest req, String script) {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		binding.setVariable("_req", req);
        String result = null;
		Object value = shell.evaluate(script);
        if (value != null) {
        	result = value.toString();
        }
        return result;
	}
}
