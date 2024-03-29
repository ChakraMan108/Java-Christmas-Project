package com.bank.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Operation.OperationType;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private OperationService opService;
    private BankAccountService baService;

    public CustomerServiceImpl(CustomerRepository repo, BankAccountService baService, OperationService opService)
	{
		this.repository = repo;
        this.baService = baService;
        this.opService = opService;
	}

    public long count() throws ServiceException {
        try {
            return repository.count();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service count error] " + ex.getMessage(), ex);
        }
    }

    public Iterable<? extends Customer> findAll() throws ServiceException {
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

    public Customer createCustomer(Customer customer) throws ServiceException {
        try {
            if (customer == null)
                throw new ServiceException("Cannot create null customer.");
            if (customer.getId() != 0 || customer.isActive() != false || customer.getCreatedDate() != null || customer.getDeactivatedDate() != null)
                throw new ServiceException("Cannot create customer with non-empty id, isActive, createdDate or deactivatedDate.");
            if (customer.getName() == null)
                throw new ServiceException("Cannot create customer with empty name.");
            if (customer.getAddress() == null)
                throw new ServiceException("Cannot create customer with empty address.");
            if (customer.getDob() == null)
                throw new ServiceException("Cannot create customer with empty date of birth.");
            if (customer.getPhoneNumber() ==null)
                throw new ServiceException("Cannot create customer with empty phone number.");
            if (customer.getEmail() == null)
                throw new ServiceException("Cannot create customerwith empty email.");
            if (customer.getType() == null)
                throw new ServiceException("Cannot create customer with empty type.");
            if (customer.getDob() != null && customer.getDob().isAfter(LocalDate.now()))
                throw new ServiceException("Cannot create customer with date of birth in the future.");

            customer.setActive(true);
            customer.setCreatedDate(LocalDateTime.now());
            Customer cust = new Customer(save(customer));
            opService.save(new Operation(OperationType.CUSTOMER_CREATION, System.getProperty("user.name"), 0,
                    cust.getId()));
            return cust;
        } catch (ServiceException ex) {
            throw new ServiceException("[Customer Service createCustomer error] " + ex.getMessage(), ex);
        }
    }

    public Customer update(Customer customer) throws ServiceException {
        try {
            if (customer == null)
                throw new ServiceException("Cannot update null customer.");
            if (!customer.isActive())
                throw new ServiceException("Cannot update deactivated customer id " + customer.getId() + ".");
            if (customer.getId() <= 0)
                throw new ServiceException("Cannot update customer with invalid id " + customer.getId() + ".");
            if (customer.getCreatedDate() != null && customer.getCreatedDate().isAfter(LocalDateTime.now()))
                throw new ServiceException(
                        "Cannot update customer id " + customer.getId() + " with created date in the future.");
            if (customer.getDeactivatedDate() != null)
                throw new ServiceException("Cannot update customer id " + customer.getId() + " with deactivated date.");
            if (!findById(customer.getId()).equals(customer)) {
                Customer updatedCustomer = save(customer);
                BankAccount bankAccount = baService.findByCustomerId(customer.getId());
                Operation o = new Operation();
                if (bankAccount != null) {
                    bankAccount.setCustomer(updatedCustomer);
                    baService.save(bankAccount);
                    o = new Operation(OperationType.CUSTOMER_UPDATE, System.getProperty("user.name"),
                            bankAccount.getId(),
                            customer.getId());
                } else {
                    updatedCustomer = save(customer);
                    o = new Operation(OperationType.CUSTOMER_UPDATE, System.getProperty("user.name"), 0,
                            customer.getId());
                }
                opService.save(o);
                return updatedCustomer;
            } else {
                // Update not needed, the customer is the same
                return null;
            }
        } catch (ServiceException ex) {
            throw new ServiceException("[Customer Service update error] " + ex.getMessage(), ex);
        }
    }

    public void deactivateCustomer(long id) throws ServiceException {
        try {
            Customer customer = repository.findById(id);
            if (!customer.isActive())
                throw new ServiceException("Cannot deactivate deactivated customer id " + customer.getId() + ".");
            if (customer.getDeactivatedDate() != null)
                throw new ServiceException(
                        "Cannot deactivate customer id " + customer.getId() + " with deactivated date.");

            BankAccount bankAccount = baService.findByCustomerId(id);
            if (bankAccount != null) {
                if (bankAccount.isActive())
                    throw new ServiceException("Cannot deactivate customer id " + customer.getId()
                            + " with an active bank account id " + bankAccount.getId() + ".");
                if (bankAccount.getBalance() < 0)
                    throw new ServiceException("Cannot deactivate customer id " + customer.getId()
                            + " with a bank account id " + bankAccount.getId() + " with a negative balance of "
                            + bankAccount.getBalance() + ".");
                if (bankAccount.getBalance() > 0)
                    throw new ServiceException("Cannot deactivate customer id " + customer.getId()
                            + " with a bank account id " + bankAccount.getId() + " with a positive balance of "
                            + bankAccount.getBalance() + ".");

                customer.setActive(false);
                customer.setDeactivatedDate(LocalDateTime.now());
                bankAccount.setCustomer(save(customer));
                baService.save(bankAccount);
                opService.save(new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"),
                        bankAccount.getId(), customer.getId()));
            } else {
                customer.setActive(false);
                customer.setDeactivatedDate(LocalDateTime.now());
                save(customer);
                opService.save(new Operation(OperationType.CUSTOMER_DEACTIVATION, System.getProperty("user.name"), 0,
                        customer.getId()));
            }
        } catch (RepositoryException | ServiceException ex) {
            throw new ServiceException("[Customer Service deactivateCustomer error] " + ex.getMessage(), ex);
        }
    }
}