package billing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.mu.customer.*;
import edu.mu.billing.*;

class CreditCardPaymentTest {

    private PaymentStrategy paymentStrategy;
    private Customer customer;

    @BeforeEach
    void setUp() {
        paymentStrategy = new CreditCardPayment();
        customer = new Customer();
    }

    /*
     *  Positive test for paying bill using credit card payment strategy
     */
    @Test
    void testPayBill() {
        double amount = 100.0;
        assertDoesNotThrow(() -> paymentStrategy.pay(customer, amount));
    }
}