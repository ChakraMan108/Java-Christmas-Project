package com.bank.ui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.exceptions.MenuException;
import com.bank.main.Main;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.CustomerRepository;

import org.apache.commons.validator.routines.EmailValidator;

    public class UiImpl implements Ui {
    public class MenuException extends Exception {
        public MenuException(String message) {
            super(message);
        }
    }
    public void authenticateApp() {
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
    public void displayMenu() {
        //while (true);    
        final long option = 0;

            try {
                System.out.println("1. Customer Management");
                System.out.println("2. Account Management");
                System.out.println("3. Account Display");
                System.out.println("4. Account Manipulation");
                System.out.println("5. Reports");
            
             {
                long menuOption = getLong();
                if (option < 1 || option > 5) {
                    throw new MenuException("Invalid Choice");
                }
                
            }
         } catch (MenuException e) {
                System.out.println(e.getMessage());
            }
         

        switch (String menuOption()) {
            case 1:
            System.out.println(("Customer Management"));
           
            break;
            case 2:
            System.out.println("Account Management");
            
            break;
            case 3:
            System.out.println("Account Display");
            
            break;
            case 4: 
            System.out.println("Account Manipulation");
            
            break;
            case 5:
            System.out.println("Reports");
            break;

            default:
            System.out.println("Invalid Option Selected, Enter Valid Option");
                break;
        }
    
     
    static void customerManagement() {
        System.out.println("1. Create Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Display Customer Details");
        System.out.println("5. Return to Main Menu"); 
    }
    static void accountManagement() {
        System.out.println("1. Create Account");
        System.out.println("2. Update Account");
        System.out.println("3. Delete Account");
        System.out.println("4. Display Account Details");
        System.out.println("5. Return to Main Menu"); 
    }
    static void accountDisplay() {
        System.out.println("1. Display All Accounts");
        System.out.println("2. Display Accounts by Customer");
        System.out.println("3. Display Accounts by Balance");
        System.out.println("4. Display Accounts by Type");
        System.out.println("5. Return to Main Menu");
    }
    static void accountManipulation() {
        System.out.println("1. Withdraw Funds from Account");
        System.out.println("2. Deposit Funds to Account");
        System.out.println("3. Transfer Funds from Account");
        System.out.println("4. Return to Main Menu");
    }
    static void reports() {
        System.out.println("1. Display All Transactions");
        System.out.println("2. Display Transactions of Accounts by Type");
        System.out.println("3. Display Transactions of Accounts by Date");
        System.out.println("4. Display Transactions of Customers by Date");
        System.out.println("5. Display Transactions by Date");
        System.out.println("6. Display Details of Operations by Date");
        System.out.println("7. Return to Main Menu");
    }
    

    public static void Main(String[] args) {
        customerManagement();
        accountManagement();
        accountDisplay();
        accountManipulation();
        reports();

        Main displayMenu = new Main();
            displayMenu.displayMenu();
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
    public void Details(ArrayList<Object> coll) {
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    private Object email;
    private static final EmailValidator emailValidator = EmailValidator.getInstance();

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
    bankAccounts.add(new BankAccount("John Smith", 0123456789, 1000));
    bankAccounts.add(new BankAccount("Jane Smith", 9876543210, 1000));
    bankAccounts.add(new BankAccount("Joan Murray", 4785632100, 5000));

    ArrayList<Customer> customers = new ArrayList<Customer>();
    customers.add(new Customer("John Smith", 0123456789));
    customers.add(new Customer("Jane Smith", 9876543210));
    customers.add(new Customer("Joan Murray", 4785632100));

    ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    Transaction[] transactions = new Transaction[3];


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
    public static void main(String[] args) {
        UiImpl ui = new UiImpl(bankAccounts, customers, transactions);
        ui.displayBankAccounts();
        ui.displayCustomers();
        ui.displayTransactions();
    }

    public <T> void displayCollection(Collection<T> collection) {
        for (T t : collection) {
            System.out.println(t);
        }
    }
    
    public class BARepository implements Repository<BankAccount> {
    List<BankAccount> getAll();
    BankAccount getById(int id);
    int insert(BankAccount acc);
    void update(int id, BankAccount acc);
    void delete(int id);

    @Override
    String toString() {
        String str = String.format("[%d] %s, %.2f (%d ops in this session)", id, accountHolder, balance, numOpsInSession);
		return str;
    }
}
    
    public BankAccountService(BankAccountRepository repo) {
        this.repository = repo;
    }

    public CustomerService(CustomerRepository repo) {
        this.repository = repo;
    }
}

    BankAccountService(); 
    CustomerService();