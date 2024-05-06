package hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.mu.hotel.ServiceRequest;

/**
 * JUnit test class for ServiceRequest.
 */
public class ServiceRequestTest {

    /**
     * Test case to verify creating a service request.
     */
    @Test
    void testCreateServiceRequest() {
        // Test data
        String requestType = "Towels";
        int quantity = 5;
        String notes = "Need some more towels";

        // make sample request
        ServiceRequest serviceRequest = new ServiceRequest(requestType, quantity, notes);

        // check that the service request is not null
        assertNotNull(serviceRequest, "Service request should not be null");

        //make sure values are all correct
        assertEquals(requestType, serviceRequest.getRequestType(), "Request type should match");
        assertEquals(quantity, serviceRequest.getQuantity(), "Quantity should match");
        assertEquals(notes, serviceRequest.getNotes(), "Notes should match");
    }
    
    @Test
    public void testSetRequestType() {
        // create request
        ServiceRequest request = new ServiceRequest();

       //set request
        String expectedRequestType = "Housekeeping";
        request.setRequestType(expectedRequestType);

        // check if correct
        assertEquals(expectedRequestType, request.getRequestType());
    }

    @Test
    public void testSetQuantity() {
        // create reqest
        ServiceRequest request = new ServiceRequest();

        // set quantity
        int expectedQuantity = 3;
        request.setQuantity(expectedQuantity);

        // check value
        assertEquals(expectedQuantity, request.getQuantity());
    }

    @Test
    public void testSetNotes() {
        // create request
        ServiceRequest request = new ServiceRequest();

        // set note
        String expectedNotes = "Please clean the room thoroughly.";
        request.setNotes(expectedNotes);

        // check if correct
        assertEquals(expectedNotes, request.getNotes());
    }
}

