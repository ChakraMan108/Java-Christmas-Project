package com.bank.repository;
//Fionn
//Collection of Objects
//save it or fetch
//Read or write

import java.util.ArrayList;

//Increment transaction ID here!!!!!!!!!!!!!!

import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.entity.Operation;
import com.bank.entity.Transaction;

import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;

public class TransactionRepository implements Repository<Transaction> {
    private long lastTransactionNumber;

    private ArrayList<Transaction> transactions;

    public TransactionRepository(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    //private static long nextId = 0;
    

    //Below auto-generated by VS Studio

    public long count() throws RepositoryException {
        long count = transactions.size();
        if (count == 0) {
            throw new RepositoryException("No transaction items found in the repository.");
        }
        return count;
    }

    public ArrayList<Transaction> findAll() throws RepositoryException {
        if (transactions.size() != 0) 
            return transactions;
        throw new RepositoryException("No transaction items found in the repository.");  
    }

    public Transaction findById(long id) throws RepositoryException {
        // TODO Auto-generated method stub
        for (Transaction t : transactions) {
            if (t.getId() == id) {
                return t;
            }
         }
        throw new RepositoryException("No transaction item with id " + id + " found in the repository.");
        
    }
    
    public long save(Transaction transaction) throws RepositoryException {
        if (!transactions.contains(transaction)) {
            transactions.add(transaction);
            return incrementTransactionNumber();
        }
        else
        {
            transactions.set(transactions.indexOf(transaction), transaction);
            return transaction.getId();
        }
    }

    public void transactionNumberManager() {
        this.lastTransactionNumber = 1_000_000_000L;
    }

    public long incrementTransactionNumber() {
        lastTransactionNumber++;
        return lastTransactionNumber;
       
        
    }


    
}

// public List<Transaction> getAll() throws RepositoryException {
//         return null;
//     }

//  return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
//         for (long i = 0; i < 5; i++) {
//         }