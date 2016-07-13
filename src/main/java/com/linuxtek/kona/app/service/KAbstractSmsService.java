/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.util.KClassUtil;
import com.twilio.sdk.TwilioRestException;


public abstract class KAbstractSmsService extends KAbstractTwilioService implements KSmsService {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSmsService.class);

	// ----------------------------------------------------------------------------

    @Override
    public void sendMessage(String to, String body) throws KSmsException {
        logger.debug("sendMessage:\nto: " + to + "\nbody: " + body);
        try {
            super.sendMessage(to, body);
        } catch(TwilioRestException e) {
            throw new KSmsException(e);
        } 
    }
    
    
	// ----------------------------------------------------------------------------
    
    @Override
    public void processMessageStatus(Map<String,Object> map) {
        logger.debug("processMessageStatus: got result: " + KClassUtil.toJson(map));
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

