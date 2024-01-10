package com.bank.service;

import com.bank.entity.Transaction;
import com.bank.exceptions.ServiceException;

public interface TransactionService {
	public Iterable<? extends Transaction> findAll() throws ServiceException; 
	public Transaction findById(long id) throws ServiceException;
	public long count() throws ServiceException;
	public Transaction save(Transaction entity) throws ServiceException;
}