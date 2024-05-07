package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import edu.mu.customer.Customer;
import edu.mu.hotel.Reservation;
import edu.mu.hotel.ReservationManager;
import edu.mu.hotel.ServiceRequest;
import edu.mu.test.Main;

public class MainTest {
	
	
	/*
	 * most methods in main are not really meant to be tested, so we chose to leave those out because still have great code coverage
	 */

    @Test
    void testCalculateDaysStayed() {
        // test dates
        LocalDate checkInDate = LocalDate.of(2024, 6, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 6, 5);

        // expected
        int expectedDays = 4;

        // call calculate days
        int actualDays = Main.calculateDaysStayed(checkInDate, checkOutDate);

        //check if correct
        assertEquals(expectedDays, actualDays, "Number of days stayed should match");
    }
    
    //hard to get these to work because we need input so we didnt do as much testing
    @Test
    public void testPayCustomerBillWithRewards() {
       //create reservation and customer
        Customer customer = new Customer("Jack", "James", "123", "123", "123",
        		"123", 5, true, 0,"123");
        Reservation reservation = createDummyReservation();
        
       

        // prepare scanner with test input y
        ByteArrayInputStream inContent = new ByteArrayInputStream("y".getBytes());
        System.setIn(inContent);


        //call method
        Main.payCustomerBill(customer, 1);

       
    }

    @Test
    public void testEmptyServiceRequest() {
        // create an empty list of service requests
        List<ServiceRequest> serviceRequests = new ArrayList<>();

        //call empty service request
        Main.emptyServiceRequest(serviceRequests);

        //make sure size is correct
        assertEquals(1, serviceRequests.size());

        //get first object
        ServiceRequest request = serviceRequests.get(0);

        // test attributes
        assertEquals("N/A", request.getRequestType());
        assertEquals(0, request.getQuantity());
        assertEquals("N/A", request.getNotes());
    }
    
    private Reservation createDummyReservation() {
        return ReservationManager.getInstance().createReservation(1, "Standard", "2024-07-01", "2024-07-05", new ArrayList<>());
    }

    
    
    
    
    
    
    

}

