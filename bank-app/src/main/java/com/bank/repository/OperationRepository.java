package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class OperationRepository implements Repository<Operation> {

    private ArrayList<Operation> operations = new ArrayList<>();
    private static long lastOperationId = 0;

    public ArrayList<Operation> findAll() throws RepositoryException {
        if (!operations.isEmpty())
            return operations;
        throw new RepositoryException("No operation items found in the repository!");
    }

    public Operation findById(long id) throws RepositoryException {
        for (Operation entity : operations) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        throw new RepositoryException("No bank account item with id " + id + " found in the repository!");
    }

    public long count() throws RepositoryException {
        return operations.size();
    }

    public Operation save(Operation operation) throws RepositoryException {
        try {
            operation.setId(incrementOperationId());
            operation.setDate(LocalDate.now());
            operations.add(operation);
            return operation;
        } catch (Exception ex) {
            String errorMessage = "Failed to save operation.";
            throw new RepositoryException(errorMessage, ex);
        }
    }

    public long incrementOperationId() {
        return ++lastOperationId;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/operations.json"), operations);
    }
}
