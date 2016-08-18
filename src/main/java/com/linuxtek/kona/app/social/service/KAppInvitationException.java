/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.io.Serializable;

/**
 * KAppInvitationException.
 */
public class KAppInvitationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KAppInvitationException(String message) {
        super(message);
    }


    public KAppInvitationException(String message, Throwable cause) {
        super(message, cause);
    }

    public KAppInvitationException(Throwable cause) {
        super(cause);
    }

}
