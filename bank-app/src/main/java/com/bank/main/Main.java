package com.bank.main;

import java.time.LocalDate;
import com.bank.entity.Customer;

public class Main {
    public static void main(String[] args) {
        
            Customer cust1 = new Customer("Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
            System.out.println(cust1);
    }

}