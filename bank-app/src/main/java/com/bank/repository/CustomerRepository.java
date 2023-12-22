package com.bank.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.bank.entity.Customer;
import com.bank.exceptions.RepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomerRepository implements Repository<Customer> {

    private ArrayList<Customer> customers = new ArrayList<>();
    
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
        if (!customers.contains(customer)) {
            customer.setId(generateId());
            customers.add(customer);
            return customer;
        }
        else
        {
            customers.set(customers.indexOf(customer), customer);
            return customer;
        }
    }

    public long generateId() {
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public void saveJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File("../data/customers.json"), customers);
    }

}
