package com.bank.service;

import com.bank.entity.Operation;
import com.bank.exceptions.ServiceException;

public interface OperationService {
	public Iterable<? extends Operation> findAll() throws ServiceException; 
	public Operation findById(long id) throws ServiceException;
	public long count() throws ServiceException;
	public Operation save(Operation entity) throws ServiceException;
}