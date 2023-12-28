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

    public long count() throws RepositoryException {
        return transactions.size();
    }

    public ArrayList<Transaction> findAll() throws RepositoryException {
        return transactions;
    }

    public Transaction findById(long id) throws RepositoryException {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id)
                return transaction;
         }
        throw new RepositoryException("No transaction with id " + id + " found in the repository.");
    }
    
    public Transaction save(Transaction transaction) throws RepositoryException {
        transaction.setId(generateId());
        transaction.setCreatedDate(LocalDate.now());
        transactions.add(transaction);
        return transaction;
    }

    public long generateId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/transactions.json"), transactions);
    }
}