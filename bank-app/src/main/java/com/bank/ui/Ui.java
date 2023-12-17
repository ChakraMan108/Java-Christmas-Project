package com.bank.ui;

import java.util.Scanner;

import java.util.Collection;

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
}
