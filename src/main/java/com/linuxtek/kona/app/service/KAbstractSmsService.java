/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;


import java.util.ArrayList;
import java.util.HashMap;
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


public abstract class KAbstractSmsService extends KAbstractTwilioService {

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
}
