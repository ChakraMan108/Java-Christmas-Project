package com.bank.service;

public interface BankService<T> {
	long getId(T type) throws BankServiceException;
}
