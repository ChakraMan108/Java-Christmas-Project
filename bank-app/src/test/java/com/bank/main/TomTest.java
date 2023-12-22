package com.bank.main;

import java.time.LocalDate;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.exceptions.MenuException;
import com.bank.entity.BankAccount.AccountType;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;
import com.bank.service.OperationService;
import com.bank.service.TransactionService;
import com.bank.ui.UiImpl;

public class TomTest {
            
    public static void main(String[] args) throws Exception {

        // Service initialisation
        BankAccountService baService = new BankAccountService();
        OperationService opService = new OperationService();
        TransactionService trService = new TransactionService();
        CustomerService cuService = new CustomerService();
        
        // Customer creation       
        Customer c1 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
        Customer c2 = new Customer(1, "Avaya", "Mervue", LocalDate.parse("2000-04-15"), "086896457", "avaya@avaya.com", Customer.CustomerType.COMPANY);
        
         
        cuService.createCustomer(c1);
        cuService.createCustomer(c2);
        System.out.println("\nAfter customer creation:\n" + c1);
        System.out.println("\nAfter customer creation:\n" + c2);

        // Customer deactivation
        cuService.deactivateCustomer(c1.getId());
        System.out.println("\nAfter customer deactivation:\n" + c1);

        // Customer Update
        c2.setPhoneNumber("0871111111");
        c2.setActive(true);
        cuService.save(c2);
        System.out.println("\nAfter customer update:\n" + c2);

        // Account Creation
        BankAccount acc2 = new BankAccount(); 
        Customer foundCustomer = cuService.findById(c2.getId()); 
        baService.createAccount(acc2, foundCustomer);
        System.out.println("\nAfter account creation:\n" + acc2);

        // Account Deactivation
        baService.deactivateAccount(acc2.getId());
        System.out.println("\nAfter account deactivation:\n" + acc2);
        
        // Account Update
        acc2.setType(AccountType.SAVING_ACCOUNT);
        baService.save(acc2);
        System.out.println("\nAfter account update:\n" + acc2);

        // Account Deposit
        long depositIntoId = baService.save(acc2).getId();
        baService.depositIntoAccount(baService.findById(depositIntoId).getId(), 100000);
        baService.depositIntoAccount(baService.findById(depositIntoId).getId(), 1);
        System.out.println("\nAfter account deposit:\n" + acc2);

        //Account Withdrawal
         long withdrawIntoId = baService.save(acc2).getId();
        baService.withdrawFromAccount(baService.findById(withdrawIntoId).getId(), 50000);
        baService.withdrawFromAccount(baService.findById(withdrawIntoId).getId(), 500);
        System.out.println("\nAfter account withdrawal:\n" + acc2);

        System.out.println("Balance of account id + " + acc2.getId() + " is EUR " + acc2.getBalance()/100);

        // Customer deactivation after account creation
        cuService.deactivateCustomer(c2.getId());
        System.out.println("\nAfter customer deactivation:\n" + c2);

        //Find All Accounts
        System.out.println("\nAll accounts:\n" + baService.findAll());

        // Find All Operations
        System.out.println("\nAll operations:\n" + opService.findAll());
        
        // Find All Transactions
        System.out.println("\nAll transactions:\n" + trService.findAll());

        //Find All Customers
        System.out.println("\nAll customers:\n" + cuService.findAll());

        //Count all Customers
        System.out.println("Number of customers: " + cuService.count());

        //Count all Accounts
        System.out.println("Number of accounts: " + baService.count());

        //Count all Transactions
        System.out.println("Number of transactions: " + trService.count());

        //Count all Operations
        System.out.println("Number of operations: " + opService.count());

        cuService.saveJson();
        baService.saveJson();
        trService.saveJson();
        opService.saveJson();

        UiImpl ui = new UiImpl();
                
        try {
            ui.authenticateApp();
            ui.displayMenu();
        } catch (MenuException ex) {
            System.out.println("Menu returned an error: " + ex.getMessage());   
        }
    }
}

