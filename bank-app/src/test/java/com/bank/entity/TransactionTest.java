package com.bank.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void testGettersAndSetters() {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmount(1000);
        transaction.setCreatedDate(LocalDateTime.of(2022, 1, 1, 1, 1));
        transaction.setUsername("Test User");
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transaction.setAccountId(1);

        assertEquals(1, transaction.getId());
        assertEquals(1000, transaction.getAmount());
        assertEquals(LocalDateTime.of(2022, 1, 1,1,1), transaction.getCreatedDate());
        assertEquals("Test User", transaction.getUsername());
        assertEquals(Transaction.TransactionType.DEPOSIT, transaction.getType());
        assertEquals(1, transaction.getAccountId());
    }

    @Test
    public void testEquals() {
        Transaction transaction1 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        Transaction transaction2 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);

        assertTrue(transaction1.equals(transaction2));
    }

    @Test
    public void testHashCode() {
        Transaction transaction1 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        Transaction transaction2 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);

        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    public void testDefaultConstructor() {
        Transaction transaction = new Transaction();
        assertEquals(0, transaction.getId());
        assertEquals(0, transaction.getAmount());
        assertNull(transaction.getCreatedDate());
        assertNull(transaction.getUsername());
        assertNull(transaction.getType());
        assertEquals(0, transaction.getAccountId());
    }
    
    @Test
    public void testParameterizedConstructor() {
        Transaction transaction = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        assertEquals(1000, transaction.getAmount());
        assertEquals("Test User", transaction.getUsername());
        assertEquals(Transaction.TransactionType.DEPOSIT, transaction.getType());
        assertEquals(1, transaction.getAccountId());
    }
    
    @Test
    public void testEquals_sameObject() {
        Transaction transaction = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        assertTrue(transaction.equals(transaction));
    }
    
    @Test
    public void testEquals_differentObjectSameValues() {
        Transaction transaction1 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        Transaction transaction2 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        assertTrue(transaction1.equals(transaction2));
    }
    
    @Test
    public void testEquals_differentObjectDifferentValues() {
        Transaction transaction1 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        Transaction transaction2 = new Transaction(2000, "Different User", Transaction.TransactionType.WITHDRAWAL, 2);
        assertFalse(transaction1.equals(transaction2));
    }
    
    @Test
    public void testHashCode_sameValues() {
        Transaction transaction1 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        Transaction transaction2 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }
    
    @Test
    public void testHashCode_differentValues() {
        Transaction transaction1 = new Transaction(1000, "Test User", Transaction.TransactionType.DEPOSIT, 1);
        Transaction transaction2 = new Transaction(2000, "Different User", Transaction.TransactionType.WITHDRAWAL, 2);
        assertNotEquals(transaction1.hashCode(), transaction2.hashCode());
    }
    
    @Test
    public void testToString_withDate() {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmount(1000);
        transaction.setCreatedDate(LocalDateTime.of(2022, 1, 1, 1, 1));
        transaction.setUsername("Test User");
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transaction.setAccountId(1);
    
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 1\nAmount: 10.00\nUsername: Test User\nTransaction Type: DEPOSIT\nAccount ID: 1\nDate: 2022-01-01 01:01\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String actual = transaction.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToString_withoutDate() {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmount(1000);
        transaction.setCreatedDate(null);
        transaction.setUsername("Test User");
        transaction.setType(Transaction.TransactionType.DEPOSIT);
        transaction.setAccountId(1);
    
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 1\nAmount: 10.00\nUsername: Test User\nTransaction Type: DEPOSIT\nAccount ID: 1\nDate: N/A\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String actual = transaction.toString();
        assertEquals(expected, actual);
    }
}