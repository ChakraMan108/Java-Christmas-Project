package com.bank.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.entity.Operation;
import com.bank.exceptions.MenuException;

public class UiImpl implements Ui {

    public UiImpl() {

    }

    public void authenticateApp() throws MenuException {
        try {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("=     AUTHENTICATION   =");
            System.out.println("========================");
            System.out.println("Enter your username: ");
            String username = getString();
            System.out.println("Enter your password: ");
            String password = getString();
            if (!username.equals("admin") || !password.equals("admin"))
                throw new MenuException("Invalid Credentials!");
        } catch (MenuException ex) {
            throw new MenuException(ex.getMessage());
        }
    }

    public void displayMenu() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("=       MAIN MENU      =");
            System.out.println("========================");
            System.out.println("1. Customer Management");
            System.out.println("2. Account Management");
            System.out.println("3. Account Display");
            System.out.println("4. Account Manipulation");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            System.out.println("========================");
            System.out.println("Selection option:");

            try {
                String userInput = getString();
                switch (userInput) {
                    case "1":
                        customerManagement();
                        break;
                    case "2":
                        accountManagement();
                        break;
                    case "3":
                        accountDisplay();
                        break;
                    case "4":
                        accountManipulation();
                        break;
                    case "5":
                        reports();
                        break;
                    case "6":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid Option Selected. Enter Valid Option.");
                }
            } catch (MenuException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!exit);
    }

    private void customerManagement() {
        boolean exit = false;

        System.out.println("\n\n1. Create Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Deactivate Customer");
        System.out.println("4. Display Customer Details");
        System.out.println("5. Return to Main Menu");
    }

    private void accountManagement() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("= ACCOUNT MANAGEMENT   =");
            System.out.println("========================");
            System.out.println("1. Create Account");
            System.out.println("2. Update Account");
            System.out.println("3. Deactivate Account");
            System.out.println("4. Display Account Details");
            System.out.println("5. Return to Main Menu");
            System.out.println("========================");
            System.out.println("Selection option:");

            try {
                String userInput = getString();
                switch (userInput) {
                    case "1":
                        System.out.println("\nCreate Account");

                        break;

                    case "2":
                        System.out.println("\nUpdate Account");

                        break;

                    case "3":
                        System.out.println("\nDeactivate Account");

                        break;

                    case "4":
                        System.out.println("\nDisplay Account Details");

                        break;

                    case "5":
                        System.out.println("\nReturn to Main Menu");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid Option Selected. Enter Valid Option.");
                }
            } catch (MenuException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!exit);
    }

    private void accountDisplay() {
        boolean exit = false;

        System.out.println("\n1. Display All Accounts");
        System.out.println("2. Display Accounts by Customer Type");
        System.out.println("3. Display Accounts by Balance");
        System.out.println("4. Display Accounts by Account Type");
        System.out.println("5. Return to Main Menu");
    }

    private void accountManipulation() {
        boolean exit = false;
        System.out.println("\n1. Withdraw Funds from Account");
        System.out.println("2. Deposit Funds to Account");
        System.out.println("3. Transfer Funds from/to Account");
        System.out.println("4. Return to Main Menu");
    }

    private void reports() {
        boolean exit = false;

        System.out.println("\n1. Display Totals");
        System.out.println("2. Display Accounts by Date");
        System.out.println("3. Display Customers by Date");
        System.out.println("4. Display Transactions by Date");
        System.out.println("5. Display Operations by Date");
        System.out.println("6. Return to Main Menu");
    }

    public String getString() throws MenuException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new MenuException("Invalid Input!");
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

    public void Details(ArrayList<Object> coll) {
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    public void validateEmail(String email) throws MenuException {
        // EmailValidator emailValidator = new EmailValidator.getInstance();
        // if(!emailValidator.isValid(email))
        // {
        // throw new MenuException("Invalid Email Address");
        // }
        // else
        // {
        // System.out.println("Email Address is Valid");
        // }
        if (email == null || email.trim().equals(""))
            throw new NullPointerException("Invalid Input");
    }

    public void displayBankAccounts(ArrayList<BankAccount> bankAccounts) {
        for (BankAccount bankAccount : bankAccounts) {
            System.out.println(bankAccount);
        }
    }

    public void displayCustomers(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void displayTransactions(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public <T> void displayCollection(Collection<T> collection) {
        for (T t : collection) {
            System.out.println(t);
        }
    }

    // Rob
    static void accountDisplay() {
        boolean exit = false;
        do {
            System.out.println("1. Display All Accounts");
            System.out.println("2. Display Accounts by Customer");
            System.out.println("3. Display Accounts by Balance");
            System.out.println("4. Display Accounts by Type");
            System.out.println("5. Return to Main Menu");
            System.out.println("Please enter a number between 1 and 5");
            
            int menuOption = Integer.parseInt(scanner.nextLine());
            String userInput = getString();
            try {
                switch (menuOption) {
                    case 1:
                        displayAccounts();
                        break;
                    case 2:
                        displayAccountsByCustomer();
                        break;
                    case 3:
                        displayAccountsByBalance();
                        break;
                    case 4:
                        displayAccountsByType();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        throw new MenuException("Invalid Option Selected. Enter Valid Option. Please enter a number between 1 and 5.");
                }
            } catch (MenuException e) {
                System.out.println("Unexpected error occurred:" + e.getMessage());
            } finally {
                System.out.println("Thank you for using the Bank App.");

                if Scanner != null {
                    Scanner.close();
                }
        } while (!exit);
    }
    
    
    
    static void accountDisplay() {
        boolean exit = false;
        do {
            System.out.println("1. Display All Accounts");
            System.out.println("2. Display Accounts by Customer");
            System.out.println("3. Display Accounts by Balance");
            System.out.println("4. Display Accounts by Type");
            System.out.println("5. Return to Main Menu");
            System.out.println("Please enter a number between 1 and 5");
            int menuOption = Integer.parseInt(scanner.nextLine());
            String userInput = Scanner.nextLine();
            try {
                switch (menuOption) {
                    case 1:
                        CURRENT();
                        break;
                    case 2:
                        SAVINGS();
                        break;
                    case 3:
                        STUDENT();
                        break;
                    case 4:
                        returnToMainMenu();
                        break;
                    default:
                        throw new MenuException("Invalid Option Selected. Enter Valid Option. Please enter a number between 1 and 5.");
                }
            } catch (MenuException e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
    }
    static void displayAllAccounts() {
        System.Out.println("Please enter an account Id");
        long Id = Long.parseLong(scanner.nextLine());
        try { switch (menuOption) {baService.getId().findAll();}
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
            
        System.out.println("\nAll accounts:\n" + baService.findAll());;
    }
    
    static void displayAccountsByCustomer() {
        System.Out.println("Please enter a customer name");
        String name = scanner.nextLine();
        try { {baService.getId().findByName();}
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nAll accounts:\n" + baService.getId().findAll());
    }
    
    static void displayAccountsByBalance() {
        System.out.println("Please enter an account Id");
        long Id = Long.parseLong(scanner.nextLine());
        try { {baService.getId().sortByBalance();}
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        System.out.println("3. Display Accounts by Balance");
        System.out.println("Balance of account id" + acc.getId() + "is EUR" + acc.getBalanceamount()/100);
    }
    
    static void displayAccountsByType() {
        System.out.println("Please enter an account Id");
        long Id = Long.parseLong(scanner.nextLine());
        System.out.println("Please enter an account type");
        String type = scanner.nextLine();
        try {  {baService.getId().sortByType();}
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        System.out.println("\nAll accounts:\n" + baService.sortByType());
    }
    
    
    // Tom
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Handle any exceptions.
        }
    }

    // Fionn

    // Dhare
}
