package com.linuxtek.kona.app.comm.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.remote.service.KService;


/**
 * KSmsService.
 */
public interface KSmsService extends KService {
    public static final String SERVICE_PATH = "rpc/kona/SmsService";
    
    public void sendMessage(String to, String body);
    
    public void sendMessage(String to, String body, String mediaUrl);

    public void sendMessage(String to, String body, List<String> mediaUrls);
    
    public void sendMessage(String from, String to, String body, List<String> mediaUrls);
    
    public boolean isTestPhoneNumber(String phoneNumber);

    void processMessageStatus(Map<String, Object> map);
}
