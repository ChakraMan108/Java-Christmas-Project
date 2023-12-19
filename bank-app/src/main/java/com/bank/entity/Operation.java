package com.bank.entity;

import java.time.LocalDate;

public class Operation {

    public enum OperationType {
       CUSTOMER_CREATION,
        CUSTOMER_DEACTIVATION,
        ACCOUNT_CREATION,
        ACCOUNT_DEACTIVATION
        


    }
    private long id;
    private OperationType operationtype;
    private String username;
    private LocalDate date;
    private Customer customer;
    
    public Operation(OperationType operationtype, String username, LocalDate date, Customer customer ) {
        
        this.operationtype = operationtype;
        this.username = username;
        this.date = date;
        this.customer=customer;
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

    public void setOperationtype(OperationType operationtype) {
        this.operationtype = operationtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation [id=" + id + ", operationtype=" + operationtype + ", username=" + username + ", date=" + date
                + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((operationtype == null) ? 0 : operationtype.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
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
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        return true;
    }

}

