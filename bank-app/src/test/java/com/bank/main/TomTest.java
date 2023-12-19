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

        Customer c1 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
        Customer c2 = new Customer(2, "Mary", "Dun Na Mara", LocalDate.parse("1980-08-01"), "087333444", "mary@x.com", Customer.CustomerType.INDIVIDUAL);
        
        System.out.println(c1);
        System.out.println(c2);
        
        BankAccount acc2 = new BankAccount();  
  
        System.out.println(acc2);
        baService.save(acc2);

        System.out.println(baService.findAll());

        // Account creation
        BankAccount acc1 = new BankAccount();
        
        System.out.println(opService.findAll());
        //service.saveJson(bankAccounts);

        //Bank Account and Transaction testing
        baService.depositIntoAccount(baService.findById(baService.save(acc2)).getId(), 100000);
        baService.depositIntoAccount(baService.findById(baService.save(acc2)).getId(), 1);
        baService.withdrawFromAccount(baService.findById(baService.save(acc2)).getId(), 50000);
        baService.withdrawFromAccount(baService.findById(baService.save(acc2)).getId(), 50);

        System.out.println(baService.findAll());

        System.out.println(trService.findAll());

        System.out.println("Number of transactions: " + trService.count());
    }
}
