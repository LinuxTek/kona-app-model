package com.linuxtek.kona.app.comm.service;

import java.io.Serializable;

/**
 * KQueueException.
 */
public class KQueueException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

    public KQueueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KQueueException(final String message) {
        super(message);
    }

    public KQueueException(final Throwable cause) {
        super(cause);
    }
}
