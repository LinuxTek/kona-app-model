/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.Serializable;

/**
 * KAuthException.
 */

@SuppressWarnings("serial")
public class KAuthException extends RuntimeException 
    implements Serializable {

    public enum Type implements Serializable {
        LOGIN_REQUIRED,
        INVALID_TOKEN,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        MAX_LOGIN_ATTEMPTS,
        NOT_AUTHORIZED
    };

    private Type type = null;

    public KAuthException() {
    }

    public KAuthException(String message) {
        super(message);
    }

    public KAuthException(String message, Type type) {
        super(message);
        this.type = type;
    }

    public KAuthException(String ex, Type type, Throwable cause) {
        super(ex, cause);
        this.type = type;
    }

    public KAuthException(Type type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    public Type getType() {
        return (type);
    }

    public String toString() {
        return ("KAuthException TYPE: " + type + "\n" + super.toString());
    }
}
