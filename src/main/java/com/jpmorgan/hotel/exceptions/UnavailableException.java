package com.jpmorgan.hotel.exceptions;

public class UnavailableException extends Exception {

    private static final long serialVersionUID = 7060336684846833717L;

    // Could add good stuff in here about next available date or something

    public UnavailableException(String message, Throwable cause) {
	super(message, cause);
    }

    public UnavailableException(String message) {
	super(message);
    }

}
