/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.io.Serializable;

public class KEmailException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public KEmailException(String message) {
		super(message);
	}

	public KEmailException(String message, Throwable t) {
		super(message, t);
	}

	public KEmailException(Throwable t) {
		super(t);
	}
}
