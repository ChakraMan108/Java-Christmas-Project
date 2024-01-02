package com.bank.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

@PrepareForTest({Ui.class})

public class UiTest {

    Ui ui = new Ui();

    @Test
    public void testLoadProperties() {
        assertDoesNotThrow(() -> ui.loadProperties());
    }

    @Test
    public void testLoadData() {
        assertDoesNotThrow(() -> ui.loadData());
    }

    @Test
    public void testAuthenticateApp() {
        // Set appUsername and appPassword to known values
        // ui.appUsername = "test";
        // ui.appPassword = "test";

        // Mock getString() method to return known values
        // This requires creating a subclass of Ui for testing
        Ui testUi = new Ui() {
            @Override
            public String getString() {
                return "test";
            }
        };

        assertDoesNotThrow(() -> testUi.authenticateApp());
    }
}