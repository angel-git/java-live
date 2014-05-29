package com.ags.jonlive.exception;

/**
 * @author Angel
 * @since 12/05/2014
 */
public class JOnLiveException extends Exception {

    public JOnLiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public JOnLiveException(String message) {
        super(message);
    }
}
