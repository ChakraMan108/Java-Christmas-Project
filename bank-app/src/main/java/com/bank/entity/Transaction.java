//Fionn is working here
//Getter and Setter, Method, Variable
package com.bank.entity;

//import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

//import com.bank.entity.Customer;
//import com.bank.entity.Customer.CustomerType;
//import com.bank.entity.BankAccount;



import java.util.ArrayList;
//import com.bank.service.BankAccountService;
//import com.bank.service.TransactionService;


enum TransactionType {
    DEPOSIT, WITHDRAWAL
    }

public class Transaction {
    
    private String accountName;
    private String accountNumber;
    private long balance;

    private Date transactionDate;
    private TransactionType transactionType;
    private long transactionId;
    private long transactionAmount;
    private static long transactionIdCounter = 1;


/* ****************************** */
// Constructors
    public Transaction()
    {

    }

    public Transaction(String accountName, String accountNumber, long balance,
                       TransactionType transactionType, long transactionId, long transactionAmount) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionDate = new Date(); // Set current date and time
        this.transactionType = transactionType;
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
private static long generateTransactionId() {
    return transactionIdCounter++;
}

//Scanner here temp. to test if it runs
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Creating an ArrayList to store Transaction objects
    ArrayList<Transaction> transactions = new ArrayList<>();

    // Taking user input for transaction details
    System.out.println("Enter number of transactions:");
    int numOfTransactions = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    for (int i = 0; i < numOfTransactions; i++) {
        System.out.println("Enter account name:");
        String accountName = scanner.nextLine();

        System.out.println("Enter account number:");
        String accountNumber = scanner.nextLine();

        System.out.println("Enter balance:");
        long balance = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter transaction type (DEPOSIT or WITHDRAWAL):");
        TransactionType transactionType = TransactionType.valueOf(scanner.nextLine().toUpperCase());

        long transactionId = generateTransactionId(); // Generate transaction ID

        System.out.println("Enter transaction amount:");
        long transactionAmount = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        // Creating a new Transaction object and adding it to the ArrayList
        Transaction transaction = new Transaction(accountName, accountNumber, balance,
                transactionType, transactionId, transactionAmount);
        transactions.add(transaction);
    }

    // Displaying transaction details
    for (Transaction transaction : transactions) {
        System.out.println("Account Name: " + transaction.getAccountName());
        System.out.println("Account Number: " + transaction.getAccountNumber());
        System.out.println("Balance: " + transaction.getBalance());
        System.out.println("Transaction Date: " + transaction.getTransactionDate());
        System.out.println("Transaction Type: " + transaction.getTransactionType());
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Transaction Amount: " + transaction.getTransactionAmount());
        System.out.println();
    }

    scanner.close();
}
}
  // @Override
   //public String toString() {

//------------------------OLD IGNORE-----------------------------------
    //BankAccount.getId
    //public long getId(BankAccount acc);
    //public long getBalance(BankAccount acc);


    //Transaction despositTransaction = new Transaction();



