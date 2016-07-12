/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KEmailAddress;
import com.linuxtek.kona.app.entity.KEmailGroup;
import com.linuxtek.kona.app.entity.KEmailGroupAddress;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.util.KInflector;

public abstract class KAbstractEmailGroupService<EMAIL_GROUP extends KEmailGroup, 
										EMAIL_GROUP_EXAMPLE,
										EMAIL_ADDRESS extends KEmailAddress,
										EMAIL_GROUP_ADDRESS extends KEmailGroupAddress>
		extends KAbstractService<EMAIL_GROUP, EMAIL_GROUP_EXAMPLE>
		implements KEmailGroupService<EMAIL_GROUP, EMAIL_ADDRESS, EMAIL_GROUP_ADDRESS> {

	protected final static Logger logger = LoggerFactory.getLogger(KAbstractEmailGroupService.class);
    
	// ----------------------------------------------------------------------------
	
	protected abstract EMAIL_GROUP getNewEmailGroupObject();
	
	protected abstract EMAIL_ADDRESS getNewEmailAddressObject();
	
	protected abstract EMAIL_GROUP_ADDRESS getNewEmailGroupAddressObject();
	
	protected abstract <S extends KEmailAddressService<EMAIL_ADDRESS>> S getEmailAddressService();
	
	protected abstract <S extends KEmailGroupAddressService<EMAIL_GROUP_ADDRESS>> S getEmailGroupAddressService();
	
	// ----------------------------------------------------------------------------
    
	@Override
	public void validate(EMAIL_GROUP emailGroup) {
		if (emailGroup.getCreatedDate() == null) {
			emailGroup.setCreatedDate(new Date());
		}
		
		if (emailGroup.getUid() == null) {
			emailGroup.setUid(uuid());
		}
		
		String name = KInflector.getInstance().slug(emailGroup.getDisplayName());
		
		emailGroup.setName(name);
	}

	// ----------------------------------------------------------------------

	@Override
	public EMAIL_GROUP fetchByName(String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
    
	// ----------------------------------------------------------------------
    
	@Override
	public List<EMAIL_GROUP_ADDRESS> fetchGroupAddressList(String name) {
        EMAIL_GROUP group = fetchByName(name);
        return getEmailGroupAddressService().fetchByGroupId(group.getId());
	}
    
	// ----------------------------------------------------------------------

	@Override
	public EMAIL_GROUP_ADDRESS addGroupAddress(String groupName, String email) {
        EMAIL_GROUP group = fetchByName(groupName);
        return addGroupAddress(group.getId(), email);
	}
    
	private EMAIL_GROUP_ADDRESS addGroupAddress(Long groupId, String email) {
        EMAIL_ADDRESS address = getEmailAddressService().fetchByEmail(email);
        if (address == null) {
        	address = getNewEmailAddressObject();
        	address.setEmail(email);
        	address.setEnabled(true);
        	address.setCreatedDate(new Date());
            address = getEmailAddressService().add(address);
        }
        
        if (!getEmailAddressService().isValid(address)) {
        	throw new KEmailException("Invalid email address: " + email);
        }
        
        EMAIL_GROUP_ADDRESS ga = getNewEmailGroupAddressObject();
        ga.setAddressId(address.getId());
        ga.setGroupId(groupId);
        ga.setCreatedDate(new Date());
        return getEmailGroupAddressService().add(ga);
	}

	// ----------------------------------------------------------------------
    
	@Override
	public EMAIL_GROUP_ADDRESS removeGroupAddress(String groupName, String email) {
        EMAIL_ADDRESS address = getEmailAddressService().fetchByEmail(email);
        EMAIL_GROUP group = fetchByName(groupName);
        EMAIL_GROUP_ADDRESS ga = getEmailGroupAddressService().fetchByGroupIdAndAddressId(group.getId(), address.getId());
        getEmailGroupAddressService().remove(ga);
        return ga;
	}
    
	// ----------------------------------------------------------------------

	@Override
	public void addGroupAddressList(String groupName, List<EMAIL_ADDRESS> emailAddressList) {
        EMAIL_GROUP group = fetchByName(groupName);
        
        Date now = new Date();
        for (EMAIL_ADDRESS address : emailAddressList) {
        	EMAIL_GROUP_ADDRESS ga = getNewEmailGroupAddressObject();
        	ga.setAddressId(address.getId());
        	ga.setGroupId(group.getId());
        	ga.setCreatedDate(now);
        	getEmailGroupAddressService().add(ga);
        }
	}
    
	// ----------------------------------------------------------------------
	
	@Override
	public EMAIL_GROUP create(String groupName, List<String> emailList) {
        EMAIL_GROUP group = create(groupName);
        for (String email : emailList) {
        	try {
        		addGroupAddress(group.getId(), email);
        	} catch (KEmailException e) {
        		logger.warn("Cound not add email [{}] to group [{}]", email, groupName);
        	}
        }
        return group;
	}
    
	// ----------------------------------------------------------------------
	
	@Override
	public EMAIL_GROUP create(String groupName) {
        EMAIL_GROUP group = getNewEmailGroupObject();
        group.setDisplayName(groupName);
        group.setCreatedDate(new Date());
        group = add(group);
        return group;
	}
	
	// ----------------------------------------------------------------------

	/**
	 * @param groupName Name of the group
	 * @param maxCount Max number of addresses in the group
	 * @param sourceList (optional) list of sources from which to pull email addresses
	 * @param excludeGroupList (optional) don't include emails contained in the listed groups
	 */
	@Override
	public EMAIL_GROUP create(String groupName, Long maxCount, List<String> sourceList, List<String> excludeGroupList) {
        EMAIL_GROUP group = create(groupName);
        
        if (maxCount != null) {
        	List<EMAIL_ADDRESS> emailAddressList = getEmailAddressService().fetchRandom(maxCount, sourceList, excludeGroupList);
        	if (emailAddressList.size() == 0) {
        		logger.warn("EmailAddress fetchRandom yielded no results");
        	}
        	addGroupAddressList(groupName, emailAddressList);
        }
        return group;
	}
}

