package com.bank.exceptions;

public class UIException extends Exception {

    public UIException(String message) {
            super(message);
        }

    public UIException(String message, Throwable cause) {
            super(message, cause);
    }
}

