package hotel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.mu.customer.*;
import edu.mu.hotel.*;

class CIOInvokerTest {

    private CIOInvoker cioInvoker;
    private Customer customer;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        CIOCommand checkInCommand = new CheckInCommand(new CIOReceiver());
        CIOCommand checkOutCommand = new CheckOutCommand(new CIOReceiver());
        cioInvoker = new CIOInvoker(checkInCommand, checkOutCommand);
        customer = new Customer();
        reservation = new Reservation();
    }

    // Positive test for checking in
    @Test
    void testCheckIn() {
        assertDoesNotThrow(() -> cioInvoker.checkIn(customer, reservation));
    }

    // Positive test for checking out
    @Test
    void testCheckOut() {
        assertDoesNotThrow(() -> cioInvoker.checkOut(customer, reservation));
    }
}