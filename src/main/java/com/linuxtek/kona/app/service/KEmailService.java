/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KEmail;
import com.linuxtek.kona.app.entity.KEmailEvent;
import com.linuxtek.kona.app.entity.KEmailFooter;
import com.linuxtek.kona.app.entity.KEmailStats;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KEmailService<T extends KEmail,
                               TEVENT extends KEmailEvent> extends KService, KDataService<T> {

	public static final String SERVICE_PATH = "rpc/kona/EmailService";

	public void send(String body, String subject, String from, String replyTo, String to, String cc, String bcc,
			boolean html) throws KEmailException;

	public String sendAWS(String from, String to, String subject, String textBody, String htmlBody) 
			throws KEmailException;

	// replyTo: when recipient replies, each reply-to address will receive the reply
	// returnPath: the address to which bounces and complaints are forwarded to
	public String sendAWS(String from, String[] replyTo, String returnPath, String[] to, String[] cc, String[] bcc, 
			String subject, String textBody, String htmlBody) throws KEmailException;

	public void processLocalQueue();
	
	public T fetchByUid(String uid);
	
	public T fetchBySesId(String sesId);
	
	public T fetchByCampaignIdAndChannelIdAndToId(Long campaignId, Long campaignChannelId, Long toAddressId);
	
	public List<T> fetchByGroupId(Long groupId);
	
	public List<T> fetchByCampaignId(Long campaignId);
	
	public List<T> fetchByGroupName(String groupName);
	
	public List<T> fetchByCampaignIdAndChannelId(Long campaignId, Long campaignChannelId);
    
	public KEmailStats calcStats(List<T> emailList);
	
	public KEmailStats calcStatsByGroupName(String groupName);
	
	public KEmailStats calcStatsByCampaignId(Long campaignId);
	
	public KEmailStats calcStatsByCampaignIdAndChannelId(Long campaignId, Long channelId);
    
    public TEVENT addEvent(TEVENT event);
    
	public void deliver(Long campaignId, Long campaignChannelId, Long groupId, 
			String fromAddress, String subject, String text, 
			String html, KEmailFooter footer, Long throttleTime);
    
	public void deliver(String fromAddress, String toAddress, 
			String subject, String text, String html, KEmailFooter footer);
    
	public void processSESNotifications();
}
