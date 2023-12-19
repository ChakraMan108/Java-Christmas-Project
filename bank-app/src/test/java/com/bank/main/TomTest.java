package com.bank.main;

import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Operation;
import com.bank.entity.Transaction;
import com.bank.entity.Customer.CustomerType;
import com.bank.entity.Operation.OperationType;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.OperationRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.BankAccountService;
import com.bank.service.OperationService;

public class TomTest {
            
    public static void main(String[] args) throws Exception {

        ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        BankAccountRepository repo = new BankAccountRepository(bankAccounts);
        BankAccountService service = new BankAccountService(repo);

        ArrayList<Operation> operations = new ArrayList<Operation>();
        OperationRepository opRepo = new OperationRepository(operations);
        OperationService opService = new OperationService(opRepo);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        TransactionRepository transactionRepo = new TransactionRepository(transactions);

        Customer cust1 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
        Customer cust2 = new Customer(2, "Mary", "Dun Na Mara", LocalDate.parse("1980-08-01"), "087333444", "mary@x.com", Customer.CustomerType.INDIVIDUAL);
        Customer cust3 = new Customer();
        
        System.out.println(cust1);
        System.out.println(cust2);
        System.out.println(cust3);

        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount();
        
        System.out.println(acc1 + "\n" + acc2);
    
        System.out.println(service.save(acc1));
        System.out.println(service.save(acc2));

        try {
            System.out.println(service.count());
        } catch (ServiceException ex) {
            throw ex;
        }

        System.out.println(acc1);
        System.out.println(acc2);
       
        System.out.println(service.findAll());

        acc1.setAccountName("Tom");
        acc1.setActive(true);
        acc1.setBalance(1000);
        acc1.setCreatedDate(LocalDate.now());
        acc1.setType(BankAccount.AccountType.CURRENT_ACCOUNT);
        service.save(acc1);
        
        System.out.println(acc1);

        
        Operation op1 = new Operation();
        Operation op2 = new Operation();
        
        op1.setOperationtype(OperationType.ACCOUNT_CREATION);
        op1.setUsername(System.getProperty("user.name"));
        opService.save(op1);
        
        opService.save(op2);
        System.out.println(op1);
        System.out.println(op2);

        
        // System.out.println(opRepo.findAll().toString());
        // System.out.println(opService.findAll());

        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        
        System.out.println(t1);
        System.out.println(t2);

        transactionRepo.save(t1);
        transactionRepo.save(t2);
        
        System.out.println(transactionRepo.findAll().toString());
        //service.saveJson(bankAccounts);
        // System.out.println(service.findById(1234567));
    }
}
