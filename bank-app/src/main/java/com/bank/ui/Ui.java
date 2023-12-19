package com.bank.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.validator.routines.EmailValidator;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.entity.Transaction;
import com.bank.exceptions.MenuException;

public interface Ui {
	public void authenticateApp() throws MenuException;
	public void displayMenu() throws MenuException;
	public String getString() throws MenuException;
	public long getLong() throws MenuException;
	public <T> void displayCollection(Collection<T> collection) throws MenuException;
}


