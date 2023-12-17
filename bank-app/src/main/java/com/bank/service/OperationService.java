package com.bank.service;

import com.bank.entity.BankAccount;

public class OperationService implements Service {

   // @Override
    public void displayAccountDescription(BankAccount account) {
        System.out.println("Account Owner: " + account.getOwner().getName());
        System.out.println("Date Opened: " + account.getDateOpened());
        // Add other relevant account details
    }

   // @Override
    public void displayAccountState(BankAccount account) {
        System.out.println("Account ID: " + account.getid());
        System.out.println("Current Balance: " + account.getBalance());
        // Add other relevant account state details
    }

   // @Override
    public void deposit(BankAccount account, long amount) {
        long newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        System.out.println("Funds deposited successfully. New balance: " + newBalance);
    }

   // @Override
    public void withdraw(BankAccount account, long amount) {
        long currentBalance = account.getBalance();
        if (currentBalance >= amount) {
            long newBalance = currentBalance - amount;
            account.setBalance(newBalance);
            System.out.println("Funds withdrawn successfully. New balance: " + newBalance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    //@Override
    public void transfer(BankAccount fromAccount, BankAccount toAccount, long amount) {
        long fromAccountBalance = fromAccount.getBalance();
        if (fromAccountBalance >= amount) {
            long newFromBalance = fromAccountBalance - amount;
            fromAccount.setBalance(newFromBalance);

            long toAccountBalance = toAccount.getBalance();
            long newToBalance = toAccountBalance + amount;
            toAccount.setBalance(newToBalance);

            System.out.println("Funds transferred successfully.");
            System.out.println("From Account (" + fromAccount.getid() + ") New Balance: " + newFromBalance);
            System.out.println("To Account (" + toAccount.getid() + ") New Balance: " + newToBalance);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

   
    }

