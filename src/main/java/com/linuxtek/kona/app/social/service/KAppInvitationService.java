/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.List;

import com.linuxtek.kona.app.social.entity.KAppInvitation;
import com.linuxtek.kona.app.social.entity.KAppInvitationChannel;
import com.linuxtek.kona.app.social.entity.KAppInvitationStatus;
import com.linuxtek.kona.app.social.entity.KAppInvitationType;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KAppInvitationService<INVITATION extends KAppInvitation> extends KService, KDataService<INVITATION> {
    public static final String SERVICE_PATH = "rpc/kona/InvitationService";

    public INVITATION invite(Long addressBookId, KAppInvitationType type, KAppInvitationChannel channel, boolean resend);
    
    public INVITATION inviteByMobileNumber(Long userId, KAppInvitationType type, String mobileNumber, String firstName, boolean resend);
    
    public INVITATION inviteByEmail(Long userId, KAppInvitationType type, String email, String firstName, boolean resend);
    
    public INVITATION accept(INVITATION invitation);
    
    public INVITATION fetchByInvitationCode(String invitationCode);
    
    public INVITATION accessInvitationCode(String invitationCode);
    
    // process a user's invitations after he/she has registered
    // this method is called by UserService()
	public void processNewUserInvitations(Long userId);

    
    public List<INVITATION> fetchByUserId(Long userId, KAppInvitationStatus status, 
    		KAppInvitationType type, KAppInvitationChannel channel);
    
    public List<INVITATION> fetchByAddressBookId(Long addressBookId, KAppInvitationStatus status, 
    		KAppInvitationType type, KAppInvitationChannel channel);
    
    public List<INVITATION> fetchByEmail(String email, KAppInvitationStatus status, 
    		KAppInvitationType type, KAppInvitationChannel channel);
    
    public List<INVITATION> fetchByMobileNumber(String mobileNumber, KAppInvitationStatus status, 
    		KAppInvitationType type, KAppInvitationChannel channel);
}
