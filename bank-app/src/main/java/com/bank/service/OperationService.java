
package com.bank.service;

import com.bank.entity.Operation;
import com.bank.repository.OperationRepository;



import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class OperationService {


    private List<Operation> operations;

    private OperationRepository operationRepository;


    //OperationService:The constructor takes an instance of OperationRepository as a parameter and initializes the operationRepository field. This is known as dependency injection, where the repository is injected into the service.
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }


    //saveOperation:This method saves a new Operation object using the save method provided by the operationRepository. If an exception occurs during the save operation, it is caught, and a RuntimeException is thrown with an error message indicating the failure.
    public Operation saveOperation(Operation operation) {
        try {
            return operationRepository.save(operation);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to save operation", e);
        }
    }


    //getOperationById: This method retrieves an Operation by its ID using the findById method provided by the operationRepository. The result is wrapped in an Optional. If an exception occurs, it is caught, and a RuntimeException is thrown with an error message.
    public Optional<Operation> getOperationById(long id) {
        return operations.stream()
                .filter(operation -> operation.getId() == id)
                .findFirst();
    }



    //getAllOperations: This method retrieves all Operation objects using the findAll method provided by the operationRepository. If an exception occurs, it is caught, and a RuntimeException is thrown with an error message.
    public List<Operation> getAllOperations() {
        try {
            return operationRepository.findAll();
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve all operations", e);
        }
    }
//     public List<Operation> getAllOperations() {
//         return operations;
//     }
// }


    //deleteOperationById:This method deletes an Operation by its ID using the deleteById method provided by the operationRepository. If an exception occurs, it is caught, and a RuntimeException is thrown with an error message.
    public void deleteOperationById(long id) {
        Iterator<Operation> iterator = operations.iterator();
        while (iterator.hasNext()) {
            Operation operation = iterator.next();
            if (operation.getId() == id) {
                iterator.remove(); // Remove the operation from the list
                // Optionally, you may want to perform additional cleanup or trigger events here
                return;
            }
        }
        throw new IllegalArgumentException("Operation with ID " + id + " not found");
    }
}
    







/*

    
    private OperationRepository operationRepository;

    @Override
    public Operation createCustomer(Customer customer, String username) {
        // Implement the logic for creating a customer
        Operation operation = new Operation(1L, Operation.OperationType.CUSTOMER_CREATION, username, LocalDate.now(), customer);
        return operationRepository.save(operation);
    }

    @Override
    public void deactivateCustomer(Customer customer, String username) {
        // Implement the logic for deactivating a customer
        // Set the appropriate OperationType and save to repository
    }

    @Override
    public Operation createBankAccount(Customer customer, String username) {
        // Implement the logic for creating a bank account
        // Set the appropriate OperationType and save to repository
    }

    @Override
    public void deactivateBankAccount(Customer customer, String username) {
        // Implement the logic for deactivating a bank account
        // Set the appropriate OperationType and save to repository
    }
    // Implement more methods if needed
}
*/









//     private final OperationRepository operationRepository;

    
//     public OperationService(OperationRepository operationRepository) {
//         this.operationRepository = operationRepository;
//     }

//     public Operation saveOperation(Operation operation) throws RepositoryException {
//         return operationRepository.save(operation);
//     }

//     public Optional<Operation> getOperationById(long id) {
//         return operationRepository.findById(id);
//     }

//     public List<Operation> getAllOperations() throws RepositoryException {
//         return operationRepository.findAll();
//     }

//     public void deleteOperation(long id) {
//         operationRepository.deleteById(id);
//     }

//     // You can add more service methods as needed based on your business logic
// }