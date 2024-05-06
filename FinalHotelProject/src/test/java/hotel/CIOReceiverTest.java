package hotel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.mu.customer.*;
import edu.mu.hotel.*;

class CIOReceiverTest {

    private CIOReceiver cioReceiver;
    private Customer customer;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        cioReceiver = new CIOReceiver();
        customer = new Customer();
        reservation = new Reservation();
        reservation.setCustomerId(customer.getCustomerID());
        reservation.setReservationId(1); // Example reservation ID
    }

    // Positive test for checking in
    @Test
    void testCheckIn() {
        assertDoesNotThrow(() -> cioReceiver.checkin(customer, reservation));
        assertTrue(reservation.isActive());
        assertTrue(reservation.isKeyCardActive());
        assertNotNull(reservation.getAccessCode());
        assertEquals(40, customer.getRewardsPoints());
    }

    // Positive test for checking out
    @Test
    void testCheckOut() {
        // First, check in
        cioReceiver.checkin(customer, reservation);
        assertTrue(reservation.isActive());
        // Then, check out
        assertDoesNotThrow(() -> cioReceiver.checkout(customer, reservation));
        assertFalse(reservation.isActive());
        assertFalse(reservation.isKeyCardActive());
        assertNull(reservation.getAccessCode());
    }
}

