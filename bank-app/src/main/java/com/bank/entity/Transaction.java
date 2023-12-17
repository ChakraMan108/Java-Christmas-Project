//Fionn is working here
//Getter and Setter, Method, Variable
package com.bank.entity;

import java.time.LocalDate;

import com.bank.entity.Customer;
import com.bank.entity.Customer.CustomerType;
import com.bank.entity.BankAccount;


import com.bank.service.BankAccountService;
import com.bank.service.TransactionService;

//import com.bank.entity.Transaction.TransactionType;
public class Transaction {
    public enum TransactionType {
        TDeposit,
        TWithdrawl
    }
    private String accountName;
    private String accountNumber;
    private long balance;

    private LocalDate transactionDate;
    private TransactionType transactiontype;
    private long transactionId;
    private long transactionAmount;


/* ****************************** */
// Constructors
    public Transaction()
    {

    }

    public Transaction(String accountNumber, long balance, boolean isActive, LocalDate transactionDate, TransactionType transactiontype, long transactionId, long transactionAmount)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
/* ****************************** */
// Getters and setters


/* ****************************** */

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
