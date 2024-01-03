package com.bank.ui;

import java.util.Collection;

import com.bank.exceptions.UIException;

public interface UiInterface {
	public void authenticateApp() throws UIException;
	public void displayMainMenu() throws UIException;
	public String getString() throws UIException;
	public long getLong() throws UIException;
	public <T> void displayCollection(Collection<T> collection) throws UIException;
}


