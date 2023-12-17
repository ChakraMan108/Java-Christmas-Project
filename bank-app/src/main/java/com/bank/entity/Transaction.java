//Fionn is working here
//Getter and Setter, Method, Variable
package com.bank.entity;

//import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

//import com.bank.entity.Customer;
//import com.bank.entity.Customer.CustomerType;
import com.bank.entity.BankAccount;




//import com.bank.service.BankAccountService;
//import com.bank.service.TransactionService;


enum TransactionType {
    DEPOSIT, WITHDRAWAL
    }

public class Transaction {
    
    private String accountNumber; //BankAccount.java

    private long id;
    private long amount;
    private LocalDate createdDate;
    private String username;
    private TransactionType transactionType = null;

    private long accountIdCounter = 1;


/* ****************************** */
// Constructors
    public Transaction()
    {

    }

    public Transaction(String accountNumber, long id, long amount, 
                        LocalDate createdDate, String username, TransactionType transactionType, long accountIdCounter) {
        this.accountNumber = accountNumber;
        this.id = id;
        this.amount = amount;
        this.createdDate = createdDate;
        this.username = username;
        this.transactionType = transactionType;
        this.accountIdCounter = accountIdCounter;
    }
/* ****************************** */
// Getters and setters
public String getAccountNumber() {
    return accountNumber;
}

public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
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

public LocalDate getCreatedDate() {
    return createdDate;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public TransactionType getTransactionType() {
    return transactionType;
}

public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
}

public long getAccountId() {
    return accountIdCounter++;
}

/* ****************************** */

//Scanner here temp. to test if it runs
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transaction transaction = new Transaction();

        // Get input for accountNumber
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        transaction.setAccountNumber(accNumber);

        // Get input for id
        System.out.print("Enter transaction ID: ");
        long transId = Long.parseLong(scanner.nextLine());
        transaction.setId(transId);

        // Get input for amount
        System.out.print("Enter transaction amount: ");
        long transAmount = Long.parseLong(scanner.nextLine());
        transaction.setAmount(transAmount);

        // Get input for username
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        transaction.setUsername(userName);

        // Get input for transaction type (assume hardcoded as DEPOSIT for simplicity)
        transaction.setTransactionType(TransactionType.DEPOSIT);

        // Adding transaction to an ArrayList (optional)
        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        // Print transaction details
        for (Transaction t : transactionList) {
            System.out.println("\n" + //
                    "Transaction Details:");
            System.out.println("Account Number: " + t.getAccountNumber());
            System.out.println("Transaction ID: " + t.getId());
            System.out.println("Transaction Amount: " + t.getAmount());
            System.out.println("Created Date: " + t.getCreatedDate());
            System.out.println("Username: " + t.getUsername());
            System.out.println("Transaction Type: " + t.getTransactionType());
            System.out.println("Account ID: " + t.getAccountId());
        }
    }
}
  // @Override
   //public String toString() {

//------------------------OLD IGNORE-----------------------------------
    //BankAccount.getId
    //public long getId(BankAccount acc);
    //public long getBalance(BankAccount acc);


    //Transaction despositTransaction = new Transaction();



