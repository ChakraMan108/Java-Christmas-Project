package com.bank.entity;

import java.time.LocalDate;

import com.bank.service.BankAccountService;

public class BankAccount {
   private String accountName;
   private String accountNumber;
   private long balance;
   private boolean isActive;
   private LocalDate createdDate;
   private LocalDate deactivatedDate;

   public BankAccount(String accountName, String accountNumber, long balance){
      this.accountName = accountName;
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.isActive = true;
      this.createdDate = new Date();
   }

/* ****************************** */

   public String getaccountName(){
      return accountName;
   }

   public void setaccountName(){
      this.accountName = accountName;
   }

   public String getaccountNumber(){
      return accountNumber;
   }

   public void setaccountNumber(){
      this.accountNumber = accountNumber;
   }

   public long getBalance(){
      return balance;
   }

   public void setBalance(){
      this.balance = balance;
   }

   public boolean isActive(){
      return isActive;
   }

   public void setIsActive(){
      this.isActive = isActive;
   }

/* ****************************** */

   public void deposit(long amount){
      balance += amount;
   }

   public void withdraw(long amount){
      amount -= amount;
   }

   public String seeBal(){
      return toString();
   }

   public String openAccount(){

      return accountNumber;
   }

   public String closeAccount(){

      return accountNumber;
   }
}
