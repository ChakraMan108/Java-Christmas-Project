package com.bank.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    public enum TransactionType {
    DEPOSIT, 
    WITHDRAWAL
    }

    private long id;
    private long amount;
    private LocalDateTime createdDate;
    private String username;
    private TransactionType type = null;
    private long accountId;


    // Public constructor for testing and empty objects
    public Transaction()
    {
    
    }

    // Parametrised constructor taking mandatory fields
    public Transaction(long amount, String username, TransactionType type, long accountId) {
        this.amount = amount;
        this.username = username;
        this.type = type;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (amount ^ (amount >>> 32));
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + (int) (accountId ^ (accountId >>> 32));
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
        Transaction other = (Transaction) obj;
        if (id != other.id)
            return false;
        if (amount != other.amount)
            return false;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (type != other.type)
            return false;
        if (accountId != other.accountId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String createDateStr = null;
        if (createdDate != null) {
            createDateStr = createdDate.format(formatter);
        } else {
            createDateStr = "N/A";
        }
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: " + id + "\nAmount: " + amount / 100 + "." + String.format("%02d", amount % 100) + "\nUsername: " + username
                + "\nTransaction Type: " + type + "\nAccount ID: " + accountId + "\nDate: " + createDateStr
                + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}



