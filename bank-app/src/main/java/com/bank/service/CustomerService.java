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
        } catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service count error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<Customer> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service findAll error] " + ex.getMessage(), ex);
        }
    }

    public Customer findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service findById error] " + ex.getMessage(), ex);
        }
    }

    public Customer save(Customer customer) throws ServiceException {
        try {
            return repository.save(customer);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service save error] " + ex.getMessage(), ex);
        }
    }

    public Customer update(Customer customer) throws ServiceException {
        try {
            Customer updatedCustomer = new Customer();
            if (!customer.isActive())
                throw new ServiceException("Cannot update deactivated customer id " + customer.getId() + ".");
            BankAccount bankAccount = baService.findByCustomerId(customer.getId());
            if (bankAccount != null) {
                updatedCustomer = save(customer);
                bankAccount.setCustomer(updatedCustomer);
                baService.save(bankAccount);
                Operation o = new Operation(OperationType.CUSTOMER_UPDATE, System.getProperty("user.name"),
                        bankAccount.getId(), customer.getId());
                opService.save(o);
            } else {
                updatedCustomer = save(customer);
                Operation o = new Operation(OperationType.CUSTOMER_UPDATE, System.getProperty("user.name"),
                        0, customer.getId());
                opService.save(o);
            }
            return updatedCustomer;   
        } catch (ServiceException ex) {
            throw new ServiceException("[Customer Service update error] " + ex.getMessage(), ex);
        }
    }

    public void deactivateCustomer(long id) throws ServiceException {
        try {
            Customer customer = repository.findById(id);
            if (!customer.isActive())
                throw new ServiceException("Cannot deactivate deactivated customer id " + customer.getId());
            BankAccount bankAccount = baService.findByCustomerId(id);
            if (bankAccount != null) {
                if (bankAccount.getCustomer().getId() == customer.getId() && bankAccount.isActive()) {
                    throw new ServiceException("Cannot deactivate customer id " + customer.getId()
                            + " with an active bank account id " + bankAccount.getId() + ".");
                }
                customer.setActive(false);
                customer.setDeactivatedDate(LocalDate.now());
                save(customer);
                bankAccount.setCustomer(customer);
                baService.save(bankAccount);
                Operation o = new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"),
                        bankAccount.getId(), customer.getId());
                opService.save(o);
            } else {
                customer.setActive(false);
                customer.setDeactivatedDate(LocalDate.now());
                save(customer);
                Operation o = new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"),
                        0, customer.getId());
                opService.save(o);
            }
        } catch (RepositoryException | ServiceException ex) {
            throw new ServiceException("[Customer Service deactivateCustomer error] " + ex.getMessage(), ex);
        }
    }

    public Customer createCustomer(Customer customer) throws ServiceException {
        try {
            Customer cust = new Customer();
            customer.setActive(true);
            customer.setCreatedDate(LocalDate.now());
            cust = save(customer);
            Operation o = new Operation(OperationType.CUSTOMER_CREATION, System.getProperty("user.name"), 0,
                    cust.getId());
            opService.save(o);
            return cust;
        } catch (ServiceException ex) {
            throw new ServiceException("[Customer Service createCustomer error] " + ex.getMessage(), ex);
        }
    }

    public void saveJson() throws ServiceException {
        try {
            repository.saveJson();
        } catch (IOException ex) {
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }

    public void loadJson() throws ServiceException {
        try {
            repository.loadJson();
        } catch (IOException ex) {
            throw new ServiceException("[Customer Service error] " + ex.getMessage(), ex);
        }
    }
}