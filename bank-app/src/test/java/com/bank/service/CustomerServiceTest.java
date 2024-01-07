package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Customer.CustomerType;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService = CustomerService.getInstance();

    @Mock
    private CustomerRepository repository = CustomerRepository.getInstance();

    @Mock
    private OperationService opService = OperationService.getInstance();

    @Mock
    private BankAccountService baService = BankAccountService.getInstance();

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
    public void testUpdateNullCustomer() {
        assertThrows(ServiceException.class, () -> customerService.update(null));
    }

    @Test
    public void testUpdateDeactivatedCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(false);
        assertThrows(ServiceException.class, () -> customerService.update(customer));
    }

    @Test
    public void testUpdateInvalidId() {
        Customer customer = new Customer();
        customer.setId(-1L);
        assertThrows(ServiceException.class, () -> customerService.update(customer));
    }

    @Test
    public void testUpdateCreatedDateInFuture() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCreatedDate(LocalDateTime.now().plusDays(1));
        assertThrows(ServiceException.class, () -> customerService.update(customer));
    }

    @Test
    public void testUpdateWithDeactivatedDate() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setDeactivatedDate(LocalDateTime.now());
        assertThrows(ServiceException.class, () -> customerService.update(customer));
    }

    @Test
    public void testUpdateSuccess() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setDob(LocalDate.now().minusYears(20));
        customer.setPhoneNumber("1234567890");
        customer.setEmail("johndoe@example.com");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setActive(true);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setCustomer(customer);
        Customer customer2 = new Customer(customer);
        customer2.setName("Jane Doe");
        Mockito.when(repository.findById(1L)).thenReturn(customer2);
        Mockito.when(repository.save(customer)).thenReturn(customer);
        Mockito.when(baService.findByCustomerId(1L)).thenReturn(bankAccount);
        Mockito.when(baService.save(bankAccount)).thenReturn(bankAccount);

        Customer updatedCustomer = customerService.update(customer);

        assertNotNull(updatedCustomer);
        assertEquals(customer, updatedCustomer);
    }

    @Test
    public void testUpdateNotNeeded() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setDob(LocalDate.now().minusYears(20));
        customer.setPhoneNumber("1234567890");
        customer.setEmail("johndoe@example.com");
        customer.setType(CustomerType.INDIVIDUAL);
        customer.setActive(true);

        Mockito.when(repository.findById(1L)).thenReturn(customer);

        Customer updatedCustomer = customerService.update(customer);

        assertNull(updatedCustomer);
    }

    @Test
    public void testCreateCustomerNull() {
        assertThrows(ServiceException.class, () -> customerService.createCustomer(null));
    }

    @Test
    public void testCreateCustomerNonEmptyId() {
        Customer customer = new Customer();
        customer.setId(1L);
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerIsActive() {
        Customer customer = new Customer();
        customer.setActive(true);
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerNonEmptyCreatedDate() {
        Customer customer = new Customer();
        customer.setCreatedDate(LocalDateTime.now());
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerDobInFuture() {
        Customer customer = new Customer();
        customer.setDob(LocalDate.now().plusDays(1));
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerEmptyName() {
        Customer customer = new Customer();
        customer.setName("");
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerEmptyAddress() {
        Customer customer = new Customer();
        customer.setAddress("");
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerNullDob() {
        Customer customer = new Customer();
        customer.setDob(null);
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerEmptyPhoneNumber() {
        Customer customer = new Customer();
        customer.setPhoneNumber("");
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerEmptyEmail() {
        Customer customer = new Customer();
        customer.setEmail("");
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerNullType() {
        Customer customer = new Customer();
        customer.setType(null);
        assertThrows(ServiceException.class, () -> customerService.createCustomer(customer));
    }

    @Test
    public void testCreateCustomerSuccess() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setDob(LocalDate.now().minusYears(20));
        customer.setPhoneNumber("1234567890");
        customer.setEmail("johndoe@example.com");
        customer.setType(CustomerType.INDIVIDUAL);

        Mockito.when(repository.save(customer)).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customer);

        assertNotNull(createdCustomer);
        assertEquals(customer, createdCustomer);
        assertTrue(createdCustomer.isActive());
        assertNotNull(createdCustomer.getCreatedDate());
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

    @Test
    public void testDeactivateCustomerAlreadyDeactivated() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(false);
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        assertThrows(ServiceException.class, () -> customerService.deactivateCustomer(1L));
    }

    @Test
    public void testDeactivateCustomerWithDeactivatedDate() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
        customer.setDeactivatedDate(LocalDateTime.now());
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        assertThrows(ServiceException.class, () -> customerService.deactivateCustomer(1L));
    }

    @Test
    public void testDeactivateCustomerWithActiveBankAccount() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setActive(true);
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        Mockito.when(baService.findByCustomerId(1L)).thenReturn(bankAccount);
        assertThrows(ServiceException.class, () -> customerService.deactivateCustomer(1L));
    }

    @Test
    public void testDeactivateCustomerWithNegativeBalance() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setActive(false);
        bankAccount.setBalance(-1);
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        Mockito.when(baService.findByCustomerId(1L)).thenReturn(bankAccount);
        assertThrows(ServiceException.class, () -> customerService.deactivateCustomer(1L));
    }

    @Test
    public void testDeactivateCustomerWithPositiveBalance() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setActive(false);
        bankAccount.setBalance(1);
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        Mockito.when(baService.findByCustomerId(1L)).thenReturn(bankAccount);
        assertThrows(ServiceException.class, () -> customerService.deactivateCustomer(1L));
    }

    @Test
    public void testDeactivateCustomerSuccessWithBankAccount() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setActive(false);
        bankAccount.setBalance(0);
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        Mockito.when(baService.findByCustomerId(1L)).thenReturn(bankAccount);
        Mockito.when(repository.save(customer)).thenReturn(customer);
        Mockito.when(baService.save(bankAccount)).thenReturn(bankAccount);
        assertDoesNotThrow(() -> customerService.deactivateCustomer(1L));
        assertFalse(customer.isActive());
        assertNotNull(customer.getDeactivatedDate());
    }

    @Test
    public void testDeactivateCustomerSuccessWithoutBankAccount() throws ServiceException, RepositoryException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);
        Mockito.when(repository.findById(1L)).thenReturn(customer);
        Mockito.when(baService.findByCustomerId(1L)).thenReturn(null);
        Mockito.when(repository.save(customer)).thenReturn(customer);
        assertDoesNotThrow(() -> customerService.deactivateCustomer(1L));
        assertFalse(customer.isActive());
        assertNotNull(customer.getDeactivatedDate());
    }
}