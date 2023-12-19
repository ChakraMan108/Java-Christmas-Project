package com.bank.service;
//Fionn - Just passes data here
// Look in Operation service specifically Findall
// Do FindByID differently


import java.util.ArrayList;
import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.entity.Transaction;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.TransactionRepository;

import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;



public class TransactionService implements Service {

    private TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public long count() throws ServiceException {

    }

    public ArrayList<Transaction> findAll() throws ServiceException {

    }

    public Transaction findById(long id) throws ServiceException {

    }

    public long save(Transaction transaction) throws RepositoryException {

    }
}



    //transaction.setID(++nextId);    // Must be static