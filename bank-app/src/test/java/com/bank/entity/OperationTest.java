package com.bank.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class OperationTest {

    @Test
    public void testGettersAndSetters() {
        Operation operation = new Operation();
        operation.setId(1);
        operation.setOperationtype(Operation.OperationType.CUSTOMER_CREATION);
        operation.setUsername("Test User");
        operation.setAccountId(1);
        operation.setCustomerId(1);
        operation.setDate(LocalDate.of(2022, 1, 1));

        assertEquals(1, operation.getId());
        assertEquals(Operation.OperationType.CUSTOMER_CREATION, operation.getOperationtype());
        assertEquals("Test User", operation.getUsername());
        assertEquals(1, operation.getAccountId());
        assertEquals(1, operation.getCustomerId());
        assertEquals(LocalDate.of(2022, 1, 1), operation.getDate());
    }

    @Test
    public void testEquals() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        Operation operation2 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);

        assertTrue(operation1.equals(operation2));
    }

    @Test
    public void testHashCode() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        Operation operation2 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);

        assertEquals(operation1.hashCode(), operation2.hashCode());
    }

    @Test
    public void testToString() {
        Operation operation = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        String expected = "Operation [id=0, operationtype=CUSTOMER_CREATION, username=Test User, accountId=1, customerId=1, date=null]";

        assertEquals(expected, operation.toString());
    }
}