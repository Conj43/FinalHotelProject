package hotel;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.mu.customer.*;
import edu.mu.hotel.*;
import edu.mu.hotel.rooms.RoomType;

class CheckOutCommandTest {

    private CIOCommand checkOutCommand;
    private Customer customer;
    private Reservation reservation;
    private RoomType roomType;

    @BeforeEach
    void setUp() {
        CIOReceiver cioReceiver = new CIOReceiver();
        checkOutCommand = new CheckOutCommand(cioReceiver);
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
     *  Positive test for checking in
     */
    @Test
    void testExecuteCheckIn() {
        assertDoesNotThrow(() -> checkOutCommand.execute(customer, reservation));
    }
}