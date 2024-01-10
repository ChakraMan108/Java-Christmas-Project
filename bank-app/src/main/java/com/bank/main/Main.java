package com.bank.main;

import com.bank.authentication.AuthenticationService;
import com.bank.authentication.AuthenticationServiceCli;
import com.bank.entity.Transaction;
import com.bank.entity.Transaction.TransactionType;
import com.bank.exceptions.ServiceException;
import com.bank.exceptions.UIException;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.BankAccountRepositoryJson;
import com.bank.repository.CustomerRepository;
import com.bank.repository.CustomerRepositoryJson;
import com.bank.repository.OperationRepository;
import com.bank.repository.OperationRepositoryJson;
import com.bank.repository.TransactionRepository;
import com.bank.repository.TransactionRepositoryJson;
import com.bank.service.BankAccountService;
import com.bank.service.BankAccountServiceImpl;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;
import com.bank.service.OperationService;
import com.bank.service.OperationServiceImpl;
import com.bank.service.TransactionService;
import com.bank.service.TransactionServiceImpl;
import com.bank.ui.Ui;
import com.bank.ui.UiCli;

public class Main {
    
    private static boolean authenticated;

    public static void main(String[] args) {

		CustomerRepository cuRepo = new CustomerRepositoryJson();
		BankAccountRepository baRepo = new BankAccountRepositoryJson();
		OperationRepository opRepo = new OperationRepositoryJson();
		TransactionRepository trRepo = new TransactionRepositoryJson();
        	
        CustomerService cuService = new CustomerServiceImpl(cuRepo);
		BankAccountService baService = new BankAccountServiceImpl(baRepo);
		OperationService opService = new OperationServiceImpl(opRepo);
		TransactionService trService = new TransactionServiceImpl(trRepo);
        AuthenticationService authService = new AuthenticationServiceCli();

		Ui ui = new UiCli(cuService, baService, opService, trService, authService);
        
        try {
            do {
                try {
                    authenticated = ui.authenticate();
                    ui.pressEnterToContinue();
                } catch (UIException ex) {
                    System.out.println(ex.getMessage());
                    ui.pressEnterToContinue();
                }
            } while (!authenticated);
            //ui.loadData();
            //ui.progressBar(20);
            ui.displayApp();
        } catch (UIException ex) {
            System.out.println(ex.getMessage() + "\nExiting the Bank Application.");
        }
    }
}