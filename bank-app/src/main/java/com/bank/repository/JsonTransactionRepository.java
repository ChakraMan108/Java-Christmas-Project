package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonTransactionRepository implements TransactionRepository {

    private Properties appProps = new Properties();
    private String dataPath;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public JsonTransactionRepository() {
        try {
            loadProperties();
            loadJson();
        } catch (RepositoryException | IOException ex) {
            System.out.println("[JsonTransactionRepository constructor error]" + ex.getMessage());
        }
    }

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
        String jsonDataPath = getDataPath();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(jsonDataPath), transactions);
    }

    public void loadJson() throws IOException {
        try {
            String jsonDataPath = getDataPath();
            File f = new File(jsonDataPath);
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<Transaction> transactionList = objectMapper.readValue(new File(jsonDataPath),
                        new TypeReference<ArrayList<Transaction>>() {
                        });
                transactions = transactionList;
            } else {
                saveJson();
            }
        } catch (IOException ex) {
            throw new IOException("Error loading customer JSON file: " + ex.getMessage(), ex);
        }
    }

    public Properties loadProperties() throws RepositoryException {
        try {
            InputStream appConfigPath = getClass().getClassLoader()
                    .getResourceAsStream("com/bank/repository/transaction_repo.properties");
            appProps.load(appConfigPath);
            return appProps;
        } catch (IOException e) {
            throw new RepositoryException("[TransactionRepository loadProperties error]" + e.getMessage());
        }
    }

    private String getDataPath() {
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            dataPath = appProps.getProperty("app.win.path");
        else
            dataPath = appProps.getProperty("app.nix.path");
        return dataPath;
    }
}