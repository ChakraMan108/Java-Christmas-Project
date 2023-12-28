package com.bank.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
        customer.setCreatedDate(LocalDate.of(2022, 1, 1));
        customer.setDeactivatedDate(LocalDate.of(2022, 1, 2));
        customer.setType(Customer.CustomerType.INDIVIDUAL);

        assertEquals(1, customer.getId());
        assertEquals("Test", customer.getName());
        assertEquals("Test Address", customer.getAddress());
        assertEquals(LocalDate.of(2000, 1, 1), customer.getDob());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("test@test.com", customer.getEmail());
        assertTrue(customer.isActive());
        assertEquals(LocalDate.of(2022, 1, 1), customer.getCreatedDate());
        assertEquals(LocalDate.of(2022, 1, 2), customer.getDeactivatedDate());
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
    public void testToString() {
        Customer customer = new Customer("Test", "Test Address", LocalDate.of(2000, 1, 1), "1234567890", "test@test.com", Customer.CustomerType.INDIVIDUAL);
        String expected = "Customer [id=0, name=Test, address=Test Address, dob=2000-01-01, phoneNumber=1234567890, email=test@test.com, isActive=false, createdDate=null, deactivatedDate=null, type=INDIVIDUAL]";
        
        assertEquals(expected, customer.toString());
    }
}