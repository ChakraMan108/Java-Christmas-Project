package com.bank.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.entity.Operation;
import com.bank.exceptions.RepositoryException;

@ExtendWith(MockitoExtension.class)
public class OperationRepositoryTest {

    @Mock
    private OperationRepository repository = OperationRepository.getInstance();

    private Operation operation;
    private ArrayList<Operation> operations = new ArrayList<Operation>();

    @BeforeEach
    public void setup() {
        operation = new Operation();
        operation.setId(1L);
        operations.add(operation);
    }

    @Test
    public void testCountEmpty() throws RepositoryException {
        assertEquals(0, repository.count());
    }

    @Test
    public void testCountOne() throws RepositoryException {
        Mockito.when(repository.count()).thenReturn(1L);
        assertEquals(1, repository.count());
    }

    @Test
    public void testFindAll() throws RepositoryException {
        Mockito.when(repository.findAll()).thenReturn(operations);
        assertNotNull(repository.findAll());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testFindById() throws RepositoryException {
        try {
            Mockito.when(repository.findById(1)).thenReturn(operation);
            assertNotNull(repository.findById(1L));
            assertEquals(operation, repository.findById(1));
        } catch (RepositoryException e) {
            fail("Repository Exception should not be thrown");
        }
    }

    @Test
    public void testSave() throws RepositoryException {
        try {
            Mockito.when(repository.save(operation)).thenReturn(operation);
            assertNotNull(repository.save(operation));
            assertEquals(operation, repository.save(operation));
        } catch (RepositoryException e) {
            fail("Repository Exception should not be thrown");
        }
    }

    @Test
    public void testGenerateId() {
        Mockito.when(repository.generateId()).thenReturn(1_000_000_000L);
        assertTrue(repository.generateId() >= 1_000_000_000L);
    }

    @Test
    public void testSaveJson() {
        assertDoesNotThrow(() -> repository.saveJson());
    }

    @Test
    public void testLoadJson() {
        assertDoesNotThrow(() -> repository.loadJson());
    }
}