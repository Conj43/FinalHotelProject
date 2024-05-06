package billing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.mu.customer.*;
import edu.mu.billing.*;

class RedeemLoyaltyPointsTest {

    private PaymentStrategy paymentStrategy;
    private Customer customer;

    @BeforeEach
    void setUp() {
        paymentStrategy = new RedeemLoyaltyPoints();
        customer = new Customer();
    }

    // Positive test for paying bill using loyalty points
    @Test
    void testPayBillWithPoints() {
        // Assuming the customer has enough points
        customer.setRewardPoints(100);
        double amount = 100.0;
        assertDoesNotThrow(() -> paymentStrategy.pay(customer, amount));
        assertEquals(0, customer.getRewardsPoints()); // Customer should have 0 points after redeeming
    }

    // Negative test for paying bill with insufficient loyalty points
    @Test
    void testPayBillWithInsufficientPoints() {
        // Assuming the customer doesn't have enough points
        customer.setRewardPoints(50);
        double amount = 100.0;
        assertDoesNotThrow(() -> paymentStrategy.pay(customer, amount));
        // After the payment, customer should have 50 points left and bill paid using credit card
        assertEquals(50, customer.getRewardsPoints());
    }
}

