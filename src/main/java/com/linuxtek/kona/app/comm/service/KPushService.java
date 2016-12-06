package com.linuxtek.kona.app.comm.service;

import java.util.Map;

import com.linuxtek.kona.remote.service.KService;

public interface KPushService extends KService {
    public static final String SERVICE_PATH = "rpc/kona/PushService";
    
    public static enum Platform {
        // Apple Push Notification Service
        APNS,
        
        // Sandbox version of Apple Push Notification Service
        APNS_SANDBOX,
        
        // Amazon Device Messaging
        ADM,
        
        // Google Cloud Messaging
        GCM,
        
        // Baidu CloudMessaging Service
        BAIDU,
        
        // Windows Notification Service
        WNS,
        
        // Microsoft Push Notificaion Service
        MPNS;
    }

    public String createIOSApplicationEndpoint(
        String applicationName,  
        byte[] p12CertificateAndKeyFile, 
        String password,
        boolean sandbox);


    public String createApplicationEndpoint(
        Platform platform, 
        String applicationName, 
        String principal, 
        String credential);
    
    public void deleteApplicationEndpoint(String applicationArn);

    public String createDeviceEndpoint(
        Platform platform, 
        String applicationArn, 
        String deviceToken, 
        String customData);
    
    public void deleteDeviceEndpoint(String deviceEndpoint);

    public String publish(Platform platform, String endpointArn, String message);

    public String publish(Platform platform, String endpointArn, String message, Map<String, Object> data);
    
    public String publish(Platform platform, String endpointArn, Map<String,Object> rawMessage);
    
    public KPushService.Platform getPushPlatform(String platformName, boolean sandbox);
}
