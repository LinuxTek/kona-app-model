package com.linuxtek.kona.app.comm.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.linuxtek.kona.app.core.entity.KUser;

public class KSmsMessage<USER extends KUser> implements Serializable {
    private static final long serialVersionUID = 1L;

    private USER user;
    private String mobileNumber;
    private String messageSid;
    private boolean inbound;
    private String rawMessage;
    private Map<String,Object> data;
    private Map<String,String> mediaMap;
    private Integer mediaCount;

    
	public USER getUser() {
		return user;
	}

	public void setUser(USER user) {
		this.user = user;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getMessageSid() {
		return messageSid;
	}

	public void setMessageSid(String messageSid) {
		this.messageSid = messageSid;
	}

	public boolean isInbound() {
		return inbound;
	}

	public void setInbound(boolean inbound) {
		this.inbound = inbound;
	}

	public String getRawMessage() {
		return rawMessage;
	}

	public void setRawMessage(String rawMessage) {
		this.rawMessage = rawMessage;
	}

	public Map<String,String> getMediaMap() {
		return mediaMap;
	}

	public void setMediaMap(Map<String,String> mediaMap) {
		this.mediaMap = mediaMap;
	}

	public Integer getMediaCount() {
		return mediaCount;
	}

	public void setMediaCount(Integer mediaCount) {
		this.mediaCount = mediaCount;
	}

	public Map<String,Object> getData() {
		return data;
	}

	public void setData(Map<String,Object> data) {
		this.data = data;
	}
	
	public void addDataItem(String key, Object value) {
		if (data == null) {
			data = new HashMap<String,Object>();
		}

		data.put(key, value);
	}


	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", user=").append(user);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", inbound=").append(inbound);
        sb.append(", rawMessage=").append(rawMessage);
        sb.append(", mediaMap=").append(mediaMap);
        sb.append(", mediaCount=").append(mediaCount);
        sb.append(", data=").append(data);
        sb.append("]");
        return sb.toString();
    }


}

