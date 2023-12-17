package com.bank.entity;

import java.util.Random;
import java.time.LocalDate;

import com.bank.service.BankAccountService;

public class BankAccount {
   private String accountName;
   private integer accountNumber;
   private long balance;
   private boolean isActive;
   private LocalDate createdDate;
   private LocalDate deactivatedDate;

/* ****************************** */
// Constructors

   public BankAccount(String accountName, String accountNumber, long balance){
      this.accountName = accountName;
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.isActive = true;
      this.createdDate = LocalDate.now();
   }

/* ****************************** */
// Getters and setters

   public String getaccountName(){
      return accountName;
   }

   public void setaccountName(String accountName){
      this.accountName = accountName;
   }

   public String getaccountNumber(){
      return accountNumber;
   }

   public void setaccountNumber(integer accountNumber){
      this.accountNumber = accountNumber;
   }

   public long getBalance(){
      return balance;
   }

   public void setBalance(long balance){
      this.balance = balance;
   }

   public boolean isActive(){
      return isActive;
   }

   public void setIsActive(boolean isActive){
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
      Random random = new Random();
      long randomNumber = (long)(random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;

      return accountNumber;
   }

   public String closeAccount(integer accountNumber){

      return accountNumber;
   }

   @Override
   public String toString() {
      return "BankAccount [accountName=" + accountName + ", accountNumber=" + accountNumber + ", balance=" + balance
            + ", isActive=" + isActive + ", createdDate=" + createdDate + ", deactivatedDate=" + deactivatedDate
            + ", accountNameg=" + accountNameg + ", accountNumberraccountNumber=" + accountNumberraccountNumber + "]";
   }
}
