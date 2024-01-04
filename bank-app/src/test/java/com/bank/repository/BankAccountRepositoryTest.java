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

import com.bank.entity.BankAccount;
import com.bank.entity.Customer;
import com.bank.exceptions.RepositoryException;

@ExtendWith(MockitoExtension.class)
public class BankAccountRepositoryTest {

    @Mock
    private BankAccountRepository repository = BankAccountRepository.getInstance();

    private Customer customer;
    private BankAccount bankAccount;
    private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    @BeforeEach
    public void setup() {
        bankAccount = new BankAccount();
        bankAccount.setId(1L);
        customer = new Customer();
        customer.setName("Test");
        customer.setId(1L);
        bankAccount.setCustomer(customer);
        bankAccounts.add(bankAccount);
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
        Mockito.when(repository.findAll()).thenReturn(bankAccounts);
        assertNotNull(repository.findAll());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testFindById() throws RepositoryException {
        try {
            Mockito.when(repository.findById(1)).thenReturn(bankAccount);
            assertNotNull(repository.findById(1L));
            assertEquals(bankAccount, repository.findById(1));
        } catch (RepositoryException e) {
            fail("Repository Exception should not be thrown");
        }
    }

    @Test
    public void testFindByCustomerName() throws RepositoryException {
        try {
            Mockito.when(repository.findByCustomerName("Test")).thenReturn(bankAccount);
            assertNotNull(repository.findByCustomerName("Test"));
            assertEquals("Test", repository.findByCustomerName("Test").getCustomer().getName());
        } catch (RepositoryException e) {
            fail("Repository Exception should not be thrown");
        }
    }

    @Test
    public void testFindByCustomerId() throws RepositoryException {
        try {
            Mockito.when(repository.findByCustomerId(1L)).thenReturn(bankAccount);
            assertNotNull(repository.findByCustomerId(1L));
            assertEquals(1L, repository.findByCustomerId(1L).getCustomer().getId());
        } catch (RepositoryException e) {
            fail("Repostiry Exception should not be thrown");
        }
    }

    @Test
    public void testSave() throws RepositoryException {
        try {
            Mockito.when(repository.save(bankAccount)).thenReturn(bankAccount);
            assertNotNull(repository.save(bankAccount));
            assertEquals(bankAccount, repository.save(bankAccount));
        } catch (RepositoryException e) {
            fail("Repository Exception should not be thrown");
        }
    }

    @Test
    public void testGenerateAccountId() {
        Mockito.when(repository.generateAccountId()).thenReturn(1_000_000_000L);
        assertTrue(repository.generateAccountId() >= 1_000_000_000L);
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