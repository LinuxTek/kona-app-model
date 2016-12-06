package com.linuxtek.kona.app.comm.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KEmailGroupAddress extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getGroupId();

	void setGroupId(Long groupId);

	Long getAddressId();

	void setAddressId(Long addressId);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getLastUpdated();

	void setLastUpdated(Date lastUpdated);

}
