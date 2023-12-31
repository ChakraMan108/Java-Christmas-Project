package com.bank.repository;

import java.util.List;
import com.bank.exceptions.RepositoryException;

public interface Repository<T> {
	public List<T> findAll() throws RepositoryException; 
	public T findById(long id) throws RepositoryException;
	public long count() throws RepositoryException;
	public T save(T entity) throws RepositoryException;
}