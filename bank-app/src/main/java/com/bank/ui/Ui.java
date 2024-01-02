package com.bank.ui;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.validator.routines.EmailValidator;

import com.bank.entity.BankAccount;
import com.bank.entity.BankAccount.AccountType;
import com.bank.entity.Customer;
import com.bank.entity.Customer.CustomerType;
import com.bank.entity.Operation;
import com.bank.entity.Transaction;
import com.bank.exceptions.ServiceException;
import com.bank.exceptions.UIException;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;
import com.bank.service.OperationService;
import com.bank.service.TransactionService;

public class Ui implements UiInterface {

    private String appUsername;
    private String appPassword;

    // Service initialisation
    BankAccountService baService = new BankAccountService();
    OperationService opService = new OperationService();
    TransactionService trService = new TransactionService();
    CustomerService cuService = new CustomerService();

    public void loadProperties() {
        try {
            InputStream appConfigPath = Ui.class.getClassLoader().getResourceAsStream("app.properties");
            Properties appProps = new Properties();
            appProps.load(appConfigPath);
            appUsername = appProps.getProperty("app.username");
            appPassword = appProps.getProperty("app.password");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadData() {
        try {
            cuService.loadJson();
            baService.loadJson();
            opService.loadJson();
            trService.loadJson();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void authenticateApp() throws UIException {
        try {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=        AUTHENTICATION      =");
            System.out.println("==============================");
            System.out.print("Enter username: ");
            String username = getString();
            System.out.print("Enter password: ");
            String password = getString();
            if (!username.equals(appUsername) || !password.equals(appPassword))
                throw new UIException("Invalid credentials!");
        } catch (Exception ex) {
            throw new UIException("[UI error] " + ex.getMessage());
        }
    }

    public void displayMenu() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=          MAIN MENU         =");
            System.out.println("==============================");
            System.out.println("1. Customer Management");
            System.out.println("2. Account Management");
            System.out.println("3. Account Display");
            System.out.println("4. Account Manipulation");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            System.out.println("==============================");
            System.out.print("Selection option: ");

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
                        try {
                            cuService.saveJson();
                            baService.saveJson();
                            opService.saveJson();
                            trService.saveJson();
                        } catch (ServiceException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("Exiting the Bank Application.");
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI error] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void customerManagement() throws UIException {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=     CUSTOMER MANAGEMENT    =");
            System.out.println("==============================");
            System.out.println("1. Create Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Deactivate Customer");
            System.out.println("4. Display Customer Details");
            System.out.println("5. Return to Main Menu");
            System.out.println("==============================");
            System.out.print("Selection option: ");
            try {
                String userInput = getString();

                switch (userInput) {
                    case "1":
                        createCustomer();
                        break;
                    case "2":
                        updateCustomer();
                        break;
                    case "3":
                        deactivateCustomer();
                        break;
                    case "4":
                        displayCustomerDetails();
                        break;
                    case "5":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI error] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void accountManagement() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=     ACCOUNT MANAGEMENT     =");
            System.out.println("==============================");
            System.out.println("1. Create Account");
            System.out.println("2. Update Account");
            System.out.println("3. Deactivate Account");
            System.out.println("4. Return to Main Menu");
            System.out.println("==============================");
            System.out.print("Selection option: ");

            try {
                String userInput = getString();
                switch (userInput) {
                    case "1":
                        createAccount();
                        break;
                    case "2":
                        updateAccount();
                        break;
                    case "3":
                        deactivateAccount();
                        break;
                    case "4":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI error] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void accountDisplay() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=      ACCOUNT DISPLAY       =");
            System.out.println("==============================");
            System.out.println("1. Display Account by ID");
            System.out.println("2. Display Accounts by Customer");
            System.out.println("3. Display Accounts by Balance");
            System.out.println("4. Display Accounts by Type");
            System.out.println("5. Return to Main Menu");
            System.out.print("Selection option: ");
            try {
                String userInput = getString();
                switch (userInput) {
                    case "1":
                        displayAccountsById();
                        break;
                    case "2":
                        displayAccountsByCustomer();
                        break;
                    case "3":
                        displayAccountsByBalance();
                        break;
                    case "4":
                        displayAccountsByType();
                        break;
                    case "5":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException e) {
                System.out.println("[UI error] " + e.getMessage());
            }
        } while (!exit);
    }

    private void accountManipulation() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=   ACCOUNT MANIPULATION     =");
            System.out.println("==============================");
            System.out.println("1. Withdraw Funds From Account");
            System.out.println("2. Deposit Funds To Account");
            System.out.println("3. Transfer Funds Between Accounts");
            System.out.println("4. Return to Main Menu");
            System.out.println("==============================");
            System.out.print("Selection option: ");
            try {
                String userInput = getString();
                switch (userInput) {
                    case "1":
                        withdrawFromAccount();
                        break;
                    case "2":
                        depositIntoAccount();
                        break;
                    case "3":
                        transferToAccount();
                        break;
                    case "4":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI error] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void reports() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n==============================");
            System.out.println("=          REPORTING         =");
            System.out.println("==============================");
            System.out.println("1. Display Totals");
            System.out.println("2. Display Accounts by Date");
            System.out.println("3. Display Customers by Date");
            System.out.println("4. Display Transactions by Date");
            System.out.println("5. Display Operations by Date");
            System.out.println("6. Return to Main Menu");
            System.out.println("========================");
            System.out.print("Selection option: ");

            try {
                String userInput = getString();
                switch (userInput) {
                    case "1":
                        displayTotals();
                        break;
                    case "2":
                        displayAccountsByDate();
                        break;
                    case "3":
                        displayCustomersByDate();
                        break;
                    case "4":
                        displayTransactionsByDate();
                        break;
                    case "5":
                        displayOperationsByDate();
                        break;
                    case "6":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI error] " + ex.getMessage());
            }
        } while (!exit);
    }

    public String getString() throws UIException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input == null || input.trim().equals("")) {
            throw new UIException("[Input Error] Invalid input.");
        }
        return input;
    }

    public long getLong() throws UIException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new UIException("Invalid input.");
        }
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new UIException("[Input Error] Invalid input. Requires an integer up to 18 digits long.");
        }
    }

