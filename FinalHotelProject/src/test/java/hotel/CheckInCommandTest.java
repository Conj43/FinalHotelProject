package hotel;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.mu.customer.*;
import edu.mu.hotel.*;
import edu.mu.hotel.rooms.RoomType;

class CheckInCommandTest {

    private CIOCommand checkInCommand;
    private Customer customer;
    private Reservation reservation;
    private RoomType roomType;

    @BeforeEach
    void setUp() {
        CIOReceiver cioReceiver = new CIOReceiver();
        checkInCommand = new CheckInCommand(cioReceiver);
        customer = new Customer();
        reservation = new Reservation();
        roomType = new RoomType() {
			
			@Override
			public double calculateCost(int days, LocalDate checkIn) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		reservation.setRoom(roomType);
    }

    /*
     * Positive test for checking in
     */
    @Test
    void testExecuteCheckIn() {
        assertDoesNotThrow(() -> checkInCommand.execute(customer, reservation));
    }
}


