package com.bank.main;

import com.bank.exceptions.UIException;
import com.bank.repository.BankAccountRepository;
import com.bank.repository.CustomerRepository;
import com.bank.repository.JsonBankAccountRepository;
import com.bank.repository.JsonCustomerRepository;
import com.bank.repository.JsonOperationRepository;
import com.bank.repository.JsonTransactionRepository;
import com.bank.repository.OperationRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.AuthenticationService;
import com.bank.service.BankAccountService;
import com.bank.service.BankAccountServiceImpl;
import com.bank.service.CliAuthenticationService;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;
import com.bank.service.OperationService;
import com.bank.service.OperationServiceImpl;
import com.bank.service.TransactionService;
import com.bank.service.TransactionServiceImpl;
import com.bank.ui.CliUi;
import com.bank.ui.Ui;

public class Main {
    
    private static boolean authenticated;

    public static void main(String[] args) {

		CustomerRepository cuRepo = new JsonCustomerRepository();
		BankAccountRepository baRepo = new JsonBankAccountRepository();
		OperationRepository opRepo = new JsonOperationRepository();
		TransactionRepository trRepo = new JsonTransactionRepository();
        	
        OperationService opService = new OperationServiceImpl(opRepo);
		TransactionService trService = new TransactionServiceImpl(trRepo);
		BankAccountService baService = new BankAccountServiceImpl(baRepo, opService, trService);
        CustomerService cuService = new CustomerServiceImpl(cuRepo, baService, opService);
        AuthenticationService authService = new CliAuthenticationService();

		Ui ui = new CliUi(cuService, baService, opService, trService, authService);
        
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