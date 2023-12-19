package com.bank.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.validator.routines.EmailValidator;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.exceptions.MenuException;

public interface Ui {
	public void authenticateApp() throws MenuException;
	public void displayMenu() throws MenuException;
	public String getString() throws MenuException;
	public long getLong() throws MenuException;
	public <T> void displayCollection(Collection<T> collection) throws MenuException;
}

public class UiImpl implements Ui {
    
    public void authenticateApp() throws MenuException {
        System.out.println("Enter your username: ");
        String username = getString();
        System.out.println("Enter your password: ");
        String password = getString();
        if (username.equals("admin") && password.equals("admin")) {
            displayMenu();
        } else {
            throw new MenuException("Invalid Credentials");
        }
    }
    public void displayMenu() throws MenuException {
        do {
            System.out.println("1. Customer Management");
            System.out.println("2. Account Management");
            System.out.println("3. Account Display");
            System.out.println("4. Account Manipulation");
            System.out.println("5. Enter your choice: ");
            try {
                long option = getLong();
                if (option < 1 || option > 4) {
                    throw new MenuException("Invalid Choice");
                }
                break;
            } catch (MenuException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        }
        switch (displayMenu()) {
            case 1:
            System.out.println(("Customer Management"));
            System.out.println("1. Create Customer");
            System.out.println("2. Update Customer Details");
            System.out.println("3. Deactivate Customer");
            break;
            case 2:
            System.out.println("Account Management");
            System.out.println("4. Open New Account");
            System.out.println("5. Close Account");
            System.out.println("6. Change Account type");
            break;
            case 3:
            System.out.println("Account Display");
            System.out.println("7. Display Customer Details");
            System.out.println("8. Display Account Balance");
            System.out.println("9. Deposit Funds to Account");
            System.out.println("10. Withdraw Funds from Account");
            System.out.println("10. Transfer Funds to/from Account");
            break;
            case 4: 
            System.out.println("Display Totals");
            System.out.println("11. Display Total no. of Customers");
            System.out.println("12. Display Total no. of Accounts");
            System.out.println("13. Display Total no. of Transactions");
            break;
            default:

                break;
        }
        
        
        
    }
    public String getString() throws MenuException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new MenuException("Invalid Input");
        }
        return input;
    }
    public long getLong() throws MenuException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new MenuException("Invalid Input");
        }
        return Long.parseLong(input);
    }
    public <T> void displayCollection(Collection<T> collection) throws MenuException {
        for (T t : collection) {
            System.out.println(t);
        }
    }

    public void Details(ArrayList coll) {
        for (Object o : coll) {
            System.out.println(o);
        }

    private Object email;
    private static final emailValidator = EmailValidator.getInstance();

    public void validateEmail(String email) throws MenuException {
    if(!emailValidator.isValid(email)) {
        throw new MenuException("Invalid Email Address");
        
    } else {
        System.out.println("Email Address is Valid");
    }
    if (email == null || email.trim().equals("")) {
        throw new NullPointerException("Invalid Input");
    }
    }
    
    
    public UiImpl(ArrayList<BankAccount> bankAccounts, ArrayList<Customer> customers,
            ArrayList<Transaction> transactions) {
        this.bankAccounts = bankAccounts;
        this.customers = customers;
        this.transactions = transactions;
    }



    ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
    bankAccounts.add(new BankAccount("John Smith", 0123456789, 1000);
    bankAccounts.add(new BankAccount("Jane Smith", 9876543210, 1000);
    bankAccounts.add(new BankAccount("Conor Murray", 4785632100, 5000);
    ArrayList<Customer> customers = new ArrayList<Customer>();
    customers.add(new Customer("John Smith", 0123456789);
    customers.add(new Customer("Jane Smith", 9876543210);
    customers.add(new Customer("Conor Murray", 4785632100);
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public void displayBankAccounts() {
    for (BankAccount bankAccount : bankAccounts) {
        System.out.println(bankAccount);
    }

}   
    public void displayCustomers() {
    for (Customer customer : customers) {
        System.out.println(customer);
    } 
}   
    public void displayTransactions() {
    for (Transaction transaction : transactions) {
        System.out.println(transaction);
    }
}
    public static void main(String[] args) {
        UiImpl ui = new UiImpl();
        ui.displayBankAccounts();
        ui.displayCustomers();
        ui.displayTransactions();
    }

    public static <T> void displayCollection(Collection<T> collection) {
        for (T t : collection) {
            System.out.println(t);
        }
    }
    

