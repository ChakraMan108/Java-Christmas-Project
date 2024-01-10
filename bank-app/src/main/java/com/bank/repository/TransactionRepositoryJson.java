package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TransactionRepositoryJson implements TransactionRepository {
    
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
    try {
        transaction.setId(generateId());
        transaction.setCreatedDate(LocalDateTime.now());
        transactions.add(transaction);
        saveJson();
        return transaction;
    } catch (IOException ex) {
        throw new RepositoryException("[Transaction Repository save error]" + ex.getMessage(), ex);
    }
    }

    public long generateId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        String jsonDataPath = ""; //= CliUi.getDataPath() + "/transactions.json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(jsonDataPath), transactions);
    }

    public void loadJson() throws IOException {
        try {
            String jsonDataPath = ""; //= CliUi.getDataPath() + "/transactions.json";
            File f = new File(jsonDataPath);
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<Transaction> transctionList = objectMapper.readValue(new File(jsonDataPath),
                        new TypeReference<ArrayList<Transaction>>() {
                        });
                transactions = transctionList;
            } else {
                saveJson();
            }
        } catch (IOException ex) {
            throw new IOException("Error loading customer JSON file: " + ex.getMessage(), ex);
        }
    }

}