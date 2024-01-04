
package com.bank.service;

import java.io.IOException;
import java.util.ArrayList;
import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.OperationRepository;

public class OperationService implements Service<Operation> {

    private OperationRepository repository = OperationRepository.getInstance();

    public Operation save(Operation operation) throws ServiceException {
        try {
            return repository.save(operation);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Operation Service error] " + ex.getMessage(), ex);
        }
    }

    public Operation findById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RepositoryException ex) {
            throw new ServiceException("[Operation Service error] " + ex.getMessage(), ex);
        }
    }

    public long count() throws ServiceException {
        try {
            return repository.count();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Operation Service error] " + ex.getMessage(), ex);
        }
    }

    public ArrayList<Operation> findAll() throws ServiceException {
        try {
            return repository.findAll();
        } catch (RepositoryException ex) {
            throw new ServiceException("[Operation Service error] " + ex.getMessage(), ex);
        }
    }
    
    public void saveJson() throws ServiceException {
        try {
            repository.saveJson();
        } catch (IOException ex) {
            throw new ServiceException("[Operation Service error] " + ex.getMessage(), ex);
        }
    }

    public void loadJson() throws ServiceException {
        try {
            repository.loadJson();
        } catch (IOException ex) {
            throw new ServiceException("[Operation Service error] " + ex.getMessage(), ex);
        }
    }
}