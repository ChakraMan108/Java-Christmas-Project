package com.bank.service;

import  org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.OperationRepository;

public class OperationServiceTest {

    private OperationService operationService;
    private OperationRepository mockRepository;

    @Before
    public void setUp() {
        mockRepository = mock(OperationRepository.class);
        operationService = new OperationService();
        OperationService.repository = mockRepository;
    }

    @Test
    public void testSave() throws RepositoryException, ServiceException {
        Operation operation = new Operation(Operation.OperationType.ACCOUNT_CREATION, "user1", 123, 456);

        when(mockRepository.save(operation)).thenReturn(operation);

        Operation savedOperation = operationService.save(operation);

        assertNotNull(savedOperation);
        assertEquals(operation, savedOperation);

        verify(mockRepository, times(1)).save(operation);
    }

    @Test(expected = ServiceException.class)
    public void testSaveWithRepositoryException() throws RepositoryException, ServiceException {
        Operation operation = new Operation(Operation.OperationType.ACCOUNT_CREATION, "user1", 123, 456);

        when(mockRepository.save(operation)).thenThrow(new RepositoryException("Repository error"));

        operationService.save(operation);
    }

    @Test
    public void testFindById() throws RepositoryException, ServiceException {
        long operationId = 123;

        when(mockRepository.findById(operationId)).thenReturn(new Operation());

        Operation foundOperation = operationService.findById(operationId);

        assertNotNull(foundOperation);

        verify(mockRepository, times(1)).findById(operationId);
    }

    @Test(expected = ServiceException.class)
    public void testFindByIdWithRepositoryException() throws RepositoryException, ServiceException {
        long operationId = 123;

        when(mockRepository.findById(operationId)).thenThrow(new RepositoryException("Repository error"));

        operationService.findById(operationId);
    }

    @Test
    public void testCount() throws RepositoryException, ServiceException {
        when(mockRepository.count()).thenReturn(5L);

        long count = operationService.count();

        assertEquals(5, count);

        verify(mockRepository, times(1)).count();
    }

    @Test(expected = ServiceException.class)
    public void testCountWithRepositoryException() throws RepositoryException, ServiceException {
        when(mockRepository.count()).thenThrow(new RepositoryException("Repository error"));

        operationService.count();
    }

    @Test
    public void testFindAll() throws RepositoryException, ServiceException {
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(new Operation(Operation.OperationType.ACCOUNT_CREATION, "user1", 123, 456));

        when(mockRepository.findAll()).thenReturn(operations);

        ArrayList<Operation> foundOperations = operationService.findAll();

        assertNotNull(foundOperations);
        assertEquals(1, foundOperations.size());

        verify(mockRepository, times(1)).findAll();
    }

    @Test(expected = ServiceException.class)
    public void testFindAllWithRepositoryException() throws RepositoryException, ServiceException {
        when(mockRepository.findAll()).thenThrow(new RepositoryException("Repository error"));

        operationService.findAll();
    }

    @Test
    public void testSaveJson() throws IOException {
        // Mocking the repository's saveJson method
        doNothing().when(mockRepository).saveJson();

        // Performing the actual method call
        operationService.saveJson();

        // Verifying that the saveJson method was called
        verify(mockRepository, times(1)).saveJson();
    }
}
