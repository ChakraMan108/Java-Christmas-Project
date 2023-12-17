//Fionn is working here
//Getter and Setter, Method, Variable
package com.bank.entity;

import java.time.LocalDate;
import java.util.Date;

import com.bank.entity.Customer;
import com.bank.entity.Customer.CustomerType;
import com.bank.entity.BankAccount;


import java.util.ArrayList;
import com.bank.service.BankAccountService;
import com.bank.service.TransactionService;

//import com.bank.entity.Transaction.TransactionType;
public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
    private String accountName;
    private String accountNumber;
    private long balance;

    private Date transactionDate;
    private TransactionType transactionType;
    private long transactionId;
    private long transactionAmount;


/* ****************************** */
// Constructors
    public Transaction()
    {

    }

    public Transaction(String accountName, String accountNumber, long balance,
                       Date transactionDate, TransactionType transactionType,
                       long transactionId, long transactionAmount) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionDate = new Date();
        this.TransactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
    }
/* ****************************** */
// Getters and setters
public String getAccountName() {
    return accountName;
}

public void setAccountName(String accountName) {
    this.accountName = accountName;
}

public String getAccountNumber() {
    return accountNumber;
}

public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
}

public long getBalance() {
    return balance;
}

public void setBalance(long balance) {
    this.balance = balance;
}

public Date getTransactionDate() {
    return transactionDate;
}

public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
}

public TransactionType getTransactionType() {
    return transactionType;
}

public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
}

public long getTransactionId() {
    return transactionId;
}

public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
}

public long getTransactionAmount() {
    return transactionAmount;
}

public void setTransactionAmount(long transactionAmount) {
    this.transactionAmount = transactionAmount;
}

/* ****************************** */

    public class TransactionList {
        ArrayList<String> arrayList = new ArrayList<>();




    }


  // @Override
   //public String toString() {
    //    return

   //}

//------------------------OLD IGNORE-----------------------------------
    //BankAccount.getId
    //public long getId(BankAccount acc);
    //public long getBalance(BankAccount acc);


    //Transaction despositTransaction = new Transaction();


    //Transaction WithdrawTransaction = new Transaction();



    //Transaction transferTransaction = new Transaction();


}
