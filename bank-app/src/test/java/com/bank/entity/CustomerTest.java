package com.bank.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");
        customer.setAddress("Test Address");
        customer.setDob(LocalDate.of(2000, 1, 1));
        customer.setPhoneNumber("1234567890");
        customer.setEmail("test@test.com");
        customer.setActive(true);
        customer.setCreatedDate(LocalDateTime.of(2022, 1, 1,1,1));
        customer.setDeactivatedDate(LocalDateTime.of(2022, 1, 2,1 ,1));
        customer.setType(Customer.CustomerType.INDIVIDUAL);

        assertEquals(1, customer.getId());
        assertEquals("Test", customer.getName());
        assertEquals("Test Address", customer.getAddress());
        assertEquals(LocalDate.of(2000, 1, 1), customer.getDob());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("test@test.com", customer.getEmail());
        assertTrue(customer.isActive());
        assertEquals(LocalDateTime.of(2022, 1, 1,1,1), customer.getCreatedDate());
        assertEquals(LocalDateTime.of(2022, 1, 2,1,1), customer.getDeactivatedDate());
        assertEquals(Customer.CustomerType.INDIVIDUAL, customer.getType());
    }

    @Test
    public void testEquals() {
        Customer customer1 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        Customer customer2 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);

        assertTrue(customer1.equals(customer2));
    }

    @Test
    public void testHashCode() {
        Customer customer1 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        Customer customer2 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);

        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();
        assertNull(customer.getName());
        assertNull(customer.getAddress());
        assertNull(customer.getDob());
        assertNull(customer.getPhoneNumber());
        assertNull(customer.getEmail());
        assertNull(customer.getType());
        assertFalse(customer.isActive());
    }
    
    @Test
    public void testParameterizedConstructor() {
        Customer customer = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        assertEquals("Test", customer.getName());
        assertEquals("Test Address", customer.getAddress());
        assertEquals(LocalDate.of(2000, 1, 1), customer.getDob());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("test@test.com", customer.getEmail());
        assertEquals(Customer.CustomerType.INDIVIDUAL, customer.getType());
        assertFalse(customer.isActive());
    }
    
    @Test
    public void testEquals_sameObject() {
        Customer customer = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        assertTrue(customer.equals(customer));
    }
    
    @Test
    public void testEquals_differentObjectSameValues() {
        Customer customer1 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        Customer customer2 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        assertTrue(customer1.equals(customer2));
    }
    
    @Test
    public void testEquals_differentObjectDifferentValues() {
        Customer customer1 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        Customer customer2 = new Customer("Different", "Different Address", LocalDate.of(2001, 1, 1), "0987654321", "different@test.com", Customer.CustomerType.COMPANY);
        assertFalse(customer1.equals(customer2));
    }
    
    @Test
    public void testHashCode_sameValues() {
        Customer customer1 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        Customer customer2 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }
    
    @Test
    public void testHashCode_differentValues() {
        Customer customer1 = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        Customer customer2 = new Customer("Different", "Different Address", LocalDate.of(2001, 1, 1), "0987654321", "different@test.com", Customer.CustomerType.COMPANY);
        assertNotEquals(customer1.hashCode(), customer2.hashCode());
    }
    
    @Test
    public void testToString_withDates() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");
        customer.setAddress("Test Address");
        customer.setDob(LocalDate.of(2000, 1, 1));
        customer.setPhoneNumber("1234567890");
        customer.setEmail("test@test.com");
        customer.setActive(true);
        customer.setCreatedDate(LocalDateTime.of(2022, 1, 1,1,1));
        customer.setDeactivatedDate(LocalDateTime.of(2022, 1, 2,1,1));
        customer.setType(Customer.CustomerType.INDIVIDUAL);
    
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 1\nName: Test\nAddress: Test Address\nDob: 2000-01-01\nPhone Number: 1234567890\nEmail: test@test.com\nActive: true\nCreated Date: 2022-01-01 01:01\nDactivated Date: 2022-01-02 01:01\nCustomer Type: INDIVIDUAL\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String actual = customer.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToString_withoutDates() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");
        customer.setAddress("Test Address");
        customer.setDob(LocalDate.of(2000, 1, 1));
        customer.setPhoneNumber("1234567890");
        customer.setEmail("test@test.com");
        customer.setActive(true);
        customer.setCreatedDate(null);
        customer.setDeactivatedDate(null);
        customer.setType(Customer.CustomerType.INDIVIDUAL);
    
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 1\nName: Test\nAddress: Test Address\nDob: 2000-01-01\nPhone Number: 1234567890\nEmail: test@test.com\nActive: true\nCreated Date: N/A\nDactivated Date: N/A\nCustomer Type: INDIVIDUAL\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String actual = customer.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testCopyConstructor_allFieldsSet() {
        Customer original = new Customer();
        original.setId(1);
        original.setName("Test");
        original.setAddress("Test Address");
        original.setDob(LocalDate.of(2000, 1, 1));
        original.setPhoneNumber("1234567890");
        original.setEmail("test@test.com");
        original.setActive(true);
        original.setCreatedDate(LocalDateTime.of(2022, 1, 1,1,1));
        original.setDeactivatedDate(LocalDateTime.of(2022, 1, 2,1 ,1));
        original.setType(Customer.CustomerType.INDIVIDUAL);
    
        Customer copy = new Customer(original);
    
        assertEquals(original.getId(), copy.getId());
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getAddress(), copy.getAddress());
        assertEquals(original.getDob(), copy.getDob());
        assertEquals(original.getPhoneNumber(), copy.getPhoneNumber());
        assertEquals(original.getEmail(), copy.getEmail());
        assertEquals(original.isActive(), copy.isActive());
        assertEquals(original.getCreatedDate(), copy.getCreatedDate());
        assertEquals(original.getDeactivatedDate(), copy.getDeactivatedDate());
        assertEquals(original.getType(), copy.getType());
    }
    
    @Test
    public void testCopyConstructor_noFieldsSet() {
        Customer original = new Customer();
        Customer copy = new Customer(original);
    
        assertEquals(original.getId(), copy.getId());
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getAddress(), copy.getAddress());
        assertEquals(original.getDob(), copy.getDob());
        assertEquals(original.getPhoneNumber(), copy.getPhoneNumber());
        assertEquals(original.getEmail(), copy.getEmail());
        assertEquals(original.isActive(), copy.isActive());
        assertEquals(original.getCreatedDate(), copy.getCreatedDate());
        assertEquals(original.getDeactivatedDate(), copy.getDeactivatedDate());
        assertEquals(original.getType(), copy.getType());
    }

}