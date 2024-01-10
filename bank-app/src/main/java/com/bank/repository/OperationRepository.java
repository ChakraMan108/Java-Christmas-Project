package com.bank.repository;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;

public interface OperationRepository {
	public Iterable<? extends Operation> findAll() throws RepositoryException; 
	public Operation findById(long id) throws RepositoryException;
	public long count() throws RepositoryException;
	public Operation save(Operation entity) throws RepositoryException;
}