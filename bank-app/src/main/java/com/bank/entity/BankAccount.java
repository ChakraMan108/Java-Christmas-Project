package com.bank.entity;

import java.util.ArrayList;

import com.bank.service.BankAccountService;

public class BankAccount {
   private String AccountHolder;
   private int ID;
   private long balance;
   private Date createTimeStamp = new Date(); 
   //Creates timestamp of account generation
}

ArrayList <BankAccount> accounts = new ArrayList<BankAccount>() {

};
