package com.bank.main;

import com.bank.ui.UiImpl;

public class Main {
    public static void main(String[] args) {
        
        UiImpl ui = new UiImpl();
        
        ui.authenticateApp();
        ui.displayMenu();

    }
}