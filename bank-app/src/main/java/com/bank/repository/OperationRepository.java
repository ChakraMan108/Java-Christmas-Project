package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.ui.Ui;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class OperationRepository implements Repository<Operation> {

    private static OperationRepository INSTANCE;
    private String info = "Operation Repository";

    public static OperationRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OperationRepository();
        }
        return INSTANCE;
    }

    private ArrayList<Operation> operations = new ArrayList<>();

    public ArrayList<Operation> findAll() throws RepositoryException {
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
            operation.setDate(LocalDate.now());
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
        String jsonDataPath = Ui.getDataPath() + "/operations.json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(jsonDataPath), operations);
    }

    public void loadJson() throws IOException {
        try {
            String jsonDataPath = Ui.getDataPath() + "/operations.json";
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

    public String getInfo() {
        return info;
    }
}