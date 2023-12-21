package com.bank.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.entity.Operation;
import com.bank.exceptions.CustomerException;
import com.bank.exceptions.MenuException;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;
import com.bank.service.OperationService;
import com.bank.service.TransactionService;

public class UiImpl implements Ui {

    // Service initialisation
        BankAccountService baService = new BankAccountService();
        OperationService opService = new OperationService();
        TransactionService trService = new TransactionService();
        CustomerService cuService = new CustomerService();
    
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
    //Fionn
    private void accountManipulation() {
        boolean exit = false;
        
        do {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("= ACCOUNT MANIPULATION   =");
            System.out.println("========================");
            System.out.println("1. Withdraw Funds from Account");
            System.out.println("2. Deposit Funds to Account");
            System.out.println("3. Transfer Funds from/to Account");
            System.out.println("========================");
            System.out.println("4. Return to Main Menu");
        try {
            String userInput = getString();
            switch (userInput) {
                case "1":
                    System.out.println("\nWithdraw Funds from Account");
            
                        break;
                case "2":
                    System.out.println("\nDeposit Funds to Account");
                        break;
                case "3":
                    System.out.println("\nTransfer Funds from/to Account");
                        break;
                case "4":
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

    // private void accountManipulation() {
    //     boolean exit = false;

    //     try{
    //     Customer c1 = new Customer(1, "Joe", "Beech Park", LocalDate.parse("2000-04-15"), "085111222", "tom@x.com", Customer.CustomerType.INDIVIDUAL);
    //     Customer c2 = new Customer(1, "Avaya", "Mervue", LocalDate.parse("2000-04-15"), "086896457", "avaya@avaya.com", Customer.CustomerType.COMPANY);
    //     cuService.createCustomer(c1);
    //     cuService.createCustomer(c2);
    //     System.out.println("\nAfter customer creation:\n" + c1);
    //     System.out.println("\nAfter customer creation:\n" + c2);
    //     }
    //     catch (ServiceException ex){
    //         throw new CustomerException("Case1: Exception received from the Bank Account Repository by the Bank Account Service.");
    //     }
        

    //     do {
    //         clearConsole();
    //         System.out.println("\n========================");
    //         System.out.println("= ACCOUNT MANIPULATION   =");
    //         System.out.println("========================");
    //         System.out.println("1. Withdraw Funds from Account");
    //         System.out.println("2. Deposit Funds to Account");
    //         System.out.println("3. Transfer Funds from/to Account");
    //         System.out.println("========================");
    //         System.out.println("4. Return to Main Menu");
    //     try {
    //         String userInput = getString();
    //         switch (userInput) {
    //             case "1":
    //                 System.out.println("\nWithdraw Funds from Account");
    //                 System.out.println("\nEnter account ID");
    //                 long wId = getLong();
    //                 System.out.println("\nEnter withdrawl ammount");
    //                 long wAmountC = getLong();  
    //                 long wAmount = wAmountC * 100;
    //             try{
    //                 System.out.println("Number of customers: " + cuService.count());
    //                 baService.withdrawFromAccount(wId, wAmount);
    //                 baService.findById(wId);
    //                 System.out.println("\nAfter account withdrawal:\n" + wId); // Not going to work
    //                     }
    //                     catch (ServiceException ex) {
    //                         throw new MenuException("Case1: Exception received from the Bank Account Repository by the Bank Account Service.");
    //                     }
    //                     break;
    //             case "2":
    //                 System.out.println("\nDeposit Funds to Account");
    //                 System.out.println("\nEnter account ID");
    //                 long dId = getLong();
    //                 System.out.println("\nEnter deposit ammount");
    //                 long dAmountC = getLong(); 
    //                 long dAmount = dAmountC * 100;  
    //             try{
    //                 baService.depositIntoAccount(dId, dAmount);
    //                 baService.findById(dId);
    //                 System.out.println("\nAfter account deposit:\n" + dId); // Not going to work
    //                     }
    //                     catch (ServiceException ex) {
    //                         throw new MenuException("Case2: Exception received from the Bank Account Repository by the Bank Account Service.");
    //                     }
    //                     break;
    //             case "3":
    //                 System.out.println("\nTransfer Funds from/to Account");
    //                 System.out.println("\nEnter the transferer ID");
    //                 long tId = getLong();
    //                 System.out.println("\nEnter the recipients ID");
    //                 long rId = getLong();
    //                 System.out.println("\nEnter transfer ammount");
    //                 long tAmountC = getLong();
    //                 long tAmount = tAmountC * 100;  
    //                 try{
    //                     baService.withdrawFromAccount(tId, tAmount);
    //                     }
    //                     catch (ServiceException ex) {
    //                         throw new MenuException("Case3: Exception received from the Bank Account Repository by the Bank Account Service.");
    //                     }
    //                     try{
    //                     baService.depositIntoAccount(rId, tAmount);
    //                     System.out.println("\nTransferer's account after withdrawal:\n" + tId); // Not going to work
    //                     System.out.println("\nRecipients's account after withdrawal:\n" + rId); 
    //                     }
    //                     catch (ServiceException ex1) {
    //                         throw new MenuException("Exception received from the Bank Account Repository by the Bank Account Service.");
    //                     }
    //                     break;
    //             case "4":
    //                 System.out.println("\nReturn to Main Menu");
    //                 exit = true;
    //                     break;
    //                 default:
    //                     System.out.println("Invalid Option Selected. Enter Valid Option.");
    //             }
    //             } catch (MenuException ex) {
    //                 System.out.println(ex.getMessage());
    //             }
    //     } while (!exit);
    // }

    // Dhare
}
