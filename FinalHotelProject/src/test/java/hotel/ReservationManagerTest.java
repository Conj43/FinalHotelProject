package hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import edu.mu.hotel.GsonReservation;
import edu.mu.hotel.Reservation;
import edu.mu.hotel.ReservationManager;
import edu.mu.hotel.ServiceRequest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for ReservationManager.
 */
public class ReservationManagerTest {

    private ReservationManager reservationManager;
    int lastReservationId;

    @BeforeEach
    void setUp() {
        reservationManager = ReservationManager.getInstance(); //get instance to use before each test
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
    	
    	reservationManager.clearDatabase(); //clear database so we dont overlap
    	
        int customerId = 1;
        String roomType = "Standard";
        String checkIn = "2025-01-01";
        String checkOut = "2025-01-05";
        List<ServiceRequest> serviceRequests = new ArrayList<>(); // Initialize with appropriate service requests
        serviceRequests.add(new ServiceRequest("Towels", 5, "Want some more towels"));

       
        Reservation reservation = reservationManager.createReservation(customerId, roomType, checkIn, checkOut, serviceRequests);

        //checks each attribute to make sure constructor works
        assertNotNull(reservation, "Reservation should not be null bruh");
        assertEquals(customerId, reservation.getCustomerId(), "Customer ID should match");
        assertEquals("Standard Room", reservation.getRoomType(), "Room type should match");
        assertEquals(checkIn, reservation.getCheckInDateString(), "Check-in date should match");
        assertEquals(checkOut, reservation.getCheckOutDateString(), "Check-out date should match");
        assertTrue(reservation.isActive(), "Reservation should be active");
        
        reservationManager.clearDatabase(); //clears database
    }

    /**
     * Test case to verify getting active reservations.
     */
    @Test
    void testGetActiveReservations() {

    	
        createDummyReservations(); //makes test reservations


        List<Reservation> activeReservations = reservationManager.getActiveReservations();


        assertFalse(activeReservations.isEmpty(), "Active reservations list should not be empty"); //since we made reservations, shouldnt be empty
        assertTrue(activeReservations.stream().allMatch(Reservation::isActive), "All reservations should be active");
        
        reservationManager.clearDatabase(); //clears database
    }

    /**
     * Test case to verify getting a reservation by ID.
     */
    @Test
    void testGetReservationById() {

        Reservation createdReservation = createDummyReservation();// create one reservation


        Reservation retrievedReservation = reservationManager.getReservationById(createdReservation.getReservationId()); //get reseration by id


        assertNotNull(retrievedReservation, "Retrieved reservation should not be null"); //check to make sure not null and is correct
        assertEquals(createdReservation, retrievedReservation, "Retrieved reservation should match created reservation");
        
        reservationManager.clearDatabase(); //clears database
    }

    /**
     * Test case to verify cancelling a reservation.
     */
    @Test
    void testCancelReservation() {

        Reservation reservation = createDummyReservation(); //create reseration


        boolean cancellationResult = reservationManager.cancelReservation(reservation.getReservationId()); //test cancel dumy reservation


        assertTrue(cancellationResult, "Cancellation should be successful"); //make sure it retruns true
        assertFalse(reservation.isActive(), "Reservation should be inactive after cancellation"); //make sure is active is false
        
        reservationManager.clearDatabase(); //clear database
    }

    /**
     * Test case to verify confirming a booking.
     */
    @Test
    void testConfirmBooking() {

        Reservation reservation = createDummyReservation(); //create dummy reservation


        String confirmationMessage = reservationManager.confirmBooking(reservation); //get confimation string messae

        //make sure it matches
        assertTrue(confirmationMessage.contains(String.valueOf(reservation.getReservationId())), "Confirmation message should contain reservation ID");
        
        reservationManager.clearDatabase(); //clear database
    }

    /**
     * Test case to verify generating past history.
     */
    @Test
    void testGeneratePastHistory() {

        createDummyReservations(); //create dummy reservations


        LocalDate startDate = LocalDate.of(2024, 1, 1); //start and end date for range
        LocalDate endDate = LocalDate.of(2024, 12, 30);
        List<Reservation> pastHistory = reservationManager.generatePastHistory(startDate, endDate); //generate the history


        assertFalse(pastHistory.isEmpty(), "Past history list should not be empty"); //shouldnt be emtpy
        assertTrue(pastHistory.stream().allMatch(reservation -> {
            LocalDate reservationDate = LocalDate.parse(reservation.getCheckInDateString()); //should be all reservations that were made
            return reservationDate.isAfter(startDate) && reservationDate.isBefore(endDate);
        }), "All reservations in past history should fall within specified date range");
        
        reservationManager.clearDatabase(); //clear database
    }
    
    
    
    
    /*
     * test converting to gson reservation from reservation
     */
    @Test
    public void testConvertToReservation() {
    	
    	
    	
    	//create gson reservation and set values
        GsonReservation temp = new GsonReservation();
        
        temp.setReservationId(1);
        temp.setCustomerId(123);
        temp.setCheckInDate("2024-05-01");
        temp.setCheckOutDate("2024-05-10");
        temp.setRoomTypeName("Standard Room");
        temp.setRoomNumber(100);
        temp.setActive(true);
        temp.setAccessCode("test-access-code");
        temp.setKeyCardActive(true);
        temp.setServiceRequests(Arrays.asList(new ServiceRequest("test service request", 1, "test")));

        

        //convert to rservation and check the values
        Reservation reservation = reservationManager.convertToReservation(temp);
        
        assertNotNull(reservation);
        assertEquals(1, reservation.getReservationId());
        assertEquals(123, reservation.getCustomerId());
        assertEquals("2024-05-01", reservation.getCheckInDateString());
        assertEquals("2024-05-10", reservation.getCheckOutDateString());
        assertEquals(100, reservation.getRoom().getRoomNumber());
        assertTrue(reservation.isActive());
        assertEquals("test-access-code", reservation.getAccessCode());
        assertTrue(reservation.isKeyCardActive());
        assertEquals(1, reservation.getServiceRequests().size());
    }
    
    

    // helper methods to create reservations

    private Reservation createDummyReservation() {
        return reservationManager.createReservation(1, "Standard", "2024-07-01", "2024-07-05", new ArrayList<>());
    }

    private void createDummyReservations() {
        reservationManager.createReservation(1, "Standard", "2024-08-01", "2024-08-05", new ArrayList<>());
        reservationManager.createReservation(2, "Deluxe", "2024-06-15", "2024-06-20", new ArrayList<>());
        reservationManager.createReservation(3, "Suite", "2024-07-01", "2024-07-05", new ArrayList<>());
    }
    
    
    
    
}

