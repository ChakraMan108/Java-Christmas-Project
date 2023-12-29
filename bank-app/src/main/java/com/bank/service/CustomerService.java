package com.bank.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Operation.OperationType;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.CustomerRepository;

public class CustomerService implements Service<Customer> {

    private CustomerRepository repository = CustomerRepository.getInstance();
    private OperationService opService = new OperationService();
    private BankAccountService baService = new BankAccountService();

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
            BankAccount bankAccount = baService.findByCustomerId(id);
            Customer customer = repository.findById(id);
            if (!customer.isActive())
                throw new ServiceException("[Customer Service error] Cannot deactivate deactivated customer id " + customer.getId());
            if (bankAccount.getCustomer().getId() == customer.getId()) {
                    if (bankAccount.isActive()) {
                        throw new ServiceException("[Customer Service error] Cannot deactivate customer id " + customer.getId() + " with active bank account id " + bankAccount.getId());
                    }
                    else {
                        customer.setActive(false);
                        customer.setDeactivatedDate(LocalDate.now());
                        bankAccount.setCustomer(customer);
                        baService.save(bankAccount);
                        save(customer);
                    }
            }
            Operation o = new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"), bankAccount.getId() , customer.getId());
            opService.save(o);
        } catch (RepositoryException | ServiceException ex) {
                throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public Customer createCustomer(Customer customer) throws ServiceException {
     try {
        Customer cust = new Customer();
        customer.setActive(true);
        customer.setCreatedDate(LocalDate.now());
        cust = save(customer);
        Operation o = new Operation(OperationType.CUSTOMER_CREATION,System.getProperty("user.name"), 0 , cust.getId());
        opService.save(o);
        return cust;    
        } catch (ServiceException ex) { 
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
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