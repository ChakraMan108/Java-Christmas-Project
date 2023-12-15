package com.bank.exceptions.ui;

import java.util.Collection;

import com.bank.exceptions.MenuException;

public interface Menu {
	public boolean isLoggedIn() throws MenuException;
	public boolean isDisplayed() throws MenuException;
	public void displayMenu() throws MenuException;
	public String getString() throws MenuException;
	public long getLong() throws MenuException;
	public <T> void displayCollection(Collection<T> collection) throws MenuException;
}