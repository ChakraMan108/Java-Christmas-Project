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

import com.bank.entity.Transaction;
import com.bank.exceptions.RepositoryException;
import com.bank.exceptions.ServiceException;
import com.bank.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository repository;

    private Transaction transaction;

    @BeforeEach
    public void setup() {
        transaction = new Transaction();
        transaction.setId(1L);
    }

    @Test
    public void testCount() throws ServiceException {
        try {
            Mockito.when(repository.count()).thenReturn(1L);
            assertEquals(1L, transactionService.count());
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testFindAll() throws ServiceException {
        try {
            Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
            assertNotNull(transactionService.findAll());
            assertEquals(0, transactionService.findAll().size());
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testFindById() throws ServiceException {
        try {
            Mockito.when(repository.findById(1L)).thenReturn(transaction);
            assertNotNull(transactionService.findById(1L));
            assertEquals(transaction, transactionService.findById(1L));
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testSave() throws ServiceException {
        try {
            Mockito.when(repository.save(transaction)).thenReturn(transaction);
            assertNotNull(transactionService.save(transaction));
            assertEquals(transaction, transactionService.save(transaction));
        } catch (RepositoryException e) {
            fail("RepositoryException should not be thrown");
        }
    }

    @Test
    public void testSaveJson() throws ServiceException {
        try {
            Mockito.doNothing().when(repository).saveJson();
            assertDoesNotThrow(() -> transactionService.saveJson());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testLoadJson() throws ServiceException {
        try {
            Mockito.doNothing().when(repository).loadJson();
            assertDoesNotThrow(() -> transactionService.loadJson());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }
}