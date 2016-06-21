/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.Serializable;

/**
 * KSmsException.
 */

@SuppressWarnings("serial")
public class KSmsException extends RuntimeException 
        implements Serializable {

    public KSmsException(String message) {
        super(message);
    }


    public KSmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KSmsException(Throwable cause) {
        super(cause);
    }

}
