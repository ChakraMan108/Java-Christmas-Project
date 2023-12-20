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
    private OperationService os = new OperationService();

    public long count() throws ServiceException {
        try {
            return repository.count();
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.", ex);
        }
    }

    public ArrayList<BankAccount> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.", ex);
        }
    }

    public BankAccount findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.", ex);         
        }
    }

    public BankAccount save(BankAccount bankAccount) throws ServiceException {
        try { 
            return repository.save(bankAccount);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.", ex);
        }
    }

    public void depositIntoAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (amount < 1) {
               throw new ServiceException("Cannot deposit €" + amount/100 + " to account id " + account.getId(), Long.toString(account.getId()));           
            }
            account.setBalance(account.getBalance() + amount);
            save(account);

            Transaction t = new Transaction(amount, System.getProperty("user.name"),Transaction.TransactionType.DEPOSIT, account.getId());
            ts.save(t);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.");
        }
    }

    public void withdrawFromAccount(long id, long amount) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (account.getBalance() - amount < 0) {
                throw new ServiceException("Insufficient balance to withdraw €" + amount/100 + " from account id " + account.getId(), Long.toString(account.getId()));
            }
            account.setBalance(account.getBalance() - amount);
            save(account);
            
            Transaction t = new Transaction(amount, System.getProperty("user.name"),Transaction.TransactionType.WITHDRAWAL, account.getId());
            ts.save(t);
        }
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.");
        }
    }

    public void deactivateAccount(long id) throws ServiceException {
        try {
            BankAccount account = repository.findById(id);
            if (!account.isActive())
                throw new ServiceException("Cannot deactivate already deactivated account id " + account.getId(), Long.toString(account.getId()));
            account.setActive(false);
            account.setDeactivatedDate(LocalDate.now());
            save(account);
            Operation o = new Operation(OperationType.ACCOUNT_DEACTIVATION, System.getProperty("user.name"), account.getId(), account.getCustomer().getId());
            os.save(o);   
        } catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Bank Account Repository by the Bank Account Service.");
        }
    }

    public BankAccount createAccount(BankAccount bankAccount, Customer customer) throws ServiceException {
        try {
            if (!customer.isActive())
                throw new ServiceException("Cannot create account for inactive customer!");
            BankAccount account = new BankAccount();
            bankAccount.setActive(true);
            bankAccount.setCreatedDate(LocalDate.now());
            bankAccount.setCustomer(customer);
            account = save(bankAccount);
            try {
                Operation o = new Operation(OperationType.ACCOUNT_CREATION, System.getProperty("user.name"), account.getCustomer().getId(), account.getId());
                os.save(o);
            } catch (ServiceException ex) { 
                throw ex;
            }
            return account;
        } catch (ServiceException ex) {
            throw new ServiceException("Exception received from the Bank Account Service by the Bank Account Service.");
        }
    }

    public void saveJson() throws IOException {
        repository.saveJson();
    }
}




