package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BankAccountRepository implements Repository<BankAccount> {

    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public long count() throws RepositoryException {
        return bankAccounts.size();
    }

    public ArrayList<BankAccount> findAll() throws RepositoryException {
        if (!bankAccounts.isEmpty()) 
            return bankAccounts;
        throw new RepositoryException("No bank account items found in the repository!");  
    }

    public BankAccount findById(long id) throws RepositoryException {
        for (BankAccount ba : bankAccounts) {
            if (ba.getId() == id) {
                return ba;
            }
        }
        throw new RepositoryException("No bank account item with id " + id + " found in the repository!");
    /* using stream & lambda - return bankAccounts.stream().filter(a->a.getId() == id).collect(Collectors.toList()).get(0); */
    }

    public BankAccount save(BankAccount bankAccount) throws RepositoryException {
        if (!bankAccounts.contains(bankAccount)) {
            bankAccount.setId(generateAccountId());
            bankAccounts.add(bankAccount);
            return bankAccount;
        }
        else
        {
            bankAccounts.set(bankAccounts.indexOf(bankAccount), bankAccount);
            return bankAccount;
        }
    }

    public long generateAccountId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/bankaccounts.json"), bankAccounts);
    }

}
