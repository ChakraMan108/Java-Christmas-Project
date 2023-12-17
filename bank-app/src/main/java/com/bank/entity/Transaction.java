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
    private String accountNumber;
    private long balance;


    private String name;
    private boolean isActive;
    private BankAccount account;

    private LocalDate transactionDate;
    private TransactionType transactiontype;
    private long depositAmmount;
    private long withDrawlAmmount;

/* ****************************** */
// Constructors
    public Transaction()
    {

    }


    public Transaction(String accountNumber, long balance, String name, boolean isActive, BankAccount account, LocalDate transactionDate, TransactionType transactiontype, long depositAmmount, long withDrawlAmmount)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
/* ****************************** */
// Getters and setters


/* ****************************** */

   @Override
   public String toString() {
        return

   }

//------------------------OLD IGNORE-----------------------------------
    //BankAccount.getId
    //public long getId(BankAccount acc);
    //public long getBalance(BankAccount acc);


    //Transaction despositTransaction = new Transaction();


    //Transaction WithdrawTransaction = new Transaction();



    //Transaction transferTransaction = new Transaction();


}
