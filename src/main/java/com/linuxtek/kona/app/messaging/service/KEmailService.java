package com.linuxtek.kona.app.messaging.service;

import java.util.List;

import com.linuxtek.kona.app.messaging.entity.KEmail;
import com.linuxtek.kona.app.messaging.entity.KEmailEvent;
import com.linuxtek.kona.app.messaging.entity.KEmailFooter;
import com.linuxtek.kona.app.messaging.entity.KEmailStats;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

public interface KEmailService<EMAIL extends KEmail,
                               EMAIL_EVENT extends KEmailEvent> 
		extends KService, KDataService<EMAIL> {

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
	
	public EMAIL fetchByUid(String uid);
	
	public EMAIL fetchBySesId(String sesId);
	
	public EMAIL fetchByCampaignIdAndChannelIdAndToId(Long campaignId, Long campaignChannelId, Long toAddressId);
	
	public List<EMAIL> fetchByGroupId(Long groupId);
	
	public List<EMAIL> fetchByCampaignId(Long campaignId);
	
	public List<EMAIL> fetchByGroupName(String groupName);
	
	public List<EMAIL> fetchByCampaignIdAndChannelId(Long campaignId, Long campaignChannelId);
    
	public KEmailStats calcStats(List<EMAIL> emailList);
	
	public KEmailStats calcStatsByGroupName(String groupName);
	
	public KEmailStats calcStatsByCampaignId(Long campaignId);
	
	public KEmailStats calcStatsByCampaignIdAndChannelId(Long campaignId, Long channelId);
    
    public EMAIL_EVENT addEvent(EMAIL_EVENT event);
    
	public void deliver(Long campaignId, Long campaignChannelId, Long groupId, 
			String fromAddress, String subject, String text, 
			String html, KEmailFooter footer, Long throttleTime);
    
	public void deliver(String fromAddress, String toAddress, 
			String subject, String text, String html, KEmailFooter footer);
    
	public void processSESNotifications();
}
