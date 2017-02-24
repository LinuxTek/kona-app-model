package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KSupportMessage;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;

public abstract class KAbstractSupportMessageService<
SUPPORT_MESSAGE extends KSupportMessage,
SUPPORT_MESSAGE_EXAMPLE,
USER extends KUser> 
extends KAbstractService<SUPPORT_MESSAGE,SUPPORT_MESSAGE_EXAMPLE>
implements KSupportMessageService<SUPPORT_MESSAGE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractSupportMessageService.class);

    // ----------------------------------------------------------------------------

    protected abstract SUPPORT_MESSAGE getNewObject();

    protected abstract <S extends KUserService<USER>> S getUserService();
    
    protected abstract void sendNotification(SUPPORT_MESSAGE message);

    // ----------------------------------------------------------------------------

    @Override
    public void validate(SUPPORT_MESSAGE supportMessage) {
        if (supportMessage.getCreatedDate() == null) {
            supportMessage.setCreatedDate(new Date());
        }

        if (supportMessage.getUid() == null) {
            supportMessage.setUid(uuid());
        }

        supportMessage.setUpdatedDate(new Date());

        if (supportMessage.getUserId() == null && supportMessage.getEmail() != null) {
            USER u = getUserService().fetchByEmail(supportMessage.getEmail());

            if (u != null) {
                supportMessage.setUserId(u.getId());
            }
        }

        if (supportMessage.getUserId() == null && supportMessage.getMobileNumber() != null) {
            USER u = getUserService().fetchByMobileNumber(supportMessage.getMobileNumber());

            if (u != null) {
                supportMessage.setUserId(u.getId());
            }
        }
        
        if (supportMessage.getUserId() != null 
                && (supportMessage.getFirstName() == null || supportMessage.getLastName() == null)) {

            USER u = getUserService().fetchById(supportMessage.getUserId());

            if (supportMessage.getFirstName() == null) {
                supportMessage.setFirstName(u.getFirstName());
            }

            if (supportMessage.getLastName() == null) {
                supportMessage.setLastName(u.getLastName());
            }
        }
    }

    // ----------------------------------------------------------------------------

    @Override
    public SUPPORT_MESSAGE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    // ----------------------------------------------------------------------------

    @Override
    public List<SUPPORT_MESSAGE> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    // ----------------------------------------------------------------------------

    @Override
    public List<SUPPORT_MESSAGE> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    // ----------------------------------------------------------------------------

    @Override
    public List<SUPPORT_MESSAGE> fetchByEmail(String email) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    // ----------------------------------------------------------------------------

    @Override
    public List<SUPPORT_MESSAGE> fetchByMobileNumber(String mobileNumber) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    // ----------------------------------------------------------------------------

    @Override 
    public SUPPORT_MESSAGE send(SUPPORT_MESSAGE message) {
        message = save(message);
        sendNotification(message);
        return message;
    }

    // ----------------------------------------------------------------------------

    @Override
    public SUPPORT_MESSAGE send(KServiceClient client, Long userId, String email, String mobileNumber, String message) {
        SUPPORT_MESSAGE supportMessage = getNewObject();

        supportMessage.setAppId(client.getAppId());
        supportMessage.setUserId(userId);
        supportMessage.setEmail(email);
        supportMessage.setMobileNumber(mobileNumber);
        supportMessage.setMessage(message);
        supportMessage.setHostname(client.getHostname());
        supportMessage.setBrowser(client.getBrowser());
        
        return send(supportMessage);
    }

}
