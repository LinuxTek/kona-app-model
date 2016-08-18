/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.Map;
import java.util.LinkedHashMap;

import java.io.Serializable;

/**
 * KValidationException.
 */

@SuppressWarnings("serial")
public class KValidationException extends RuntimeException 
        implements Serializable {

    private Map<String,String> errorMap = 
            new LinkedHashMap<String,String>();

    public KValidationException() {
    }

    public KValidationException(String message) {
        super(message);
        addError("_SYSTEM", message);
    }

    public KValidationException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public KValidationException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public KValidationException(Throwable cause) {
        super(cause);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }

    public void addError(String field, String error) {
        errorMap.put(field, error);
    }
}
