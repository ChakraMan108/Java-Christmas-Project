package com.bank.ui;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

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

public class UI implements UiInterface {

    // Service initialisation
    BankAccountService baService = new BankAccountService();
    OperationService opService = new OperationService();
    TransactionService trService = new TransactionService();
    CustomerService cuService = new CustomerService();

    // UI class constructor
    public UI() {

    }

    private long idToUpdate;

    public void authenticateApp() throws UIException {
        try {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("=     AUTHENTICATION   =");
            System.out.println("========================");
            System.out.println("Enter username: ");
            String username = getString();
            System.out.println("Enter password: ");
            String password = getString();
            if (!username.equals("admin") || !password.equals("admin"))
                throw new UIException("Invalid credentials!");
        } catch (Exception ex) {
            throw new UIException("[UI Error:] " + ex.getMessage());
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
                        System.out.println("Exiting the Bank Application.");
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option (1-6).");
                }
            } catch (UIException ex) {
                System.out.println("[UI Error:] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void customerManagement() throws UIException {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("=  CUSTOMER MANAGEMENT  =");
            System.out.println("========================");
            System.out.println("1. Create Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Deactivate Customer");
            System.out.println("4. Display Customer Details");
            System.out.println("5. Return to Main Menu");
            System.out.println("========================");
            System.out.println("Selection option:");
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
                System.out.println("[UI Error:] " + ex.getMessage());
            }
        } while (!exit);
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
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI Error:] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void accountDisplay() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("=   ACCOUNT DISPLAY    =");
            System.out.println("========================");
            System.out.println("1. Display Account by ID");
            System.out.println("2. Display Accounts by Customer");
            System.out.println("3. Display Accounts by Balance");
            System.out.println("4. Display Accounts by Type");
            System.out.println("5. Return to Main Menu");
            System.out.println("Selection option:");
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
                System.out.println("[UI Error:] " + e.getMessage());
            }
        } while (!exit);
    }

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
            System.out.println("4. Return to Main Menu");
            System.out.println("========================");
            System.out.println("Selection option:");
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
                System.out.println("[UI Error:] " + ex.getMessage());
            }
        } while (!exit);
    }

    private void reports() {
        boolean exit = false;
        do {
            clearConsole();
            System.out.println("\n========================");
            System.out.println("=       REPORTING       =");
            System.out.println("========================");
            System.out.println("1. Display Totals");
            System.out.println("2. Display Accounts by Date");
            System.out.println("3. Display Customers by Date");
            System.out.println("4. Display Transactions by Date");
            System.out.println("5. Display Operations by Date");
            System.out.println("6. Return to Main Menu");
            System.out.println("========================");
            System.out.println("Selection option:");

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
                        displayOperationsByDate(null, null);
                        break;
                    case "6":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (UIException ex) {
                System.out.println("[UI Error:] " + ex.getMessage());
            }
        } while (!exit);
    }

    public String getString() throws UIException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input == null || input.trim().equals("")) {
            throw new UIException("Invalid input.");
        }
        return input;
    }

    public long getLong() throws UIException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {

            throw new UIException("Invalid input.");
        }
        return Long.parseLong(input);
    }

    public void Details(ArrayList<Object> coll) {
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    public void validateEmail(String email) throws UIException {
        if (!EmailValidator.getInstance().isValid(email))
            throw new UIException("Invalid Email Address.");
        if (email == null || email.trim().equals(""))
            throw new NullPointerException("Invalid Input.");
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

    private void displayAccountsById() {
        try {
            System.out.println("\nDisplay Account By ID\n========================");
            System.out.println("Enter account ID: ");
            long id = getLong();
            System.out.println("\nAccount details:\n" + baService.findById(id));
            System.out.println("----------------------------------------------------------------------");
        } catch (UIException | ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAccountsByCustomer() {
        try {
            System.out.println("\nDisplay Accounts By Customer\n========================");
            System.out.println("Enter customer name: ");
            String name = getString();
            System.out.println("\nAccount details:\n" + baService.findByCustomerName(name));
            System.out.println("----------------------------------------------------------------------");
        } catch (UIException | ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAccountsByBalance() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Balance\n========================");
            System.out.println("Enter balance minimum (EUR): ");
            long minimum = getLong();
            System.out.println("Enter balance maximum (EUR): ");
            long maximum = getLong();
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            for (BankAccount ba : bankAccounts) {
                if (ba.getBalance() >= minimum * 100 && ba.getBalance() <= maximum * 100) {
                    count++;
                    System.out.println("\nAccount details:\n" + ba);
                    System.out.println("----------------------------------------------------------------------");
                } else {
                    System.out.println("No accounts found.");
                }
                System.out.println("Number of accounts found: " + count);
            }
        } catch (UIException | ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAccountsByType() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Type\n========================");
            System.out.println("Enter Account Type: ");
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
            System.out.println(e.getMessage());
        }
    }

    // Fionn
    private void withdrawFromAccount() throws UIException {
        System.out.println("\nWithdraw From Account\n========================");
        try {
            System.out.println("\nEnter account ID: ");
            long id = getLong();
            System.out.println("\nEnter withdrawal ammount (Euro): ");
            long amount = getLong() * 100;
            baService.withdrawFromAccount(id, amount);
            System.out.println("\nSuccessfully withdrawn " + baService.findById(id).getBalance() / 100
                    + " Euro into account ID " + id + ".");
        } catch (ServiceException | UIException ex) {
            throw new UIException("[UI Error:] " + ex.getMessage());
        }
    }

    private void depositIntoAccount() throws UIException {
        System.out.println("\nDeposit Into Account\n========================");
        try {
            System.out.println("\nEnter account ID:");
            long id = getLong();
            System.out.println("\nEnter deposit ammount (Euro)");
            long amount = getLong() * 100;
            baService.depositIntoAccount(id, amount);
            System.out.println("\nSuccessfully deposited " + baService.findById(id).getBalance() / 100
                    + " Euro into account ID " + id + ".");
        } catch (ServiceException ex) {
            throw new UIException("[UI Error:] " + ex.getMessage());
        }
    }

    private void transferToAccount() throws UIException {
        System.out.println("\nTransfer Funds Between Account\n========================");
        try {
            System.out.println("\nEnter the payer's account ID: ");
            long payerId = getLong();
            System.out.println("\nEnter the payee's account ID: ");
            long payeeId = getLong();
            System.out.println("\nEnter transfer ammount (Euro): ");
            long amount = getLong() * 100;
            baService.withdrawFromAccount(payerId, amount);
            baService.depositIntoAccount(payeeId, amount);
            System.out.println(
                    "\nSuccessfully  debited " + baService.findById(payerId).getBalance() / 100 + " Euro in account ID "
                            + payerId + ".");
            System.out.println(
                    "\nSuccessfully credited " + baService.findById(payeeId).getBalance() / 100 + " Euro in account ID "
                            + payeeId + ".");
        } catch (ServiceException ex) {
            throw new UIException("[UI Error:] " + ex.getMessage());
        }
    }

    // Dhara
    private void displayMainMenu() {
        System.out.println("\n\n1. Create Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Deactivate Customer");
        System.out.println("4. Display Customer Details");
        System.out.println("5. Return to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private void createCustomer() {
        try {
            System.out.print("Enter customer ID: ");
            long id = getLong();
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
            System.out.print("Enter customer type (e.g., REGULAR, PREMIUM): ");
            String typeStr = getString();
            CustomerType type = CustomerType.valueOf(typeStr.toUpperCase());
            try {
                cuService.save(new Customer(id, fullName, address, dob, phoneNumber, email, type));
            } catch (ServiceException e) {
                System.out.println("[UI error] " + e.getMessage());
            }
        } catch (UIException e) {
            e.printStackTrace();
        }
        System.out.println("Customer created successfully!");
    }

    private void updateCustomer() {
        try {
            System.out.print("Enter customer ID to update: ");
            long idToUpdate = getLong();
            Customer existingCustomer = cuService.findById(idToUpdate);
            System.out.println("Current Customer Details:");
            System.out.println(existingCustomer);
            System.out.println("--------------------------");
            System.out.println("Enter updated name (or press Enter to keep current): ");
            String updatedName = getString();
            if (!updatedName.isEmpty()) {
                existingCustomer.setName(updatedName);
            }
            System.out.print("Enter updated address (or press Enter to keep current): ");
            String updatedAddress = getString();
            if (!updatedAddress.isEmpty()) {
                existingCustomer.setAddress(updatedAddress);
            }
            System.out.print("Enter updated date of birth (YYYY-MM-DD) (or press Enter to keep current): ");
            String updatedDobStr = getString();
            if (!updatedDobStr.isEmpty()) {
                LocalDate updatedDob = LocalDate.parse(updatedDobStr);
                existingCustomer.setDob(updatedDob);
            }
            System.out.print("Enter updated phone number (or press Enter to keep current): ");
            String updatedPhoneNumber = getString();
            if (!updatedPhoneNumber.isEmpty()) {
                existingCustomer.setPhoneNumber(updatedPhoneNumber);
            }
            System.out.print("Enter updated email (or press Enter to keep current): ");
            String updatedEmail = getString();
            validateEmail(updatedEmail);
            System.out.print("Enter updated customer type: ");
            String updatedTypeStr = getString();
            if (!updatedTypeStr.isEmpty() || !updatedTypeStr.equals("COMPANY".toUpperCase())
                    || !updatedTypeStr.equals("INDIVIDUAL".toUpperCase())) {
                CustomerType updatedType = CustomerType.valueOf(updatedTypeStr.toUpperCase());
                existingCustomer.setType(updatedType);
            } else {
                throw new UIException("Invalid customer type.");
            }
            System.out.println("Updated Customer Details:");
            System.out.println(existingCustomer);
            cuService.save(existingCustomer);
            System.out.println("Customer updated successfully!");

        } catch (ServiceException | UIException e) {
            System.out.println("[UI error] " + e.getMessage());
        }
    }

    private void deactivateCustomer() throws UIException {
        Long idToUpdate = getLong();
        try {
            cuService.findById(idToUpdate);
            cuService.deactivateCustomer(idToUpdate);
            System.out.println("Customer id " + idToUpdate + " deactivated successfully.");
        } catch (ServiceException e) {
            System.out.println("[UI error] " + e.getMessage());
        }
    }

    private void displayCustomerDetails() {
        try {
            Customer customer = cuService.findById(idToUpdate);
            if (customer != null) {
                System.out.println("Name: " + customer.getName());
                System.out.println("Address: " + customer.getAddress());
                System.out.println("Date of Birth: " + customer.getDob());
                System.out.println("Phone Number: " + customer.getPhoneNumber());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Type: " + customer.getType());
                System.out.println("Customer ID: " + customer.isActive());
                System.out.println("Created Date: " + customer.getCreatedDate());
                System.out.println("Deactivated Date: " + customer.getDeactivatedDate());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (ServiceException e) {
            System.out.println("[UI error] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // method for report-Dhara
    private void displayTotals() {
        try {
            System.out.println("Total number of accounts: " + baService.count());
            System.out.println("Total number of customers: " + cuService.count());
            System.out.println("Total number of transactions: " + trService.count());
            System.out.println("Total number of operations: " + opService.count());
            long totalBalance = baService.findAll().stream().mapToLong(BankAccount::getBalance).sum();
            System.out.println("Total balance across all accounts (EUR): " + totalBalance / 100);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void displayAccountsByDate() {
        try {
            ArrayList<BankAccount> accounts = baService.findAll();

            Map<LocalDate, List<BankAccount>> bankAccount = accounts.stream()
                    .collect(Collectors.groupingBy(BankAccount::getCreatedDate));

            for (Entry<LocalDate, List<BankAccount>> entry : bankAccount.entrySet()) {
                LocalDate date = entry.getKey();
                List<BankAccount> baAccountsOnDate = entry.getValue();

                System.out.println("Date: " + date);
                for (BankAccount account : baAccountsOnDate) {
                    System.out.println("  BankAccount: " + account.getAccountName() + " | ID: " + account.getId());
                }
                System.out.println();
            }
        } catch (ServiceException ex) {

            System.err.println("An error occurred: " + ex.getMessage());
        }

    }

    public void displayCustomersByDate() {
        try {
            ArrayList<Customer> customers = cuService.findAll();

            Map<LocalDate, List<Customer>> customersByDate = customers.stream()
                    .collect(Collectors.groupingBy(Customer::getCreatedDate));

            for (Map.Entry<LocalDate, List<Customer>> entry : customersByDate.entrySet()) {
                LocalDate date = entry.getKey();
                List<Customer> customersOnDate = entry.getValue();

                System.out.println("Date: " + date);
                for (Customer customer : customersOnDate) {
                    System.out.println("  Customer: " + customer.getName() + " | ID: " + customer.getId());

                }
                System.out.println();
            }
        } catch (ServiceException ex) {

            System.err.println("An error occurred: " + ex.getMessage());
        }
    }

    public void displayTransactionsByDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.println("Enter Start Date:");
            LocalDate startDate = LocalDate.parse(getString(), formatter);
            System.out.println("Enter End Date:");
            LocalDate endDate = LocalDate.parse(getString(), formatter);
            System.out.println("Displaying transactions from " + startDate + " to " + endDate);
            System.out.println("\n----------------------------------------------------------------------");
            try {
                List<Transaction> transactions = trService.findAll();
                if (!transactions.isEmpty())
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getCreatedDate();
                        if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                            System.out.println(transaction);
                        }
                    }
                else {
                    System.out.println("No transactions found.");
                }
                System.out.println("----------------------------------------------------------------------");
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        } catch (UIException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayOperationsByDate(LocalDate startDate, LocalDate endDate) {
        System.out.println("Operations from " + startDate + " to " + endDate);
        System.out.println("-----------------------------------");

        try {
            List<Operation> operations = opService.findAll();
            for (Operation operation : operations) {
                LocalDate operationDate = operation.getDate();
                if (!operationDate.isBefore(startDate) && !operationDate.isAfter(endDate)) {
                    System.out.println(operation);
                }
            }
            System.out.println("-----------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}