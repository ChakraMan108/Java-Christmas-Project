package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.bank.entity.Customer;
import com.bank.exceptions.RepositoryException;
import com.bank.ui.Ui;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class CustomerRepository implements Repository<Customer> {

    private static CustomerRepository INSTANCE;
    private String info = "Customer Repository";

    public static CustomerRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerRepository();
        }
        return INSTANCE;
    }

    private ArrayList<Customer> customers = new ArrayList<>();

    private String jsonDataPath = Ui.getDataPath() + "/customers.json";

    
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
        try {
            jsonDataPath = Ui.getDataPath() + "/customers.json";
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(new File(jsonDataPath), customers);
        } catch (IOException ex) {
            throw new IOException("Error saving customer JSON file: " + ex.getMessage(), ex);
        }
    }

    public void loadJson() throws IOException {
        try {
            jsonDataPath = Ui.getDataPath() + "/customers.json";
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

    public String getInfo() {
        return info;
    }

}