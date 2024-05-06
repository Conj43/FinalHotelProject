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
}

