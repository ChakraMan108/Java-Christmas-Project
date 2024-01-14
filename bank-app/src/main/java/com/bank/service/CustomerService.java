package com.bank.service;

import com.bank.entity.Customer;
import com.bank.exceptions.ServiceException;

public interface CustomerService {
	public Iterable<? extends Customer> findAll() throws ServiceException; 
	public Customer findById(long id) throws ServiceException;
	public long count() throws ServiceException;
	public Customer save(Customer entity) throws ServiceException;
	public Customer createCustomer(Customer customer) throws ServiceException;
	public Customer update(Customer customer) throws ServiceException;
	public void deactivateCustomer(long id) throws ServiceException;
	

}