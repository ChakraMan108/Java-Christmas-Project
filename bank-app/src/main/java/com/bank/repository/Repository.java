package com.bank.repository;

public interface Repository<T> {
	public List<T> getAll() throws RepositoryException; 
	public T getById(int id) throws RepositoryException;
	public void add(T acc) throws RepositoryException;
	public void update(int id, T acc) throws RepositoryException;
	public void delete(int id) throws RepositoryException;
}

