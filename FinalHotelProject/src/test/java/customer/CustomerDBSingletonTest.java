package customer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.customer.Customer;
import edu.mu.customer.CustomerDBSingleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test class for CustomerDBSingleton.
 */
public class CustomerDBSingletonTest {

    CustomerDBSingleton customerDbManager = null;

    /**
     * Initializes CustomerDBSingleton instance before each test.
     *
     * @throws Exception if an error occurs during setup
     */
    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Initializing CustomerDBSingleton...");
        customerDbManager = CustomerDBSingleton.getInstance();
    }

   

    /**
     * Test case to verify the singleton pattern.
     */
    @Test
    void testSingletonPattern() {
    	//makes 2 instances but should just be same instance
        CustomerDBSingleton instance1 = CustomerDBSingleton.getInstance();
        CustomerDBSingleton instance2 = CustomerDBSingleton.getInstance();
        assertEquals(instance1, instance2, "Instances should be the same");
    }

    /**
     * Test case to verify adding and retrieving a customer.
     */
    @Test
    void testAddAndGetCustomer() {
        Customer customer = new Customer("John", "Doe", "john@example.com", "123456789", "123 Main St", "1990-01-01", 30, true, 100, "1234567890");
        customerDbManager.addCustomer(customer);//add customer to db then retrieve them and test to see if equal
        Customer retrievedCustomer = customerDbManager.getCustomer(customer.getCustomerID());
        assertEquals(customer, retrievedCustomer, "Retrieved customer should match added customer");
    }

    /**
     * Test case to verify saving and loading the database.
     */
    @Test
    void testSaveAndLoadDatabase() {
        // Add some customers to the database
        Customer customer1 = new Customer("Alice", "Smith", "alice@example.com", "5551234567", "789 Elm St", "1988-12-15", 33, true, 150, "9876543210");
        Customer customer2 = new Customer("Bob", "Brown", "bob@example.com", "1112223333", "321 Maple St", "1975-08-20", 48, true, 75, "4567890123");
        customerDbManager.addCustomer(customer1);
        customerDbManager.addCustomer(customer2);

        //try saving database
        customerDbManager.saveDatabase();

        // set to null
        customerDbManager = null;

        // create new instance to make sure it works
        customerDbManager = CustomerDBSingleton.getInstance();

        // load data from database
        Customer retrievedCustomer1 = customerDbManager.getCustomer(customer1.getCustomerID());
        Customer retrievedCustomer2 = customerDbManager.getCustomer(customer2.getCustomerID());

        //test customers that were retrieved
        assertNotNull(retrievedCustomer1, "Retrieved customer 1 should not be null");
        assertNotNull(retrievedCustomer2, "Retrieved customer 2 should not be null");
        assertEquals(customer1, retrievedCustomer1, "Retrieved customer 1 should match added customer 1");
        assertEquals(customer2, retrievedCustomer2, "Retrieved customer 2 should match added customer 2");
    }
}
