package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KNotification;
import com.linuxtek.kona.app.core.entity.KNotificationChannel;
import com.linuxtek.kona.app.core.entity.KNotificationDelivery;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractNotificationService< Notification extends KNotification, 
													NotificationExample, 
													NotificationDelivery extends KNotificationDelivery, 
													User extends KUser>
		extends KAbstractService<Notification,NotificationExample>
		implements KNotificationService<Notification> {
	
	private static Logger logger = LoggerFactory.getLogger(KAbstractNotificationService.class);
	
	// ----------------------------------------------------------------------------
    
	protected abstract Notification getNewNotificationObject();
    
	protected abstract NotificationDelivery getNewNotificationDeliveryObject();
    
	protected abstract boolean isChannelEnabled(Long userId, String event, KNotificationChannel channel);
    
	protected abstract void sendNotification(Notification notification, NotificationDelivery delivery);
    
	protected abstract <S extends KUserService<User>> S getUserService();
    
	protected abstract <S extends KNotificationDeliveryService<NotificationDelivery>> S getNotificationDeliveryService();
    
	// ----------------------------------------------------------------------------

	protected String generateAccessCode() {
		return uuid();
	}
    
	// ----------------------------------------------------------------------------

    @Override
	public void validate(Notification notification) {
    	if (notification.getCreatedDate() == null) {
			notification.setCreatedDate(new Date());
		}
    	
    	notification.setUpdatedDate(new Date());
    	
     	if (notification.getUid() == null) {
			notification.setUid(uuid());
		}
	}
    
	// ----------------------------------------------------------------------------
    
	@Override
	public Notification fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
    
	@Override
	public List<Notification> fetchByUserIdSinceUid(Long userId, String uid, Integer limit) {
        Date eventDate = null;
        
        if (uid != null) {
        	Notification notification = fetchByUid(uid);
            
            if (notification != null) {
                eventDate = notification.getEventDate();
            }
        }
        
		if (limit == null) {
			limit = 100;
		}
        
        String[] sortOrder = { "eventDate DESC" };
        
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        
        if (eventDate != null) {
            filter.put(">eventDate", eventDate);
        }
        
        return fetchByCriteria(0, limit, sortOrder, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public void notifyEvent(Long userId, String event, Date eventDate) {
		
		Notification notification = getNewNotificationObject();
		notification.setUserId(userId);
		notification.setEventDate(eventDate);
		notification.setCreatedDate(new Date());
		
		notification = add(notification);
        
		KNotificationChannel channels[] = KNotificationChannel.values();
        
        for (KNotificationChannel channel : channels) {
        	if (isChannelEnabled(userId, event, channel)) {
                try {
                	NotificationDelivery delivery = createDelivery(notification, channel);
                	sendNotification(notification, delivery);
                } catch (Exception e) {
                	logger.error("Error sending notificaiton: ", e);
                }
        	}
        }
	}
	
	// ----------------------------------------------------------------------------

	protected NotificationDelivery createDelivery(Notification notification, KNotificationChannel channel) {
		String code = generateAccessCode();

		NotificationDelivery delivery = getNewNotificationDeliveryObject();
		delivery.setNotificationId(notification.getId());
		delivery.setChannelId(channel.getId());
		delivery.setCode(code);
		delivery.setDeliveredDate(new Date());
		delivery.setCreatedDate(new Date());
		
		return getNotificationDeliveryService().add(delivery);
	}
}
