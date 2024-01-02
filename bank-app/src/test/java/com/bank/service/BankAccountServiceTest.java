package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.exceptions.ServiceException;

public class BankAccountServiceTest {

    BankAccountService service = new BankAccountService();
    long customerIdToTest;
    long bankAccountIdToTest;

    @BeforeEach
    public void setUp() throws ServiceException {
        testSave();
    }

    @Test
    public void testSave() throws ServiceException {
        BankAccount account = new BankAccount();
        account.setBalance(1000);
        account.setActive(true);
        account.setCreatedDate(LocalDate.now());
        Customer customer = new Customer("Test User", "Test Address", LocalDate.now(), "123456789", "some@email.com",
                Customer.CustomerType.INDIVIDUAL);
        account.setCustomer(customer);
        BankAccount savedAccount = service.save(account);
        bankAccountIdToTest = savedAccount.getId();
        customerIdToTest = savedAccount.getCustomer().getId();
        assertEquals(account, savedAccount);
    }

    @Test
    public void testCount() throws ServiceException {
        long count = service.count();
        assertTrue(count >= 0);
    }

    @Test
    public void testFindAll() throws ServiceException {
        ArrayList<BankAccount> accounts = service.findAll();
        assertNotNull(accounts);
    }

    @Test
    public void testFindById() throws ServiceException {
        BankAccount account = service.findById(bankAccountIdToTest);
        assertNotNull(account);
    }

    @Test
    public void testFindByCustomerName() throws ServiceException {
        BankAccount account = service.findByCustomerName("Test User");
        assertNotNull(account);
    }

    @Test
    public void testFindByCustomerId() throws ServiceException {
        BankAccount account = service.findByCustomerId(customerIdToTest);
        assertNotNull(account);
    }

    @Test
    public void testDepositIntoAccount() throws ServiceException {
        service.depositIntoAccount(bankAccountIdToTest, 1000);
        BankAccount account = service.findById(bankAccountIdToTest);
        assertEquals(2000, account.getBalance());
    }

    @Test
    public void testWithdrawFromAccount() throws ServiceException {
        service.withdrawFromAccount(bankAccountIdToTest, 500);
        BankAccount account = service.findById(bankAccountIdToTest);
        assertEquals(500, account.getBalance());
    }

    @Test
    public void testDeactivateAccount() throws ServiceException {
        service.deactivateAccount(bankAccountIdToTest);
        BankAccount account = service.findById(bankAccountIdToTest);
        assertFalse(account.isActive());
    }

    @Test
    public void testCreateAccount() throws ServiceException {
        Customer customer = new Customer();
        customer.setActive(true);
        BankAccount account = new BankAccount();
        BankAccount createdAccount = service.createAccount(account, customer);
        assertEquals(account, createdAccount);
    }
}