package com.bank.entity;

import java.time.LocalDate;

public class BankAccount {
   
   public enum AccountType {
      CURRENT_ACCOUNT,
      SAVING_ACCOUNT,
      STUDENT_ACCOUNT
   }

   private long id;
   private String accountName;
   private AccountType type;
   private long balance;
   private boolean isActive;
   private LocalDate createdDate;
   private LocalDate deactivatedDate;

   public BankAccount() {

   }

   public BankAccount(String accountName, AccountType type, long balance) {
      this.accountName = accountName;
      this.type = type;
      this.balance = balance;
      this.isActive = true;
      this.createdDate = LocalDate.now();
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getAccountName() {
      return accountName;
   }

   public void setAccountName(String accountName) {
      this.accountName = accountName;
   }

   public long getBalance() {
      return balance;
   }

   public void setBalance(long balance) {
      this.balance = balance;
   }

   public boolean isActive() {
      return isActive;
   }

   public void setActive(boolean isActive) {
      this.isActive = isActive;
   }

   public LocalDate getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(LocalDate createdDate) {
      this.createdDate = createdDate;
   }

   public LocalDate getDeactivatedDate() {
      return deactivatedDate;
   }

   public void setDeactivatedDate(LocalDate deactivatedDate) {
      this.deactivatedDate = deactivatedDate;
   }

   public AccountType getType() {
      return type;
   }

   public void setType(AccountType type) {
      this.type = type;
   }

   @Override
   public String toString() {
      return "BankAccount [id=" + id + ", accountName=" + accountName + ", type=" + type + ", balance=" + balance
            + ", isActive=" + isActive + ", createdDate=" + createdDate + ", deactivatedDate=" + deactivatedDate + "]";
   }



}