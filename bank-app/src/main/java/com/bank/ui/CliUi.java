package com.bank.ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.bank.service.AuthenticationService;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;
import com.bank.service.OperationService;
import com.bank.service.TransactionService;

public class CliUi implements Ui {
    
    private static CustomerService cuService;
    private static BankAccountService baService;
    private static OperationService opService;
    private static TransactionService trService;
    private AuthenticationService authService;
    public static String dataPath;

    public static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Public Constructor injecting services using service interfaces
    public CliUi(CustomerService cuService, BankAccountService baService, OperationService opService,
            TransactionService trService, AuthenticationService authService) {
                this.cuService = cuService;
                this.baService = baService;
                this.opService = opService;
                this.trService = trService;
                this.authService = authService;
    }

    public boolean authenticate() throws UIException {
        try {
            clearConsole();
            if (authService.authenticate()) {
                return true;
            }
            else {
                return false;
            }
        } catch (ServiceException ex) {
            throw new UIException("[UI authenticate error]" + ex.getMessage());
        }
    }
    
    public void displayApp() {
        boolean exit = false;
        do {
            try {
                clearConsole();
                MainMenu mainMenu = new MainMenu();
                exit = mainMenu.isExited();
            } catch (UIException ex) {
                System.out.println("[UI error] " + ex.getMessage());
                exit = false;
                pressEnterToContinue();
            }
        } while (!exit);
        System.out.println("\nGoodbye! Exiting the Bank Application.");
    }

     public String getString() throws UIException {
        String input = scanner.nextLine();
        if (input == null || input.trim() == "") {
            throw new UIException("[getString Input Error] Invalid input.");
        }
        return input;
    }

