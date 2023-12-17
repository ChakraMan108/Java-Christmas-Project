package com.bank.main;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;

public class Main {
    public static void main(String[] args) {
        
            Customer cust1 = new Customer("Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "joe@doe.com", Customer.CustomerType.INDIVIDUAL);
            System.out.println(cust1);

            BankAccount acc1 = new BankAccount("Cool", "1234567", 0);
            System.out.println(acc1);

            cust1.setAccount(acc1);
            System.out.println(cust1);
 
            String accountNumber = UUID.randomUUID().toString().replace("-", ""); 
            System.out.println("Generated Bank Account Number: " + accountNumber); 
            Random random = new Random();
            long randomNumber = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
            System.out.println("Random 10-digit number: " + randomNumber);
  } 
}       