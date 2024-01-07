package com.bank.main;

import java.io.IOException;

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
                ui.progressBar(20);
            ui.displayMainMenu();
        } catch (UIException | InterruptedException | IOException ex) {
            System.out.println(ex.getMessage() + "\nExiting the Bank Application.");
        }
    }
}