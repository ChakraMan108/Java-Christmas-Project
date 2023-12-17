package com.bank.repository;

import java.util.ArrayList;
import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.service.OperationService;

public class OperationRepository<T> implements Repository<T> {

    public List<T> findAll() throws RepositoryException{
    return null;
    }
    //@Override
    public List<T> findAll(long id) {
        
        return null; 
    }

    private List<BankAccount> accounts;
    private OperationService operationService;

    public OperationRepository(OperationService operationService) {
        this.accounts = new ArrayList<>();
        this.operationService = operationService;
    }

   
    public List<BankAccount> getAllAccounts() {
        return accounts;
    }

    public void deleteById(long id) {
    }

    public T findById(long id) {
        return null;
    }
    public long count() throws RepositoryException{
        return count();
    }
    @Override
    public Operation save(T entity) throws RepositoryException {
        
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

 // Add a new account to the repository
    // public void save(BankAccount account) {
    //     accounts.add(account);
    // }

    // Get all accounts from the repository
    
    // // Perform an operation on a specific account using the OperationService
    // public void performOperation(BankAccount account, OperationType operationType, long amount) {
    //     switch (operationType) {
    //         case SHOW_DESCRIPTION:
    //             operationService.showAccountDescription(account);
    //             break;
    //         case SHOW_STATE:
    //             operationService.showAccountState(account);
    //             break;
    //         case DEPOSIT:
    //             operationService.deposit(account, amount);
    //             break;
    //         case WITHDRAW:
    //             operationService.withdraw(account, amount);
    //             break;
    //         // Add more cases for other operation types if needed
    //     }
    // }

    
    }

