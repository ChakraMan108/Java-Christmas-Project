package com.bank.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
   private LocalDateTime createdDate;
   private LocalDateTime deactivatedDate;
   private Customer customer;

   // Empty constructor for instantiating empty/null objects for testing etc.
   public BankAccount() {

   }

   // Parametrised constructor taking mandatory fields
   public BankAccount(String accountName, AccountType type, long balance) {
      this.accountName = accountName;
      this.type = type;
      this.balance = balance;
   }

   // Parametrised constructor taking all fields from a customer object to create a copy
   public BankAccount(BankAccount existingAccount) {
      this.id = existingAccount.id;
      this.accountName = existingAccount.accountName;
      this.type = existingAccount.type;
      this.balance = existingAccount.balance;
      this.isActive = existingAccount.isActive;
      this.createdDate = existingAccount.createdDate;
      this.deactivatedDate = existingAccount.deactivatedDate;
      this.customer = existingAccount.customer;
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

   public LocalDateTime getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(LocalDateTime createdDate) {
      this.createdDate = createdDate;
   }

   public LocalDateTime getDeactivatedDate() {
      return deactivatedDate;
   }

   public void setDeactivatedDate(LocalDateTime deactivatedDate) {
      this.deactivatedDate = deactivatedDate;
   }

   public AccountType getType() {
      return type;
   }

   public void setType(AccountType type) {
      this.type = type;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (id ^ (id >>> 32));
      result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
      result = prime * result + ((type == null) ? 0 : type.hashCode());
      result = prime * result + (int) (balance ^ (balance >>> 32));
      result = prime * result + (isActive ? 1231 : 1237);
      result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
      result = prime * result + ((deactivatedDate == null) ? 0 : deactivatedDate.hashCode());
      result = prime * result + ((customer == null) ? 0 : customer.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      BankAccount other = (BankAccount) obj;
      if (id != other.id)
         return false;
      if (accountName == null) {
         if (other.accountName != null)
            return false;
      } else if (!accountName.equals(other.accountName))
         return false;
      if (type != other.type)
         return false;
      if (balance != other.balance)
         return false;
      if (isActive != other.isActive)
         return false;
      if (createdDate == null) {
         if (other.createdDate != null)
            return false;
      } else if (!createdDate.equals(other.createdDate))
         return false;
      if (deactivatedDate == null) {
         if (other.deactivatedDate != null)
            return false;
      } else if (!deactivatedDate.equals(other.deactivatedDate))
         return false;
      if (customer == null) {
         if (other.customer != null)
            return false;
      } else if (!customer.equals(other.customer))
         return false;
      return true;
   }

   @Override
   public String toString() {  
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      String createdDateStr = null;
      String deactivatedDateStr = null;
      if (createdDate != null) {
          createdDateStr = createdDate.format(formatter);
      }
      else {
          createdDateStr = "N/A";
      }
      if (deactivatedDate != null) {
          deactivatedDateStr = deactivatedDate.format(formatter);
      }
      else {
          deactivatedDateStr = "N/A";
      }
      return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: " + id + "\nAccount Name: " + accountName + "\nAccount Type: " + type
            + "\nBalance: " + balance / 100 + "." + String.format("%02d", balance % 100) + "\nActive: " + isActive + "\nCreated Date: " + createdDateStr
            + "\nDeactivated Date: " + deactivatedDateStr + "\nCustomer:\n" + customer + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
   }
}