    public long getLong() throws UIException {
        String input = scanner.nextLine();
        if (input == null || input.trim() == "") {
            throw new UIException("[getLong Input Error] Invalid input.");
        }
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new UIException("[getLong Input Error] Invalid input. Requires an integer up to 18 digits long.");
        }
    }

    private long getCurrencyAmount() throws UIException {
        String input = scanner.nextLine();
        if (input == null || input.trim().equals("")) {
            throw new UIException("Invalid input.");
        }
        BigDecimal bd = new BigDecimal(input);
        try {
            bd.setScale(2);
            return bd.movePointRight(2).longValueExact();
        } catch (NumberFormatException | ArithmeticException e) {
            throw new UIException(
                    "[getCurrencyAmount Error] Invalid input. Requires a number with up to 2 decimal places.");
        }
    }

    private void validateEmail(String email) throws UIException {
        if (!EmailValidator.getInstance().isValid(email))
            throw new UIException("[Email Validation Error] Invalid Email Address.");
        if (email == null || email.trim().equals(""))
            throw new NullPointerException("[Email Validation Error] Invalid Input.");
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win"))
                // clear screen on windows OS
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                // clear screen on *nix OS
                System.out.print("\033[H\033[2J");
            System.out.flush();
            // Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayAccountsById() {
        try {
            System.out.println("\nDisplay Account By ID\n==============================");
            System.out.print("Enter account ID: ");
            long id = getLong();
            System.out.println("\nAccount details:\n" + baService.findById(id));
            pressEnterToContinue();
        } catch (UIException | ServiceException e) {
            System.out.println("[Display account error]" + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayAccountsByCustomer() {
        try {
            System.out.println("\nDisplay Accounts By Customer\n==============================");
            System.out.print("Enter customer name: ");
            String name = getString();
            System.out.println("\nAccount details:\n" + baService.findByCustomerName(name));
            pressEnterToContinue();
        } catch (UIException | ServiceException e) {
            System.out.println("[Display account error]" + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayAccountsByBalance() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Balance\n==============================");
            System.out.print("Enter balance minimum (EUR): ");
            long minimum = getCurrencyAmount();
            System.out.print("Enter balance maximum (EUR): ");
            long maximum = getCurrencyAmount();
            System.out.println(
                    "\nDisplaying accounts between " + minimum / 100 + "." + String.format("%02d", minimum % 100)
                            + " EUR and " + maximum / 100 + "." + String.format("%02d", maximum % 100)
                            + " EUR.");
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            for (BankAccount ba : bankAccounts) {
                if (ba.getBalance() >= minimum && ba.getBalance() <= maximum) {
                    count++;
                    System.out.println("\nAccount details:\n" + ba);
                }
            }
            if (count == 0) {
                System.out.println("No accounts found.");
                pressEnterToContinue();
            } else {
                System.out.println("Number of accounts found: " + count);
                pressEnterToContinue();
            }
        } catch (UIException | ServiceException e) {
            System.out.println("[Display account error]" + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayAccountsByType() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Type\n==============================");
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
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            for (BankAccount ba : bankAccounts) {
                if (ba.getType() == type) {
                    count++;
                    System.out.println("\nAccount details:\n" + ba);
                }
            }
            System.out.println("Number of accounts found: " + count);
            pressEnterToContinue();
        } catch (UIException | ServiceException | IllegalArgumentException e) {
            System.out.println("[Display account error] " + e.getMessage());
            pressEnterToContinue();
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
            System.out.println("\nSuccessfully withdrawn " + amount / 100 + "." + String.format("%02d", amount % 100)
                    + " EUR into account ID " + id + ".\nThe new balance is "
                    + baService.findById(id).getBalance() / 100 + "."
                    + String.format("%02d", baService.findById(id).getBalance() % 100)
                    + " EUR.");
            pressEnterToContinue();
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
            System.out.println("\nSuccessfully deposited " + amount / 100 + "." + String.format("%02d", amount % 100)
                    + " EUR into account ID " + id + ".\nThe new balance is "
                    + baService.findById(id).getBalance() / 100 + "."
                    + String.format("%02d", baService.findById(id).getBalance() % 100)
                    + " EUR.");
            pressEnterToContinue();
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
            System.out.println("\nSuccessfully transferred " + amount / 100 + "." + String.format("%02d", amount % 100)
                    + " EUR from account ID " + payerId + " to account ID " + payeeId
                    + ".\nThe new balance of account ID " + payerId + " is "
                    + baService.findById(payerId).getBalance() / 100 + "."
                    + String.format("%02d", baService.findById(payerId).getBalance() % 100)
                    + " EUR.\nThe new balance of account ID " + payeeId
                    + " is " + baService.findById(payeeId).getBalance() / 100 + "."
                    + String.format("%02d", baService.findById(payeeId).getBalance() % 100) + " EUR.");
            pressEnterToContinue();
        } catch (ServiceException | UIException ex) {
            throw new UIException("[Transfer failed] " + ex.getMessage());
        }
    }

    public void createCustomer() {
        try {
            System.out.println("\nCreate Customer\n==============================");
            System.out.print("Enter customer firstname: ");
            String firstname = getString();
            System.out.print("Enter customer lastname: ");
            String lastname = getString();
            String fullName = firstname + " " + lastname;
            System.out.print("Enter customer address: ");
            String address = getString();
            System.out.print("Enter customer date of birth [yyyy-mm-dd]: ");
            LocalDate dob = LocalDate.parse(getString());
            System.out.print("Enter customer phone number: ");
            String phoneNumber = getString();
            System.out.print("Enter customer email: ");
            String email = getString();
            validateEmail(email);
            System.out.print("Enter customer type [1 = INDIVIDUAL | 2 = COMPANY]: ");
            String typeStr = getString();
            if (typeStr == "1")
                    typeStr = "INDIVIDUAL";
                else if (typeStr == "2")
                        typeStr = "COMPANY";
                    else
                        throw new UIException("Invalid customer type.");
            CustomerType type = CustomerType.valueOf(typeStr.toUpperCase());
            Customer savedCustomer = cuService
                    .createCustomer(new Customer(fullName, address, dob, phoneNumber, email, type));
            System.out.println("\nCustomer id " + savedCustomer.getId() + " created successfully!");
            System.out.println(savedCustomer);
            pressEnterToContinue();
        } catch (ServiceException | UIException | DateTimeParseException | IllegalArgumentException e) {
            System.out.println("[Customer creation failed] " + e.getMessage());
            pressEnterToContinue();
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
            Customer updatedCustomer = new Customer(existingCustomer);
            // Fail fast validation to force some validation exceptions before any user
            // input. cuService.update () returns null as no changes were made at this point
            cuService.update(updatedCustomer);
            System.out.print("Enter updated name (enter # for no change): ");
            String updatedName = getString();
            if (!updatedName.equals("#")) {
                updated = true;
                updatedCustomer.setName(updatedName);
            }
            System.out.print("Enter updated address (enter # for no change): ");
            String updatedAddress = getString();
            if (!updatedAddress.equals("#")) {
                updated = true;
                updatedCustomer.setAddress(updatedAddress);
            }
            System.out.print("Enter updated date of birth [yyyy-mm-dd] (enter # for no change): ");
            String updatedDobStr = getString();
            if (!updatedDobStr.equals("#")) {
                updated = true;
                LocalDate updatedDob = LocalDate.parse(updatedDobStr);
                updatedCustomer.setDob(updatedDob);
            }
            System.out.print("Enter updated phone number (enter # for no change): ");
            String updatedPhoneNumber = getString();
            if (!updatedPhoneNumber.equals("#")) {
                updated = true;
                updatedCustomer.setPhoneNumber(updatedPhoneNumber);
            }
            System.out.print("Enter updated email (enter # for no change): ");
            String updatedEmail = getString();
            if (!updatedEmail.equals("#")) {
                validateEmail(updatedEmail);
                updated = true;
                updatedCustomer.setEmail(updatedEmail);
            }
            System.out.print("Enter updated customer type [1 = INDIVIDUAL | 2 = COMPANY] (enter # for no change): ");
            String updatedTypeStr = getString();
            if (!updatedTypeStr.equals("#")) {
                switch (updatedTypeStr) {
                    case "1":
                        updatedTypeStr = "INDIVIDUAL";
                        if (existingCustomer.getType() == CustomerType.COMPANY) {
                            updated = true;
                        }
                        break;
                    case "2":
                        updatedTypeStr = "COMPANY";
                        if (existingCustomer.getType() == CustomerType.INDIVIDUAL) {
                            updated = true;
                        }
                        break;
                    default:
                        throw new UIException("Invalid account type.");
                }
                CustomerType updatedType = CustomerType.valueOf(updatedTypeStr.toUpperCase());
                updatedCustomer.setType(updatedType);
            }
            if (updated) {
                if (cuService.update(updatedCustomer) == null) {
                    throw new UIException("No changes made.");
                } else {
                    System.out.println("\nCustomer updated successfully!");
                    System.out.println("Updated Customer details:");
                    System.out.println(updatedCustomer);
                    pressEnterToContinue();
                }
            } else {
                throw new UIException("No changes made.");
            }
        } catch (ServiceException | UIException | DateTimeParseException | IllegalArgumentException e) {
            System.out.println("[Customer update failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void deactivateCustomer() throws UIException {
        try {
            System.out.println("\nDeactivate Customer\n==============================");
            System.out.print("Enter customer ID to update: ");
            Long idToUpdate = getLong();
            cuService.findById(idToUpdate);
            cuService.deactivateCustomer(idToUpdate);
            System.out.println("\nCustomer id " + idToUpdate + " deactivated successfully!");
            System.out.println("Customer details:\n" + cuService.findById(idToUpdate));
            pressEnterToContinue();
        } catch (ServiceException e) {
            System.out.println("[Customer deactivation failed] " + e.getMessage());
            pressEnterToContinue();
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
            if (customer.getCreatedDate() != null)
                System.out.println("Created Date: " + customer.getCreatedDate().format(dateTimeFormatter));
            else
                System.out.println("Created Date: " + customer.getCreatedDate());
            if (customer.getDeactivatedDate() != null)
                System.out.println("Deactivated Date: " + customer.getDeactivatedDate().format(dateTimeFormatter));
            else
                System.out.println("Deactivated Date: " + customer.getDeactivatedDate());
            pressEnterToContinue();
        } catch (ServiceException | UIException e) {
            System.out.println("[Customer display failed] " + e.getMessage());
            pressEnterToContinue();
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
            System.out.println("Total balance of all accounts: " + totalBalance / 100 + "."
                    + String.format("%02d", totalBalance % 100)
                    + " EUR");
            pressEnterToContinue();
        } catch (Exception e) {
            System.out.println("[Display totals failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayAccountsByDate() {
        try {
            long count = 0;
            System.out.println("\nDisplay Accounts By Date\n==============================");
            Pair<LocalDateTime> dates = getDateRange();
            String dateFirstStr = dates.getFirst().format(dateTimeFormatter);
            String dateSecondStr = dates.getFirst().format(dateTimeFormatter);
            System.out.println("\nDisplaying accounts from " + dateFirstStr + " to " + dateSecondStr);
            ArrayList<BankAccount> bankAccounts = baService.findAll();
            if (!bankAccounts.isEmpty()) {
                for (BankAccount bankAccount : bankAccounts) {
                    LocalDateTime bankAccounDate = bankAccount.getCreatedDate();
                    if (!bankAccounDate.isBefore(dates.getFirst()) && !bankAccounDate.isAfter(dates.getSecond())) {
                        count++;
                        System.out.println("\nAccount #" + count);
                        System.out.println(bankAccount);
                    }
                }
            }
            if (count == 0) {
                System.out.println("No accounts found.");
            }
            pressEnterToContinue();
        } catch (ServiceException | UIException e) {
            System.out.println("[Display accounts by date failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayCustomersByDate() {
        try {
            long count = 0;
            System.out.println("\nDisplay Customers By Date\n==============================");
            Pair<LocalDateTime> dates = getDateRange();
            String dateFirstStr = dates.getFirst().format(dateTimeFormatter);
            String dateSecondStr = dates.getFirst().format(dateTimeFormatter);
            System.out.println("\nDisplaying accounts from " + dateFirstStr + " to " + dateSecondStr);
            ArrayList<Customer> customers = cuService.findAll();
            if (!customers.isEmpty()) {
                for (Customer customer : customers) {
                    LocalDateTime customerDate = customer.getCreatedDate();
                    if (!customerDate.isBefore(dates.getFirst()) && !customerDate.isAfter(dates.getSecond())) {
                        count++;
                        System.out.println("\nCustomer #" + count);
                        System.out.println(customer);
                    }
                }
            }
            if (count == 0) {
                System.out.println("No customers found.");
            }
            pressEnterToContinue();
        } catch (ServiceException | UIException e) {
            System.out.println("[Display customers by date failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayTransactionsByDate() {
        try {
            long count = 0;
            System.out.println("\nDisplay Transactions By Date\n==============================");
            Pair<LocalDateTime> dates = getDateRange();
            String dateFirstStr = dates.getFirst().format(dateTimeFormatter);
            String dateSecondStr = dates.getFirst().format(dateTimeFormatter);
            System.out.println("\nDisplaying accounts from " + dateFirstStr + " to " + dateSecondStr);
            ArrayList<Transaction> transactions = (ArrayList<Transaction>) trService.findAll();
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    LocalDateTime transactionDate = transaction.getCreatedDate();
                    if (!transactionDate.isBefore(dates.getFirst()) && !transactionDate.isAfter(dates.getSecond())) {
                        count++;
                        System.out.println("\nTransaction #" + count);
                        System.out.println(transaction);
                    }
                }
            }
            if (count == 0) {
                System.out.println("No transactions found.");
            }
            pressEnterToContinue();
        } catch (ServiceException | UIException e) {
            System.out.println("[Display transactions by date failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void displayOperationsByDate() {
        try {
            long count = 0;
            System.out.println("\nDisplay Operations By Date\n==============================");
            Pair<LocalDateTime> dates = getDateRange();
            String dateFirstStr = dates.getFirst().format(dateTimeFormatter);
            String dateSecondStr = dates.getFirst().format(dateTimeFormatter);
            System.out.println("\nDisplaying accounts from " + dateFirstStr + " to " + dateSecondStr);
            ArrayList<Operation> operations = opService.findAll();
            if (!operations.isEmpty()) {
                for (Operation operation : operations) {
                    LocalDateTime operationDate = operation.getDate();
                    if (!operationDate.isBefore(dates.getFirst()) && !operationDate.isAfter(dates.getSecond())) {
                        count++;
                        System.out.println("\nOperation #" + count);
                        System.out.println(operation);
                    }
                }
            }
            if (count == 0) {
                System.out.println("No operations found.");
            }
            pressEnterToContinue();
        } catch (ServiceException | UIException e) {
            System.out.println("[Display operations by date failed] " + e.getMessage());
            pressEnterToContinue();
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
            pressEnterToContinue();
        } catch (ServiceException | UIException e) {
            System.out.println("[Bank account creation failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void updateAccount() {
        try {
            boolean updated = false;
            System.out.println("\nUpdate Account\n==============================");
            System.out.print("Enter bank account ID to update: ");
            long idToUpdate = getLong();
            BankAccount existingAccount = baService.findById(idToUpdate);
            BankAccount updatedAccount = new BankAccount(existingAccount);
            // Fail fast validation to force some validation exceptions before any user
            // input. cuService.update () returns null as no changes were made at this point
            baService.update(updatedAccount);
            System.out.println("Current Account Details:");
            System.out.println(existingAccount);
            System.out.println("Enter updated name (enter # for no change): ");
            String updatedName = getString();
            if (!updatedName.equals("#")) {
                updatedAccount.setAccountName(updatedName);
                updated = true;
            }
            System.out.print(
                    "Enter updated BankAccount type [1 = CURRENT_ACCOUNT | 2 = SAVING_ACCCOUNT | 3 = STUDENT_ACCOUNT] (enter # for no change): ");
            String updatedTypeStr = getString();
            if (!updatedTypeStr.equals("#")) {
                switch (updatedTypeStr) {
                    case "1":
                        updatedTypeStr = "CURRENT_ACCOUNT";
                        if (existingAccount.getType() == AccountType.SAVING_ACCOUNT
                                || existingAccount.getType() == AccountType.STUDENT_ACCOUNT) {
                            updated = true;
                        }
                        break;
                    case "2":
                        updatedTypeStr = "SAVING_ACCOUNT";
                        if (existingAccount.getType() == AccountType.CURRENT_ACCOUNT
                                || existingAccount.getType() == AccountType.STUDENT_ACCOUNT) {
                            updated = true;
                        }
                        break;
                    case "3":
                        updatedTypeStr = "STUDENT_ACCOUNT";
                        if (existingAccount.getType() == AccountType.CURRENT_ACCOUNT
                                || existingAccount.getType() == AccountType.SAVING_ACCOUNT) {
                            updated = true;
                        }
                        break;
                    default:
                        throw new UIException("Invalid account type.");
                }
                AccountType updatedType = AccountType.valueOf(updatedTypeStr);
                updatedAccount.setType(updatedType);
            }
            if (updated) {
                if (baService.update(updatedAccount) == null) {
                    throw new UIException("No changes made.");
                } else {
                    System.out.println("\nBank account updated successfully!");
                    System.out.println("Updated bank account details:");
                    System.out.println(updatedAccount);
                    pressEnterToContinue();
                }
            } else {
                throw new UIException("No changes made.");
            }
        } catch (ServiceException | UIException | DateTimeParseException e) {
            System.out.println("[Bank account update failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    private void deactivateAccount() throws UIException {
        try {
            System.out.println("\nDeactivate Bank Account\n==============================");
            System.out.print("Enter account ID to update: ");
            Long idToUpdate = getLong();
            baService.findById(idToUpdate);
            baService.deactivateAccount(idToUpdate);
            System.out.println("\nBank account id " + idToUpdate + " deactivated successfully.");
            System.out.println("Bank account details:\n" + baService.findById(idToUpdate));
            pressEnterToContinue();
        } catch (ServiceException e) {
            System.out.println("[Bank account deactivation failed] " + e.getMessage());
            pressEnterToContinue();
        }
    }

    public void pressEnterToContinue() {
        System.out.print("Press ENTER to continue.");
        Scanner localScanner = new Scanner(System.in);
        String readString = localScanner.nextLine();
        if (readString == null || readString.trim().equals(""))
            readString = null;
    }

    public static String getDataPath() {
        return dataPath;
    }

    private Pair<LocalDateTime> getDateRange() throws UIException {
        try {
            LocalDate startDate;
            LocalDate endDate;
            LocalDateTime startDateTime;
            LocalDateTime endDateTime;
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.print("Enter Start Date [yyyy-mm-dd] (enter # for 1 year ago): ");
            String startDateStr = getString();
            if (!startDateStr.equals("#")) {
                startDate = LocalDate.parse(startDateStr, dateFormatter);
                startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));
            } else {
                startDateTime = LocalDateTime.now().minusYears(1);
            }
            System.out.print("Enter End Date [yyyy-mm-dd] (enter # for today): ");
            String endDateStr = getString();
            if (!endDateStr.equals("#")) {
                endDate = LocalDate.parse(endDateStr, dateFormatter);
                endDateTime = LocalDateTime.of(endDate, LocalTime.of(0, 0));
            } else {
                endDateTime = LocalDateTime.now();
            }
            return new Pair<LocalDateTime>(startDateTime, endDateTime);
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

    public static CustomerService getCuService() {
        return cuService;
    }

    public static BankAccountService getBaService() {
        return baService;
    }

    public static OperationService getOpService() {
        return opService;
    }

    public static TransactionService getTrService() {
        return trService;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    
}
