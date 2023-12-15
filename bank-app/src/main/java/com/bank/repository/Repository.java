package com.bank.repository;

import java.util.List;

import com.bank.exceptions.RepositoryException;

public interface Repository<T> {
	public List<T> findAll() throws RepositoryException; 
	public T findById(int id) throws RepositoryException;
	public void add(T acc) throws RepositoryException;
	public void update(int id, T acc) throws RepositoryException;
	public void delete(int id) throws RepositoryException;
}