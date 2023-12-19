package com.bank.main;

import java.time.LocalDate;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Operation.OperationType;
import com.bank.entity.Transaction;
import com.bank.entity.Transaction.TransactionType;
import com.bank.service.BankAccountService;
import com.bank.service.OperationService;
import com.bank.service.TransactionService;

public class TomTest {
            
    public static void main(String[] args) throws Exception {

        BankAccountService baService = new BankAccountService();
        OperationService opService = new OperationService();
        TransactionService trService = new TransactionService();

        Customer cust1 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
        Customer cust2 = new Customer(2, "Mary", "Dun Na Mara", LocalDate.parse("1980-08-01"), "087333444", "mary@x.com", Customer.CustomerType.INDIVIDUAL);
        
        System.out.println(cust1);
        System.out.println(cust2);
        
        BankAccount acc2 = new BankAccount();  
        long someId = baService.save(acc2);
  
        System.out.println(acc2);
       
        System.out.println(baService.findAll());

        // Account creation
        Customer c1 = new Customer();
        BankAccount acc1 = new BankAccount();
        Operation op1 = new Operation();

        acc1.setAccountName("Tom");
        acc1.setBalance(0);
        acc1.setType(BankAccount.AccountType.CURRENT_ACCOUNT);

        op1.setOperationtype(OperationType.ACCOUNT_CREATION);
        op1.setUsername(System.getProperty("user.name"));
        op1.setAccountId(baService.save(acc1));
        opService.save(op1);
        System.out.println(op1); 
             
        //service.saveJson(bankAccounts);

        //Bank Account and Transaction testing
        Transaction t1 = new Transaction();
        BankAccount ba1 = baService.findById(someId);
        baService.depositIntoAccount(ba1.getId(), 100000);
        t1.setType(TransactionType.DEPOSIT);
        t1.setUsername(System.getProperty("user.name"));
        t1.setAmount(100000);
        trService.save(t1);

        //Bank Account and Transaction testing
        Transaction t2 = new Transaction();
        BankAccount ba2 = baService.findById(someId);
        baService.withdrawFromAccount(ba2.getId(), 50000);
        t2.setType(TransactionType.WITHDRAWAL);
        t2.setUsername(System.getProperty("user.name"));
        t2.setAmount(50000);
        trService.save(t2);

        System.out.println("After deposit\n" + trService.findAll().toString());

        System.out.println(baService.findAll());
    }
}
