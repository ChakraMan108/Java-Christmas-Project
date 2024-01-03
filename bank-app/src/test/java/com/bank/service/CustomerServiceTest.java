package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.entity.Customer;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository repository;

    @Mock
    private OperationService opService;

    @Mock
    private BankAccountService baService;

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
    }

    @Test
    public void testCount() throws ServiceException, RepositoryException {
        Mockito.when(repository.count()).thenReturn(1L);
        assertEquals(1L, customerService.count());
    }

    @Test
    public void testFindAll() throws ServiceException, RepositoryException {
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(customerService.findAll());
    }

    @Test
    public void testFindById() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        assertNotNull(customerService.findById(1L));
        assertEquals(customer, customerService.findById(1L));
    }

    @Test
    public void testSave() throws ServiceException, RepositoryException {
        Mockito.when(repository.save(customer)).thenReturn(customer);
        assertNotNull(customerService.save(customer));
        assertEquals(customer, customerService.save(customer));
    }

    @Test
    public void testUpdate() throws ServiceException, RepositoryException {
        Mockito.when(repository.save(customer)).thenReturn(customer);
        assertNotNull(customerService.update(customer));
        assertEquals(customer, customerService.update(customer));
    }

    @Test
    public void testDeactivateCustomer() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        customerService.deactivateCustomer(1L);
        assertFalse(customer.isActive());
    }

    @Test
    public void testCreateCustomer() throws ServiceException, RepositoryException {
        Mockito.when(repository.save(customer)).thenReturn(customer);
        assertNotNull(customerService.createCustomer(customer));
        assertEquals(customer, customerService.createCustomer(customer));
    }

    @Test
    public void testSaveJson() throws ServiceException, RepositoryException {
        try {
            Mockito.doNothing().when(repository).saveJson();
            customerService.saveJson();
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }
}