package com.linuxtek.kona.app.comm.service;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class KAbstractSmsService extends KAbstractTwilioService implements KSmsService {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSmsService.class);

	// ----------------------------------------------------------------------------

    @Override
    public void sendMessage(String to, String body) {
    	super.sendMessage(to, body);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void sendMessage(String to, String body, String mediaUrl) {
        super.sendMessage(to, body, mediaUrl);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void sendMessage(String from, String to, String body, List<String> mediaUrls) {
        super.sendMessage(from, to, body, mediaUrls);
    }
    
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void processMessageStatus(Map<String,Object> map) {
        super.processMessageStatus(map);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public boolean isTestPhoneNumber(String phoneNumber) {
    	List<String> prefixList = getTestPhoneNumberPrefixList();
    	
    	if (prefixList == null) return false;
    	
    	for (String prefix : prefixList) {
    		if (phoneNumber.startsWith(prefix)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
}

