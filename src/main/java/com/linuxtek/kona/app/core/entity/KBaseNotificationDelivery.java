package com.linuxtek.kona.app.core.entity;


import java.util.Date;

public class KBaseNotificationDelivery implements KNotificationDelivery {
    private Long id;
    private Long notificationId;
    private Long channelId;
    private String code;
    private Date deliveredDate;
    private Date viewedDate;
    private Date createdDate;
    private Date updatedDate;

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
	public Long getNotificationId() {
        return notificationId;
    }

    @Override
	public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    @Override
	public Long getChannelId() {
        return channelId;
    }

    @Override
	public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    @Override
	public String getCode() {
        return code;
    }

    @Override
	public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
	public Date getDeliveredDate() {
        return deliveredDate;
    }

    @Override
	public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    @Override
	public Date getViewedDate() {
        return viewedDate;
    }

    @Override
	public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
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
	public Date getUpdatedDate() {
        return updatedDate;
    }

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
        sb.append(", notificationId=").append(notificationId);
        sb.append(", channelId=").append(channelId);
        sb.append(", code=").append(code);
        sb.append(", deliveredDate=").append(deliveredDate);
        sb.append(", viewedDate=").append(viewedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
