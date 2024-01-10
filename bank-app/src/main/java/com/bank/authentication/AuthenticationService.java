package com.bank.authentication;

import com.bank.exceptions.ServiceException;

public interface AuthenticationService {

    public boolean authenticate() throws ServiceException;
}
