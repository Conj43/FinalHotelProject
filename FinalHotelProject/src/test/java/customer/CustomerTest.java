package customer;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    
    //these test all getters and setter indivudually
    @Test
    void testSetAndGetCustomerId() {
        customer.setCustomerID(3);
        assertEquals(3, customer.getCustomerID());
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
    	customer.setCustomerID(0);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setPhoneNum("123456789");
        customer.setAddress("123 Main St");
        customer.setBirthDate("1990-01-01");
        customer.setAge(30);
        customer.setRewardsMember(true);
        customer.setRewardPoints(100);
        customer.setCardNum("123456");

        
        //tests to make sure to string outputs correct to string
        String expectedToString = "Customer [customerID=0, firstName=John, lastName=Doe, email=john@example.com, phoneNum=123456789, address=123 Main St, birthDate=1990-01-01, age=30, isRewardsMember=true, rewardPoints=100, cardNum=123456]";
        assertEquals(expectedToString, customer.toString());
    }
    
    @ParameterizedTest
    @CsvSource({
        "James, Franklin, james@example.com, 123-456-7890, 123 Lol Lane, 1999-01-01, 25, true, 100, 1234567890",
        "Alice, Johnson, alice@example.com, 5551234567, 789 Elm St, 1988-12-15, 33, true, 150, 9876543210",
        "Bob, Brown, bob@example.com, 1112223333, 321 Maple St, 1975-08-20, 48, true, 75, 4567890123"
    })
    void testParameterizedConstructor(String firstName, String lastName, String email, String phoneNum, String address,
                                      String birthDate, int age, boolean isRewardsMember, int rewardPoints, String cardNum) {
        customer = new Customer(firstName, lastName, email, phoneNum, address, birthDate, age, isRewardsMember, rewardPoints, cardNum);

        //test each attribute from constructor
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(email, customer.getEmail());
        assertEquals(phoneNum, customer.getPhoneNum());
        assertEquals(address, customer.getAddress());
        assertEquals(birthDate, customer.getBirthDate());
        assertEquals(age, customer.getAge());
        assertEquals(isRewardsMember, customer.isRewardsMember());
        assertEquals(rewardPoints, customer.getRewardsPoints());
        assertEquals(cardNum, customer.getCardNum());
    }
    
}