/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.social.entity.KAddressBook;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.locale.KValidator;
import com.linuxtek.kona.util.KClassUtil;


public abstract class KAbstractAddressBookService<ADDRESSBOOK extends KAddressBook, 
										 ADDRESSBOOK_EXAMPLE,
										 USER extends KUser>
		extends KAbstractService<ADDRESSBOOK,ADDRESSBOOK_EXAMPLE>
		implements KAddressBookService<ADDRESSBOOK> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractAddressBookService.class);
    
	// ----------------------------------------------------------------------------

	protected abstract ADDRESSBOOK getNewObject();
	
	protected abstract <S extends KUserService<USER>> S getUserService();
	
	// ----------------------------------------------------------------------------
    
    @Override 
    public ADDRESSBOOK add(ADDRESSBOOK addressBook) {
        validate(addressBook);
        
        // check if similar entry already exists
        ADDRESSBOOK ab = null;
        
        Long userId = addressBook.getUserId();

        USER refUser = null;
        
        List<ADDRESSBOOK> abList = null;
        
        // use mobile number as a unique identifier
        if (addressBook.getMobileNumber() != null) {
        	abList = fetchByMobileNumber(userId, addressBook.getMobileNumber());
        	
            if (abList != null && abList.size() > 0) {
            	/*
                ab = mergeAndUpdate(addressBook, ab);
                return ab;
                */
            	return abList.get(0);
            }
            
            refUser = getUserService().fetchByMobileNumber(addressBook.getMobileNumber());
        }

        // mobile is null, so check if email exists
        if (addressBook.getEmail() != null) {
        	abList = fetchByEmail(userId, addressBook.getEmail());
            if (abList != null && abList.size() > 0) {
            	/*
                ab = mergeAndUpdate(addressBook, ab);
                return ab;
                */
            	return abList.get(0);
            }
            
            if (refUser == null) {
            	refUser = getUserService().fetchByEmail(addressBook.getEmail());
            }
        }

        // email is null, so check first name, last name and address
        if (addressBook.getFirstName() != null 
        		&& addressBook.getLastName() != null 
                && addressBook.getAddress() != null) {
        	
		    Map<String,Object> filter = KMyBatisUtil.createFilter("firstName", addressBook.getFirstName());
		    filter.put("lastName", addressBook.getLastName());
		    filter.put("address", addressBook.getAddress());
		    
		    abList = fetchByCriteria(0, 99999, null, filter, false);
		    
            if (abList != null && abList.size() > 0) {
                ab = abList.get(0);
                Long abUserId = ab.getUserId();
                
                // if userIds don't match, then another user has the same contact in their address book
                if (userId.equals(abUserId)) {
                	return mergeAndUpdate(addressBook, ab);
                }
            }
        }
                
        if (refUser != null) {
            addressBook.setRefUserId(refUser.getId());
        }
        
        getDao().insert(addressBook);
        
        return addressBook;
    }

    // ----------------------------------------------------------------------
    
    @Override
    // TODO: complete this validation for rest of fields
    public void validate(ADDRESSBOOK addressBook) {
    	if (addressBook.getCreatedDate() == null) {
    		addressBook.setCreatedDate(new Date());
    	}
    	
    	if (addressBook.getId() != null) {
    		addressBook.setLastUpdated(new Date());
    	}
    	
    	String email = addressBook.getEmail();
    	if (email != null) {
    		if (KValidator.isEmail(email.trim())) {
    			email = null;
    		} else {
    			email = email.trim();
    		}
    	}
    	addressBook.setEmail(email);
    	
    	String mobileNumber = addressBook.getMobileNumber();
    	if (mobileNumber != null) {
    		if (!KValidator.isE164PhoneNumber(mobileNumber.trim())) {
    			mobileNumber = null;
    		} else {
    			mobileNumber = mobileNumber.trim();
    		}
    	}
    	addressBook.setMobileNumber(mobileNumber);
    	
    	String firstName = addressBook.getFirstName();
    	if (firstName != null) {
    		if (firstName.trim().length() == 0) {
    			firstName = null;
    		} else {
    			firstName = firstName.trim();
    		}
    	}
    	addressBook.setFirstName(firstName);
    	
    	String lastName = addressBook.getLastName();
    	if (lastName != null) {
    		if (lastName.trim().length() == 0) {
    			lastName = null;
    		} else {
    			lastName = lastName.trim();
    		}
    	}
    	addressBook.setLastName(lastName);
    }
    

    // ----------------------------------------------------------------------
    
    // TODO: complete this method
    private ADDRESSBOOK mergeAndUpdate(ADDRESSBOOK newItem, ADDRESSBOOK currentItem) {
        Long userId1 = newItem.getUserId();
        Long userId2 = currentItem.getUserId();
        
        if (userId1 == null || userId2 == null || !userId1.equals(userId2)) {
        	throw new IllegalArgumentException("AddressBook userId is null or does not match.");
        }
        
        // TODO: do similar checks for rest of fields
        if (currentItem.getFirstName() == null || currentItem.getFirstName().trim().length() == 0) {
        	String firstName = newItem.getFirstName();
        	if (firstName != null) {
        		firstName = firstName.trim();
        	}
        	currentItem.setFirstName(firstName);
        }
        
        currentItem.setLastName(newItem.getLastName());
        currentItem.setAddress(newItem.getAddress());
        currentItem.setCity(newItem.getCity());
        currentItem.setState(newItem.getState());
        currentItem.setPostalCode(newItem.getPostalCode());
        currentItem.setCountry(newItem.getCountry());
        currentItem.setMobileNumber(newItem.getMobileNumber());

        if (newItem.getTwitterId() != null) {
            currentItem.setTwitterId(newItem.getTwitterId());
        }

        if (newItem.getTwitterHandle() != null) {
            currentItem.setTwitterHandle(newItem.getTwitterHandle());
        }

        if (newItem.getFacebookId() != null) {
            currentItem.setFacebookId(newItem.getFacebookId());
        }
        
        if (newItem.getFacebookUsername() != null) {
            currentItem.setFacebookUsername(newItem.getFacebookUsername());
        }
        
  
        if ((currentItem.getEmail() == null && newItem.getEmail() != null) ||
                currentItem.getEmail() != null && newItem.getEmail() != null
        		&& !newItem.getEmail().equalsIgnoreCase(currentItem.getEmail())) {
        	currentItem.setEmail(newItem.getEmail());
            currentItem.setEmailVerified(false);
        } 
        
        return update(currentItem);
    }

    // ----------------------------------------------------------------------
    
    @Override
    public List<ADDRESSBOOK> fetchByEmail(Long userId, String email) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("email", email);
		return fetchByCriteria(0, 99999, null, filter, false);
    }
    
    // ----------------------------------------------------------------------
    
    @Override
	public List<ADDRESSBOOK> fetchByMobileNumber(Long userId, String mobileNumber) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("mobileNumber", mobileNumber);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
    // ----------------------------------------------------------------------
    
    @Override
	public List<ADDRESSBOOK> fetchByExample(ADDRESSBOOK example) {
		Map<String,Object> filter = KClassUtil.toMap(example);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

    // ----------------------------------------------------------------------
    
	@Override
	public List<ADDRESSBOOK> fetchByUserId(Long userId, boolean uninvitedOnly) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
		List<ADDRESSBOOK> list = fetchByCriteria(0, 99999, null, filter, false);
		
        if (!uninvitedOnly) return list;
        
        ArrayList<ADDRESSBOOK> result = new ArrayList<ADDRESSBOOK>();
        
        for (ADDRESSBOOK addressBook : list) {
            if (addressBook.getInvitedDate() == null) {
            	result.add(addressBook);
            }
        }
        
        return result;
	}
    
    // ----------------------------------------------------------------------
	
	@Override
	public List<ADDRESSBOOK> saveBatch(List<ADDRESSBOOK> list) {
        ArrayList<ADDRESSBOOK> result = new ArrayList<ADDRESSBOOK>();
        
        for (ADDRESSBOOK addressBook : list) {
            addressBook = add(addressBook);
            result.add(addressBook);
        }
        
        return result;
	}
    
    // ----------------------------------------------------------------------
	
	@Override
	public ADDRESSBOOK create(Long userId, String mobileNumber, String email, String firstName, String lastName) {
		ADDRESSBOOK addressBook = getNewObject();
		addressBook.setUserId(userId);
		addressBook.setMobileNumber(mobileNumber);
		addressBook.setEmail(email);
		addressBook.setFirstName(firstName);
		addressBook.setLastName(lastName);
		return add(addressBook);
	}
}
