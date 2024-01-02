package com.bank.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Operation.OperationType;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;

public class BankAccountService implements Service<BankAccount> {

    private static final BankAccountRepository repository = new BankAccountRepository();
    private TransactionService ts = new TransactionService();
    private OperationServiceTest os = new OperationServiceTest();

    public long count() throws ServiceException {
        try {
            return repository.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<BankAccount> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount findByCustomerName(String name) throws ServiceException {
        try {
            return repository.findByCustomerName(name);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount save(BankAccount bankAccount) throws ServiceException {
        try { 
            return repository.save(bankAccount);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public void depositIntoAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (amount < 1) {
               throw new ServiceException("[Bank Account Service error] Invalid deposit EUR " + amount/100 + " to account id " + account.getId(), Long.toString(account.getId()));           
            }
            account.setBalance(account.getBalance() + amount);
            save(account);
            try {
                Transaction t = new Transaction(amount, System.getProperty("user.name"),Transaction.TransactionType.DEPOSIT, account.getId());
                ts.save(t);
            } catch (ServiceException ex) {
                throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
            }
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public void withdrawFromAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (account.getBalance() - amount < 0) {
                throw new ServiceException("[Bank Account Service error] Insufficient balance to withdraw EUR " + amount/100 + " from account id " + account.getId(), Long.toString(account.getId()));
            }
            account.setBalance(account.getBalance() - amount);
            save(account);
            try {
                Transaction t = new Transaction(amount, System.getProperty("user.name"),Transaction.TransactionType.WITHDRAWAL, account.getId());
                ts.save(t);
            } catch (ServiceException ex) {
                throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
            }
        }
        catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public void deactivateAccount(long id) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (!account.isActive())
                throw new ServiceException("[Bank Account Service error] Cannot deactivate deactivated account id " + account.getId(), Long.toString(account.getId()));
            account.setActive(false);
            account.setDeactivatedDate(LocalDate.now());
            save(account);
            try {
                Operation o = new Operation(OperationType.ACCOUNT_DEACTIVATION, System.getProperty("user.name"), account.getId(), account.getCustomer().getId());
                os.save(o);   
            } catch (ServiceException ex) {
                throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
            }          
        } catch (RepositoryException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
    }

    public BankAccount createAccount(BankAccount bankAccount, Customer customer) throws ServiceException {
        if (!customer.isActive())
            throw new ServiceException("[Bank Account Service error] Cannot create account for deactivated customer.");
        BankAccount account = new BankAccount();
        bankAccount.setActive(true);
        bankAccount.setCreatedDate(LocalDate.now());
        bankAccount.setCustomer(customer);
        account = save(bankAccount);
        try {
            Operation o = new Operation(OperationType.ACCOUNT_CREATION, System.getProperty("user.name"), account.getCustomer().getId(), account.getId());
            os.save(o);
        } catch (ServiceException ex) {
            throw new ServiceException("[Bank Account Service error] " + ex.getMessage(), ex);
        }
        return account;
    }

    public void saveJson() throws IOException {
        repository.saveJson();
    }
}