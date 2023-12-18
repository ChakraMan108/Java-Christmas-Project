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
        if (bankAccounts.size() != 0)
            return bankAccounts.size();    
        throw new RepositoryException("No bank account items found in the repository!");
    }

    public ArrayList<BankAccount> findAll() throws RepositoryException {
        if (bankAccounts.size() != 0) 
            return bankAccounts;
        throw new RepositoryException("No bank account items found in the repository!");  
    }

    public BankAccount findById(long id) throws RepositoryException {
        for (BankAccount ba : bankAccounts) {
            if (ba.getId() == id) {
                return ba;
            }
        }
        throw new RepositoryException("No bank account item with id " + id + " found in the repository!");
    }

    public long save(BankAccount bankAccount) throws RepositoryException {
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
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }
}
