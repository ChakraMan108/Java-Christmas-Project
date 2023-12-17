package com.bank.exceptions;

import java.util.Date;

public class CustomerException extends RuntimeException 
{
    private Date timestamp = new Date();
    
    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
