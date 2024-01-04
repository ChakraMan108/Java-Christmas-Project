package com.bank.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RepositoryExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Test message";
        RepositoryException exception = new RepositoryException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Test cause");
        RepositoryException exception = new RepositoryException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}