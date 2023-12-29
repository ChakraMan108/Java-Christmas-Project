package com.bank.service;

import java.io.IOException;
import java.util.ArrayList;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.TransactionRepository;

public class TransactionService implements Service<Transaction> {

    private TransactionRepository repository = TransactionRepository.getInstance();

    public long count() throws ServiceException {
        try {
            return repository.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<Transaction> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public Transaction findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public Transaction save(Transaction transaction) throws ServiceException {
        try {
            return repository.save(transaction);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }
    
    public void saveJson() throws IOException {
        try {
            repository.saveJson();
        } catch (IOException ex) {
            throw new IOException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public void loadJson() throws IOException {
        try {
            repository.loadJson();
        } catch (IOException ex) {
            throw new IOException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }
}