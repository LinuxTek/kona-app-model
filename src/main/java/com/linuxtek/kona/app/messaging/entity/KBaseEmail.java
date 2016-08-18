package com.linuxtek.kona.app.messaging.entity;

import java.util.Date;

public class KBaseEmail implements KEmail {
    private Long id;
    private String uid;
    private Long campaignId;
    private Long campaignChannelId;
    private Long groupId;
    private Long toAddressId;
    private String sesId;
    private String fromAddress;
    private String toAddress;
    private String subject;
    private boolean failed;
    private boolean delivered;
    private boolean bounced;
    private boolean complained;
    private boolean optedOut;
    private Integer openCount;
    private Integer clickCount;
    private Integer printCount;
    private Integer forwardCount;
    private Date createdDate;
    private Date sentDate;
    private Date lastUpdated;

    private static final long serialVersionUID = 1L;

    @Override
	public Long getId() {
        return id;
    }
    
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public String getUid() {
        return uid;
    }

    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    @Override
	public Long getCampaignId() {
        return campaignId;
    }

    @Override
	public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    @Override
	public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    @Override
	public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    @Override
	public Long getGroupId() {
        return groupId;
    }

    @Override
	public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
	public Long getToAddressId() {
        return toAddressId;
    }

    @Override
	public void setToAddressId(Long toAddressId) {
        this.toAddressId = toAddressId;
    }

    @Override
	public String getSesId() {
        return sesId;
    }

    @Override
	public void setSesId(String sesId) {
        this.sesId = sesId == null ? null : sesId.trim();
    }

    @Override
	public String getFromAddress() {
        return fromAddress;
    }

    @Override
	public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress == null ? null : fromAddress.trim();
    }

    @Override
	public String getToAddress() {
        return toAddress;
    }

    @Override
	public void setToAddress(String toAddress) {
        this.toAddress = toAddress == null ? null : toAddress.trim();
    }

    @Override
	public String getSubject() {
        return subject;
    }

    @Override
	public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    @Override
	public boolean isFailed() {
        return failed;
    }

    @Override
	public void setFailed(boolean failed) {
        this.failed = failed;
    }

    @Override
	public boolean isDelivered() {
        return delivered;
    }

    @Override
	public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
	public boolean isBounced() {
        return bounced;
    }

    @Override
	public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }

    @Override
	public boolean isComplained() {
        return complained;
    }

    @Override
	public void setComplained(boolean complained) {
        this.complained = complained;
    }

    @Override
	public boolean isOptedOut() {
        return optedOut;
    }

    @Override
	public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }

    @Override
	public Integer getOpenCount() {
        return openCount;
    }

    @Override
	public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    @Override
	public Integer getClickCount() {
        return clickCount;
    }

    @Override
	public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    @Override
	public Integer getPrintCount() {
        return printCount;
    }

    @Override
	public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    @Override
	public Integer getForwardCount() {
        return forwardCount;
    }

    @Override
	public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
	public Date getSentDate() {
        return sentDate;
    }

    @Override
	public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @Override
	public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
	public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", toAddressId=").append(toAddressId);
        sb.append(", sesId=").append(sesId);
        sb.append(", fromAddress=").append(fromAddress);
        sb.append(", toAddress=").append(toAddress);
        sb.append(", subject=").append(subject);
        sb.append(", failed=").append(failed);
        sb.append(", delivered=").append(delivered);
        sb.append(", bounced=").append(bounced);
        sb.append(", complained=").append(complained);
        sb.append(", optedOut=").append(optedOut);
        sb.append(", openCount=").append(openCount);
        sb.append(", clickCount=").append(clickCount);
        sb.append(", printCount=").append(printCount);
        sb.append(", forwardCount=").append(forwardCount);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", sentDate=").append(sentDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
