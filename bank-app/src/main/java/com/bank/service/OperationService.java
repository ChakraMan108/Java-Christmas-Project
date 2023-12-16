package com.bank.service;

import com.bank.model.BankAccount;

import java.math.BigDecimal;

public class OperationService implements Service {

    @Override
    public void displayAccountDescription(BankAccount account) {
        System.out.println("Account Owner: " + account.getOwner());
        System.out.println("Date Opened: " + account.getDateOpened());
        // Add other relevant account details
    }

    @Override
    public void displayAccountState(BankAccount account) {
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Current Balance: " + account.getBalance());
        // Add other relevant account state details
    }

    @Override
    public void depositFunds(BankAccount account, BigDecimal amount) {
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        System.out.println("Funds deposited successfully. New balance: " + newBalance);
    }

    @Override
    public void withdrawFunds(BankAccount account, BigDecimal amount) {
        BigDecimal currentBalance = account.getBalance();
        if (currentBalance.compareTo(amount) >= 0) {
            BigDecimal newBalance = currentBalance.subtract(amount);
            account.setBalance(newBalance);
            System.out.println("Funds withdrawn successfully. New balance: " + newBalance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    @Override
    public void transferFunds(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {
        BigDecimal fromAccountBalance = fromAccount.getBalance();
        if (fromAccountBalance.compareTo(amount) >= 0) {
            BigDecimal newFromBalance = fromAccountBalance.subtract(amount);
            fromAccount.setBalance(newFromBalance);

            BigDecimal toAccountBalance = toAccount.getBalance();
            BigDecimal newToBalance = toAccountBalance.add(amount);
            toAccount.setBalance(newToBalance);

            System.out.println("Funds transferred successfully.");
            System.out.println("From Account (" + fromAccount.getAccountId() + ") New Balance: " + newFromBalance);
            System.out.println("To Account (" + toAccount.getAccountId() + ") New Balance: " + newToBalance);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}
