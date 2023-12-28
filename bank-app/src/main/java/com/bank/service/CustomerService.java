package com.bank.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Operation.OperationType;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.CustomerRepository;

public class CustomerService implements Service<Customer> {

    private final CustomerRepository repository = new CustomerRepository();
    private OperationService opService = new OperationService();

    public long count() throws ServiceException {
        try {
            return repository.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<Customer> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public Customer findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);       
        }
    }

    public Customer save(Customer customer) throws ServiceException {
        try { 
            return repository.save(customer);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public void deactivateCustomer(long id) throws ServiceException {
        try {
            Customer customer = repository.findById(id);
            if (!customer.isActive())
                throw new ServiceException("[Customer Service error] Cannot deactivate deactivated customer id " + customer.getId(), Long.toString(customer.getId()));
            customer.setActive(false);
            customer.setDeactivatedDate(LocalDate.now());
            save(customer);
            try {
                Operation o = new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"), 0, customer.getId());
                opService.save(o);
            } catch (ServiceException ex) {
                throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);          
            }

        } catch (RepositoryException ex) {
                throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public Customer createCustomer(Customer customer) throws ServiceException {
        Customer cust = new Customer();
        customer.setActive(true);
        customer.setCreatedDate(LocalDate.now());
        cust = save(customer);
        try {
            Operation o = new Operation(OperationType.CUSTOMER_CREATION,System.getProperty("user.name"), 0 , cust.getId());
            opService.save(o);
        } catch (ServiceException ex) { 
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
        return cust;
    }

    public void saveJson() throws IOException {
        try {
            repository.saveJson();
        } catch (IOException ex) {
            throw new IOException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public void loadJson() throws IOException {
        try {
            repository.loadJson();
        } catch (IOException ex) {
            throw new IOException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }
}