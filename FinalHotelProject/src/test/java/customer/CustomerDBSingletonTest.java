package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import edu.mu.customer.Customer;
import edu.mu.customer.CustomerDBSingleton;

public class CustomerDBSingletonTest {
	
	CustomerDBSingleton customerDbManager = null;

	@BeforeEach
    void setUp() throws Exception {
        System.out.println("Initializing CustomerDBSingleton...");
        customerDbManager = CustomerDBSingleton.getInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up any resources if needed
    }
    
    @Test
    void testSingletonPattern() {
        CustomerDBSingleton instance1 = CustomerDBSingleton.getInstance();
        CustomerDBSingleton instance2 = CustomerDBSingleton.getInstance();
        assertEquals(instance1, instance2, "Instances should be the same");
    }
    
    @Test
    void testAddAndGetCustomer() {
        Customer customer = new Customer("John", "Doe", "john@example.com", "123456789", "123 Main St", "1990-01-01", 30, true, 100, "1234567890");
        customerDbManager.addCustomer(customer);
        Customer retrievedCustomer = customerDbManager.getCustomer(customer.getCustomerID());
        assertEquals(customer, retrievedCustomer, "Retrieved customer should match added customer");
    }
    
    @Test
    void testSaveAndLoadDatabase() {
        // Add some customers to the database
        Customer customer1 = new Customer("Alice", "Smith", "alice@example.com", "5551234567", "789 Elm St", "1988-12-15", 33, true, 150, "9876543210");
        Customer customer2 = new Customer("Bob", "Brown", "bob@example.com", "1112223333", "321 Maple St", "1975-08-20", 48, true, 75, "4567890123");
        customerDbManager.addCustomer(customer1);
        customerDbManager.addCustomer(customer2);
        
        // Save the database to a JSON file
        customerDbManager.saveDatabase();
        
        // Clear the current database instance
        customerDbManager = null;
        
        // Create a new instance of CustomerDBSingleton
        customerDbManager = CustomerDBSingleton.getInstance();
        
        // Load the database from the JSON file
        Customer retrievedCustomer1 = customerDbManager.getCustomer(customer1.getCustomerID());
        Customer retrievedCustomer2 = customerDbManager.getCustomer(customer2.getCustomerID());
        
        assertNotNull(retrievedCustomer1, "Retrieved customer 1 should not be null");
        assertNotNull(retrievedCustomer2, "Retrieved customer 2 should not be null");
        assertEquals(customer1, retrievedCustomer1, "Retrieved customer 1 should match added customer 1");
        assertEquals(customer2, retrievedCustomer2, "Retrieved customer 2 should match added customer 2");
    }
    
    
    
    
    
   
    
}
