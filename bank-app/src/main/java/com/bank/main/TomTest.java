package com.bank.main;

import java.time.LocalDate;
import com.bank.entity.BankAccount;
import com.bank.entity.Customer;

public class TomTest {
            
    public static void main(String[] args) {

        Customer cust1 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);

        Customer cust2 = new Customer(2, "Mary", "Dun Na Mara", LocalDate.parse("1980-08-01"), "087333444", "mary@x.com", Customer.CustomerType.INDIVIDUAL);
           
        Customer cust3 = new Customer();

        System.out.println(cust1);
        System.out.println(cust2);
        System.out.println(cust3);

        BankAccount acc1 = new BankAccount(1, "Tom", BankAccount.AccountType.CURRENT_ACCOUNT, 1000);

        System.out.println(acc1);
        cust1.setAccount(acc1);       
        System.out.println(cust1);


    }
}
