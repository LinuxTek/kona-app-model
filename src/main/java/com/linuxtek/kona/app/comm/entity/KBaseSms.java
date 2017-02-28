package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

public class KBaseSms implements KSms {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long campaignChannelId;
    private Long toUserId;
    private String toNumber;
    private String fromNumber;
    private String message;
    private String mediaUrls;
    private String status;
    private String errorCode;
    private String errorMessage;
    private String messageSid;
    private boolean failed;
    private boolean delivered;
    private boolean optedOut;
    private Integer clickCount;
    private Date sentDate;
    private Date createdDate;
    private Date updatedDate;

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the uid
     */
    @Override
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the campaignId
     */
    @Override
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId
     *            the campaignId to set
     */
    @Override
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * @return the campaignChannelId
     */
    @Override
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    /**
     * @param campaignChannelId
     *            the campaignChannelId to set
     */
    @Override
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    /**
     * @return the toUserId
     */
    @Override
    public Long getToUserId() {
        return toUserId;
    }

    /**
     * @param toUserId
     *            the toUserId to set
     */
    @Override
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * @return the toNumber
     */
    @Override
    public String getToNumber() {
        return toNumber;
    }

    /**
     * @param toNumber
     *            the toNumber to set
     */
    @Override
    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    /**
     * @return the fromNumber
     */
    @Override
    public String getFromNumber() {
        return fromNumber;
    }

    /**
     * @param fromNumber
     *            the fromNumber to set
     */
    @Override
    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the mediaUrls
     */
    @Override
    public String getMediaUrls() {
        return mediaUrls;
    }

    /**
     * @param mediaUrls
     *            the mediaUrls to set
     */
    @Override
    public void setMediaUrls(String mediaUrls) {
        this.mediaUrls = mediaUrls;
    }

    /**
     * @return the status
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the errorCode
     */
    @Override
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the messageSid
     */
    @Override
    public String getMessageSid() {
        return messageSid;
    }

    /**
     * @param messageSid
     *            the messageSid to set
     */
    @Override
    public void setMessageSid(String messageSid) {
        this.messageSid = messageSid;
    }

    /**
     * @return the failed
     */
    @Override
    public boolean isFailed() {
        return failed;
    }

    /**
     * @param failed
     *            the failed to set
     */
    @Override
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /**
     * @return the delivered
     */
    @Override
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * @param delivered
     *            the delivered to set
     */
    @Override
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    /**
     * @return the optedOut
     */
    @Override
    public boolean isOptedOut() {
        return optedOut;
    }

    /**
     * @param optedOut
     *            the optedOut to set
     */
    @Override
    public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }

    /**
     * @return the clickCount
     */
    @Override
    public Integer getClickCount() {
        return clickCount;
    }

    /**
     * @param clickCount
     *            the clickCount to set
     */
    @Override
    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * @return the sentDate
     */
    @Override
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * @param sentDate
     *            the sentDate to set
     */
    @Override
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * @return the createdDate
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the updatedDate
     */
    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate
     *            the updatedDate to set
     */
    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", campaignId=").append(campaignId);
        sb.append(", campaignChannelId=").append(campaignChannelId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", fromNumber=").append(fromNumber);
        sb.append(", toNumber=").append(toNumber);
        sb.append(", message=").append(message);
        sb.append(", mediaUrls=").append(mediaUrls);
        sb.append(", messageSid=").append(messageSid);
        sb.append(", status=").append(status);
        sb.append(", errorCode=").append(errorCode);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", failed=").append(failed);
        sb.append(", delivered=").append(delivered);
        sb.append(", optedOut=").append(optedOut);
        sb.append(", clickCount=").append(clickCount);
        sb.append(", sentDate=").append(sentDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
