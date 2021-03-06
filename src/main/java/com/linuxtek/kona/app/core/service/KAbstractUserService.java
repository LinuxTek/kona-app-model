/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KAccount;
import com.linuxtek.kona.app.core.entity.KAppUser;
import com.linuxtek.kona.app.core.entity.KRegistration;
import com.linuxtek.kona.app.core.entity.KToken;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.entity.KUserAuth;
import com.linuxtek.kona.app.core.entity.KUserPresence;
import com.linuxtek.kona.app.core.entity.KUserRole;
import com.linuxtek.kona.app.core.entity.KUserStatus;
import com.linuxtek.kona.app.core.entity.KUserType;
import com.linuxtek.kona.app.social.entity.KInvitation;
import com.linuxtek.kona.app.social.service.KInvitationService;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.remote.service.KServiceClient;
import com.linuxtek.kona.util.KStringUtil;

public abstract class KAbstractUserService<U extends KUser, EXAMPLE, 
										   UA extends KUserAuth, 
										   A extends KAccount, 
										   AU extends KAppUser, 
										   R extends KRegistration, 
										   I extends KInvitation, 
										   T extends KToken> 
		extends KAbstractService<U,EXAMPLE>
		implements KUserService<U> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserService.class);

	// ----------------------------------------------------------------------------
	
	protected abstract U getNewObject();
	
	protected abstract <S extends KAccountService<A>> S getAccountService();
	
	protected abstract <S extends KUserAuthService<UA,U>> S getUserAuthService();
	
	protected abstract <S extends KAppUserService<AU>> S getAppUserService();
	
	protected abstract <S extends KRegistrationService<R,U>> S getRegistrationService();
	
	protected abstract <S extends KTokenService<T>> S getTokenService();
	
	protected abstract <S extends KInvitationService<I>> S getInvitationService();
    
	protected abstract void sendRegisteredUserEmail(Long appId, U user);
	
	protected abstract Long getDefaultAppId();
	
	// ----------------------------------------------------------------------------
	
	protected String generateUid() {
		return KUtil.uuid();
	}
	
	protected boolean autoUpdateDisplayName() {
		return true;
	}
	
	protected Long getDefaultTypeId() {
		return KUserType.USER.getId();
	}
	
	protected Long getDefaultPresenceId() {
		return KUserPresence.OFFLINE.getId();
	}
	
	protected Long getDefaultStatusId() {
		return KUserStatus.ENABLED.getId();
	}
	
	protected Long getDefaultRoles() {
		return KUserRole.USER.getId();
	}
	
	protected String getDefaultLocale() {
		return Locale.US.toLanguageTag();
	}
	
	protected String getDefaultTimeZone() {
		return TimeZone.getTimeZone("America/New_York").getID();
	}
	
	// ----------------------------------------------------------------------------
	
	protected void checkUserExists(String username, String email) {
		if (username == null) {
			throw new KAuthException("Username is null");
		}

		if (!isUsernameAvailable(username)) {
			throw new KAuthException("Username not available: " + username);
		}

		if (email != null) {
			U user = fetchByEmail(email);
			if (user != null) {
				throw new IllegalArgumentException("Email exists: " + email);
			}
		}
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U createGuestUser(KServiceClient client) {
		U user = getNewObject();
		user.setRoles(KUserRole.GUEST.getId());
		return registerUser(user, null, client);
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U registerUser(U user, String password, KServiceClient client) {
        
        if (user.getUid() == null) {
            user.setUid(generateUid());
        }

        if (user.getUsername() == null) {
            user.setUsername(user.getUid());
        }

        // Check if user is valid so far
        checkUserExists(user.getUsername(), user.getEmail());


        if (user.getTypeId() == null) {
            user.setTypeId(getDefaultTypeId());
        }

        if (user.getRoles() == null) {
            user.setRoles(getDefaultRoles());
        }
        
        if (user.getStatusId() == null) {
            user.setStatusId(getDefaultStatusId());
        }
        
        if (user.getPresenceId() == null) {
            user.setPresenceId(getDefaultPresenceId());
        }
        
		if (user.getTimeZone() == null) {
			user.setTimeZone(getDefaultTimeZone());
		}
		
		if (user.getLocale() == null) {
			user.setLocale(getDefaultLocale());
		}
        
        String displayName = KStringUtil.createFullName(user.getFirstName(), user.getLastName());
        if (displayName == null || displayName.length() == 0) {
            displayName = user.getUsername();
        }
        
        user.setDisplayName(displayName);
        
        user.setCreatedDate(new Date());
        
        user.setActive(true);
        

        
        A account = null;
        if (user.getAccountId() == null) {
            account = getAccountService().createAccount(null);
            user.setAccountId(account.getId());
        } else {
        	account = getAccountService().fetchById(user.getAccountId());
        }

        user = add(user);

        if (account.getOwnerId() == null) {
            account.setOwnerId(user.getId());
            getAccountService().update(account);
        }
        
    
        // update auth record
        if (password != null) {
        	getUserAuthService().setPlainPassword(user.getId(), password);
        }

        if (client == null) {
        	client = new KServiceClient();
        }

        if (client.getHostname() == null) {
            client.setHostname("127.0.0.1"); // hostname cannot be null in registration table
        }
        
        if (client.getAppId() == null) {
        	client.setAppId(getDefaultAppId());
        }
        
        // log registration record
        Integer signupTime = null;
        getRegistrationService().createRegistration(user, client, signupTime);
        
        // create AppUser record
        getAppUserService().create(client.getAppId(), user.getId(), null, null);
        
        // process invitations sent for this user
        getInvitationService().processNewUserInvitations(user.getId());

        sendRegisteredUserEmail(client.getAppId(), user);
        
        return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U retire(U user) {
        // fetch fresh object
        user = fetchById(user.getId());

        // return user if already retired
        if (user == null || user.getRetiredDate() != null) {
            return user;
        }

        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        user.setUsername(prefix + user.getUsername());
        user.setMobileNumber(prefix + user.getMobileNumber());
        user.setEmail(prefix + user.getEmail());
        user.setEnabled(false);
        user.setActive(false);
        user.setRetiredDate(new Date());
        user = update(user);


        // retiring the account may fail if there are other non-retired
        // users associated with the account.
        // Since account will check active users, it's imperative that
        // this object be updated first.  otherwise AccountService will
        // see this user as active and fail to retire the account.
        if (user.getParentId() == null) {
            A account = getAccountService().fetchById(user.getAccountId());
            try {
                getAccountService().retire(account);
            } catch (Throwable t) { }
        }

        return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U fetchByUsername(String username) {
        logger.debug("UserServiceImpl: fetchByUsername: username: {}", username);
        Map<String,Object> filter = KMyBatisUtil.createFilter("username", username);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U fetchByEmail(String email) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("email", email);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public U fetchByMobileNumber(String mobileNumber) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("mobileNumber", mobileNumber);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	// ----------------------------------------------------------------------------
	
	@Override
	public List<U> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public U fetchByAccessToken(String accessToken, boolean validateToken) {
        U user = null;
        
        T token = getTokenService().fetchByAccessToken(accessToken, validateToken);
        
        if (token != null) {
        	user = fetchById(token.getUserId());
        }
        
        return user;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U updatePrimaryPhoto(Long userId, Long photoId, String urlPath, String thumbnailUrlPath) {
	    // NOTE: photoId references UserMedia object
        U user = fetchById(userId);
        user.setPhotoId(photoId);
        user.setPhotoUrl(urlPath);
        user.setThumbnailUrl(thumbnailUrlPath);
        return update(user);
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void validate(U user) {
		if (user.getUid() == null) {
			user.setUid(generateUid());
		}

		if (user.getUsername() == null) {
			user.setUsername(user.getUid());
		}

		if (user.getTypeId() == null) {
			user.setTypeId(getDefaultTypeId());
		}

		if (user.getRoles() == null) {
			user.setRoles(getDefaultRoles());
		}

		if (user.getStatusId() == null) {
			user.setStatusId(getDefaultStatusId());
		}

		if (user.getPresenceId() == null) {
			user.setPresenceId(getDefaultPresenceId());
		}

		if (autoUpdateDisplayName()) {
			String displayName = KStringUtil.createFullName(user.getFirstName(), user.getLastName());
			if (displayName == null || displayName.length() == 0) {
				displayName = user.getUsername();
			}
			
			user.setDisplayName(displayName);
		}
		
		if (user.getTimeZone() == null) {
			user.setTimeZone(getDefaultTimeZone());
		}
		
		if (user.getLocale() == null) {
			user.setLocale(getDefaultLocale());
		}
		
		if (user.getCreatedDate() == null) {
			user.setCreatedDate(new Date());
		}

		user.setUpdatedDate(new Date());
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public boolean isEmailVerified(Long userId) {
		R registration = getRegistrationService().fetchByUserId(userId);
		if (registration == null) return false;
		return registration.isEmailVerified();
		
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public boolean isMobileNumberVerified(Long userId) {
		R registration = getRegistrationService().fetchByUserId(userId);
		if (registration == null) return false;
		return registration.isMobileVerified();
		
	}
    
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U setMobileNumberVerified(Long userId) {
		R registration = getRegistrationService().fetchByUserId(userId);
        
		if (registration == null) {
			throw new IllegalArgumentException("Registration not found for userId: " + userId);
		}
        
        
		registration.setMobileVerified(true);
        
		getRegistrationService().update(registration);
        
        return fetchById(userId);
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public U setEmailVerified(Long userId) {
		R registration = getRegistrationService().fetchByUserId(userId);
        
		if (registration == null) {
			throw new IllegalArgumentException("Registration not found for userId: " + userId);
		}
        
        
		registration.setEmailVerified(true);
        
		getRegistrationService().update(registration);
        
        return fetchById(userId);
	}
}
