package com.bank.service;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.bank.exceptions.ServiceException;
import com.bank.exceptions.UIException;

public class CliAuthenticationService implements AuthenticationService {

    private String username;
    private String password;

    public CliAuthenticationService() {
        try {
            loadProperties();        
        } catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Properties loadProperties() throws ServiceException {
        try {
            InputStream appConfigPath = getClass().getClassLoader()
                    .getResourceAsStream("com/bank/service/auth.properties");
            Properties appProps = new Properties();
            appProps.load(appConfigPath);
            setUsername(appProps.getProperty("app.username"));
            setPassword(appProps.getProperty("app.password"));
            return appProps;
        } catch (IOException e) {
            throw new ServiceException("[AuthenticationServiceCli loadProperties failed]" + e.getMessage());
        }
    }

    public boolean authenticate() throws ServiceException {
        try {
            
            Console console = System.console();
            if (console == null) {
                System.out.println("No console: not in interactive mode!");
                System.exit(0);
            }
            
            System.out.println("\n==============================");
            System.out.println("=   BANK APP AUTHENTICATION  =");
            System.out.println("=============================="); 

            String usernameEntry = console.readLine("Username: ");
            if (usernameEntry == null || usernameEntry.trim().equals(null))
                throw new ServiceException("Invalid input.");
            char[] passwordChar = console.readPassword("Password: ");
            if (passwordChar == null || passwordChar.length == 0)
                throw new UIException("Invalid input.");
            String passwordEntry = String.valueOf(passwordChar);
            if (!usernameEntry.equals(getUsername()) || !passwordEntry.equals(getPassword())) {
                System.out.println("\nInvalid credentials! Please try again.");
                return false;
            } else {
                System.out.println("\nAuthentication successful.");
                return true;
            }
        } catch (Exception ex) {
            throw new ServiceException("[AuthenticationServiceCli authenticate error]" + ex.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
