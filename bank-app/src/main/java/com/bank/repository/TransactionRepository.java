package com.bank.repository;

import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;

public interface TransactionRepository {
	public Iterable<? extends Transaction> findAll() throws RepositoryException; 
	public Transaction findById(long id) throws RepositoryException;
	public long count() throws RepositoryException;
	public Transaction save(Transaction entity) throws RepositoryException;
}