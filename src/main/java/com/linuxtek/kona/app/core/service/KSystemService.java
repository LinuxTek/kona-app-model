/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;

import com.linuxtek.kona.app.core.entity.KApp;
import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.util.KCallback;
import com.linuxtek.kona.app.comm.model.KEmailMedia;
import com.linuxtek.kona.app.comm.service.KEmailException;
import com.linuxtek.kona.app.comm.service.KSmsException;
import com.linuxtek.kona.remote.service.KService;

public interface KSystemService<APP extends KApp, USER extends KUser, FILE extends KFile> extends KService {

	public Configuration getConfig(Long appId);

    public FILE toFile(USER user, byte[] data, String contentType, String filename);

    public FILE toFile(USER user, byte[] data, String contentType, String filename, String srcFilename, String srcHostname, boolean tempFile);

    public FILE urlToFile(String url) throws IOException;

    public FILE urlToFile(USER user, String url) throws IOException;

    public boolean isTestPhoneNumber(String phoneNumber);

    public boolean isTestLoginCode(String code);

    public String formatPhoneNumber(String phoneNumber);

    
	public void sendEmail(String body, String subject, String from, String replyTo,
			String to, String cc, String bcc, boolean html, List<KEmailMedia> mediaList) throws KEmailException;
		
	public void sendEmail(String templateName, Map<String,Object> params, String subject, String from,
			String replyTo, String to, String cc, String bcc, List<KEmailMedia> mediaList) throws KEmailException;

	public void sendEmail(String templateName, Map<String,Object> params, String subject, String to) throws KEmailException;
		
	
    public void sendSms(String to, String body,  KCallback callback) throws KSmsException;

    public void sendSms(String to, String body, String mediaUrl,  KCallback callback);

    public void sendSms(String to, String body, List<String> mediaUrls,  KCallback callback);

    public void sendSms(String to, String body, List<String> mediaUrls, Long delay,  KCallback callback);


	public void alert(String subject, Throwable t);

	public void alert(String subject, String body);

	public void alert(String subject, String body, Throwable t);

	public void alert(String subject, String body, Boolean html);

	public APP getSystemApp();

	public USER getSystemUser();


	
}
