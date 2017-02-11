package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseSetting implements KSetting {
	private static final long serialVersionUID = 1L;
	
    private Long id;
    private Long userId;
    private Long accountId;
    private String name;
    private String value;
    private boolean overwriteGlobal;
    private Date createdDate;
    private Date updatedDate;

    @Override
	public Long getId() {
        return id;
    }
    
    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    
    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
	public String getValue() {
        return value;
    }

    @Override
	public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
	public boolean isOverwriteGlobal() {
        return overwriteGlobal;
    }

    @Override
	public void setOverwriteGlobal(boolean overwriteGlobal) {
        this.overwriteGlobal = overwriteGlobal;
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
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", overwriteGlobal=").append(overwriteGlobal);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
    
}
