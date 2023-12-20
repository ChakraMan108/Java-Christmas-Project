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

    private static final CustomerRepository repository = new CustomerRepository();
    private OperationService opService = new OperationService();

    public long count() throws ServiceException {
        try {
            return repository.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Customer Repository by the Customer Service.", ex);
        }
    }

    public ArrayList<Customer> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Customer Repository by the Customer Service.", ex);
        }
    }

    public Customer findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Customer Repository by the Customer Service.", ex);         
        }
    }

    public Customer save(Customer customer) throws ServiceException {
        try { 
            return repository.save(customer);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Customer Repository by the Customer Service.", ex);
        }
    }

    public void deactivateCustomer(long id) throws ServiceException {
        try {
            Customer customer = repository.findById(id);
            if (!customer.isActive())
                throw new ServiceException("Cannot deactivate alredy deactivated customer id " + customer.getId(), Long.toString(customer.getId()));
            customer.setActive(false);
            customer.setDeactivatedDate(LocalDate.now());
            save(customer);
            
            Operation o = new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"), 0, customer.getId());
            opService.save(o);   
        } catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Customer Repository by the Customer Service.");
        }
    }

    public Customer createCustomer(Customer customer) throws ServiceException {
        try {
            Customer cust = new Customer();
            customer.setActive(true);
            customer.setCreatedDate(LocalDate.now());
            cust = save(customer);
            try {
                Operation o = new Operation(OperationType.CUSTOMER_CREATION,System.getProperty("user.name"), 0 , cust.getId());
                opService.save(o);
            } catch (ServiceException ex) { 
                throw new ServiceException("Exception received from the Operation Service by the Customer Service.");
            }
            return cust;
        } catch (ServiceException ex) {
            throw new ServiceException("Exception received from the Customer Service by the Customer Service.");
        }
    }

    public void saveJson() throws IOException {
        repository.saveJson();
    }
}




