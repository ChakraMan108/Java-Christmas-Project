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

public final class CustomerService implements Service<Customer> {

    private static CustomerService INSTANCE;
    private String info = "Customer Service";

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }

    private CustomerRepository repository = CustomerRepository.getInstance();
    private OperationService opService = OperationService.getInstance();
    private BankAccountService baService = BankAccountService.getInstance();

    public long count() throws ServiceException {
        try {
            loadJson();
            return repository.count();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Customer Service count error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<Customer> findAll() throws ServiceException {
        try {
            loadJson();
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
            if (customer.equals(null))
                throw new ServiceException("Cannot update null customer.");
            if (!customer.isActive())
                throw new ServiceException("Cannot update deactivated customer id " + customer.getId() + ".");
            if (customer.getId() <= 0)
                throw new ServiceException("Cannot update customer with invalid id.");
            if (customer.getName().equals(""))
                throw new ServiceException("Cannot update customer with empty name.");
            if (customer.getAddress().equals(""))
                throw new ServiceException("Cannot update customer with empty address.");
            if (customer.getDob().equals(null))
                throw new ServiceException("Cannot update customer with empty date of birth.");
            if (customer.getPhoneNumber().equals(""))
                throw new ServiceException("Cannot update customer with empty phone number.");
            if (customer.getEmail().equals(""))
                throw new ServiceException("Cannot update customer with empty email.");
            if (customer.getType() == null)
                throw new ServiceException("Cannot update customer with empty type.");
            if (customer.getCreatedDate().equals(null))
                throw new ServiceException("Cannot update customer with empty created date.");
            if (customer.getCreatedDate().isAfter(LocalDate.now()))
                throw new ServiceException("Cannot update customer with created date in the future.");
            if (customer.getCreatedDate().isBefore(customer.getDob()))
                throw new ServiceException("Cannot update customer with created date before date of birth.");
            if (customer.getDeactivatedDate() != null) {
                if (customer.getDeactivatedDate().isAfter(LocalDate.now()))
                    throw new ServiceException("Cannot update customer with deactivated date in the future.");
                if (customer.getDeactivatedDate().isBefore(customer.getCreatedDate()))
                    throw new ServiceException("Cannot update customer with deactivated date before created date.");
                if (customer.getDeactivatedDate().isBefore(customer.getDob()))
                    throw new ServiceException("Cannot update customer with deactivated date before date of birth.");
                if (customer.isActive())
                    throw new ServiceException("Cannot update activated customer with deactivated date.");
            }
            if (!findById(customer.getId()).equals(customer)) {
                Customer updatedCustomer = save(customer);
                BankAccount bankAccount = baService.findByCustomerId(customer.getId());
                Operation o = new Operation();

                if (bankAccount != null) {
                    bankAccount.setCustomer(updatedCustomer);
                    baService.save(bankAccount);
                    o = new Operation(OperationType.CUSTOMER_UPDATE, System.getProperty("user.name"), bankAccount.getId(),
                        customer.getId());
                } else {
                    updatedCustomer = save(customer);
                    o = new Operation(OperationType.CUSTOMER_UPDATE, System.getProperty("user.name"), 0, customer.getId());
                }
                opService.save(o);
                return updatedCustomer;
            }
            else {
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
                throw new ServiceException("Cannot deactivate deactivated customer id " + customer.getId());
            else {
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

    public String getInfo() {
        return info;
    }
}