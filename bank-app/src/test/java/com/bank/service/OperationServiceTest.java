package com.bank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.entity.Operation;
import com.bank.exceptions.ServiceException;

public class OperationServiceTest {

    OperationService service = new OperationService();

    @BeforeAll
    public static void setUp() {
        testSave();
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
        long id = 1; // replace with actual id
        Operation operation = service.findById(id);
        assertNotNull(operation);
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
        assertDoesNotThrow(() -> service.saveJson());
    }

    @Test
    public void testLoadJson() {
        assertDoesNotThrow(() -> service.loadJson());
    }
}