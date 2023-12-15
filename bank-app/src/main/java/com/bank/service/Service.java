package com.bank.service;

import com.bank.exceptions.ServiceException;

public interface Service<T> {
	long getId(T type) throws ServiceException;
}
