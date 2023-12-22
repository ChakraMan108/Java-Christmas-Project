package com.bank.main;

import com.bank.exceptions.MenuException;
import com.bank.ui.UiImpl;

public class Main {
    public static void main(String[] args) {
        
        UiImpl ui = new UiImpl();
        
        try {
           //1 ui.authenticateApp();
            ui.displayMenu();
        } catch (MenuException ex) {
            System.out.println(ex.getMessage() + "\nExiting the application.");   
        }
    }
}