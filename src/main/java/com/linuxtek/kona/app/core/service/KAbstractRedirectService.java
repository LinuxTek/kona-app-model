/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KRedirect;
import com.linuxtek.kona.app.core.entity.KShortUrl;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.http.KServletUtil;

public abstract class KAbstractRedirectService<REDIRECT extends KRedirect, 
											   REDIRECT_EXAMPLE,
											   SHORTURL extends KShortUrl> 
		extends KAbstractService<REDIRECT,REDIRECT_EXAMPLE>
		implements KRedirectService<REDIRECT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRedirectService.class);

	// ----------------------------------------------------------------------------

	protected abstract REDIRECT getNewObject();
	
	protected abstract <S extends KShortUrlService<SHORTURL>> S getShortUrlService();

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(REDIRECT redirect) {
		if (redirect.getCreatedDate() == null) {
			redirect.setCreatedDate(new Date());
		}

		redirect.setUpdatedDate(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<REDIRECT> fetchByShortUrlId(Long shortUrlId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrlId", shortUrlId);
		return fetchByCriteria(0,9999, null, filter,  false);
	}

	// ----------------------------------------------------------------------------
	
	@Override
	public List<REDIRECT> fetchByHostname(String hostname) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("hostname", hostname);
		return fetchByCriteria(0,9999, null, filter,  false);
	}
	
	// ----------------------------------------------------------------------------
	
	  @Override
	    public REDIRECT buildRedirect(HttpServletRequest req, String path) throws IOException {
	        logger.debug("RedirectService: redirect() called for: " + path);

	        Date now = new Date();

	        SHORTURL surl = getShortUrlService().fetchByPath(path);
	        
	        if (surl == null) {
	            throw new IOException("ShortUrl path not found: " + path);
	        }
	        
	        String redirectUrl = getShortUrlService().explode(req, surl);

	        REDIRECT redirect = getNewObject();
	        redirect.setShortUrlId(surl.getId());
	        redirect.setPromoId(surl.getPromoId());
	        redirect.setRequestUrl(KServletUtil.getFullRequestURL(req));
	        redirect.setRedirectUrl(redirectUrl);
	        redirect.setHostname(KServletUtil.getClientHostname(req));
	        redirect.setBrowser(KServletUtil.getClientBrowser(req));
	        redirect.setReferer(KServletUtil.getClientReferer(req));
	        redirect.setLocale(KServletUtil.getClientLocale(req));
	        redirect.setRequestedDate(now);
	        redirect.setRedirectedDate(now);

	        redirect = add(redirect);
	        return redirect;
	    }
}
