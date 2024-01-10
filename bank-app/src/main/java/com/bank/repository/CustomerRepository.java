package com.bank.repository;

import com.bank.entity.Customer;
import com.bank.exceptions.RepositoryException;

public interface CustomerRepository {
	public Iterable<? extends Customer> findAll() throws RepositoryException; 
	public Customer findById(long id) throws RepositoryException;
	public long count() throws RepositoryException;
	public Customer save(Customer entity) throws RepositoryException;
}