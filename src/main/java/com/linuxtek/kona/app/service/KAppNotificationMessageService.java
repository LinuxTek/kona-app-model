/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;

import com.linuxtek.kona.app.entity.KAppNotificationMessage;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppNotificationMessageService<T extends KAppNotificationMessage> extends KService, KDataService<T> {
	public static final String SERVICE_PATH = "rpc/kona/AppNotificationMessageService";
	
	public T fetchByUid(String uid);
	
	public List<T> fetchByAppId(Long appId);

	public T broadcast(Long appId, String title, String message, 
			String imageUrl, String actionUrl, Map<String, Object> filter, boolean sandbox);

	public T broadcast(T message, Map<String, Object> filter, boolean sandbox);
	
	/**
	 * @param message
	 * @param userIds
	 * @param latitude
	 * @param longitude
	 * @param radius in meters
	 * @param sandbox
	 * @return NotificationMessage object
	 */
	public T broadcast(T message, List<Long> userIds, Double latitude, Double longitude, Double radius, boolean sandbox);
}
