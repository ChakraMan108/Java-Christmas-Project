package com.bank.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.entity.Transaction;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.TransactionRepository;

import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;



public class TransactionService implements Service<Transaction> {

    private TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public long count() throws ServiceException {
        try {
            return repo.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);
        }
    }

    public ArrayList<Transaction> findAll() throws ServiceException {
        try {
            return repo.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);
        }
    }

    public Transaction findById(long id) throws ServiceException {
        try {
            return repo.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);         
        }
    }

    public long save(Transaction transaction) throws ServiceException {
        try {
            return repo.save(transaction);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);
        }
    }

    public void createTransaction(long id, long amount) throws ServiceException {
        try {
            Transaction transaction = new Transaction(id, amount);
            long generatedTransactionId = repo.save(transaction);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.");
        }
    }

}

