package com.bank.entity;

import com.bank.service.BankAccountService;

public class BankAccount {
   private String name;
   private String accNum;
   private double balance;

   public BankAccount(String name, String accNum, double balance){
      this.name = name;
      this.accNum = accNum;
      this.balance = balance;
   }

/* ****************************** */

   public String getName(){
      return name;
   }

   public void setName(){
      this.name = name;
   }

   public String getAccNum(){
      return accNum;
   }

   public void setAccNum(){
      this.accNum = accNum;
   }

   public double getBalance(){
      return balance;
   }

   public void setBalance(){
      this.balance = balance;
   }

/* ****************************** */

   public void deposit(double amount){
      balance += amount;
   }

   public void withdraw(double amount){
      amount -= amount;
   }

   public String seeBal(){
      return toString();
   }

   public String openAccount(){

      return accNum;
   }

   public String closeAccount(){

      return accNum;
   }

   public String toString(){
      return accNum + "\n" + name + "\n" + Double.toString(balance);
   }
   //Test change. 2 fergal
   //Rob changes
   // Tom change
}
