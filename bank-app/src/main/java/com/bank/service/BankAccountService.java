package com.bank.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;


public class BankAccountService implements Service<BankAccount> {

    private BankAccountRepository repo;

    public BankAccountService(BankAccountRepository repo) {
        this.repo = repo;
    }

    public long count() throws ServiceException {
        try {
            return repo.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);
        }
    }

    public ArrayList<BankAccount> findAll() throws ServiceException {
        try {
            return repo.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);
        }
    }

    public BankAccount findById(long id) throws ServiceException {
        try {
            return repo.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);         
        }
    }

    public long save(BankAccount bankAccount) throws ServiceException {
        try {
            return repo.save(bankAccount);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);
        }
    }

    public void depositIntoAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repo.findById(id);
            if (amount < 1) {
               throw new ServiceException("Cannot deposit €" + amount/100 + " to account id" + account.getId(), Long.toString(account.getId()));           
            }
            account.setBalance(account.getBalance() + amount);
            repo.save(account);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.");
        }
    }

    public void withdrawFromAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repo.findById(id);
            if (account.getBalance() - amount < 0) {
                throw new ServiceException("Insufficient balance to withdraw €" + amount/100 + " from account id" + account.getId(), Long.toString(account.getId()));
            }
            account.setBalance(account.getBalance() - amount);
            repo.save(account);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.");
        }
    }

    public void saveJson(ArrayList<BankAccount> bankAccounts) throws IOException {
        repo.saveJson(bankAccounts);
    }
}




