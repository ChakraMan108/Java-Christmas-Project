package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class BankAccountRepository implements Repository<BankAccount> {

    private static BankAccountRepository INSTANCE;
    private String info = "Bank Account Repository";

    public static BankAccountRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAccountRepository();
        }
        return INSTANCE;
    }

    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public long count() throws RepositoryException {
        return bankAccounts.size();
    }

    public ArrayList<BankAccount> findAll() throws RepositoryException {
        return bankAccounts;
    }

    public BankAccount findById(long id) throws RepositoryException {
        /*
         * using stream & lambda - return bankAccounts.stream().filter(a->a.getId() ==
         * id).collect(Collectors.toList()).get(0);
         */
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        throw new RepositoryException("Bank account id " + id + " not found in the repository.");
    }

    public BankAccount findByCustomerName(String name) throws RepositoryException {
        for (BankAccount ba : bankAccounts) {
            if (ba.getCustomer().getName().equals(name)) {
                return ba;
            }
        }
        throw new RepositoryException("Bank account linked to customer name " + name + " not found in the repository.");
    }

    public BankAccount findByCustomerId(long id) throws RepositoryException {
        for (BankAccount ba : bankAccounts) {
            if (ba.getCustomer().getId() == id) {
                return ba;
            }
        }
        System.err.println("Bank account linked to customer id " + id + " not found in the repository.");
        return null;
        //throw new RepositoryException("Bank account linked to customer id " + id + " not found in the repository.");
    }

    public BankAccount save(BankAccount bankAccount) throws RepositoryException {
        try {
            if (!bankAccounts.contains(bankAccount)) {
                bankAccount.setId(generateAccountId());
                bankAccounts.add(bankAccount);
                saveJson();
                return bankAccount;
            } else {
                bankAccounts.set(bankAccounts.indexOf(bankAccount), bankAccount);
                saveJson();
                return bankAccount;
            }
        } catch (IOException ex) {
            throw new RepositoryException("[Bank Account Reposity save error]" + ex.getMessage(), ex);
        }
    }

    public long generateAccountId() {
        // UUID.randomUUID().toString().replace("-", "");
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("c:/temp/bankaccounts.json"), bankAccounts);
    }

    public void loadJson() throws IOException {
        try {
            File f = new File("c:/temp/bankaccounts.json");
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<BankAccount> bankAccountList = objectMapper.readValue(new File("c:/temp/bankaccounts.json"),
                        new TypeReference<ArrayList<BankAccount>>() {
                        });
                bankAccounts = bankAccountList;
            } else {
                System.err.println("Creating new bankaccounts.json file.");
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