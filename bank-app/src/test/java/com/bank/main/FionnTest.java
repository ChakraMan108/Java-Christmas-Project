package com.bank.main;

import java.time.LocalDate;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.BankAccount.AccountType;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;
import com.bank.service.CustomerService;
import com.bank.service.OperationService;
import com.bank.service.TransactionService;


public class FionnTest {
            
    public static void main(String[] args) throws Exception {

        // Service initialisation
        BankAccountService baService = new BankAccountService();
        OperationService opService = new OperationService();
        TransactionService trService = new TransactionService();
        CustomerService cuService = new CustomerService();
        
        // Customer creation       
        Customer c2 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
        cuService.createCustomer(c2);
        System.out.println("After customer creation:" + c2);

        // Customer deactivation
        cuService.deactivateCustomer(c2.getId());
        System.out.println("After customer deactivation:" + c2);

        // Customer Update
        c2.setEmail("ABC@ABC.ie");
        cuService.save(c2);
        System.out.println("After customer upodate:" + c2);

        //Find All Customers
        System.out.println(cuService.findAll());

        // Account Creation
        BankAccount acc2 = new BankAccount();  
        baService.createAccount(acc2);
        System.out.println(acc2);

        // Account Deactivation
        baService.deactivateAccount(acc2.getId());
        System.out.println(acc2);
        
        // Account Update
        acc2.setType(AccountType.SAVING_ACCOUNT);
        baService.save(acc2);

        // Account Deposit
        baService.depositIntoAccount(baService.findById(baService.save(acc2).getId()).getId(), 100000);
        baService.depositIntoAccount(baService.findById(baService.save(acc2).getId()).getId(), 1);
        
        //Account Withdrawal
        baService.withdrawFromAccount(baService.findById(baService.save(acc2).getId()).getId(), 50000);
        baService.withdrawFromAccount(baService.findById(baService.save(acc2).getId()).getId(), 50);

        //Find All Accounts
        System.out.println(baService.findAll());

        // Find All Operations
        System.out.println(opService.findAll());
        
        // Find All Transactions
        System.out.println(trService.findAll());

        


        //Count all Customers
        System.out.println("Number of customers: " + cuService.count());

        //Count all Accounts
        System.out.println("Number of accounts: " + baService.count());

        //Count all Transactions
        System.out.println("Number of transactions: " + trService.count());

        //Count all Operations
        System.out.println("Number of operations: " + opService.count());


        // FindById
        System.out.println(trService.findById(2));

        trService.save();

        baService.saveJson();
        
    }
}