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

import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository repository = TransactionRepository.getInstance();

    private Transaction transaction;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    @BeforeEach
    public void setup() {
        transaction = new Transaction();
        transaction.setId(1L);
        transactions.add(transaction);
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
        Mockito.when(repository.findAll()).thenReturn(transactions);
        assertNotNull(repository.findAll());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testFindById() throws RepositoryException {
        try {
            Mockito.when(repository.findById(1)).thenReturn(transaction);
            assertNotNull(repository.findById(1L));
            assertEquals(transaction, repository.findById(1));
        } catch (RepositoryException e) {
            fail("Repository Exception should not be thrown");
        }
    }

    @Test
    public void testSave() throws RepositoryException {
        try {
            Mockito.when(repository.save(transaction)).thenReturn(transaction);
            assertNotNull(repository.save(transaction));
            assertEquals(transaction, repository.save(transaction));
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