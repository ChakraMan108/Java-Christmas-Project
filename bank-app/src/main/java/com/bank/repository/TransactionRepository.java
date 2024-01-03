package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class TransactionRepository implements Repository<Transaction> {

    private static TransactionRepository INSTANCE;
    private String info = "Transaction Repository";

    public static TransactionRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransactionRepository();
        }
        return INSTANCE;
    }
    
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
        transaction.setCreatedDate(LocalDate.now());
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("c:/temp/transactions.json"), transactions);
    }

    public void loadJson() throws IOException {
        try {
            File f = new File("c:/temp/transactions.json");
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<Transaction> transctionList = objectMapper.readValue(new File("c:/temp/transactions.json"),
                        new TypeReference<ArrayList<Transaction>>() {
                        });
                transactions = transctionList;
            } else {
                System.err.println("File not found. Creating new transactions.json file.");
                saveJson();
            }
        } catch (IOException ex) {
            throw new IOException("Error loading customer JSON file: " + ex.getMessage(), ex);
        }
    }

    public String getInfo() {
        return info;
    }
}