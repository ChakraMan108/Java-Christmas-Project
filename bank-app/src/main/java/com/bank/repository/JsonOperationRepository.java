package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonOperationRepository implements OperationRepository {

    private Properties appProps = new Properties();
    private String dataPath;
    private ArrayList<Operation> operations = new ArrayList<>();

    public JsonOperationRepository() {
        try {
            loadProperties();
            loadJson();
        } catch (RepositoryException | IOException ex) {
            System.out.println("[JsonOperationRepository constructor error]" + ex.getMessage());
        }
    }

    public Iterable<? extends Operation> findAll() throws RepositoryException {
        return operations;
    }

    public Operation findById(long id) throws RepositoryException {
        for (Operation operation : operations) {
            if (operation.getId() == id)
                return operation;
        }
        throw new RepositoryException("No operation with id " + id + " found in the repository.");
    }

    public long count() throws RepositoryException {
        return operations.size();
    }

    public Operation save(Operation operation) throws RepositoryException {
        try {
            operation.setId(generateId());
            operation.setDate(LocalDateTime.now());
            operations.add(operation);
            saveJson();
            return operation;
        } catch (IOException ex) {
            throw new RepositoryException("[Operation Repository save error]" + ex.getMessage(), ex);
        }
    }

    public long generateId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        String jsonDataPath = getDataPath();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(jsonDataPath), operations);
    }

    public void loadJson() throws IOException {
        try {
            String jsonDataPath = getDataPath();
            File f = new File(jsonDataPath);
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<Operation> operationList = objectMapper.readValue(new File(jsonDataPath),
                        new TypeReference<ArrayList<Operation>>() {
                        });
                operations = operationList;
            } else {
                saveJson();
            }
        } catch (IOException ex) {
            throw new IOException("Error loading customer JSON file: " + ex.getMessage(), ex);
        }
    }

    public Properties loadProperties() throws RepositoryException {
        try {
            InputStream appConfigPath = JsonOperationRepository.class.getClassLoader()
                    .getResourceAsStream("operation_repo.properties");
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