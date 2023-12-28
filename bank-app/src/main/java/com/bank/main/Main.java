package com.bank.main;

import com.bank.exceptions.UIException;
import com.bank.ui.UI;

public class Main {
    public static void main(String[] args) {
        
        UI ui = new UI();
        
        try {
            ui.authenticateApp();
            ui.displayMenu();
        } catch (UIException ex) {
            System.out.println(ex.getMessage() + "\nExiting the Bank Application."); 
        }
    }
}