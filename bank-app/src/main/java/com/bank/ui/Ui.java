package com.bank.ui;

import com.bank.exceptions.UIException;

public interface Ui {
	public boolean authenticate() throws UIException;
	public void displayApp() throws UIException;
	public String getString() throws UIException;
	public long getLong() throws UIException;
	public void pressEnterToContinue() throws UIException;
}


