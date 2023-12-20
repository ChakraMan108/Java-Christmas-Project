package com.bank.service;

import java.util.List;
import com.bank.exceptions.ServiceException;

public interface Service<T> {
	public List<T> findAll() throws ServiceException; 
	public T findById(long id) throws ServiceException;
	public long count() throws ServiceException;
	public T save(T entity) throws ServiceException;
}
