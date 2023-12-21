package com.bank.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import org.apache.commons.validator.routines.EmailValidator;
import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Customer.CustomerType;
import com.bank.entity.Transaction;
import com.bank.exceptions.MenuException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerService;

public class UiImpl implements Ui {

    public UiImpl() {

    }

    CustomerService customerService = new CustomerService();
    CustomerRepository customerRepository = new CustomerRepository();
    private long idToUpdate;

    public void authenticateApp() throws MenuException {
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
                throw new MenuException("Invalid credentials!");
        } catch (Exception ex) {
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
                        System.out.println("Exiting the Bank Application.");
                        break;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option (1-6).");
                }
            } catch (MenuException | ServiceException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!exit);
    }

    private void customerManagement() throws ServiceException {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        try {
            while (!exit) {
                displayMainMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        updateCustomer();
                        break;
                    case 3:
                        deactivateCustomer(null);
                        break;
                    case 4:
                        displayCustomerDetails();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            if (scanner != null) {
                scanner.close();
            }
        }

        System.out.println("Exiting Customer Management System. Goodbye!");
        scanner.close();
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
                        System.out.println("\nDisplay Totals");
                        break;

                    case "2":
                        System.out.println("\nDisplay Accounts by Date");
                        break;

                    case "3":
                        System.out.println("\nDisplay Customers by Date");
                        break;

                    case "4":
                        System.out.println("\nDisplay Transactions by Date");
                        break;

                    case "5":
                        System.out.println("\nDisplay Operations by Date");
                        break;

                    case "6":
                        System.out.println("\nReturn to Main Menu");
                        exit = true;
                        break;
                      
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } catch (MenuException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!exit);
    }

    public String getString() throws MenuException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if (input == null || input.trim().equals("")) {
            throw new MenuException("Invalid input.");
        }
        return input;
    }

    public long getLong() throws MenuException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new MenuException("Invalid input.");
        }
        return Long.parseLong(input);
    }

    public void Details(ArrayList<Object> coll) {
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    public void validateEmail(String email) throws MenuException {
        if (!EmailValidator.getInstance().isValid(email))
            throw new MenuException("Invalid Email Address.");
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
            System.err.println("Cannot execute the terminal command cls/clear.\n" + e.getMessage());
        }
    }

    // Fionn

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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        System.out.print("Enter customer date of birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        System.out.print("Enter customer type (e.g., REGULAR, PREMIUM): ");
        String typeStr = scanner.nextLine();
        CustomerType type = CustomerType.valueOf(typeStr.toUpperCase());

        // customerList.add(newCustomer);

        System.out.println("Customer created successfully!");
    }

    private void updateCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer ID to update: ");
        long idToUpdate = scanner.nextLong();
        scanner.nextLine();

        Customer existingCustomer = findById(idToUpdate);

        if (existingCustomer != null) {
            System.out.println("Current Customer Details:");
            displayMainMenu();

            System.out.print("Enter updated name (or press Enter to keep current): ");
            String updatedName = scanner.nextLine();
            if (!updatedName.isEmpty()) {
                existingCustomer.setName(updatedName);
            }

            System.out.print("Enter updated address (or press Enter to keep current): ");
            String updatedAddress = scanner.nextLine();
            if (!updatedAddress.isEmpty()) {
                existingCustomer.setAddress(updatedAddress);
            }

            System.out.print("Enter updated date of birth (YYYY-MM-DD) (or press Enter to keep current): ");
            String updatedDobStr = scanner.nextLine();
            if (!updatedDobStr.isEmpty()) {
                LocalDate updatedDob = LocalDate.parse(updatedDobStr);
                existingCustomer.setDob(updatedDob);
            }

            System.out.print("Enter updated phone number (or press Enter to keep current): ");
            String updatedPhoneNumber = scanner.nextLine();
            if (!updatedPhoneNumber.isEmpty()) {
                existingCustomer.setPhoneNumber(updatedPhoneNumber);
            }

            System.out.print("Enter updated email (or press Enter to keep current): ");
            String updatedEmail = scanner.nextLine();
            if (!updatedEmail.isEmpty()) {
                existingCustomer.setEmail(updatedEmail);
            }

            System.out.print("Enter updated customer type: ");
            String updatedTypeStr = scanner.nextLine();
            if (!updatedTypeStr.isEmpty()) {
                CustomerType updatedType = CustomerType.valueOf(updatedTypeStr.toUpperCase());
                existingCustomer.setType(updatedType);
            }

            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer with ID " + idToUpdate + " not found.");
        }
    }

    private Customer findById(long idToUpdate2) {// if removed doesn't fetch frpm service
        return null;
    }

    private void deactivateCustomer(Customer customer) {
        customer.setActive(false);

        Customer deactivatedCustomer = new Customer(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getDob(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                CustomerType.NEW_TYPE);

        System.out.println("Customer deactivated successfully.");
    }

    private void displayCustomerDetails() throws ServiceException {

        try {
            Customer customer = customerService.findById(idToUpdate);

            if (customer != null) {
                System.out.println("Name: " + customer.getName());
                System.out.println("Name: " + customer.getAddress());
                System.out.println("Name: " + customer.getDob());
                System.out.println("Name: " + customer.getPhoneNumber());
                System.out.println("Name: " + customer.getEmail());
                System.out.println("Name: " + customer.getType());
                System.out.println("Customer ID: " + customer.isActive());
                System.out.println("Name: " + customer.getCreatedDate());
                System.out.println("Name: " + customer.getDeactivatedDate());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (ServiceException e) {

            System.out.println("Error retrieving customer details: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {

            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
