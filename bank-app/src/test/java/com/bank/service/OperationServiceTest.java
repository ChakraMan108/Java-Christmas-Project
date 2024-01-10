package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.OperationRepository;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    @InjectMocks
    private OperationServiceImpl operationService;

    @Mock
    private OperationRepository repository;

    private Operation operation;

    @BeforeEach
    public void setup() {
        operation = new Operation();
        operation.setId(1L);
    }

    @Test
    public void testSave() throws ServiceException {
        try {
            Mockito.when(repository.save(operation)).thenReturn(operation);
            assertNotNull(operationService.save(operation));
            assertEquals(operation, operationService.save(operation));
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testFindById() throws ServiceException {
        try {
            Mockito.when(repository.findById(1L)).thenReturn(operation);
            assertNotNull(operationService.findById(1L));
            assertEquals(1L, operationService.findById(1L).getId());
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testCount() throws ServiceException {
        try {
            Mockito.when(repository.count()).thenReturn(1L);
            assertEquals(1L, operationService.count());
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testFindAll() throws ServiceException {
        try {
            Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
            assertNotNull(operationService.findAll());
            assertEquals(0, operationService.findAll().size());
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testSaveJson() throws ServiceException {
        try {
            Mockito.doNothing().when(repository).saveJson();
            assertDoesNotThrow(() -> operationService.saveJson());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testLoadJson() throws ServiceException {
        try {
            Mockito.doNothing().when(repository).loadJson();
            assertDoesNotThrow(() -> operationService.loadJson());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }
}