/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;

import com.linuxtek.kona.app.entity.KInvitation;
import com.linuxtek.kona.app.entity.KInvitationChannel;
import com.linuxtek.kona.app.entity.KInvitationStatus;
import com.linuxtek.kona.app.entity.KInvitationType;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KInvitationService<INVITATION extends KInvitation> extends KService, KDataService<INVITATION> {
    public static final String SERVICE_PATH = "rpc/kona/InvitationService";

    public INVITATION invite(Long addressBookId, KInvitationType type,
    		KInvitationChannel channel, String message, boolean resend);
    
    public INVITATION accept(INVITATION invitation);
    
    public INVITATION fetchByInvitationCode(String invitationCode);
    
    public INVITATION accessInvitationCode(String invitationCode);
    
    // process a user's invitations after he/she has registered
    // this method is called by UserService()
	public void processInvitations(Long userId);

    
    public List<INVITATION> fetchByUserId(Long userId, KInvitationStatus status, 
    		KInvitationType type, KInvitationChannel channel);
    
    public List<INVITATION> fetchByAddressBookId(Long addressBookId, KInvitationStatus status, 
    		KInvitationType type, KInvitationChannel channel);
    
    public List<INVITATION> fetchByEmail(String email, KInvitationStatus status, 
    		KInvitationType type, KInvitationChannel channel);
    
    public List<INVITATION> fetchByMobileNumber(String mobileNumber, KInvitationStatus status, 
    		KInvitationType type, KInvitationChannel channel);
}
