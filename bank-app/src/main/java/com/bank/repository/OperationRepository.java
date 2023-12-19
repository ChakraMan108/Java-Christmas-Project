package com.bank.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import com.bank.entity.BankAccount;
import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.service.BankAccountService;
import com.bank.service.OperationService;

public class OperationRepository implements Repository<Operation> {

    private ArrayList<Operation> operations = new ArrayList<>();
    private static long lastOperationId = 0;

    // public OperationRepository(ArrayList<Operation> operation) {

    //     this.operations = operation;
    // }

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
        if (operations.size() != 0)
            return operations.size();
        throw new RepositoryException("No BankAccount item found in repository");
    }

    public long save(Operation operation) throws RepositoryException {
        try {
            operation.setId(incrementOperationId());
            operation.setDate(LocalDate.now());
            operations.add(operation);
            return operation.getId();
        } catch (Exception ex) {
            String errorMessage = "Failed to save operation";
            throw new RepositoryException(errorMessage, ex);
        }
    }

    public long incrementOperationId() {
        return ++lastOperationId;
    }

}
