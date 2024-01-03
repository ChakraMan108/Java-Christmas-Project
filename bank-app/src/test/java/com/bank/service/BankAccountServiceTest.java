package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountRepository repository;

    @Mock
    private TransactionService ts;

    @Mock
    private OperationService os;

    private BankAccount bankAccount;
    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setId(1L);
        customer.setActive(true);

        bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setCustomer(customer);
    }

    @Test
    public void testCount() throws ServiceException {
        try {
            Mockito.when(repository.count()).thenReturn(1L);
            assertEquals(1L, bankAccountService.count());
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindAll() throws ServiceException {
        try {
            Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
            assertNotNull(bankAccountService.findAll());
            assertEquals(0, bankAccountService.findAll().size());
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindById() throws ServiceException {
        try {
            Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
            assertNotNull(bankAccountService.findById(1L));
            assertEquals(bankAccount, bankAccountService.findById(1L));
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindByCustomerName() throws ServiceException {
        try {
            Mockito.when(repository.findByCustomerName("John")).thenReturn(bankAccount);
            assertNotNull(bankAccountService.findByCustomerName("John"));
            assertEquals(bankAccount, bankAccountService.findByCustomerName("John"));
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindByCustomerId() throws ServiceException {
        try {
            Mockito.when(repository.findByCustomerId(1L)).thenReturn(bankAccount);
            assertNotNull(bankAccountService.findByCustomerId(1L));
            assertEquals(bankAccount, bankAccountService.findByCustomerId(1L));
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSave() throws ServiceException {
        try {
            Mockito.when(repository.save(bankAccount)).thenReturn(bankAccount);
            assertNotNull(bankAccountService.save(bankAccount));
            assertEquals(bankAccount, bankAccountService.save(bankAccount));
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDepositIntoAccount() throws ServiceException {
        try {
            Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
            assertDoesNotThrow(() -> bankAccountService.depositIntoAccount(1L, 1000L));
            assertEquals(bankAccountService.findById(1L).getBalance(), 1000L);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testWithdrawFromAccount() throws ServiceException {
        try {
            Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
            bankAccount.setBalance(500L);
            assertDoesNotThrow(() -> bankAccountService.withdrawFromAccount(1L, 500L));
            assertEquals(bankAccountService.findById(1L).getBalance(), 0);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDeactivateAccountThrows() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        assertThrows(ServiceException.class, () -> bankAccountService.deactivateAccount(1L));
    }

    @Test
    public void testDeactivateAccountDoesNotThrow() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setActive(true);
        bankAccount.setBalance(0L);
        assertDoesNotThrow(() -> bankAccountService.deactivateAccount(1L));
        assertEquals(bankAccountService.findById(1L).isActive(), false);
    }

    @Test
    public void testCreateAccount() throws ServiceException, RepositoryException {
        Mockito.when(repository.save(bankAccount)).thenReturn(bankAccount);
        assertNotNull(bankAccountService.createAccount(bankAccount, customer));
        assertEquals(bankAccount, bankAccountService.createAccount(bankAccount, customer));
    }
}