    public long getCurrencyAmount() throws UIException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new UIException("Invalid input.");
        }
        BigDecimal bd = new BigDecimal(input);
        try {
            bd.setScale(2);
            return bd.movePointRight(2).longValueExact();
        } catch (NumberFormatException | ArithmeticException e) {
            throw new UIException("[Input Error] Invalid input. Requires a number with up to 2 decimal places.");
        }
    }

    public void Details(ArrayList<Object> coll) {
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    public void validateEmail(String email) throws UIException {
        if (!EmailValidator.getInstance().isValid(email))
            throw new UIException("[Validation Error] Invalid Email Address.");
        if (email == null || email.trim().equals(""))
            throw new NullPointerException("[Input Error] Invalid Input.");
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

    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }

    private void displayAccountsById() {
        try {
            System.out.println("\nDisplay Account By ID\n==============================");
            System.out.println("Enter account ID: ");
            long id = getLong();
            System.out.println("\nAccount details:\n" + baService.findById(id));
            System.out.println("----------------------------------------------------------------------");
        } catch (UIException | ServiceException e) {
            System.out.println("[Display account error]" + e.getMessage());
        }
    }

    private void displayAccountsByCustomer() {
        try {
            System.out.println("\nDisplay Accounts By Customer\n==============================");
            System.out.println("Enter customer name: ");
            String name = getString();
            System.out.println("\nAccount details:\n" + baService.findByCustomerName(name));
            System.out.println("----------------------------------------------------------------------");
        } catch (UIException | ServiceException e) {
            System.out.println("[Display account error]" + e.getMessage());
        }
    }

    private void displayAccountsByBalance() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Balance\n==============================");
            System.out.println("Enter balance minimum (EUR): ");
            long minimum = getCurrencyAmount();
            System.out.println("Enter balance maximum (EUR): ");
            long maximum = getCurrencyAmount();
            System.out.println("\nDisplaying accounts between " + minimum / 100 + "." + minimum % 100
                    + " EUR and " + maximum / 100 + "." + maximum % 100
                    + " EUR.");
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            for (BankAccount ba : bankAccounts) {
                if (ba.getBalance() >= minimum && ba.getBalance() <= maximum) {
                    count++;
                    System.out.println("\nAccount details:\n" + ba);
                    System.out.println("----------------------------------------------------------------------");
                }
            }
            if (count == 0) {
                System.out.println("No accounts found.");
            } else {
                System.out.println("Number of accounts found: " + count);
            }
        } catch (UIException | ServiceException e) {
            System.out.println("[Display account error]" + e.getMessage());
        }
    }

    private void displayAccountsByType() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Type\n==============================");
            System.out.println("Enter Account Type [CURRENT_ACCOUNT | SAVING_ACCCOUNT | STUDENT_ACCOUNT]: ");
            String typeStr = getString();
            AccountType type = AccountType.valueOf(typeStr.toUpperCase());
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            for (BankAccount ba : bankAccounts) {
                if (ba.getType() == type) {
                    count++;
                    System.out.println("\nAccount details:\n" + ba);
                    System.out.println("----------------------------------------------------------------------");
                } else {
                    System.out.println("No accounts found.");
                }
                System.out.println("Number of accounts found: " + count);
            }
        } catch (UIException | ServiceException | IllegalArgumentException e) {
            System.out.println("[Display account error]" + e.getMessage());
        }
    }

    private void withdrawFromAccount() throws UIException {
        try {
            System.out.println("\nWithdraw From Account\n==============================");
            System.out.print("\nEnter account ID: ");
            long id = getLong();
            System.out.print("\nEnter withdrawal ammount (EUR): ");
            long amount = getCurrencyAmount();
            baService.withdrawFromAccount(id, amount);
            System.out.println("\nSuccessfully withdrawn " + amount / 100 + "." + amount % 100
                    + " EUR into account ID " + id + ".\nThe new balance is "
                    + baService.findById(id).getBalance() / 100 + "." + baService.findById(id).getBalance() % 100
                    + " EUR.");
        } catch (ServiceException | UIException ex) {
            throw new UIException("[Withdrawal failed] " + ex.getMessage());
        }
    }

    private void depositIntoAccount() throws UIException {
        try {
            System.out.println("\nDeposit Into Account\n==============================");
            System.out.print("\nEnter account ID: ");
            long id = getLong();
            System.out.print("\nEnter deposit ammount (EUR): ");
            long amount = getCurrencyAmount();
            baService.depositIntoAccount(id, amount);
            System.out.println("\nSuccessfully deposited " + amount / 100 + "." + amount % 100
                    + " EUR into account ID " + id + ".\nThe new balance is "
                    + baService.findById(id).getBalance() / 100 + "." + baService.findById(id).getBalance() % 100
                    + " EUR.");
        } catch (ServiceException | UIException ex) {
            throw new UIException("[Deposit failed] " + ex.getMessage());
        }
    }

    private void transferToAccount() throws UIException {
        try {
            System.out.println("\nTransfer Funds Between Accounts\n==============================");
            System.out.print("\nEnter the payer's account ID: ");
            long payerId = getLong();
            System.out.print("\nEnter the payee's account ID: ");
            long payeeId = getLong();
            System.out.print("\nEnter transfer ammount (EUR): ");
            long amount = getCurrencyAmount();
            baService.withdrawFromAccount(payerId, amount);
            baService.depositIntoAccount(payeeId, amount);
            System.out.println("\nSuccessfully transferred " + amount / 100 + "." + amount % 100
                    + " EUR from account ID " + payerId + " to account ID " + payeeId
                    + ".\nThe new balance of account ID " + payerId + " is "
                    + baService.findById(payerId).getBalance() / 100 + "."
                    + baService.findById(payerId).getBalance() % 100 + " EUR.\nThe new balance of account ID " + payeeId
                    + " is " + baService.findById(payeeId).getBalance() / 100 + "."
                    + baService.findById(payeeId).getBalance() % 100 + " EUR.");
        } catch (ServiceException | UIException ex) {
            throw new UIException("[Transfer failed] " + ex.getMessage());
        }
    }

    private void createCustomer() {
        try {
            System.out.println("\nCreate Customer\n==============================");
            System.out.print("Enter customer firstname: ");
            String firstname = getString();
            System.out.print("Enter customer lastname: ");
            String lastname = getString();
            String fullName = firstname + " " + lastname;
            System.out.print("Enter customer address: ");
            String address = getString();
            System.out.print("Enter customer date of birth (YYYY-MM-DD): ");
            LocalDate dob = LocalDate.parse(getString());
            System.out.print("Enter customer phone number: ");
            String phoneNumber = getString();
            System.out.print("Enter customer email: ");
            String email = getString();
            validateEmail(email);
            System.out.print("Enter customer type (INDIVIDUAL / COMPANY): ");
            String typeStr = getString();
            CustomerType type = CustomerType.valueOf(typeStr.toUpperCase());
            Customer savedCustomer = cuService
                    .createCustomer(new Customer(fullName, address, dob, phoneNumber, email, type));
            System.out.println("\nCustomer id " + savedCustomer.getId() + " created successfully!");
            System.out.println(savedCustomer);
        } catch (ServiceException | UIException | DateTimeParseException | IllegalArgumentException e) {
            System.out.println("[Customer creation failed] " + e.getMessage());
        }
    }

    private void updateCustomer() {
        try {
            boolean updated = false;
            System.out.println("\nUpdate Customer\n==============================");
            System.out.print("Enter customer ID to update: ");
            long idToUpdate = getLong();
            Customer existingCustomer = cuService.findById(idToUpdate);
            System.out.println("Current Customer Details:");
            System.out.println(existingCustomer);
            System.out.println("==============================");
            System.out.print("Enter updated name (enter # for no change): ");
            String updatedName = getString();
            if (!updatedName.equals("#")) {
                updated = true;
                existingCustomer.setName(updatedName);
            }
            System.out.print("Enter updated address (enter # for no change): ");
            String updatedAddress = getString();
            if (!updatedAddress.equals("#")) {
                updated = true;
                existingCustomer.setAddress(updatedAddress);
            }
            System.out.print("Enter updated date of birth (YYYY-MM-DD) (enter # for no change): ");
            String updatedDobStr = getString();
            if (!updatedDobStr.equals("#")) {
                updated = true;
                LocalDate updatedDob = LocalDate.parse(updatedDobStr);
                existingCustomer.setDob(updatedDob);
            }
            System.out.print("Enter updated phone number (enter # for no change): ");
            String updatedPhoneNumber = getString();
            if (!updatedPhoneNumber.equals("#")) {
                updated = true;
                existingCustomer.setPhoneNumber(updatedPhoneNumber);
            }
            System.out.print("Enter updated email (enter # for no change): ");
            String updatedEmail = getString();
            if (!updatedEmail.equals("#")) {
                validateEmail(updatedEmail);
                updated = true;
                existingCustomer.setEmail(updatedEmail);
            }
            System.out.print("Enter updated customer type [INDIVIDUAL | COMPANY] (enter # for no change): ");
            String updatedTypeStr = getString();
            if (!updatedTypeStr.equals("#")) {
                if (updatedTypeStr.equals("COMPANY".toUpperCase())
                        || updatedTypeStr.equals("INDIVIDUAL".toUpperCase())) {
                    updated = true;
                    CustomerType updatedType = CustomerType.valueOf(updatedTypeStr.toUpperCase());
                    existingCustomer.setType(updatedType);
                } else {
                    throw new UIException("Invalid customer type.");
                }
            }
            if (updated) {
                cuService.save(existingCustomer);
                System.out.println("Customer updated successfully!");
                System.out.println("\nUpdated Customer Details:");
                System.out.println(existingCustomer);
            }

        } catch (ServiceException | UIException | DateTimeParseException | IllegalArgumentException e) {
            System.out.println("[Customer update failed] " + e.getMessage());
        }
    }

    private void deactivateCustomer() throws UIException {
        try {
            System.out.println("\nDeactivate Customer\n==============================");
            System.out.print("Enter customer ID to update: ");
            Long idToUpdate = getLong();
            cuService.findById(idToUpdate);
            cuService.deactivateCustomer(idToUpdate);
            System.out.println("Customer id " + idToUpdate + " deactivated successfully.");
            System.out.println("Customer details:\n" + cuService.findById(idToUpdate));
        } catch (ServiceException e) {
            System.out.println("[Customer deactivation failed] " + e.getMessage());
        }
    }

    private void displayCustomerDetails() {
        try {
            System.out.println("\nDisplay Customer Details\n==============================");
            System.out.print("Enter customer ID to display: ");
            long id = getLong();
            Customer customer = cuService.findById(id);
            System.out.println("ID: " + customer.getId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Date of Birth: " + customer.getDob());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Type: " + customer.getType());
            System.out.println("Active " + customer.isActive());
            System.out.println("Created Date: " + customer.getCreatedDate());
            System.out.println("Deactivated Date: " + customer.getDeactivatedDate());
        } catch (ServiceException | UIException e) {
            System.out.println("[Customer display failed] " + e.getMessage());
        }
    }

    private void displayTotals() {
        try {
            System.out.println("\nDisplay Totals\n==============================");
            System.out.println("Total number of accounts: " + baService.count());
            System.out.println("Total number of customers: " + cuService.count());
            System.out.println("Total number of transactions: " + trService.count());
            System.out.println("Total number of operations: " + opService.count());
            long totalBalance = baService.findAll().stream().mapToLong(BankAccount::getBalance).sum();
            System.out.println("Total balance of all accounts: " + totalBalance / 100 + "." + totalBalance % 100
                    + " EUR");
        } catch (Exception e) {
            System.err.println("[Display totals failed] " + e.getMessage());
        }
    }

    private void displayAccountsByDate() {
        try {
            System.out.println("\nDisplay Accounts By Date\n==============================");
            Pair<LocalDate> dates = getDateRange();
            System.out.println("\nDisplaying accounts from " + dates.getFirst() + " to " + dates.getSecond());
            System.out.println("----------------------------------------------------------------------");
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            if (!bankAccounts.isEmpty()) {
                for (BankAccount bankAccount : bankAccounts) {
                    LocalDate bankAccounDate = bankAccount.getCreatedDate();
                    if (!bankAccounDate.isBefore(dates.getFirst()) && !bankAccounDate.isAfter(dates.getSecond())) {
                        System.out.println(bankAccount);
                    }
                }
            } else {
                System.out.println("No bank accounts found.");
            }
            System.out.println("----------------------------------------------------------------------");
        } catch (ServiceException | UIException e) {
            System.out.println("[Display accounts by date failed] " + e.getMessage());
        }
    }

    private void displayCustomersByDate() {
        try {
            System.out.println("\nDisplay Customers By Date\n==============================");
            Pair<LocalDate> dates = getDateRange();
            System.out.println("\nDisplaying customers from " + dates.getFirst() + " to " + dates.getSecond());
            System.out.println("----------------------------------------------------------------------");
            ArrayList<Customer> customers = cuService.findAll();
            if (!customers.isEmpty()) {
                for (Customer customer : customers) {
                    LocalDate customerDate = customer.getCreatedDate();
                    if (!customerDate.isBefore(dates.getFirst()) && !customerDate.isAfter(dates.getSecond())) {
                        System.out.println(customer);
                    }
                }
            } else {
                System.out.println("No customers found.");
            }
            System.out.println("----------------------------------------------------------------------");
        } catch (ServiceException | UIException e) {
            System.out.println("[Display customers by date failed] " + e.getMessage());
        }
    }

    private void displayTransactionsByDate() {
        try {
            System.out.println("\nDisplay Transactions By Date\n==============================");
            Pair<LocalDate> dates = getDateRange();
            System.out.println("\nDisplaying customers from " + dates.getFirst() + " to " + dates.getSecond());
            System.out.println("----------------------------------------------------------------------");
            ArrayList<Transaction> transactions = trService.findAll();
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    LocalDate transactionDate = transaction.getCreatedDate();
                    if (!transactionDate.isBefore(dates.getFirst()) && !transactionDate.isAfter(dates.getSecond())) {
                        System.out.println(transaction);
                    }
                }
            } else {
                System.out.println("No transactions found.");
            }
            System.out.println("----------------------------------------------------------------------");
        } catch (ServiceException | UIException e) {
            System.out.println("[Display transactions by date failed] " + e.getMessage());
        }
    }

    private void displayOperationsByDate() {
        try {
            System.out.println("\nDisplay Transactions By Date\n==============================");
            Pair<LocalDate> dates = getDateRange();
            System.out.println("\nDisplaying customers from " + dates.getFirst() + " to " + dates.getSecond());
            System.out.println("\n----------------------------------------------------------------------");

            ArrayList<Operation> operations = opService.findAll();
            if (!operations.isEmpty()) {
                for (Operation operation : operations) {
                    LocalDate operationDate = operation.getDate();
                    if (!operationDate.isBefore(dates.getFirst()) && !operationDate.isAfter(dates.getSecond())) {
                        System.out.println(operation);
                    }
                }
            } else {
                System.out.println("No operations found.");
            }
            System.out.println("----------------------------------------------------------------------");
        } catch (ServiceException | UIException e) {
            System.out.println("[Display operations by date failed] " + e.getMessage());
        }
    }

    private void createAccount() {
        try {
            System.out.println("\nCreate Account\n==============================");
            System.out.print("Enter customer ID to create account for: ");
            long idToUpdate = getLong();
            Customer existingCustomer = cuService.findById(idToUpdate);
            BankAccount existingBankAccount = baService.findByCustomerId(idToUpdate);
            if (existingBankAccount != null && existingBankAccount.getCustomer().getId() == existingCustomer.getId()) {
                throw new UIException("Customer already has an account.");
            }
            System.out.println("Current Customer Details:");
            System.out.println(existingCustomer);
            System.out.println("==============================");
            System.out.print("Enter Account name: ");
            String name = getString();
            System.out.print("Enter Account starting balance (EUR): ");
            long balance = getCurrencyAmount();
            System.out.print(
                    "Enter Account type [1 = CURRENT_ACCOUNT | 2 = SAVING_ACCCOUNT | 3 = STUDENT_ACCOUNT]: ");
            String typeStr = getString();
            switch (typeStr) {
                case "1":
                    typeStr = "CURRENT_ACCOUNT";
                    break;
                case "2":
                    typeStr = "SAVING_ACCOUNT";
                    break;
                case "3":
                    typeStr = "STUDENT_ACCOUNT";
                    break;
                default:
                    throw new UIException("Invalid account type.");
            }
            AccountType type = AccountType.valueOf(typeStr.toUpperCase());
            BankAccount savedBankAccount = baService.createAccount(new BankAccount(name, type, balance),
                    existingCustomer);
            System.out.println("\nAccount id " + savedBankAccount.getId() + " created successfully!");
            System.out.println(savedBankAccount);
        } catch (ServiceException | UIException e) {
            System.out.println("[Bank account creation failed] " + e.getMessage());
        }
    }

    private void updateAccount() {
        try {
            boolean updated = false;
            System.out.println("\nUpdate Account\n==============================");
            System.out.print("Enter bank account ID to update: ");
            long idToUpdate = getLong();
            BankAccount existingAccount = baService.findById(idToUpdate);
            System.out.println("Current Account Details:");
            System.out.println(existingAccount);
            System.out.println("==============================");
            System.out.println("Enter updated name (enter # for no change): ");
            String updatedName = getString();
            if (!updatedName.equals("#"))
                existingAccount.setAccountName(updatedName);
            updated = true;
            System.out.print(
                    "Enter updated BankAccount type [1 = CURRENT_ACCOUNT | 2 = SAVING_ACCCOUNT | 3 = STUDENT_ACCOUNT] (enter # for no change): ");
            String updatedTypeStr = getString();
            if (!updatedTypeStr.equals("#")) {
                switch (updatedTypeStr) {
                    case "1":
                        updatedTypeStr = "CURRENT_ACCOUNT";
                        updated = true;
                        break;
                    case "2":
                        updatedTypeStr = "SAVING_ACCOUNT";
                        updated = true;
                        break;
                    case "3":
                        updatedTypeStr = "STUDENT_ACCOUNT";
                        updated = true;
                        break;
                    default:
                        throw new UIException("Invalid account type.");
                }
                AccountType updatedType = AccountType.valueOf(updatedTypeStr.toUpperCase());
                updated = true;
                existingAccount.setType(updatedType);
            } else {
                throw new UIException("Invalid account type.");
            }
            if (updated) {
                baService.save(existingAccount);
                System.out.println("BankAccount updated successfully!");
                System.out.println("\nUpdated BankAccount Details:");
                System.out.println(existingAccount);
            } else {
                System.out.println("No changes made.");
            }
        } catch (ServiceException | UIException |

                DateTimeParseException e) {
            System.out.println("[Bank account update failed] " + e.getMessage());
        }
    }

    private void deactivateAccount() throws UIException {
        try {
            System.out.println("\nDeactivate Bank Account\n==============================");
            System.out.print("Enter account ID to update: ");
            Long idToUpdate = getLong();
            baService.findById(idToUpdate);
            baService.deactivateAccount(idToUpdate);
            System.out.println("Bank account id " + idToUpdate + " deactivated successfully.");
            System.out.println("Bank account details:\n" + baService.findById(idToUpdate));
        } catch (ServiceException e) {
            System.out.println("[Bank account deactivation failed] " + e.getMessage());
        }
    }

    private Pair<LocalDate> getDateRange() throws UIException {
        try {
            LocalDate startDate;
            LocalDate endDate;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.print("Enter Start Date (enter # for 1 year ago): ");
            String startDateStr = getString();
            if (!startDateStr.equals("#")) {
                startDate = LocalDate.parse(startDateStr, formatter);
            } else {
                startDate = LocalDate.now().minusYears(1);
            }
            System.out.print("Enter End Date (enter # for today): ");
            String endDateStr = getString();
            if (!endDateStr.equals("#")) {
                endDate = LocalDate.parse(endDateStr, formatter);
            } else {
                endDate = LocalDate.now();
            }
            return new Pair<LocalDate>(startDate, endDate);
        } catch (UIException | DateTimeParseException e) {
            throw new UIException("[Get date range failed] " + e.getMessage());
        }

    }

    class Pair<T> {
        private final T m_first;
        private final T m_second;

        public Pair(T first, T second) {
            m_first = first;
            m_second = second;
        }

        public T getFirst() {
            return m_first;
        }

        public T getSecond() {
            return m_second;
        }
    }
}
