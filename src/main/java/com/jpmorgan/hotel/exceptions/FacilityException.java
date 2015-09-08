package com.jpmorgan.hotel.exceptions;

public class FacilityException extends Exception {

    private static final long serialVersionUID = 7060336684846833717L;

    public FacilityException(String message, Throwable cause) {
	super(message, cause);
    }

    public FacilityException(String message) {
	super(message);
    }

}
