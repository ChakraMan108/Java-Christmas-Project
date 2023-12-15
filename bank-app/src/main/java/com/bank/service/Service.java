package com.bank.service;

import java.util.List;
import com.bank.exceptions.ServiceException;

public interface Service<T> {
	public List<T> findAll() throws ServiceException; 
	public T findById(int id) throws ServiceException;
	public long count() throws ServiceException;
	public void save(T entity) throws ServiceException;
}
