/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.linuxtek.kona.io.KProcess;
import com.linuxtek.kona.util.KFileUtil;
import com.linuxtek.kona.util.KInflector;
import com.linuxtek.kona.util.KStringUtil;

public abstract class KAbstractPushService implements KPushService {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPushService.class);
    
    private static final Map<Platform, Map<String, MessageAttributeValue>> attributesMap = 
    		new HashMap<Platform, Map<String, MessageAttributeValue>>();

	// ----------------------------------------------------------------------------
    
	protected abstract AmazonSNSClient getClient();
    
	// ----------------------------------------------------------------------------
	
    
    
	static {
		attributesMap.put(Platform.ADM, null);
		attributesMap.put(Platform.GCM, null);
		attributesMap.put(Platform.APNS, null);
		attributesMap.put(Platform.APNS_SANDBOX, null);
		attributesMap.put(Platform.BAIDU, addBaiduNotificationAttributes());
		attributesMap.put(Platform.WNS, addWNSNotificationAttributes());
		attributesMap.put(Platform.MPNS, addMPNSNotificationAttributes());
	}
    
	
	@Override
    public String createIOSApplicationEndpoint(String applicationName, byte[] p12CertificateAndKeyFile, String password, boolean sandbox) {
		if (applicationName == null) {
			throw new NullPointerException("Invalid application name: " + applicationName);
		}
		
		String principal = null;
		String credential = null;
		if (password == null) {
			password = "";
		}
		
	    try {
            String OPENSSL = "/usr/bin/openssl";
            File ssl = new File(OPENSSL);
            if (!ssl.exists()) {
                OPENSSL = "/usr/local/bin/openssl";
                ssl = new File(OPENSSL);
                if (!ssl.exists()) {
                    throw new RuntimeException("openssl not found. install and try again.");
                }
            }

            File p12 = KFileUtil.writeTempFile(p12CertificateAndKeyFile);

            File cert = KFileUtil.createTempFile();
            String args = "pkcs12 -nodes -password pass:" + password + " -clcerts -nokeys -in " 
            		+ p12.getAbsolutePath() + " -out " + cert.getAbsolutePath();
            
            logger.debug("createIOSApplication: generate cert: args: " + args);
            
            KProcess proc = KProcess.exec(OPENSSL, args.split("\\s+"));
            logger.debug("createIOSApplication: proc exit value: " + proc.getExitValue());
            //logger.debug("createIOSApplication: proc:\n" + proc);

            principal = KFileUtil.readFile(cert.getAbsolutePath()) + "\n";
            logger.debug("createIOSApplication: principal:\n" + principal);


            File key = KFileUtil.createTempFile();
            args = "pkcs12 -nodes -password pass:" + password + " -nocerts -in " 
            		+ p12.getAbsolutePath() + " -out " + key.getAbsolutePath();
            
            logger.debug("createIOSApplication: generate key: args: " + args);
            
            proc = KProcess.exec(OPENSSL, args.split("\\s+"));
            logger.debug("createIOSApplication: proc exit value: " + proc.getExitValue());
            
            credential = KFileUtil.readFile(key.getAbsolutePath()) + "\n";
            logger.debug("createIOSApplication: credential:\n" + credential);
            
            //cert.delete();
            //key.delete();
            //p12.delete();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
		
		
		
		Platform platform = Platform.APNS;
		if (sandbox) {
			platform = Platform.APNS_SANDBOX;
		}
		return createApplicationEndpoint(platform, applicationName, principal, credential);
	}
	
	
	/*
	 * iOS:
	 *   principal:  certificate - This should be in pem format with \n at the end of each line.
     *   credential:  privateKey - This should be in pem format with \n at the end of each line.
     *   
	 * android:
	 *   principal:  empty string ""
	 *   credential: serverAPIKey
	 */
	@Override
    public String createApplicationEndpoint(Platform platform, String applicationName,
              String principal, String credential) {
		if (applicationName == null) {
			throw new NullPointerException("Invalid application name: " + applicationName);
		}
		
		// normalize application names
		applicationName = KInflector.getInstance().slug(applicationName);
		
        CreatePlatformApplicationRequest platformApplicationRequest = new CreatePlatformApplicationRequest();
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("PlatformPrincipal", principal);
        attributes.put("PlatformCredential", credential);
        platformApplicationRequest.setAttributes(attributes);
        platformApplicationRequest.setName(applicationName);
        platformApplicationRequest.setPlatform(platform.name());
        return getClient().createPlatformApplication(platformApplicationRequest).getPlatformApplicationArn();
    }
	
    
	@Override
    public String createDeviceEndpoint(Platform platform, String applicationArn,  String deviceToken, String customData) {
        CreatePlatformEndpointRequest platformEndpointRequest = new CreatePlatformEndpointRequest();
        platformEndpointRequest.setCustomUserData(customData);
        String token = deviceToken;
        String userId = null;
        if (platform == Platform.BAIDU) {
            String[] tokenBits = deviceToken.split("\\|");
            token = tokenBits[0];
            userId = tokenBits[1];
            Map<String, String> endpointAttributes = new HashMap<String, String>();
            endpointAttributes.put("UserId", userId);
            endpointAttributes.put("ChannelId", token);
            platformEndpointRequest.setAttributes(endpointAttributes);
        }
        platformEndpointRequest.setToken(token);
        platformEndpointRequest.setPlatformApplicationArn(applicationArn);
        return getClient().createPlatformEndpoint(platformEndpointRequest).getEndpointArn();
    }
	
    @Override
    public void deleteApplicationEndpoint(String applicationArn) {
        DeletePlatformApplicationRequest request = new DeletePlatformApplicationRequest();
        request.setPlatformApplicationArn(applicationArn);
        getClient().deletePlatformApplication(request);
    }
    
    
    @Override
    public void deleteDeviceEndpoint(String deviceEndpoint) {
        DeleteEndpointRequest request = new DeleteEndpointRequest();
        request.setEndpointArn(deviceEndpoint);
        getClient().deleteEndpoint(request);
    }
	
    private Map<String, MessageAttributeValue> getValidNotificationAttributes(Map<String, MessageAttributeValue> notificationAttributes) {
        Map<String, MessageAttributeValue> validAttributes = new HashMap<String, MessageAttributeValue>();

        if (notificationAttributes == null) return validAttributes;

        for (Map.Entry<String, MessageAttributeValue> entry : notificationAttributes.entrySet()) {
            if (!isBlank(entry.getValue().getStringValue())) {
                validAttributes.put(entry.getKey(), entry.getValue());
            }
        }
        
        return validAttributes;
    }
    
    private boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        if (s.length() < 1) {
            return true;
        }

        return false;
    }

    private boolean isBlank(String s) {
        if (isEmpty(s)) {
            return true;
        }

        if (isEmpty(s.trim())) {
            return true;
        }

        return false;
    }
    
    
    @Override
    public String publish(Platform platform, String endpointArn, String message) {
    	return publish(platform, endpointArn, message, null);
    }
	
    
    @Override
    public String publish(Platform platform, String endpointArn, String message, Map<String,Object> data) {
    	Map<String,Object> rawMessage = null;
    	switch (platform) {
    	case GCM:
    		rawMessage = buildAndroidMessage(message, data);
    		break;
    	case APNS:
    	case APNS_SANDBOX:
    		rawMessage = buildAppleMessage(message, data, 1);
    		break;
    	default:
    		throw new IllegalArgumentException("Invalid platform: " + platform);
    	}
    	return publish(platform, endpointArn, rawMessage);
    }
    
    
    @Override
    public String publish(Platform platform, String endpointArn, Map<String,Object> rawMessage) {
        PublishRequest publishRequest = new PublishRequest();
        publishRequest.setMessageStructure("json");
        
        // For direct publish to mobile end points, topicArn is not relevant.
        publishRequest.setTargetArn(endpointArn);
        
        Map<String, MessageAttributeValue> notificationAttributes = 
        		getValidNotificationAttributes(attributesMap.get(platform));
        
        if (notificationAttributes != null && !notificationAttributes.isEmpty()) {
            publishRequest.setMessageAttributes(notificationAttributes);
        }
        
        
        Map<String, String> messageMap = new HashMap<String, String>();
        messageMap.put(platform.name(), KStringUtil.toJson(rawMessage));
        String message = KStringUtil.toJson(messageMap);
        
        publishRequest.setMessage(message);
        
        logMessage(message, notificationAttributes);
        return getClient().publish(publishRequest).getMessageId();
    }
    
    
    private void logMessage(String message, Map<String, MessageAttributeValue> notificationAttributes) {
    	// Display the message that will be sent to the endpoint/
    	logger.debug("{Message Body: " + message + "}");

    	if (notificationAttributes != null && !notificationAttributes.isEmpty()) {
    		StringBuilder builder = new StringBuilder();
    		builder.append("{Message Attributes: ");
    		for (Map.Entry<String, MessageAttributeValue> entry : notificationAttributes.entrySet()) {
    			builder.append("(\"" + entry.getKey() + "\": \""
    					+ entry.getValue().getStringValue() + "\"),");
    		}
    		builder.deleteCharAt(builder.length() - 1);
    		builder.append("}");
    		logger.debug(builder.toString());
    	}
    }
    
    	
    private Map<String,Object> buildAppleMessage(String message, Map<String,Object> data, Integer badgeCount) {
        Map<String, Object> appleMessageMap = new HashMap<String, Object>();
        Map<String, Object> appMessageMap = new HashMap<String, Object>();
        
        appMessageMap.put("alert", message);
        appMessageMap.put("badge", badgeCount);
        appMessageMap.put("sound", "default");
        
        appleMessageMap.put("aps", appMessageMap);
        if (data != null) {
        	for (String key : data.keySet()) {
        		appleMessageMap.put(key, data.get(key));
        	}
        }
        
        return appleMessageMap;
    }
    
    
    private Map<String, Object> buildAndroidMessage(String message, Map<String,Object> data) {
    	//Map<String, String> payload = new HashMap<String, String>();
        //payload.put("message", message);
        
        if (data == null) {
        	data = new HashMap<String, Object>();
        }
        data.put("message", message);
        
        
        // https://support.urbanairship.com/entries/71447343-GCM-Delay-while-idle-time-to-live-and-collapse-key
        Map<String, Object> androidMessageMap = new HashMap<String, Object>();
        androidMessageMap.put("data", data);
        androidMessageMap.put("collapse_key", "blync"); // only the last message will be delivered to the device when it comes back online
        androidMessageMap.put("delay_while_idle", false); // false, send to device even if 'idle'
        androidMessageMap.put("time_to_live", 86400); // expire message after this many secs if the device is offline
        androidMessageMap.put("dry_run", false); // set to true to validate message but not send it to device
        return androidMessageMap;
    }
	
	
	private static Map<String, MessageAttributeValue> addBaiduNotificationAttributes() {
		Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
		notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.DeployStatus",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("1"));
		notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.MessageKey",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("default-channel-msg-key"));
		notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.MessageType",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("0"));
		return notificationAttributes;
	}

	private static Map<String, MessageAttributeValue> addWNSNotificationAttributes() {
		Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
		notificationAttributes.put("AWS.SNS.MOBILE.WNS.CachePolicy",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("cache"));
		notificationAttributes.put("AWS.SNS.MOBILE.WNS.Type",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("wns/badge"));
		return notificationAttributes;
	}

	private static Map<String, MessageAttributeValue> addMPNSNotificationAttributes() {
		Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
		notificationAttributes.put("AWS.SNS.MOBILE.MPNS.Type",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("token")); // This attribute is required.
		notificationAttributes.put("AWS.SNS.MOBILE.MPNS.NotificationClass",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("realtime")); // This attribute is required.
														
		return notificationAttributes;
	}
    
}
