package com.bank.main;

import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.exceptions.ServiceException;
import com.bank.repository.BankAccountRepository;
import com.bank.service.BankAccountService;

public class TomTest {
            
    public static void main(String[] args) throws Exception {

        ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        BankAccountRepository repo = new BankAccountRepository(bankAccounts);
        BankAccountService service = new BankAccountService(repo);

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

        System.out.println(acc1.getId());

        for (BankAccount ba : bankAccounts) {
            System.out.println(ba);
        }
        
        System.out.println(service.findAll());

        System.out.println(service.findById(acc1.getId()));
        acc1.setAccountName("Tom");
        acc1.setActive(true);
        acc1.setBalance(1000);
        acc1.setCreatedDate(LocalDate.now());
        acc1.setType(BankAccount.AccountType.CURRENT_ACCOUNT);
        service.save(acc1);
        
        System.out.println(acc1);
        // System.out.println(service.findById(1234567));
    }
}
