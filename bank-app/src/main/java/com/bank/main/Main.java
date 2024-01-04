package com.bank.main;

import com.bank.exceptions.UIException;
import com.bank.ui.Ui;

public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui();
        try {
            ui.loadProperties();
            do {
                try {
                    ui.authenticateApp();
                } catch (UIException ex) {
                    System.out.println(ex.getMessage());
                    ui.pressEnterToContinue();
                }
            } while (!ui.isAuthenticated());
            ui.loadData();
            ui.displayMainMenu();
        } catch (UIException ex) {
            System.out.println(ex.getMessage() + "\nExiting the Bank Application.");
        }

    }
}