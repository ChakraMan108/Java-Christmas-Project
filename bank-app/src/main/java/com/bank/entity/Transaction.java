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

public class Transaction {

    public enum TransactionType {
    DEPOSIT, 
    WITHDRAWAL
    }

    private long id;
    private long amount;
    private LocalDate createdDate;
    private String username;
    private TransactionType type = null;
    private long accountId;

/* ****************************** */
// Constructors
    public Transaction()
    {
    
    }

    public Transaction(long id, long amount, String username, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.createdDate = LocalDate.now();
        this.username = username;
        this.type = type;
    }
/* ****************************** */
// Getters and setters
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", amount=" + amount + ", createdDate=" + createdDate + ", username="
                + username + ", type=" + type + ", accountId=" + accountId + "]";
    }

    //Scanner here temp. to test if it runs
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Transaction transaction = new Transaction();

            // Get user input for id
            System.out.print("Enter transaction ID: ");
            long transId = Long.parseLong(scanner.nextLine());
            transaction.setId(transId);

            // Get user input for amount here
            System.out.print("Enter transaction amount: ");
            long transAmount = Long.parseLong(scanner.nextLine());
            transaction.setAmount(transAmount);

            // Get user input for username here
            System.out.print("Enter username: ");
            String userName = scanner.nextLine();
            transaction.setUsername(userName);

            transaction.setCreatedDate(LocalDate.parse("2023-12-17"));

            // Transaction is hard coded here to deposit FIX
            System.out.print("Enter transaction type (DEPOSIT or WITHDRAWAL): ");
            String transactionType = scanner.nextLine();
            if (transactionType.equals("DEPOSIT")) 
                transaction.setType(TransactionType.DEPOSIT);
            else
                transaction.setType(TransactionType.WITHDRAWAL);

            // Adding transaction to an ArrayList 
            ArrayList<Transaction> transactionList = new ArrayList<>();
            transactionList.add(transaction);

            // Print out transaction details for testing purposes
            for (Transaction t : transactionList) {
                System.out.println("\n" + "Transaction Details:");
                System.out.println("Account Number: " + t.getAccountId());
                System.out.println("Transaction ID: " + t.getId());
                System.out.println("Transaction Amount: " + t.getAmount());
                System.out.println("Created Date: " + t.getCreatedDate());
                System.out.println("Username: " + t.getUsername());
                System.out.println("Transaction Type: " + t.getType());
                System.out.println("Account ID: " + t.getAccountId());
            }
        }
}



