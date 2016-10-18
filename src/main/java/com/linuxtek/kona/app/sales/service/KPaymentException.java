package com.linuxtek.kona.app.sales.service;

import java.io.Serializable;

/**
 * KPaymentException.
 */
public class KPaymentException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public KPaymentException(String message) {
        super(message);
    }


    public KPaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public KPaymentException(Throwable cause) {
        super(cause);
    }

}
