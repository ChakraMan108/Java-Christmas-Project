package com.bank.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class OperationTest {

    @Test
    public void testGettersAndSetters() {
        Operation operation = new Operation(Operation.OperationType.CUSTOMER_CREATION, "john_doe", 123, 456);

        operation.setId(789);
        assertEquals(789, operation.getId());

        operation.setOperationtype(Operation.OperationType.CUSTOMER_DEACTIVATION);
        assertEquals(Operation.OperationType.CUSTOMER_DEACTIVATION, operation.getOperationtype());

        operation.setUsername("new_username");
        assertEquals("new_username", operation.getUsername());

        operation.setAccountId(987);
        assertEquals(987, operation.getAccountId());

        operation.setCustomerId(654);
        assertEquals(654, operation.getCustomerId());

        LocalDate currentDate = LocalDate.now();
        operation.setDate(currentDate);
        assertEquals(currentDate, operation.getDate());
    }

    @Test
    public void testToString() {
        Operation operation = new Operation(Operation.OperationType.ACCOUNT_CREATION, "jane_doe", 789, 987);
        String expectedToString = "Operation [id=0, operationtype=ACCOUNT_CREATION, username=jane_doe, accountId=789, customerId=987, date=null]";
        assertEquals(expectedToString, operation.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "john_doe", 123, 456);
        Operation operation2 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "john_doe", 123, 456);
        Operation operation3 = new Operation(Operation.OperationType.CUSTOMER_DEACTIVATION, "jane_doe", 789, 987);

        assertTrue(operation1.equals(operation2));
        assertFalse(operation1.equals(operation3));
        assertTrue(operation1.hashCode() == operation2.hashCode());
        assertFalse(operation1.hashCode() == operation3.hashCode());
    }
}


