/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.core.entity.KApp;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.util.KCallback;
import com.linuxtek.kona.app.comm.model.KEmailMedia;
import com.linuxtek.kona.app.comm.service.KEmailException;
import com.linuxtek.kona.app.comm.service.KSmsException;
import com.linuxtek.kona.remote.service.KService;

public interface KSystemService<APP extends KApp, USER extends KUser> extends KService {
    public static final String SERVICE_PATH = "rpc/kona/SystemService";
    
	public void sendEmail(String body, String subject, String from, String replyTo,
			String to, String cc, String bcc, boolean html, List<KEmailMedia> mediaList) throws KEmailException;
		
	public void sendEmail(String templateName, Map<String,Object> params, String subject, String from,
			String replyTo, String to, String cc, String bcc, List<KEmailMedia> mediaList) throws KEmailException;

	public void sendEmail(String templateName, Map<String,Object> params, String subject, String to) throws KEmailException;
		
	
    public void sendSms(String to, String body,  KCallback callback) throws KSmsException;

	public void alert(String subject, Throwable t);

	public void alert(String subject, String body);

	public void alert(String subject, String body, Throwable t);

	public void alert(String subject, String body, Boolean html);

	public APP getSystemApp();

	public USER getSystemUser();


	
}
