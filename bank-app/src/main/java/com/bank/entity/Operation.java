package com.bank.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operation {

    public enum OperationType {
        CUSTOMER_CREATION,
        CUSTOMER_DEACTIVATION,
        CUSTOMER_UPDATE,
        ACCOUNT_CREATION,
        ACCOUNT_DEACTIVATION,
        ACCOUNT_UPDATE
    }

    private long id;
    private OperationType operationtype;
    private String username;
    private long accountId;
    private long customerId;
    private LocalDateTime date;

    // Public constructor for testing and empty objects
    public Operation() {
    }

    // Parametrised constructor taking mandatory fields
    public Operation(OperationType operationtype, String username, long accountId, long customerId) {
        this.operationtype = operationtype;
        this.username = username;
        this.accountId = accountId;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OperationType getOperationtype() {
        return operationtype;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setOperationtype(OperationType operationtype) {
        this.operationtype = operationtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((operationtype == null) ? 0 : operationtype.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + (int) (accountId ^ (accountId >>> 32));
        result = prime * result + (int) (customerId ^ (customerId >>> 32));
        result = prime * result + ((date == null) ? 0 : date.hashCode());
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
        Operation other = (Operation) obj;
        if (id != other.id)
            return false;
        if (operationtype != other.operationtype)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (accountId != other.accountId)
            return false;
        if (customerId != other.customerId)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateStr = null;
        if (date != null) {
            dateStr = date.format(formatter);
        } else {
            dateStr = "N/A";
        }
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: " + id + "\nOperation Type: " + operationtype
                + "\nUsername: " + username + "\nAccount ID: " + accountId + "\nCustomer ID: " + customerId
                + "\nDate: " + dateStr + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
