package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.customer.CustomerDBSingleton;
import edu.mu.hotel.Reservation;
import edu.mu.hotel.ReservationManager;
import edu.mu.hotel.ServiceRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for ReservationManager.
 */
public class ReservationManagerTest {

    private ReservationManager reservationManager;

    @BeforeEach
    void setUp() {
        reservationManager = ReservationManager.getInstance();
    }

    
    /**
     * Test case to verify the singleton pattern.
     */
    @Test
    void testSingletonPattern() {
    	//makes 2 instances but should just be same instance
        ReservationManager instance1 = ReservationManager.getInstance();
        ReservationManager instance2 = ReservationManager.getInstance();
        assertEquals(instance1, instance2, "Instances should be the same");
    }
    
    
    /**
     * Test case to verify creating a reservation.
     */
    @Test
    void testCreateReservation() {
    	
    	reservationManager.clearDatabase();
    	
        int customerId = 1;
        String roomType = "Standard";
        String checkIn = "2025-01-01";
        String checkOut = "2025-01-05";
        List<ServiceRequest> serviceRequests = new ArrayList<>(); // Initialize with appropriate service requests
        serviceRequests.add(new ServiceRequest("Towels", 5, "Want some more towels"));

       
        Reservation reservation = reservationManager.createReservation(customerId, roomType, checkIn, checkOut, serviceRequests);


        assertNotNull(reservation, "Reservation should not be null bruh");
        assertEquals(customerId, reservation.getCustomerId(), "Customer ID should match");
        assertEquals("Standard Room", reservation.getRoomType(), "Room type should match");
        assertEquals(checkIn, reservation.getCheckInDateString(), "Check-in date should match");
        assertEquals(checkOut, reservation.getCheckOutDateString(), "Check-out date should match");
        assertTrue(reservation.isActive(), "Reservation should be active");
        
        reservationManager.clearDatabase();
    }

    /**
     * Test case to verify getting active reservations.
     */
    @Test
    void testGetActiveReservations() {

    	
        createDummyReservations();


        List<Reservation> activeReservations = reservationManager.getActiveReservations();


        assertFalse(activeReservations.isEmpty(), "Active reservations list should not be empty");
        assertTrue(activeReservations.stream().allMatch(Reservation::isActive), "All reservations should be active");
        
        reservationManager.clearDatabase();
    }

    /**
     * Test case to verify getting a reservation by ID.
     */
    @Test
    void testGetReservationById() {

        Reservation createdReservation = createDummyReservation();


        Reservation retrievedReservation = reservationManager.getReservationById(createdReservation.getReservationId());


        assertNotNull(retrievedReservation, "Retrieved reservation should not be null");
        assertEquals(createdReservation, retrievedReservation, "Retrieved reservation should match created reservation");
        
        reservationManager.clearDatabase();
    }

    /**
     * Test case to verify cancelling a reservation.
     */
    @Test
    void testCancelReservation() {

        Reservation reservation = createDummyReservation();


        boolean cancellationResult = reservationManager.cancelReservation(reservation.getReservationId());


        assertTrue(cancellationResult, "Cancellation should be successful");
        assertFalse(reservation.isActive(), "Reservation should be inactive after cancellation");
        
        reservationManager.clearDatabase();
    }

    /**
     * Test case to verify confirming a booking.
     */
    @Test
    void testConfirmBooking() {

        Reservation reservation = createDummyReservation();


        String confirmationMessage = reservationManager.confirmBooking(reservation);


        assertTrue(confirmationMessage.contains(String.valueOf(reservation.getReservationId())), "Confirmation message should contain reservation ID");
        
        reservationManager.clearDatabase();
    }

    /**
     * Test case to verify generating past history.
     */
    @Test
    void testGeneratePastHistory() {

        createDummyReservations();


        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 30);
        List<Reservation> pastHistory = reservationManager.generatePastHistory(startDate, endDate);


        assertFalse(pastHistory.isEmpty(), "Past history list should not be empty");
        assertTrue(pastHistory.stream().allMatch(reservation -> {
            LocalDate reservationDate = LocalDate.parse(reservation.getCheckInDateString());
            return reservationDate.isAfter(startDate) && reservationDate.isBefore(endDate);
        }), "All reservations in past history should fall within specified date range");
        
        reservationManager.clearDatabase();
    }

    // Helper methods for test cases

    private Reservation createDummyReservation() {
        return reservationManager.createReservation(1, "Standard", "2024-07-01", "2024-07-05", new ArrayList<>());
    }

    private void createDummyReservations() {
        reservationManager.createReservation(1, "Standard", "2024-08-01", "2024-08-05", new ArrayList<>());
        reservationManager.createReservation(2, "Deluxe", "2024-06-15", "2024-06-20", new ArrayList<>());
        reservationManager.createReservation(3, "Suite", "2024-07-01", "2024-07-05", new ArrayList<>());
    }
}

