package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.bank.entity.BankAccount.AccountType;
import com.bank.entity.Customer.CustomerType;
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
        bankAccount.setActive(true);
        bankAccount.setCustomer(customer);
    }

    @Test
    public void testGetInstance() {
        BankAccountService service1 = BankAccountService.getInstance();
        BankAccountService service2 = BankAccountService.getInstance();
        assertNotNull(service1);
        assertSame(service1, service2);
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
    public void testDepositIntoAccount_inactiveAccount() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setActive(false);
        assertThrows(ServiceException.class, () -> bankAccountService.depositIntoAccount(1L, 1000L));
    }

    @Test
    public void testDepositIntoAccount_accountWithDeactivatedDate() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setDeactivatedDate(LocalDateTime.now());
        assertThrows(ServiceException.class, () -> bankAccountService.depositIntoAccount(1L, 1000L));
    }

    @Test
    public void testDepositIntoAccount_invalidDepositAmount() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        assertThrows(ServiceException.class, () -> bankAccountService.depositIntoAccount(1L, 0L));
    }

    @Test
    public void testDepositIntoAccount_successfulDeposit() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        Mockito.when(repository.save(bankAccount)).thenReturn(bankAccount);
        assertDoesNotThrow(() -> bankAccountService.depositIntoAccount(1L, 1000L));
        assertEquals(1000L, bankAccount.getBalance());
    }

    @Test
    public void testWithdrawFromAccount_inactiveAccount() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setActive(false);
        assertThrows(ServiceException.class, () -> bankAccountService.withdrawFromAccount(1L, 1000L));
    }

    @Test
    public void testWithdrawFromAccount_accountWithDeactivatedDate() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setDeactivatedDate(LocalDateTime.now());
        assertThrows(ServiceException.class, () -> bankAccountService.withdrawFromAccount(1L, 1000L));
    }

    @Test
    public void testWithdrawFromAccount_insufficientBalance() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setBalance(500L);
        assertThrows(ServiceException.class, () -> bankAccountService.withdrawFromAccount(1L, 1000L));
    }

    @Test
    public void testWithdrawFromAccount_successfulWithdrawal() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        Mockito.when(repository.save(bankAccount)).thenReturn(bankAccount);
        bankAccount.setBalance(2000L);
        assertDoesNotThrow(() -> bankAccountService.withdrawFromAccount(1L, 1000L));
        assertEquals(1000L, bankAccount.getBalance());
    }

    @Test
    public void testDeactivateAccount_alreadyDeactivated() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setActive(false);
        assertThrows(ServiceException.class, () -> bankAccountService.deactivateAccount(1L));
    }

    @Test
    public void testDeactivateAccount_accountWithDeactivatedDate() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setDeactivatedDate(LocalDateTime.now());
        assertThrows(ServiceException.class, () -> bankAccountService.deactivateAccount(1L));
    }

    @Test
    public void testDeactivateAccount_accountWithNonZeroBalance() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        bankAccount.setBalance(1000L);
        assertThrows(ServiceException.class, () -> bankAccountService.deactivateAccount(1L));
    }

    @Test
    public void testDeactivateAccount_successfulDeactivation() throws ServiceException, RepositoryException {
        Mockito.when(repository.findById(1L)).thenReturn(bankAccount);
        Mockito.when(repository.save(bankAccount)).thenReturn(bankAccount);
        assertDoesNotThrow(() -> bankAccountService.deactivateAccount(1L));
        assertFalse(bankAccount.isActive());
        assertNotNull(bankAccount.getDeactivatedDate());
    }

    @Test
    public void testCreateAccount_nullAccount() {
        assertThrows(ServiceException.class, () -> bankAccountService.createAccount(null, customer));
    }

    @Test
    public void testCreateAccount_nullCustomer() {
        assertThrows(ServiceException.class, () -> bankAccountService.createAccount(bankAccount, null));
    }

    @Test
    public void testCreateAccount_inactiveCustomer() {
        customer.setActive(false);
        assertThrows(ServiceException.class, () -> bankAccountService.createAccount(bankAccount, customer));
    }

    @Test
    public void testCreateAccount_invalidCustomerId() {
        customer.setId(0L);
        assertThrows(ServiceException.class, () -> bankAccountService.createAccount(bankAccount, customer));
    }

    @Test
    public void testCreateAccount_futureCustomerDob() {
        customer.setDob(LocalDate.now().plusDays(1));
        assertThrows(ServiceException.class, () -> bankAccountService.createAccount(bankAccount, customer));
    }

    @Test
    public void testCreateAccount_successfulAccountCreation() throws ServiceException, RepositoryException {
        BankAccount bankAccount2 = new BankAccount();
        Mockito.when(repository.save(bankAccount2)).thenReturn(bankAccount2);
        bankAccount2.setAccountName("John Doe");
        bankAccount2.setBalance(10L);
        bankAccount2.setType(AccountType.CURRENT_ACCOUNT);
        customer.setActive(true);
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setDob(LocalDate.now().minusYears(18));
        customer.setCreatedDate(LocalDateTime.of(2022, 1, 1,1,1));
        customer.setDeactivatedDate(null);
        customer.setAddress("123 Main St");
        customer.setEmail("email@email.com");
        customer.setPhoneNumber("1234567890");
        customer.setType(CustomerType.COMPANY);

        assertNotNull(bankAccountService.createAccount(bankAccount2, customer));
        assertEquals(bankAccount, bankAccountService.createAccount(bankAccount2, customer));
    }
}