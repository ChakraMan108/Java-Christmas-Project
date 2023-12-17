package com.bank.repository;

import com.bank.model.BankAccount;
import com.bank.service.OperationService;

import java.util.ArrayList;
import java.util.List;

public class OperationRepository implements Repository {

    private List<BankAccount> accounts;
    private OperationService operationService;

    public OperationRepository(OperationService operationService) {
        this.accounts = new ArrayList<>();
        this.operationService = operationService;
    }

    // Add a new account to the repository
    public void save(BankAccount account) {
        accounts.add(account);
    }

    // Get all accounts from the repository
    public List<BankAccount> getAllAccounts() {
        return accounts;
    }

    // Perform an operation on a specific account using the OperationService
    public void performOperation(BankAccount account, OperationType operationType, long amount) {
        switch (operationType) {
            case SHOW_DESCRIPTION:
                operationService.showAccountDescription(account);
                break;
            case SHOW_STATE:
                operationService.showAccountState(account);
                break;
            case DEPOSIT:
                operationService.deposit(account, amount);
                break;
            case WITHDRAW:
                operationService.withdraw(account, amount);
                break;
            // Add more cases for other operation types if needed
        }
    }

    // Enum to represent different types of operations
    public enum OperationType {
        SHOW_DESCRIPTION,
        SHOW_STATE,
        DEPOSIT,
        WITHDRAW
        // Add more operation types if needed
    }
}
