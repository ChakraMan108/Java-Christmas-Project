package com.bank.service;

import com.bank.exceptions.ServiceException;

public interface AuthenticationService {

    public boolean authenticate() throws ServiceException;
}
