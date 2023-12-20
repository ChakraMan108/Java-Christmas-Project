package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TransactionRepository implements Repository<Transaction> {
    
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private static long lastTransactionId = 0;

    public long count() throws RepositoryException {
        return transactions.size();
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
    
    public Transaction save(Transaction transaction) throws RepositoryException {
        try {
            transaction.setId(incrementTransactionId());
            transaction.setCreatedDate(LocalDate.now());
            transactions.add(transaction);
            return transaction;
        } catch (Exception ex) {
            String errorMessage = "Failed to save operation.";
            throw new RepositoryException(errorMessage, ex);
        }
    }

    private static long incrementTransactionId() {
        return ++lastTransactionId;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/transactions.json"), transactions);
    }
}
