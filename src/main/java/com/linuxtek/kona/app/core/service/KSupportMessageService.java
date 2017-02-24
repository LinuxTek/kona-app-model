package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KSupportMessage;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceClient;

/**
 * The client side stub for the RPC service.
 */
public interface KSupportMessageService<SUPPORT_MESSAGE extends KSupportMessage> extends KService, KDataService<SUPPORT_MESSAGE> {
    
    SUPPORT_MESSAGE fetchByUid(String uid);

    List<SUPPORT_MESSAGE> fetchByAppId(Long appId);

    List<SUPPORT_MESSAGE> fetchByUserId(Long userId);

    List<SUPPORT_MESSAGE> fetchByEmail(String email);

    List<SUPPORT_MESSAGE> fetchByMobileNumber(String mobileNumber);
    
    SUPPORT_MESSAGE send(SUPPORT_MESSAGE message);

    SUPPORT_MESSAGE send(KServiceClient client, String name, String email, String mobileNumber, String message);
}
