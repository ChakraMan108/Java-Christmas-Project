package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bank.entity.Operation;
import com.bank.exceptions.ServiceException;

public class OperationServiceTest {

    OperationService service;

    @BeforeEach
    public void setUp() throws ServiceException {
        service = new OperationService();
        Operation operation = new Operation();
        // Set properties for operation
        Operation savedOperation = service.save(operation);
        assertEquals(operation, savedOperation);
    }

    @Test
    public void testSave() throws ServiceException {
        Operation operation = new Operation();
        // Set properties for operation
        Operation savedOperation = service.save(operation);
        assertEquals(operation, savedOperation);
    }

    @Test
    public void testFindById() throws ServiceException {
        Operation operation = new Operation();
        Operation savedOperation = service.save(operation);
        long id = savedOperation.getId();
        Operation testOperation = service.findById(id);
        assertNotNull(testOperation);
    }

    @Test
    public void testCount() throws ServiceException {
        long count = service.count();
        assertTrue(count >= 0);
    }

    @Test
    public void testFindAll() throws ServiceException {
        ArrayList<Operation> operations = service.findAll();
        assertNotNull(operations);
    }

    @Test
    public void testSaveJson() {
        assertThrows(ServiceException.class, () -> service.saveJson());
    }

    @Test
    public void testLoadJson() {
        assertThrows(ServiceException.class, () -> service.loadJson());
    }
}