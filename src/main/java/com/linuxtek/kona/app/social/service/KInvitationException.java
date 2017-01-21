/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.io.Serializable;

/**
 * KInvitationException.
 */
public class KInvitationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KInvitationException(String message) {
        super(message);
    }


    public KInvitationException(String message, Throwable cause) {
        super(message, cause);
    }

    public KInvitationException(Throwable cause) {
        super(cause);
    }

}
