/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Map;

import com.linuxtek.kona.remote.service.KService;


/**
 * KSmsService.
 */
public interface KSmsService extends KService {
    public static final String SERVICE_PATH = "rpc/kona/SmsService";
    
    public void sendMessage(String to, String body) throws KSmsException;
    
    public boolean isTestPhoneNumber(String phoneNumber);

    void processMessageStatus(Map<String, Object> map);
}
