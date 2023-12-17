//Fionn is working here
//Getter and Setter, Method, Variable
package com.bank.entity;

//import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

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
    
    private String accountNumber; //BankAccount.java

    private long id;
    private long amount;
    private LocalDate createdDate;
    private String username;
    private TransactionType transactionType = null;
    private long accountId;

    private static long transactionIdCounter = 1;


/* ****************************** */
// Constructors
    public Transaction()
    {

    }

    public Transaction(String accountNumber, long id, long amount, LocalDate createdDate, String username, TransactionType transactionType, long accountId) {
        this.accountNumber = accountNumber;
        this.id = id;
        this.amount = amount;
        this.createdDate = createdDate;
        this.username = username;
        this.transactionType = transactionType;
        this.accountId = accountId;
    }
/* ****************************** */
// Getters and setters
public String getUsername() {
    return username;
}

public void setUsername(String accountName) {
    this.username = username;
}

public String getAccountNumber() {
    return accountNumber;
}

public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
}

public LocalDate getTransactionDate() {
    return createdDate;
}

public void setTransactionDate(Date transactionDate) {
    this.createdDate = createdDate;
}

public TransactionType getTransactionType() {
    return transactionType;
}

public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
}

public long getid() {
    return id;
}

public void setTransactionId(long transactionId) {
    this.id = transactionId;
}

public long getTransactionAmount() {
    return amount;
}

public void setTransactionAmount(long transactionAmount) {
    this.amount = transactionAmount;
}

/* ****************************** */
private static long generateTransactionId() {
    return transactionIdCounter++;
}



//Scanner here temp. to test if it runs
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter transaction ID: ");
        long id = Long.parseLong(scanner.nextLine());

        System.out.print("Enter transaction amount: ");
        long amount = Long.parseLong(scanner.nextLine());

        // Assume you have a method to fetch the account from BankAccount.java
        // Replace the following line with the actual call to fetch the account
        String fetchedAccount = BankAccount.getAccountNumber(); // Replace this line with actual method call

        LocalDate currentDate = LocalDate.now(); // Use the current date as created date

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // You can ask for transaction type input here (e.g., deposit or withdrawal)
        // For simplicity, let's assume it's hardcoded as a deposit in this example
        TransactionType transactionType = TransactionType.DEPOSIT;

        // Generate accountId consecutively (this is a simplified example)
        long accountId = generateAccountId(); // Replace this with your logic to generate the ID

        // Create the transaction object using user inputs
        Transaction transaction = new Transaction(accountNumber, id, amount, currentDate, username, transactionType, accountId);

        // Create an ArrayList and add the transaction object
        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        // You can perform operations with the transactionList as needed
        // For example, iterating through the list and printing details
        for (Transaction t : transactionList) {
            System.out.println("Transaction Details:");
            System.out.println("Account Number: " + t.accountNumber);
            System.out.println("Transaction ID: " + t.id);
            System.out.println("Transaction Amount: " + t.amount);
            System.out.println("Created Date: " + t.createdDate);
            System.out.println("Username: " + t.username);
            System.out.println("Transaction Type: " + t.transactionType);
            System.out.println("Account ID: " + t.accountId);
        }
    }

    // Method to generate accountId (you may implement your logic here)
    private static long generateAccountId() {
        // Replace this with your implementation to generate the account ID
        // For example:
        return 12345; // Just a placeholder, replace this with your logic
    }
}
  // @Override
   //public String toString() {

//------------------------OLD IGNORE-----------------------------------
    //BankAccount.getId
    //public long getId(BankAccount acc);
    //public long getBalance(BankAccount acc);


    //Transaction despositTransaction = new Transaction();



