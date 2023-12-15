package com.bank.ui;

import java.util.Collection;

import com.bank.exceptions.MenuException;

public interface Ui {
	public void authenticateApp() throws MenuException;
	public void displayMenu() throws MenuException;
	public String getString() throws MenuException;
	public long getLong() throws MenuException;
	public <T> void displayCollection(Collection<T> collection) throws MenuException;
}