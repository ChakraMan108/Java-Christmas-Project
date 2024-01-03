package com.bank.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CustomerExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test message";
        CustomerException exception = new CustomerException(message);
        assertEquals(message, exception.getMessage());
        assertNotNull(exception.getTimestamp());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        CustomerException exception = new CustomerException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNotNull(exception.getTimestamp());
    }

    @Test
    public void testGetTimestamp() {
        CustomerException exception = new CustomerException("Test message");
        Date now = new Date();
        assertTrue(exception.getTimestamp().before(now) || exception.getTimestamp().equals(now));
    }
}
