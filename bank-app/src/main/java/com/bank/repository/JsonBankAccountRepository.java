package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonBankAccountRepository implements BankAccountRepository {

    private Properties appProps = new Properties();
    private String dataPath;
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public JsonBankAccountRepository() {
        try {
            loadProperties();
            loadJson();
        } catch (RepositoryException | IOException ex) {
            System.out.println("[JsonBankAccountRepository constructor error]" + ex.getMessage());
        }
    }

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
        //System.err.println("Bank account linked to customer id " + id + " not found in the repository.");
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
        String jsonDataPath = getDataPath();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(jsonDataPath), bankAccounts);
    }

    public void loadJson() throws IOException {
        try {
            String jsonDataPath = getDataPath();
            File f = new File(jsonDataPath);
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<BankAccount> bankAccountList = objectMapper.readValue(new File(jsonDataPath),
                        new TypeReference<ArrayList<BankAccount>>() {
                        });
                bankAccounts = bankAccountList;
            } else {
                saveJson();
            }
        } catch (IOException ex) {
            throw new IOException("Error loading customer JSON file: " + ex.getMessage(), ex);
        }
    }

    public Properties loadProperties() throws RepositoryException {
        try {
            InputStream appConfigPath = JsonBankAccountRepository.class.getClassLoader()
                    .getResourceAsStream("bankaccount_repo.properties");
            appProps.load(appConfigPath);
            return appProps;
        } catch (IOException e) {
            throw new RepositoryException("[TransactionRepository loadProperties error]" + e.getMessage());
        }
    }   

    private String getDataPath() {
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            dataPath = appProps.getProperty("app.win.path");
        else
            dataPath = appProps.getProperty("app.nix.path");
        return dataPath;
    }
}