package com.bank.service;

import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.exceptions.ServiceException;

public interface Service<T> {
	public List<T> findAll() throws ServiceException; 
	public BankAccount findById(long id) throws ServiceException;
	public long count() throws ServiceException;
	public T save(T entity) throws ServiceException;
}
