/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Map;
import java.util.LinkedHashMap;

import java.io.Serializable;
import com.linuxtek.kona.app.entity.KUser;

/**
 * KUserException.
 */

@SuppressWarnings("serial")
public class KUserException extends RuntimeException 
        implements Serializable {

    private KUser user = null;

    private Map<String,String> errorMap = 
            new LinkedHashMap<String,String>();

    public KUserException() {
    }

    public KUserException(String message) {
        super(message);
        addError("_SYSTEM", message);
    }

    public KUserException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public KUserException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public KUserException(Throwable cause) {
        super(cause);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }

    public void addError(String field, String error) {
        errorMap.put(field, error);
    }

    public void setUser(KUser user) {
        this.user = user;
    }

    public KUser getUser() {
        return user;
    }
}
