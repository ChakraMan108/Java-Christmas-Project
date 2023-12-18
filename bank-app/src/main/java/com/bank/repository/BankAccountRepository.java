package com.bank.repository;

import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;

public class BankAccountRepository implements Repository<BankAccount> {

    private ArrayList<BankAccount> bankAccounts;

        public BankAccountRepository(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    
    public long count() throws RepositoryException {
        long count = 0;
        for (BankAccount ba : bankAccounts) {
            count++;
        }
        if (count == 0) {
            throw new RepositoryException("No bank account items found in the repository!");
        }
        return count;
    }

    public ArrayList<BankAccount> findAll() throws RepositoryException {
        if (bankAccounts.size() == 0) {
            throw new RepositoryException("No bank account items found in the repository!");  
        }
        return bankAccounts;
    }

    public BankAccount findById(long id) throws RepositoryException {
        for (BankAccount ba : bankAccounts) {
            if (ba.getId() == id) {
                return ba;
            }
         }
        throw new RepositoryException("No bank account item with id " + id + " found in the repository!");
    }

    @Override
    public long save(BankAccount bankAccount) {
        if (!bankAccounts.contains(bankAccount)) {
            bankAccounts.add(bankAccount);
            return generateAccountNumber();
        }
        else
        {
            bankAccounts.set(bankAccounts.indexOf(bankAccount), bankAccount);
            return bankAccount.getId();
        }
    }

    public long generateAccountNumber() {
        long id = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return id;
    }
}
