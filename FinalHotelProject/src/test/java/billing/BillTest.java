package billing;
import edu.mu.billing.*;
import edu.mu.customer.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



class BillTest {

    private Bill bill;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        bill = new Bill(customer, 100.0);
    }

    /*
     * Positive test for setting payment strategy
     */
    @Test
    void testSetPaymentStrategy() {
        PaymentStrategy paymentStrategy = new CreditCardPayment();
        bill.setPaymentStrategy(paymentStrategy);
        assertNotNull(bill.payBill());
    }

    /*
     * Negative test for paying bill without setting payment strategy
     */
    @Test
    void testPayBillWithoutPaymentStrategy() {
        assertFalse(bill.payBill());
    }

    /*
     *  Negative test for paying bill with invalid payment strategy
     */
    @Test
    void testPayBillWithInvalidPaymentStrategy() {
        bill.setPaymentStrategy(null);
        assertFalse(bill.payBill());
    }
}

