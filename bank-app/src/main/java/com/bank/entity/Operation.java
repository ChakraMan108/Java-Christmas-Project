package com.bank.entity;

import com.bank.model.BankAccount;
import com.bank.service.OperationService;

import java.math.BigDecimal;

public class Operation {

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
    public void depositFunds(BankAccount account, BigDecimal amount) {
        operationService.displayAccountState(account);
        operationService.depositFunds(account, amount);
        operationService.displayAccountState(account);
    }

    // Withdraw funds from a specific account
    public void withdrawFunds(BankAccount account, BigDecimal amount) {
        operationService.displayAccountState(account);
        operationService.withdrawFunds(account, amount);
        operationService.displayAccountState(account);
    }

    // Transfer funds between two specific accounts
    public void transferFunds(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {
        operationService.displayAccountState(fromAccount);
        operationService.displayAccountState(toAccount);

        operationService.transferFunds(fromAccount, toAccount, amount);

        operationService.displayAccountState(fromAccount);
        operationService.displayAccountState(toAccount);
    }
}

