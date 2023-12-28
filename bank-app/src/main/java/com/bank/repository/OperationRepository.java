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
        operation.setId(generateId());
        operation.setDate(LocalDate.now());
        operations.add(operation);
        return operation;
    }

    public long generateId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/operations.json"), operations);
    }
}