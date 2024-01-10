package com.bank.service;

import com.bank.entity.BankAccount;
import com.bank.exceptions.ServiceException;

public interface BankAccountService {
	public Iterable<? extends BankAccount> findAll() throws ServiceException; 
	public BankAccount findById(long id) throws ServiceException;
	public long count() throws ServiceException;
	public BankAccount save(BankAccount entity) throws ServiceException;
}