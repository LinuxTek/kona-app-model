/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.social.entity.KAddressBook;
import com.linuxtek.kona.app.social.entity.KFriendship;
import com.linuxtek.kona.app.social.entity.KInvitation;
import com.linuxtek.kona.app.social.entity.KInvitationChannel;
import com.linuxtek.kona.app.social.entity.KInvitationStatus;
import com.linuxtek.kona.app.social.entity.KInvitationType;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.core.service.KUserService;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;


public abstract class KAbstractInvitationService<
                                        INVITATION extends KInvitation, 
										INVITATION_EXAMPLE,
										ADDRESSBOOK extends KAddressBook,
										FRIENDSHIP extends KFriendship,
										USER extends KUser>
		extends KAbstractService<INVITATION,INVITATION_EXAMPLE>
		implements KInvitationService<INVITATION> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractInvitationService.class);


	// ----------------------------------------------------------------------------

	protected abstract INVITATION getNewObject();
	
	protected abstract String getInvitationUrl(Long userId, String code);
	
	protected abstract INVITATION sendInvitation(INVITATION invitation, ADDRESSBOOK addressBook, String invitationUrl);
	
	protected abstract <S extends KUserService<USER>> S getUserService();
	
	protected abstract <S extends KAddressBookService<ADDRESSBOOK>> S getAddressBookService();
	
	protected abstract <S extends KFriendshipService<FRIENDSHIP>> S getFriendshipService();
	
	// ----------------------------------------------------------------------------
	
	protected String generateAccessCode() {
		return uuid();
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public void validate(INVITATION invitation) {
		if (invitation.getCreatedDate() == null) {
			invitation.setCreatedDate(new Date());
		}

		invitation.setUpdatedDate(new Date());
	}

	// ----------------------------------------------------------------------

	@Override
	public INVITATION fetchByInvitationCode(String invitationCode) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("invitationCode", invitationCode);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------
	
	@Override
	public List<INVITATION> fetchByUserId(Long userId, KInvitationStatus status, 
			KInvitationType type, KInvitationChannel channel) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		
		if (status != null) {
			filter.put("statusId", status.getId());
		}

		if (type != null) {
			filter.put("typeId", type.getId());
		}

		if (channel != null) {
			filter.put("channelId", channel.getId());
		}
		
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
    // ----------------------------------------------------------------------
	
	@Override
	public List<INVITATION> fetchByAddressBookId(Long addressBookId, KInvitationStatus status, 
			KInvitationType type, KInvitationChannel channel) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("addressBookId", addressBookId);
		
        if (status != null) {
        	filter.put("statusId", status.getId());
        }
        
        if (type != null) {
        	filter.put("typeId", type.getId());
        }
        
        if (channel != null) {
        	filter.put("channelId", channel.getId());
        }
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    
    // ----------------------------------------------------------------------
	
	@Override
	public List<INVITATION> fetchByEmail(String email, KInvitationStatus status, 
			KInvitationType type, KInvitationChannel channel) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);
		
        if (status != null) {
        	filter.put("statusId", status.getId());
        }
        
        if (type != null) {
        	filter.put("typeId", type.getId());
        }
        
        if (channel != null) {
        	filter.put("channelId", channel.getId());
        }
        
        String[] sortOrder = {"invited_date DESC"};
        
		return fetchByCriteria(0, 99999, sortOrder, filter, false);
	}
    
    // ----------------------------------------------------------------------
	
	@Override
	public List<INVITATION> fetchByMobileNumber(String mobileNumber, KInvitationStatus status, 
			KInvitationType type, KInvitationChannel channel) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
		
        if (status != null) {
        	filter.put("statusId", status.getId());
        }
        
        if (type != null) {
        	filter.put("typeId", type.getId());
        }
        
        if (channel != null) {
        	filter.put("channelId", channel.getId());
        }
        
        String[] sortOrder = {"invited_date DESC"};
        
		return fetchByCriteria(0, 99999, sortOrder, filter, false);
	}
    
    // ----------------------------------------------------------------------

    private Long getInviteeUserId(ADDRESSBOOK addressBook) {
        Long userId = addressBook.getRefUserId();
        
        if (userId == null) {
            USER user = null;
            
            if (addressBook.getMobileNumber() != null) {
            	user = getUserService().fetchByMobileNumber(addressBook.getMobileNumber());
            }
            
            if (user == null && addressBook.getEmail() != null) {
            	user = getUserService().fetchByEmail(addressBook.getEmail());
            }
            
            if (user != null) {
            	userId = user.getId();
                addressBook.setRefUserId(userId);
                getAddressBookService().update(addressBook);
            }
        }
        
        return userId;
    }
    
    // ----------------------------------------------------------------------
    
	@Override 
	public INVITATION inviteByMobileNumber(Long userId, KInvitationType type, String mobileNumber, String firstName, boolean resend) {
		ADDRESSBOOK addressBook = null;
		
		List<ADDRESSBOOK> list = getAddressBookService().fetchByMobileNumber(userId, mobileNumber);
		
		if (list == null || list.size() == 0)  {
			addressBook = getAddressBookService().create(userId, mobileNumber, null, firstName, null);
		} else {
			addressBook = list.get(0);
		}
		
		return invite(addressBook.getId(), type, KInvitationChannel.SMS, resend);
	}
	
    // ----------------------------------------------------------------------
	    
	@Override 
	public INVITATION inviteByEmail(Long userId, KInvitationType type, String email, String firstName, boolean resend) {
		ADDRESSBOOK addressBook = null;
		
		List<ADDRESSBOOK> list = getAddressBookService().fetchByEmail(userId, email);
		
		if (list == null || list.size() == 0)  {
			addressBook = getAddressBookService().create(userId, null, email, firstName, null);
		} else {
			addressBook = list.get(0);
		}
		
		return invite(addressBook.getId(), type, KInvitationChannel.EMAIL, resend);
	}
	

	
    // ----------------------------------------------------------------------
	
	protected boolean existingUser(ADDRESSBOOK addressBook) {
		USER user = null;
		
		if (addressBook.getRefUserId() != null) {
			user = getUserService().fetchById(addressBook.getRefUserId());
			if (user != null) {
				return true;
			} else {
				addressBook.setRefUserId(null);
				addressBook = getAddressBookService().update(addressBook);
			}
		}
		
		if (addressBook.getMobileNumber() != null) {
			user = getUserService().fetchByMobileNumber(addressBook.getMobileNumber());
			if (user != null) {
				addressBook.setRefUserId(user.getId());
				addressBook = getAddressBookService().update(addressBook);
				return true;
			}
		}
		
		if (addressBook.getEmail() != null) {
			user = getUserService().fetchByEmail(addressBook.getEmail());
			if (user != null) {
				addressBook.setRefUserId(user.getId());
				addressBook = getAddressBookService().update(addressBook);
				return true;
			}
		}
		
		return false;
	}
	
    // ----------------------------------------------------------------------
	
	//FIXME: invitations can be made to current users who are not necessarily in the user's address book
    
	@Override 
	public INVITATION invite(Long addressBookId, KInvitationType type, KInvitationChannel channel, boolean resend) {
		
		ADDRESSBOOK addressBook = getAddressBookService().fetchById(addressBookId);
		
		if (existingUser(addressBook) && (type == KInvitationType.JOIN || channel != KInvitationChannel.IN_APP)) {
			throw new KInvitationException("Invitation being sent to existing user."); 
		}
		
		// first check if an invitation has already been sent 
		INVITATION invitation = null;
        
		List<INVITATION> list = fetchByAddressBookId(addressBook.getId(), null, type, null);
		
		for (INVITATION pastInvitation : list) {
			KInvitationStatus status = KInvitationStatus.getInstance(pastInvitation.getStatusId());
			if (status != KInvitationStatus.PENDING) {
				// we already sent an inviation to this person that was already accepted, ignored, etc.
				// only resend pending invitations
				logger.info("Invitation has already been sent to user and acknowledged: {}", pastInvitation);
				return pastInvitation;
			}
			
			// ok we have a pending invitation
			if (!resend) {
				logger.info("Found past invitation and resend set to false. Returning current invitation: {}", pastInvitation);
				return pastInvitation;
			} else {
				// if the pastInvitation is not on the same channel then we create a new invitation
				if (pastInvitation.getChannelId().equals(channel.getId())) {
					invitation = pastInvitation;
					break;
				}
			}
		}
		
		try {
            if (invitation == null) {
            	invitation = createInvitation(addressBook, type, channel);
            }
            
            String invitationUrl = getInvitationUrl(invitation.getUserId(), invitation.getInvitationCode());
            
            invitation = sendInvitation(invitation, addressBook, invitationUrl);
            
            Date now = new Date();
            int invitedCount = invitation.getInvitedCount();
            invitedCount += 1;
			invitation.setInvitedCount(invitedCount);
			invitation.setInvitedDate(now);
            update(invitation);
            
            addressBook.setInvitedDate(now);
            getAddressBookService().update(addressBook);
            
            return invitation;
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
		}
	}
    
    // ----------------------------------------------------------------------
	
	protected INVITATION createInvitation(ADDRESSBOOK addressBook, KInvitationType type, KInvitationChannel channel) {
        
		//String code = sequence.getHexNo("confirmation.code", 9);
		String code = generateAccessCode();
		
        Long inviteeUserId = getInviteeUserId(addressBook);
        
        INVITATION invitation = getNewObject();
        invitation.setTypeId(type.getId());
        invitation.setChannelId(channel.getId());
        invitation.setStatusId(KInvitationStatus.PENDING.getId());
        invitation.setUserId(addressBook.getUserId());
		invitation.setAddressBookId(addressBook.getId());
        invitation.setInviteeUserId(inviteeUserId);
		invitation.setInvitationCode(code);
		invitation.setFirstName(addressBook.getFirstName());
		invitation.setLastName(addressBook.getLastName());
		invitation.setDisplayName(addressBook.getDisplayName());
        invitation.setEmail(addressBook.getEmail());
        invitation.setMobileNumber(addressBook.getMobileNumber());
        invitation.setInvitedCount(0);
		invitation.setCreatedDate(new Date());
        
		return add(invitation);
	}
    
    // ----------------------------------------------------------------------
    
	@Override
	public  INVITATION accessInvitationCode(String invitationCode) {
        if (invitationCode == null) return null;
        
        INVITATION invitation = fetchByInvitationCode(invitationCode);
        
        if (invitation == null) return null;
        
        ADDRESSBOOK addressBook = getAddressBookService().fetchById(invitation.getAddressBookId());
        
        KInvitationChannel channel = KInvitationChannel.getInstance(invitation.getChannelId());
        
        switch (channel) {
        case  EMAIL:
            if (addressBook != null) {
            	addressBook.setEmailVerified(true);
            	getAddressBookService().update(addressBook);
            }
            break;
        	
        case  SMS:
            if (addressBook != null) {
            	addressBook.setMobileVerified(true);
            	getAddressBookService().update(addressBook);
            }
            break;
            
		default:
			break;
        }
        
        
        invitation.setViewedDate(new Date());
        
        return update(invitation);
	}
    
    // ----------------------------------------------------------------------
    
	@Override
	public  INVITATION accept(INVITATION invitation) {
		if (invitation.getInviteeUserId() == null) {
			throw new IllegalStateException("Cannot accept invitation if invitee userId is null.");
		}

		Long userId = invitation.getUserId();
		
		Long friendId = invitation.getInviteeUserId();

		invitation.setStatusId(KInvitationStatus.ACCEPTED.getId());
		
		invitation.setAcceptedDate(new Date());
		
		invitation = update(invitation);

		KInvitationType type = KInvitationType.getInstance(invitation.getTypeId());
		
		switch (type) {
		case FRIEND:
			// create friendship and notify user
			getFriendshipService().createFriendship(userId, friendId, null, true);
			break;
		default:
			
		}

		return invitation;
	}
	
	// ----------------------------------------------------------------------
	
    @Override
    public void processNewUserInvitations(Long userId) {
    	USER user = getUserService().fetchById(userId);
    	
        List<INVITATION> invitationList = null;
        
        if (user.getMobileNumber() != null) {
        	invitationList = fetchByMobileNumber(user.getMobileNumber(), 
        			KInvitationStatus.PENDING, null, KInvitationChannel.SMS);
        }
        
        if (invitationList == null && user.getEmail() != null) {
        	invitationList = fetchByEmail(user.getEmail(), 
        			KInvitationStatus.PENDING, null, KInvitationChannel.EMAIL);
        }
        
        if (invitationList == null) {
        	logger.info("No pending invitations matched user: {}", user);
        	return;
        }
        
        for (INVITATION invitation : invitationList) {
        	invitation.setInviteeUserId(user.getId());
        	invitation.setRegisteredDate(new Date());
        	accept(invitation);

        	if (invitation.getAddressBookId() != null) {
        		ADDRESSBOOK ab = getAddressBookService().fetchById(invitation.getAddressBookId());
        		ab.setRefUserId(user.getId());
        		ab.setRegisteredDate(new Date());
        		getAddressBookService().update(ab);
        	}
        }
    }
}
