package com.bank.service;

import java.io.IOException;
import java.util.ArrayList;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.TransactionRepository;

public class TransactionService implements Service<Transaction> {

    private static final TransactionRepository repository = new TransactionRepository();

    public long count() throws ServiceException {
        try {
            return repository.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);
        }
    }

    public ArrayList<Transaction> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);
        }
    }

    public Transaction findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);         
        }
    }

    public Transaction save(Transaction transaction) throws ServiceException {
        try {
            return repository.save(transaction);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Transaction Repository by the Service.", ex);
        }
    }
    
    public void saveJson() throws IOException {
        repository.saveJson();
    }
}

