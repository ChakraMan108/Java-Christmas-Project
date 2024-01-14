package com.bank.ui;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.Scanner;

import com.bank.entity.Customer;
import com.bank.entity.Customer.CustomerType;
import com.bank.exceptions.ServiceException;
import com.bank.exceptions.UIException;
import com.bank.service.CustomerService;

public class MainMenu {

    private Properties properties;
    private Menu menu;
    private boolean exited = false;

    public MainMenu() throws UIException {

        try {
            properties = loadProperties();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        MenuOption[] menuOptions = new MenuOption[] {

            new MenuOption(properties.getProperty("menu_1")) {            
                void executeOption() {  
                    createCustomer();
                }
            },
            new MenuOption(properties.getProperty("menu_2")) {            
                void executeOption() {           
                }
            },
            new MenuOption(properties.getProperty("menu_3")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_4")) {            
                void executeOption() {            
                }
            },           
            new MenuOption(properties.getProperty("menu_5")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_6")) {            
                void executeOption() {  
                }
            },
            new MenuOption(properties.getProperty("menu_7")) {            
                void executeOption() {    
                }
            },
            new MenuOption(properties.getProperty("menu_8")) {            
                void executeOption() {   
                }
            },
            new MenuOption(properties.getProperty("menu_8")) {            
                void executeOption() {            
                }
            },

            new MenuOption(properties.getProperty("menu_9")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_10")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_11")) {            
                void executeOption() {  
                }
            },
            new MenuOption(properties.getProperty("menu_12")) {            
                void executeOption() {    
                }
            },
            new MenuOption(properties.getProperty("menu_13")) {            
                void executeOption() {                  
                }
            },
            new MenuOption(properties.getProperty("menu_14")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_15")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_16")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_17")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_18")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_19")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_20")) {            
                void executeOption() {            
                }
            },
            new MenuOption(properties.getProperty("menu_21")) {            
                void executeOption() {  
                    setExited(true);
                }
            },

        };

        menu = new Menu(properties.getProperty("menu_title"), menuOptions);
        
        menu.display();

        try {
            menu.getChoice();
        } catch (UIException e) {
            setExited(false);
            throw new UIException("[MainMenu getChoice failed] " + e.getMessage());
        }
    }

    public Properties loadProperties() throws IOException {
        try {
            InputStream appConfigPath = getClass().getClassLoader()
                    .getResourceAsStream("com/bank/ui/mainmenu.properties");
            Properties appProps = new Properties();
            appProps.load(appConfigPath);
            return appProps;
        } catch (IOException e) {
            throw new IOException("[MainMenu loadProperties failed] " + e.getMessage());
        }
    }

    public boolean isExited() {
        return exited;
    }

    public void setExited(boolean exited) {
        this.exited = exited;
    }

    private void createCustomer() {
        try {
            CustomerService cuService = CliUi.getCuService();
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
            // validateEmail(email);
            System.out.print("Enter customer type [1 = INDIVIDUAL | 2 = COMPANY]: ");
            String typeStr = getString();
            if (typeStr.equals("1"))
                    typeStr = "INDIVIDUAL";
                else if (typeStr.equals("2"))
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

    public String getString() throws UIException {
        Scanner scanner = CliUi.getScanner();
        String input = scanner.nextLine();
        if (input == null || input.trim() == "") {
            throw new UIException("[getString Input Error] Invalid input.");
        }
        return input;
    }

    public void pressEnterToContinue() {
        System.out.print("Press ENTER to continue.");
        Scanner localScanner = new Scanner(System.in);
        String readString = localScanner.nextLine();
        if (readString == null || readString.trim().equals(""))
            readString = null;
    }

    
}