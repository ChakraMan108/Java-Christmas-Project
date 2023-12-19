package com.bank.repository;

import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.service.BankAccountService;
import com.bank.service.OperationService;

public class OperationRepository implements Repository<Operation> {

    private ArrayList<Operation> operations;
    private long nextId = 1;
    private ArrayList<BankAccount> accounts;
    private OperationService service;
    private BankAccountService baService;
    


    public ArrayList<Operation> findAll() throws RepositoryException {
        try {
            return service.findAll();
        } catch (Exception ex) {
            String errorMessage = "Failed to fetch all operations";
            throw new RepositoryException(errorMessage, ex);
        }
    }
    
    public OperationRepository(OperationService opService) {
        this.accounts = new ArrayList<>();
        this.service = opService;
    }

   
    public Operation findById(long id) throws RepositoryException {
        for (Operation entity : operations) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        throw new RepositoryException("No bank account item with id " + id + " found in the repository!");
    }

    
    public long count() throws RepositoryException{

        if (operations.size() != 0)
            return operations.size();
        throw new RepositoryException("No BankAccount item found in repository");

        
    }
    
    public long save(Operation operation) throws RepositoryException {
        try {
            
            long id = operation.getId();
            operation.setId(id); 
            return id;
        } catch (Exception ex) {
            
            String errorMessage = "Failed to save operation";
            throw new RepositoryException(errorMessage, ex);
        }
    }

    
    }

