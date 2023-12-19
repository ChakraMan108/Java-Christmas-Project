package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BankAccountRepository implements Repository<BankAccount> {

    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    // public BankAccountRepository(ArrayList<BankAccount> bankAccounts) {
    //     this.bankAccounts = bankAccounts;
    // }
    
    public long count() throws RepositoryException {
        if (!bankAccounts.isEmpty())
            return bankAccounts.size();    
        throw new RepositoryException("No bank account items found in the repository!");
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

    public long save(BankAccount bankAccount) throws RepositoryException {
        if (!bankAccounts.contains(bankAccount)) {
            bankAccount.setId(generateAccountId());
            bankAccount.setCreatedDate(LocalDate.now());
            bankAccount.setActive(true);
            bankAccounts.add(bankAccount);
            return bankAccount.getId();
        }
        else
        {
            if (!bankAccount.isActive())
                bankAccount.setDeactivatedDate(LocalDate.now());
            bankAccounts.set(bankAccounts.indexOf(bankAccount), bankAccount);
            return bankAccount.getId();
        }
    }

    public long generateAccountId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson(ArrayList<BankAccount> bankAccounts) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/bankaccounts.json"), bankAccounts);
    }

}
