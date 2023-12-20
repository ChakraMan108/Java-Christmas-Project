package com.bank.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        fixture.setBalance(fixture.getBalance()+100);

        long expected = 100;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_multipleDeposits_cumulativeBalance() {

        fixture.setBalance(fixture.getBalance()+100);
        fixture.setBalance(fixture.getBalance()+200);
        fixture.setBalance(fixture.getBalance()+300);

        long expected = 600;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_withdrawalsWithinLimits_balanceReduced() {

        fixture.setBalance(fixture.getBalance()+600);
        fixture.setBalance(fixture.getBalance()-100);
        fixture.setBalance(fixture.getBalance()-200);

        long expected = 300;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_withdrawalsUptoLimit_balanceZero() {

        BankAccount fixture = new BankAccount();


        fixture.setBalance(fixture.getBalance()+600);
        fixture.setBalance(fixture.getBalance()-100);
        fixture.setBalance(fixture.getBalance()-200);
        fixture.setBalance(fixture.getBalance()-300);

        long expected = 0;
        long actual = fixture.getBalance();
        assertEquals(expected, actual);
    }

}
