package com.bank.ui;

import java.util.Arrays;

import com.bank.exceptions.UIException;

public class Menu {

	private String title;
	private MenuOption[] options;

	public Menu(String title, MenuOption[] options) {
		this.title = title;
		this.options = Arrays.copyOf(options, options.length);
	}

	private void border() {
		for (int ii = 0; ii < title.length() + 22; ii++)
			System.out.print("=");
		System.out.println();
	}

    public void display() {
        border();
        System.out.printf("=           %s         =%n", title.toUpperCase());
        border();
        int index = 1;
        for (MenuOption option : options) {
            System.out.printf("%d. %s%n", index++, option.getCaption());
        }
        System.out.print("Select option: ");
    }

	public void getChoice() throws UIException {
		try {
			String choice = CliUi.scanner.nextLine();
			int choiceInt = Integer.parseInt(choice);
			if (choiceInt < 1 || choiceInt > options.length) {
				throw new UIException("[GetChoice] Invalid option selected. Please enter a valid option.");
			} else
				options[choiceInt - 1].executeOption();
		} catch (NumberFormatException e) {
			throw new UIException("[GetChoice] Invalid option selected. Please enter a valid option.");
		}
	}
}

abstract class MenuOption {

	private String caption;

	public MenuOption(String caption) {
		this.caption = caption;
	}

	String getCaption() {
		return caption;
	}

	abstract void executeOption();
}
