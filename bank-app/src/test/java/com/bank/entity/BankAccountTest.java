package com.bank.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
        account.setCreatedDate(LocalDateTime.of(2022, 1, 1,1 ,1));
        account.setDeactivatedDate(LocalDateTime.of(2022, 1, 2,1 ,1));
        Customer customer = new Customer();
        account.setCustomer(customer);

        assertEquals(1, account.getId());
        assertEquals("Test Account", account.getAccountName());
        assertEquals(BankAccount.AccountType.CURRENT_ACCOUNT, account.getType());
        assertEquals(1000, account.getBalance());
        assertTrue(account.isActive());
        assertEquals(LocalDateTime.of(2022, 1, 1,1, 1), account.getCreatedDate());
        assertEquals(LocalDateTime.of(2022, 1, 2,1,1), account.getDeactivatedDate());
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
    public void testDefaultConstructor() {
        BankAccount account = new BankAccount();
        assertNull(account.getAccountName());
        assertNull(account.getType());
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testParameterizedConstructor() {
        BankAccount account = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        assertEquals("Test Account", account.getAccountName());
        assertEquals(BankAccount.AccountType.CURRENT_ACCOUNT, account.getType());
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testEquals_sameObject() {
        BankAccount account = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        assertTrue(account.equals(account));
    }

    @Test
    public void testEquals_differentObjectSameValues() {
        BankAccount account1 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        BankAccount account2 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        assertTrue(account1.equals(account2));
    }

    @Test
    public void testEquals_differentObjectDifferentValues() {
        BankAccount account1 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        BankAccount account2 = new BankAccount("Different Account", BankAccount.AccountType.SAVING_ACCOUNT, 2000);
        assertFalse(account1.equals(account2));
    }

    @Test
    public void testHashCode_sameValues() {
        BankAccount account1 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        BankAccount account2 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        assertEquals(account1.hashCode(), account2.hashCode());
    }

    @Test
    public void testHashCode_differentValues() {
        BankAccount account1 = new BankAccount("Test Account", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);
        BankAccount account2 = new BankAccount("Different Account", BankAccount.AccountType.SAVING_ACCOUNT, 2000);
        assertNotEquals(account1.hashCode(), account2.hashCode());
    }

    @Test
    public void testToString_withDates() {
        BankAccount account = new BankAccount();
        account.setId(1);
        account.setAccountName("Test Account");
        account.setType(BankAccount.AccountType.CURRENT_ACCOUNT);
        account.setBalance(1000);
        account.setActive(true);
        account.setCreatedDate(LocalDateTime.of(2022, 1, 1,1 ,1));
        account.setDeactivatedDate(LocalDateTime.of(2022, 1, 2,1 ,1));
        Customer customer = new Customer();
        account.setCustomer(customer);
    
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 1\nAccount Name: Test Account\nAccount Type: CURRENT_ACCOUNT\nBalance: 10.00\nActive: true\nCreated Date: 2022-01-01 01:01\nDeactivated Date: 2022-01-02 01:01\nCustomer:\n" + customer + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String actual = account.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToString_withoutDates() {
        BankAccount account = new BankAccount();
        account.setId(1);
        account.setAccountName("Test Account");
        account.setType(BankAccount.AccountType.CURRENT_ACCOUNT);
        account.setBalance(1000);
        account.setActive(true);
        account.setCreatedDate(null);
        account.setDeactivatedDate(null);
        Customer customer = new Customer();
        account.setCustomer(customer);
    
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: 1\nAccount Name: Test Account\nAccount Type: CURRENT_ACCOUNT\nBalance: 10.00\nActive: true\nCreated Date: N/A\nDeactivated Date: N/A\nCustomer:\n" + customer + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String actual = account.toString();
        assertEquals(expected, actual);
    }
}
