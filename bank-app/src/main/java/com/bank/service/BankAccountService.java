package com.bank.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Operation.OperationType;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;

public final class BankAccountService implements Service<BankAccount> {

    private static BankAccountService INSTANCE;
    private String info = "Bank Account Service";

    public static BankAccountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAccountService();
        }
        return INSTANCE;
    }

    private BankAccountRepository repository = BankAccountRepository.getInstance();
    private TransactionService ts = TransactionService.getInstance();
    private OperationService os = OperationService.getInstance();

    public long count() throws ServiceException {
        try {
            return repository.count();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service count error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<BankAccount> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service findAll error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service findById error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount findByCustomerName(String name) throws ServiceException {
        try {
            return repository.findByCustomerName(name);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service findByCustomerName error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount findByCustomerId(long id) throws ServiceException {
        try {
            return repository.findByCustomerId(id);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service findByCustomerId error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount save(BankAccount bankAccount) throws ServiceException {
        try {
            return repository.save(bankAccount);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service save error] " + ex.getMessage(), ex);
        }
    }

    public void depositIntoAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (!account.isActive())
                throw new ServiceException("Cannot deposit into deactivated account id " + account.getId() + ".");
            if (account.getDeactivatedDate() != null)
                throw new ServiceException(
                        "Cannot deposit into account id " + account.getId() + " with deactivated date.");
            if (amount < 1)
                throw new ServiceException(
                        "Invalid deposit EUR " + amount / 100 + "." + String.format("%02d", amount % 100)
                                + " to account id " + account.getId());
            account.setBalance(account.getBalance() + amount);
            save(account);
            ts.save(new Transaction(amount, System.getProperty("user.name"),
                    Transaction.TransactionType.DEPOSIT, account.getId()));
        } catch (RepositoryException | ServiceException ex) {
            throw new ServiceException("[Bank Account Service depositIntoAccount error] " + ex.getMessage(), ex);
        }
    }

    public void withdrawFromAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = findById(id);
            if (!account.isActive())
                throw new ServiceException("Cannot deposit into deactivated account id " + account.getId() + ".");
            if (account.getDeactivatedDate() != null)
                throw new ServiceException(
                        "Cannot deposit into account id " + account.getId() + " with deactivated date.");
            if (account.getBalance() - amount < 0)
                throw new ServiceException("Insufficient balance to withdraw EUR " + amount / 100 + "."
                        + String.format("%02d", amount % 100) + " from account id " + account.getId() + ".");
            account.setBalance(account.getBalance() - amount);
            save(account);
            ts.save(new Transaction(amount, System.getProperty("user.name"),
                    Transaction.TransactionType.WITHDRAWAL, account.getId()));
        } catch (ServiceException ex) {
            throw new ServiceException("[Bank Account Service withdrawFromAccount error] " + ex.getMessage(), ex);
        }
    }

    public void deactivateAccount(long id) throws ServiceException {
        try {
            BankAccount account = findById(id);
            if (!account.isActive())
                throw new ServiceException(
                        "Cannot deactivate deactivated account id " + account.getId() + ".");
            if (account.getDeactivatedDate() != null)
                throw new ServiceException(
                        "Cannot deactivate account id " + account.getId() + " with a deactivated date.");
            if (account.getBalance() > 0)
                throw new ServiceException(String.format("Cannot deactivate account id %d with non-zero a balance of EUR %d.%02d.", account.getId(), account.getBalance() / 100 , account.getBalance() % 100));
            account.setActive(false);
            account.setDeactivatedDate(LocalDateTime.now());
            save(account);
            os.save(new Operation(OperationType.ACCOUNT_DEACTIVATION, System.getProperty("user.name"),
                    account.getId(), account.getCustomer().getId()));
        } catch (ServiceException ex) {
            throw new ServiceException("[Bank Account Service deactivateAccount error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount createAccount(BankAccount bankAccount, Customer customer) throws ServiceException {
        try {
            if (bankAccount == null || customer == null)
                throw new ServiceException("Cannot create account with null account or customer.");
            if (bankAccount.getId() != 0 || bankAccount.isActive() != false || bankAccount.getCreatedDate() != null || bankAccount.getDeactivatedDate() != null)
                throw new ServiceException("Cannot create account with non-empty account id, isActive createdDate or deactivatedDate.");
            if (!customer.isActive())
                throw new ServiceException("Cannot create account for deactivated customer.");
            if (customer.getId() <= 0)
                throw new ServiceException("Cannot create account for invalid customer id.");
            if (customer.getName() == null)
                throw new ServiceException("Cannot create account for customer with empty name.");
            if (customer.getAddress() == null)
                throw new ServiceException("Cannot create account for customer with empty address.");
            if (customer.getDob() == null)
                throw new ServiceException("Cannot create account for customer with empty date of birth.");
            if (customer.getPhoneNumber() == null)
                throw new ServiceException("Cannot create account for customer with empty phone number.");
            if (customer.getEmail() == null)
                throw new ServiceException("Cannot create account for customer with empty email.");
            if (customer.getType() == null)
                throw new ServiceException("Cannot create account for customer with empty type.");
            if (customer.getCreatedDate() == null)
                throw new ServiceException("Cannot create account for customer with empty created date.");
            if (customer.getDob() != null && customer.getDob().isAfter(LocalDate.now()))
                throw new ServiceException("Cannot create account for customer date of birth in the future.");
            if (customer.getCreatedDate() != null && customer.getCreatedDate().isAfter(LocalDateTime.now()))
                throw new ServiceException("Cannot create account for customer created date in the future.");
            if (customer.getDeactivatedDate() != null)
                throw new ServiceException("Cannot create account for customer with deactivated date.");
                
            BankAccount createdAccount = new BankAccount();
            bankAccount.setActive(true);
            bankAccount.setCreatedDate(LocalDateTime.now());
            bankAccount.setCustomer(customer);
            createdAccount = save(bankAccount);
            os.save(new Operation(OperationType.ACCOUNT_CREATION, System.getProperty("user.name"),
                    createdAccount.getCustomer().getId(), createdAccount.getId()));
            return createdAccount;
        } catch (ServiceException ex) {
            throw new ServiceException("[Bank Account Service createAccount error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount update(BankAccount bankAccount) throws ServiceException {
        try {
            if (bankAccount == null)
                throw new ServiceException("Cannot update null account.");
            if (bankAccount.getCustomer() == null)
                throw new ServiceException("Cannot update account id " + bankAccount.getId() + " with null customer.");
            if (!bankAccount.isActive())
                throw new ServiceException("Cannot update deactivated account id " + bankAccount.getId() + ".");
            if (!bankAccount.getCustomer().isActive())
                throw new ServiceException("Cannot update account id " + bankAccount.getId()
                        + " for deactivated customer id " + bankAccount.getCustomer().getId() + ".");
            if (bankAccount.getCustomer().getId() <= 0)
                throw new ServiceException(
                        "Cannot update account id " + bankAccount.getId() + " with invalid customer id.");
            if (bankAccount.getBalance() < 0)
                throw new ServiceException(
                        "Cannot update account id " + bankAccount.getId() + " with negative balance.");
            if (bankAccount.getId() <= 0)
                throw new ServiceException("Cannot update account with invalid id.");
            if (bankAccount.getDeactivatedDate() != null)
                throw new ServiceException(
                        "Cannot update activated account id " + bankAccount.getId() + " with deactivated date.");
            BankAccount udpdatedAccount = new BankAccount();
            udpdatedAccount = save(bankAccount);
            os.save(new Operation(OperationType.ACCOUNT_UPDATE, System.getProperty("user.name"),
                    udpdatedAccount.getCustomer().getId(), udpdatedAccount.getId()));
            return udpdatedAccount;
        } catch (ServiceException ex) {
            throw new ServiceException("[Bank Account Service update error] " + ex.getMessage(), ex);
        }
    }

    public void saveJson() throws ServiceException {
        try {
            repository.saveJson();
        } catch (IOException ex) {
            throw new ServiceException("[Bank Account Service saveJson error] " + ex.getMessage(), ex);
        }
    }

    public void loadJson() throws ServiceException {
        try {
            repository.loadJson();
        } catch (IOException ex) {
            throw new ServiceException("[Bank Account Service loadJson error] " + ex.getMessage(), ex);
        }
    }

    public String getInfo() {
        return info;
    }

}