package com.bank.service;

import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.TransactionRepository;

public final class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repo)
	{
		this.repository = repo;
	}

    public long count() throws ServiceException {
        try {
            return repository.count();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public Iterable<? extends Transaction> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public Transaction findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    public Transaction save(Transaction transaction) throws ServiceException {
        try {
            return repository.save(transaction);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
        }
    }

    // public void saveJson() throws ServiceException {
    //     try {
    //         repository.saveJson();
    //     } catch (IOException ex) {
    //         throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
    //     }
    // }

    // public void loadJson() throws ServiceException {
    //     try {
    //         repository.loadJson();
    //     } catch (IOException ex) {
    //         throw new ServiceException("[Transaction Service error] " + ex.getMessage(), ex);
    //     }
    // }
}