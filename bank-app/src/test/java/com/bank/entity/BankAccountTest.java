package com.bank.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    BankAccount fixture;

    @BeforeEach
    public void setup() {
        fixture = new BankAccount();
    }

    @Test
    public void accountCreated_zeroBalanceInitially() {
        assertEquals(0, fixture.getBalance());
    }

    @Test
    public void deposit_singleDeposit_correctBalance() {

        fixture.setBalance(fixture.getBalance() + 100);

        long expected = 100;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_multipleDeposits_cumulativeBalance() {

        fixture.setBalance(fixture.getBalance() + 100);
        fixture.setBalance(fixture.getBalance() + 200);
        fixture.setBalance(fixture.getBalance() + 300);

        long expected = 600;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_withdrawalsWithinLimits_balanceReduced() {

        fixture.setBalance(fixture.getBalance() + 600);
        fixture.setBalance(fixture.getBalance() - 100);
        fixture.setBalance(fixture.getBalance() - 200);

        long expected = 300;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_withdrawalsUptoLimit_balanceZero() {

        BankAccount fixture = new BankAccount();

        fixture.setBalance(fixture.getBalance() + 600);
        fixture.setBalance(fixture.getBalance() - 100);
        fixture.setBalance(fixture.getBalance() - 200);
        fixture.setBalance(fixture.getBalance() - 300);

        long expected = 0;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testGettersAndSetters() {
        BankAccount account = new BankAccount();
        account.setId(1);
        account.setAccountName("Test Account");
        account.setType(BankAccount.AccountType.CURRENT_ACCOUNT);
        account.setBalance(1000);
        account.setActive(true);
        account.setCreatedDate(LocalDate.of(2022, 1, 1));
        account.setDeactivatedDate(LocalDate.of(2022, 1, 2));
        Customer customer = new Customer();
        account.setCustomer(customer);

        assertEquals(1, account.getId());
        assertEquals("Test Account", account.getAccountName());
        assertEquals(BankAccount.AccountType.CURRENT_ACCOUNT, account.getType());
        assertEquals(1000, account.getBalance());
        assertTrue(account.isActive());
        assertEquals(LocalDate.of(2022, 1, 1), account.getCreatedDate());
        assertEquals(LocalDate.of(2022, 1, 2), account.getDeactivatedDate());
        assertEquals(customer, account.getCustomer());
    }

    @Test
    public void testEquals() {
        BankAccount account1 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        BankAccount account2 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);

        assertTrue(account1.equals(account2));
    }

    @Test
    public void testHashCode() {
        BankAccount account1 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        BankAccount account2 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);

        assertEquals(account1.hashCode(), account2.hashCode());
    }

    @Test
    public void testToString() {
        BankAccount account = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        String expected = "BankAccount [id=0, accountName=Test Account, type=CURRENT_ACCOUNT, balance=1000, isActive=false, createdDate=null, deactivatedDate=null, customer=null]";

        assertEquals(expected, account.toString());
    }
}
