package com.bank.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import com.bank.exceptions.UIException;

public class MainMenu {

    private Properties properties;
    public Menu menu;
    public boolean exited = false;

    public MainMenu() throws UIException {

        try {
            properties = loadProperties();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        MenuOption[] menuOption = new MenuOption[] {

            new MenuOption(properties.getProperty("menu_1")) {            
                void executeOption() {            
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
                    setExited(true);
                }
            },

        };

        menu = new Menu(properties.getProperty("menu_title"), menuOption);
        
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

    private void createAccount() {
        
    }

    public boolean isExited() {
        return exited;
    }

    public void setExited(boolean exited) {
        this.exited = exited;
    }

    
}
