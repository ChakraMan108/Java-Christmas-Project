package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.bank.entity.Customer;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonCustomerRepository implements CustomerRepository {

    private Properties appProps = new Properties();
    private String dataPath;
    private ArrayList<Customer> customers = new ArrayList<>();
    
    public JsonCustomerRepository() {
        try {
            loadProperties();
            loadJson();
        } catch (RepositoryException | IOException ex) {
            System.out.println("[JsonCustomerRepository constructor error]" + ex.getMessage());
        }
    }

    public long count() throws RepositoryException {
        return customers.size();
    }

    public ArrayList<Customer> findAll() throws RepositoryException {
        return customers;
    }

    public Customer findById(long id) throws RepositoryException {
        for (Customer customer : customers) {
            if (customer.getId() == id)
                return customer;
        }
        throw new RepositoryException("Customer id " + id + " not found in the repository.");
    }

    public Customer save(Customer customer) throws RepositoryException {
        try {
            for (Customer c : customers) {
                if (c.getId() == customer.getId()) {
                    customers.set(customers.indexOf(c), customer);
                    saveJson();
                    return customer;
                }
            }
            customer.setId(generateId());
            customers.add(customer);
            saveJson();
            return customer;
        } catch (IOException ex) {
            throw new RepositoryException("[Customer Repository save error]" + ex.getMessage(), ex);
        }
    }

    public long generateId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        String jsonDataPath = getDataPath();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(jsonDataPath), customers);
    }

    public void loadJson() throws IOException {
        try {
            String jsonDataPath = getDataPath();
            File f = new File(jsonDataPath);
            if (f.exists() && !f.isDirectory()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                ArrayList<Customer> customerList = objectMapper.readValue(new File(jsonDataPath),
                        new TypeReference<ArrayList<Customer>>() {
                        });
                customers = customerList;
            } else {
                saveJson();
            }
        } catch (IOException ex) {
            throw new IOException("Error loading customer JSON file: " + ex.getMessage(), ex);
        }
    }

    public Properties loadProperties() throws RepositoryException {
        try {
            InputStream appConfigPath = JsonCustomerRepository.class.getClassLoader()
                    .getResourceAsStream("customer_repo.properties");
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