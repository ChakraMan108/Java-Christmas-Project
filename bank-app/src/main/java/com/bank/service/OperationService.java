package com.bank.service;

import com.bank.model.BankAccount;

public class OperationService{

    // Display description of a specific account (e.g., owner, date opened)
    public void showAccountDescription(BankAccount account) {
        System.out.println("Account Description:");
        System.out.println("Owner: " + account.getId());
        System.out.println("Date Opened: " + account.getCreatedDate());
    }

    // Display state of a specific account (e.g., current balance)
    public void showAccountState(BankAccount account) {
        System.out.println("Account State:");
        System.out.println("Current Balance: " + account.getCurrentBalance());
    }

    // Deposit funds into a specific account
    public void deposit(BankAccount account, long amount) {
        // Perform deposit logic here
        account.setCurrentBalance(account.getCurrentBalance() + amount);
        System.out.println("Deposited " + amount + " into the account.");
    }

    // Withdraw funds from a specific account
    public void withdraw(BankAccount account, long amount) {
        // Perform withdrawal logic here
        long currentBalance = account.getCurrentBalance();
        if (currentBalance >= amount) {
            account.setCurrentBalance(currentBalance - amount);
            System.out.println("Withdrawn " + amount + " from the account.");
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    // Transfer funds between two specific accounts
    public void transfer(BankAccount fromAccount, BankAccount toAccount, long amount) {
        // Perform transfer logic here
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
        System.out.println("Transferred " + amount + " from one account to another.");
    }
}
