package com.bank.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.entity.Operation;
import com.bank.entity.Transaction;

import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;

public class TransactionRepository implements Repository<Transaction> {
    private static long lastTransactionNumber;

    private ArrayList<Transaction> transactions;

    public TransactionRepository(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long count() throws RepositoryException {
        if (!transactions.isEmpty())
            return transactions.size();    
        throw new RepositoryException("No transaction items found in the repository.");
    }

    public ArrayList<Transaction> findAll() throws RepositoryException {
        if (!transactions.isEmpty()) 
            return transactions;
        throw new RepositoryException("No transaction items found in the repository.");  
    }

    public Transaction findById(long id) throws RepositoryException {
        for (Transaction t : transactions) {
            if (t.getId() == id) {
                return t;
            }
         }
        throw new RepositoryException("No transaction item with id " + id + " found in the repository.");
        
    }
    
    public long save(Transaction transaction) throws RepositoryException {
        if (!transactions.contains(transaction)) {
            transaction.setId(incrementTransactionNumber());
            transaction.setCreatedDate(LocalDate.now());
            transactions.add(transaction);
            return transaction.getId();
        }
        else
        {
            transactions.set(transactions.indexOf(transaction), transaction);
            return transaction.getId();
        }
    }
    private static long incrementTransactionNumber() {
        lastTransactionNumber++;
        return lastTransactionNumber;
       
        
    }
}
