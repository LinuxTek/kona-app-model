package com.linuxtek.kona.app.comm.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.util.KClassUtil;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;


public abstract class KAbstractTwilioService {

    private static Logger logger = LoggerFactory.getLogger(KAbstractTwilioService.class);
    
    private final static Integer MAX_MESSAGE_LENGTH = 1600;
    
	// ----------------------------------------------------------------------------
    
    private TwilioRestClient client = null;
    
    //private Map<String,Message> statusQueue = new HashMap<String,Message>();
    
    private String messageStatusCallback = null;
    
	// ----------------------------------------------------------------------------
    
    protected abstract TwilioRestClient getTwilioClient();
    
    protected abstract String getFromPhoneNumber();
    
    protected abstract String getMessageStatusCallbackUrl();
    
    protected abstract List<String> getTestPhoneNumberPrefixList();
    
	// ----------------------------------------------------------------------------
    
    protected String getFrom(TwilioRestClient client) {
    	return getFromPhoneNumber();
    }
    
	// ----------------------------------------------------------------------------
    
    protected void sendMessage(String to, String body) throws TwilioRestException {
    	
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

        TwilioRestClient client = getTwilioClient();
        String from = getFrom(client);

        // Build a filter for the MessageList
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("From", from));
        params.add(new BasicNameValuePair("To", to));
        params.add(new BasicNameValuePair("Body", body));
        //params.add(new BasicNameValuePair("MediaUrl", "http://www.example.com/hearts.png"));

        messageStatusCallback = getMessageStatusCallbackUrl();
        
        if (messageStatusCallback != null) {
            params.add(new BasicNameValuePair("StatusCallback", messageStatusCallback));
        }

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        
        Message message = messageFactory.create(params);
        
        logger.debug("Message sent: " + message.getBody() + "\n\nMESSAGE SID: " + message.getSid());
        
        if (messageStatusCallback != null) {
            //statusQueue.put(message.getSid(), message);
        }
    }
    
	// ----------------------------------------------------------------------------
    
    protected void processMessageStatus(Map<String,Object> map) {
        logger.debug("processMessageStatus: got result: " + KClassUtil.toJson(map));
    }
}
