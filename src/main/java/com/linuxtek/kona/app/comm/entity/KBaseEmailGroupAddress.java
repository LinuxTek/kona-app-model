package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

public class KBaseEmailGroupAddress implements KEmailGroupAddress {
    private Long id;
    private Long groupId;
    private Long addressId;
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
	public Long getGroupId() {
        return groupId;
    }

    @Override
	public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
	public Long getAddressId() {
        return addressId;
    }

    @Override
	public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", addressId=").append(addressId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
