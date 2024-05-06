package customer;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.customer.Customer;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Initializing customer...");
        customer = new Customer();
    }

    @AfterEach
    void tearDown() throws Exception {
        
    }

    @Test
    void testSetAndGetFirstName() {
        customer.setFirstName("John");
        assertEquals("John", customer.getFirstName());
    }

    @Test
    void testSetAndGetLastName() {
        customer.setLastName("Doe");
        assertEquals("Doe", customer.getLastName());
    }

    @Test
    void testSetAndGetEmail() {
        customer.setEmail("john@example.com");
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    void testSetAndGetPhoneNum() {
        customer.setPhoneNum("123456789");
        assertEquals("123456789", customer.getPhoneNum());
    }

    @Test
    void testSetAndGetAddress() {
        customer.setAddress("123 Main St");
        assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    void testSetAndGetBirthDate() {
        customer.setBirthDate("1990-01-01");
        assertEquals("1990-01-01", customer.getBirthDate());
    }

    @Test
    void testSetAndGetAge() {
        customer.setAge(30);
        assertEquals(30, customer.getAge());
    }

    @Test
    void testSetAndIsRewardsMember() {
        customer.setRewardsMember(true);
        assertTrue(customer.isRewardsMember());
    }

    @Test
    void testSetAndGetRewardPoints() {
        customer.setRewardPoints(100);
        assertEquals(100, customer.getRewardsPoints());
    }

    @Test
    void testSetAndGetCardNum() {
        customer.setCardNum("1234567890");
        assertEquals("1234567890", customer.getCardNum());
    }
    
    @Test
    void testToString() {
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setPhoneNum("123456789");
        customer.setAddress("123 Main St");
        customer.setBirthDate("1990-01-01");
        customer.setAge(30);
        customer.setRewardsMember(true);
        customer.setRewardPoints(100);

        String expectedToString = "Customer [customerID=0, firstName=John, lastName=Doe, email=john@example.com, phoneNum=123456789, address=123 Main St, birthDate=1990-01-01, age=30, isRewardsMember=true, rewardPoints=100]";
        assertEquals(expectedToString, customer.toString());
    }

    
}