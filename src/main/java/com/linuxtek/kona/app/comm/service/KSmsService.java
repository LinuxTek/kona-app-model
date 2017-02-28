package com.linuxtek.kona.app.comm.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.comm.entity.KEmail;
import com.linuxtek.kona.app.comm.entity.KSms;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * KSmsService.
 */
public interface KSmsService<SMS extends KSms> extends KService, KDataService<SMS> {
    static final String SERVICE_PATH = "rpc/kona/SmsService";
    
    SMS fetchByUid(String uid);

    SMS fetchByMessageSid(String messageSid);

    List<SMS> fetchByCampaignIdAndChannelId(Long campaignId, Long campaignChannelId);

    SMS fetchByCampaignIdAndChannelIdAndToId(Long campaignId, Long campaignChannelId, String toNumber);
    

    SMS sendMessage(String to, String body);
    
    SMS sendMessage(String to, String body, String mediaUrl);

    SMS sendMessage(String to, String body, List<String> mediaUrls);
    
    SMS sendMessage(String from, String to, String body, List<String> mediaUrls);

    List<SMS> fetchByCampaignId(Long campaignId);
    
    boolean isTestPhoneNumber(String phoneNumber);

    void processMessageStatus(Map<String, Object> map);
}
