package com.bank.main;

import com.bank.exceptions.MenuException;
import com.bank.ui.UiImpl;

public class RobTest {
    public static void main(String[] args) {
        
        UiImpl ui = new UiImpl();
        
        try {
            ui.authenticateApp();
            ui.displayMenu();
        } catch (MenuException ex) {
            System.out.println("Menu returned an error: " + ex.getMessage());   
        }
    }
}
