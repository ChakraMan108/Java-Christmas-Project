package com.bank.authentication;

public class AuthenticationServiceCli implements AuthenticationService {

    public AuthenticationServiceCli() {

    }

    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
