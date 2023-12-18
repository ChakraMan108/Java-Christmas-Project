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
        System.out.println("1. Create Customer");
        System.out.println("2. Update Customer Details");
        System.out.println("3. Deactivate Customer");
        System.out.println("4. Open New Account");
        System.out.println("5. Close Account");
        System.out.println("6. Change Account type");
        System.out.println("7. Display Customer Details");
        System.out.println("8. Display Account Balance");
        System.out.println("9. Deposit Funds to Account");
        System.out.println("10. Transfer Funds to/from Account");
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
    if (email == null) {
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
    ArrayList<Customer> customers = new ArrayList<Customer>();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();


}
