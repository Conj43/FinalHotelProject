package hotel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.mu.customer.*;
import edu.mu.hotel.*;

class CheckInCommandTest {

    private CIOCommand checkInCommand;
    private Customer customer;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        CIOReceiver cioReceiver = new CIOReceiver();
        checkInCommand = new CheckInCommand(cioReceiver);
        customer = new Customer();
        reservation = new Reservation();
    }

    // Positive test for checking in
    @Test
    void testExecuteCheckIn() {
        assertDoesNotThrow(() -> checkInCommand.execute(customer, reservation));
    }
}


