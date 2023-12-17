package com.bank.entity;

import com.bank.model.BankAccount;
import com.bank.service.OperationService;

//import java.math.BigDecimal;

public class Operation extends BankAccount{

    private final OperationService operationService;

    public Operation(OperationService operationService) {
        this.operationService = operationService;
    }

    // Display description of a specific account
    public void displayAccountDescription(BankAccount account) {
        operationService.displayAccountDescription(account);
    }

    // Display state of a specific account
    public void displayAccountState(BankAccount account) {
        operationService.displayAccountState(account);
    }

    // Deposit funds into a specific account
    public void deposit(BankAccount account, long amount) {
        operationService.displayAccountState(account);
        operationService.deposit(account, amount);
        operationService.displayAccountState(account);
    }

    // Withdraw funds from a specific account
    public void withdraw(BankAccount account, long amount) {
        operationService.displayAccountState(account);
        operationService.withdraw(account, amount);
        operationService.displayAccountState(account);
    }

    // Transfer funds between two specific accounts
    public void transfer(BankAccount fromAccount, BankAccount toAccount, long amount) {
        operationService.displayAccountState(fromAccount);
        operationService.displayAccountState(toAccount);

        operationService.transfer(fromAccount, toAccount, amount);

        operationService.displayAccountState(fromAccount);
        operationService.displayAccountState(toAccount);
    }
}

