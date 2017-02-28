package com.linuxtek.kona.app.comm.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.comm.entity.KSms;
import com.linuxtek.kona.app.core.entity.KUser;


public abstract class KAbstractSmsService<SMS extends KSms,SMS_EXAMPLE,USER extends KUser> 
        extends KAbstractTwilioService<SMS,SMS_EXAMPLE,USER> 
        implements KSmsService<SMS> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSmsService.class);

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

