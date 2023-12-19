
package com.bank.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.OperationRepository;


public class OperationService implements Service<Operation>{


    public List<Operation> operations;

    public OperationRepository repository;


    //OperationService:The constructor takes an instance of OperationRepository as a parameter and initializes the operationRepository field. This is known as dependency injection, where the repository is injected into the service.
    public OperationService(OperationRepository repository) {
        this.repository = repository;
    }


    //saveOperation:This method saves a new Operation object using the save method provided by the operationRepository. If an exception occurs during the save operation, it is caught, and a RuntimeException is thrown with an error message indicating the failure.
    public long save(Operation operation) {
        try {
            return repository.save(operation);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to save operation", e);
        }
    }

    public Operation findById(long id) throws ServiceException {
       try{ return repository.findById(id);
         }
         catch
        (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);}
    }


    

public long count() throws ServiceException {
    try {
        return repository.count();
    } catch (RepositoryException ex) {
        throw new ServiceException("Failed to get the count of operations", ex);
    }
}



public ArrayList<Operation> findAll() throws ServiceException {
 
        try {

          return  repository.findAll();

        } 
        catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex);
        }
        
    
}

}




