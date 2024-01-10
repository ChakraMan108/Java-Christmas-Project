package com.bank.ui;

import com.bank.exceptions.UIException;

public interface Ui {
	public void displayApp() throws UIException;
	public boolean isAuthenticated() throws UIException;
	public String getString() throws UIException;
	public long getLong() throws UIException;
}


