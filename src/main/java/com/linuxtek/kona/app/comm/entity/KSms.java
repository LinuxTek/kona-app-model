package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KSms extends KEntityObject {

    /**
     * @return the id
     */
    Long getId();

    /**
     * @param id the id to set
     */
    void setId(Long id);

    /**
     * @return the uid
     */
    String getUid();

    /**
     * @param uid the uid to set
     */
    void setUid(String uid);

    /**
     * @return the campaignId
     */
    Long getCampaignId();

    /**
     * @param campaignId the campaignId to set
     */
    void setCampaignId(Long campaignId);

    /**
     * @return the campaignChannelId
     */
    Long getCampaignChannelId();

    /**
     * @param campaignChannelId the campaignChannelId to set
     */
    void setCampaignChannelId(Long campaignChannelId);

    /**
     * @return the toUserId
     */
    Long getToUserId();

    /**
     * @param toUserId the toUserId to set
     */
    void setToUserId(Long toUserId);

    /**
     * @return the toNumber
     */
    String getToNumber();

    /**
     * @param toNumber the toNumber to set
     */
    void setToNumber(String toNumber);

    /**
     * @return the fromNumber
     */
    String getFromNumber();

    /**
     * @param fromNumber the fromNumber to set
     */
    void setFromNumber(String fromNumber);

    /**
     * @return the message
     */
    String getMessage();

    /**
     * @param message the message to set
     */
    void setMessage(String message);
    
    /**
     * @return the mediaUrls
     */
    String getMediaUrls();

    /**
     * @param message the message to set
     */
    void setMediaUrls(String mediaUrls);


    /**
     * @return the messageSid
     */
    String getMessageSid();

    /**
     * @param messageSid the messageSid to set
     */
    void setMessageSid(String messageSid);

    /**
     * @return the failed
     */
    boolean isFailed();

    /**
     * @param failed the failed to set
     */
    void setFailed(boolean failed);

    /**
     * @return the delivered
     */
    boolean isDelivered();

    /**
     * @param delivered the delivered to set
     */
    void setDelivered(boolean delivered);

    /**
     * @return the optedOut
     */
    boolean isOptedOut();

    /**
     * @param optedOut the optedOut to set
     */
    void setOptedOut(boolean optedOut);

    /**
     * @return the clickCount
     */
    Integer getClickCount();

    /**
     * @param clickCount the clickCount to set
     */
    void setClickCount(Integer clickCount);

    /**
     * @return the sentDate
     */
    Date getSentDate();

    /**
     * @param sentDate the sentDate to set
     */
    void setSentDate(Date sentDate);

    /**
     * @return the createdDate
     */
    Date getCreatedDate();

    /**
     * @param createdDate the createdDate to set
     */
    void setCreatedDate(Date createdDate);

    /**
     * @return the updatedDate
     */
    Date getUpdatedDate();

    /**
     * @param updatedDate the updatedDate to set
     */
    void setUpdatedDate(Date updatedDate);

    String getStatus();

    void setStatus(String status);

    String getErrorCode();

    void setErrorCode(String errorCode);

    String getErrorMessage();

    void setErrorMessage(String errorMessage);

}