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
      return true;
   }

   @Override
   public String toString() {
      return "BankAccount [id=" + id + ", accountName=" + accountName + ", type=" + type + ", balance=" + balance
               + ", isActive=" + isActive + ", createdDate=" + createdDate + ", deactivatedDate=" + deactivatedDate + "]";
   }

}