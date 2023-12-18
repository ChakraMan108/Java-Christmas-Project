package com.bank.service;

import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;

public class BankAccountService implements Service<BankAccount> {

    BankAccountRepository repo;
    
    public BankAccountService(BankAccountRepository repo) {
        this.repo = repo;
    }

    public long count() throws ServiceException {
        long count = 0;
        try {
            repo.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);
        }
        return count;
    }

    public ArrayList<BankAccount> findAll() throws ServiceException {
        ArrayList<BankAccount> baArrayList = new ArrayList<>();
        try {
            baArrayList = repo.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);
        }
        return baArrayList;
    }

    public BankAccount findById(long id) throws ServiceException {
        BankAccount bankAccount = new BankAccount();
        try {
            bankAccount = repo.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);         
        }
        return bankAccount;
    }

    @Override
    public long save(BankAccount bankAccount) {
        long id = repo.save(bankAccount);
        bankAccount.setId(id);
        return id;
    }

    public void depositIntoAccount(long id, long amount) throws ServiceException {
        if (amount < 1) {
            throw new ServiceException("Cannot deposit €" + amount/100 + " to account id" + acc.getId(), Long.toString(acc.getId()));           
        }
        try {
            BankAccount acc = repo.findById(id);
            acc.setBalance(acc.getBalance() + amount);
            repo.save(acc);
        }
        catch (RepositoryException ex) {
            throw ex;
        }
    }

    public void withdrawFromAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount acc = repo.findById(id);
            if (acc.getBalance() - amount < 0) {
                throw new ServiceException("Insufficient balance to withdraw €" + amount/100 + " from account id" + acc.getId(), Long.toString(acc.getId()));
            }
            acc.setBalance(acc.getBalance() - amount);
            repo.save(acc);
        }
        catch (RepositoryException ex) {
            throw ex;
        }
    }
}




