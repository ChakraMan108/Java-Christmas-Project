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
    public void testDefaultConstructor() {
        Operation operation = new Operation();
        assertNull(operation.getOperationtype());
        assertNull(operation.getUsername());
        assertEquals(0, operation.getAccountId());
        assertEquals(0, operation.getCustomerId());
        assertNull(operation.getDate());
    }
    
    @Test
    public void testParameterizedConstructor() {
        Operation operation = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        assertEquals(Operation.OperationType.CUSTOMER_CREATION, operation.getOperationtype());
        assertEquals("Test User", operation.getUsername());
        assertEquals(1, operation.getAccountId());
        assertEquals(1, operation.getCustomerId());
        assertNull(operation.getDate());
    }
    
    @Test
    public void testEquals_sameObject() {
        Operation operation = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        assertTrue(operation.equals(operation));
    }
    
    @Test
    public void testEquals_differentObjectSameValues() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        Operation operation2 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        assertTrue(operation1.equals(operation2));
    }
    
    @Test
    public void testEquals_differentObjectDifferentValues() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        Operation operation2 = new Operation(Operation.OperationType.ACCOUNT_CREATION, "Different User", 2, 2);
        assertFalse(operation1.equals(operation2));
    }
    
    @Test
    public void testHashCode_sameValues() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        Operation operation2 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        assertEquals(operation1.hashCode(), operation2.hashCode());
    }
    
    @Test
    public void testHashCode_differentValues() {
        Operation operation1 = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        Operation operation2 = new Operation(Operation.OperationType.ACCOUNT_CREATION, "Different User", 2, 2);
        assertNotEquals(operation1.hashCode(), operation2.hashCode());
    }
    
    @Test
    public void testToString() {
        Operation operation = new Operation(Operation.OperationType.CUSTOMER_CREATION, "Test User", 1, 1);
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 0\nOperation Type: CUSTOMER_CREATION\nUsername: Test User\nAccount ID: 1\nCustomer ID: 1\nDate: null\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        assertEquals(expected, operation.toString());
    }
}