package com.bank.repository;

import java.util.List;
import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;

public interface TransactionRepository {
	public List<Transaction> findAll() throws RepositoryException; 
	public Transaction findById(long id) throws RepositoryException;
	public long count() throws RepositoryException;
	public Transaction save(Transaction entity) throws RepositoryException;
}