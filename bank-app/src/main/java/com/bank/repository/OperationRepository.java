package com.bank.repository;

import com.bank.model.BankAccount;
import com.bank.model.User;

import java.util.HashMap;
import java.util.Map;

public class OperationRepository implements Repository {

    private final Map<Long, User> users;
    private final Map<Long, BankAccount> accounts;

    public OperationRepository() {
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public User getUser(long userId) {
        return users.get(userId);
    }

    @Override
    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public BankAccount getAccount(long accountId) {
        return accounts.get(accountId);
    }


    // Note: This is a simple in-memory implementation using HashMaps.
    

}
