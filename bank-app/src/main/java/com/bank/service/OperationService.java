
package com.bank.service;

import java.io.IOException;
import java.util.ArrayList;
import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.OperationRepository;

public class OperationService implements Service<Operation> {

    public static final OperationRepository repository = new OperationRepository();

    public Operation save(Operation operation) throws ServiceException {
        try {
            return repository.save(operation);
        } catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex.getMessage());
        }
    }

    public Operation findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex.getMessage());
        }
    }

    public long count() throws ServiceException {
        try {
            return repository.count();
        } catch (RepositoryException ex) {
            throw new ServiceException("Failed to get the count of operations", ex.getMessage());
        }
    }

    public ArrayList<Operation> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } catch (RepositoryException ex) {
            throw new ServiceException("Exception received from the Repository by the Service.", ex.getMessage());
        }
    }
    
    public void saveJson() throws IOException {
        repository.saveJson();
    }
}
