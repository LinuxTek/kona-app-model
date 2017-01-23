package com.linuxtek.kona.app.comm.service;


import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.util.KClassUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public abstract class KAbstractTwilioService {

    private static Logger logger = LoggerFactory.getLogger(KAbstractTwilioService.class);
    
    private final static Integer MAX_MESSAGE_LENGTH = 1600;
    
	// ----------------------------------------------------------------------------
    
    private boolean initialized = false;
    
    private Map<String,Message> statusQueue = new HashMap<String,Message>();
    
    private String messageStatusCallback = null;
    
	// ----------------------------------------------------------------------------
    
    protected abstract String getAccountSid();
    
    protected abstract String getAuthToken();
    
    protected abstract String getFromPhoneNumber();
    
    protected abstract String getMessageStatusCallbackUrl();
    
    protected abstract List<String> getTestPhoneNumberPrefixList();
    
	// ----------------------------------------------------------------------------
    
    // https://www.twilio.com/docs/api/rest/sending-messages
    protected Integer getMaxMediaCount() {
    	return 10;
    }
    
    // https://www.twilio.com/docs/api/rest/accepted-mime-types
    protected Integer getMaxMessageSize() {
    	return 5*1024*1024;
    }
    
	// ----------------------------------------------------------------------------
    
    protected void init() {
        if (!initialized) {
        	Twilio.init(getAccountSid(), getAuthToken());
            initialized = true;
        }
    }
    
	// ----------------------------------------------------------------------------
    
    protected void sendMessage(String to, String body) {
        sendMessage(getFromPhoneNumber(), to, body, null);
    }
    
	// ----------------------------------------------------------------------------
    
    protected void sendMessage(String to, String body, String mediaUrl) {
        List<String> urls = new ArrayList<String>();
        urls.add(mediaUrl);
        sendMessage(getFromPhoneNumber(), to, body, urls);
    }
    
	// ----------------------------------------------------------------------------
    
    protected void sendMessage(String from, String to, String body, List<String> mediaUrls) {
        init();
    	
        if (to == null) {
            throw new KSmsException("sendMessage: Message 'to' is null");
        }
        
        List<String> prefixList = getTestPhoneNumberPrefixList();

        if (prefixList != null) {
        	for (String testPrefix : prefixList) {
        		if (testPrefix != null && to.startsWith(testPrefix)) {
        			logger.warn("sendMessage(): sending message to test account: to: " + to);
        			return;
        		}
        	}
        }

        if (body == null) {
            throw new KSmsException("sendMessage: Message 'body' is null");
        }


        body = body.trim();
        
        if (body.length() > MAX_MESSAGE_LENGTH) {
            body = body.substring(0, MAX_MESSAGE_LENGTH);
        }
        
        MessageCreator creator = Message.creator(
        		new PhoneNumber(to),
                new PhoneNumber(from), 
                body
        );
        
        if (mediaUrls != null && mediaUrls.size() > 0) {
        	List<URI> uriList = new ArrayList<URI>();
            
        	Integer count = mediaUrls.size();
            
        	if (count > getMaxMediaCount()) {
        		count = getMaxMediaCount();
                logger.info("Media messages truncated to max number: " + count);
        	}
            
            for (int i=0; i < count; i++) {
            	String mediaUrl = mediaUrls.get(i);
            	URI uri = URI.create(mediaUrl);
                uriList.add(uri);
            }
            
            creator.setMediaUrl(uriList);
        }
        
        messageStatusCallback = getMessageStatusCallbackUrl();
        
        if (messageStatusCallback != null) {
            creator.setStatusCallback(messageStatusCallback);
        }
        
        Message message = creator.create();
 

        //params.add(new BasicNameValuePair("MediaUrl", "http://www.example.com/hearts.png"));


        logger.debug("Message sent: " + message.getBody() + "\n\nMESSAGE SID: " + message.getSid());
        
        if (messageStatusCallback != null) {
        	statusQueue.put(message.getSid(), message);
        }
    }
    
	// ----------------------------------------------------------------------------
    
    protected void processMessageStatus(Map<String,Object> map) {
        logger.debug("processMessageStatus: got result: " + KClassUtil.toJson(map));
    }
}
