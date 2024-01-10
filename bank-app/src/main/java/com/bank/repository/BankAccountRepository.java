package com.bank.repository;

import com.bank.entity.BankAccount;
import com.bank.exceptions.RepositoryException;

public interface BankAccountRepository {
	public Iterable<? extends BankAccount> findAll() throws RepositoryException; 
	public BankAccount findById(long id) throws RepositoryException;
	public long count() throws RepositoryException;
	public BankAccount save(BankAccount entity) throws RepositoryException;
}