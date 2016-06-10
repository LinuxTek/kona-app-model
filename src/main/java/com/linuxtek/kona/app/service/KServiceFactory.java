/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

/**
 * 
 */
public interface KServiceFactory {

    @SuppressWarnings("rawtypes")
	public KAuthService getAuthService();
    
    @SuppressWarnings("rawtypes")
    public KUserService getUserService();
    
    @SuppressWarnings("rawtypes")
    public KFileService getFileService();
}
