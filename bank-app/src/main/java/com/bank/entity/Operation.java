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
    
    public Operation(long id, OperationType operationtype, String username, LocalDate date, Customer customer ) {
        this.id = id;
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


       
        // Add more operation types if needed
}

