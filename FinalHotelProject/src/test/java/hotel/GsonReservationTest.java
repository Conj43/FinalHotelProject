package hotel;





import static org.junit.Assert.*;
import org.junit.Test;

import edu.mu.hotel.GsonReservation;
import edu.mu.hotel.ServiceRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonReservationTest {

    GsonReservation reservation = new GsonReservation();

    
    /*
     * pretty much just test all the getters and setters for this class
     */
    @Test
    public void testReservationIdGetterAndSetter() {
        int reservationId = 123;
        reservation.setReservationId(reservationId);
        assertEquals(reservationId, reservation.getReservationId());
    }

    @Test
    public void testCustomerIdGetterAndSetter() {
        int customerId = 456;
        reservation.setCustomerId(customerId);
        assertEquals(customerId, reservation.getCustomerId());
    }

    @Test
    public void testCheckInDateGetterAndSetter() {
        String checkInDate = "2024-05-01";
        reservation.setCheckInDate(checkInDate);
        assertEquals(checkInDate, reservation.getCheckInDate());
    }

    @Test
    public void testCheckOutDateGetterAndSetter() {
        String checkOutDate = "2024-05-10";
        reservation.setCheckOutDate(checkOutDate);
        assertEquals(checkOutDate, reservation.getCheckOutDate());
    }

    @Test
    public void testRoomTypeNameGetterAndSetter() {
        String roomTypeName = "Standard";
        reservation.setRoomTypeName(roomTypeName);
        assertEquals(roomTypeName, reservation.getRoomTypeName());
    }

    @Test
    public void testRoomBasePriceGetterAndSetter() {
        double roomBasePrice = 100.50;
        reservation.setRoomBasePrice(roomBasePrice);
        assertEquals(roomBasePrice, reservation.getRoomBasePrice(), 0.001);
    }

    @Test
    public void testRoomNumberGetterAndSetter() {
        int roomNumber = 101;
        reservation.setRoomNumber(roomNumber);
        assertEquals(roomNumber, reservation.getRoomNumber());
    }

    @Test
    public void testIsRoomOccupiedGetterAndSetter() {
        boolean isRoomOccupied = true;
        reservation.setRoomOccupied(isRoomOccupied);
        assertEquals(isRoomOccupied, reservation.isRoomOccupied());
    }

    @Test
    public void testAmenitiesGetterAndSetter() {
        Map<String, Boolean> amenities = new HashMap<>();
        amenities.put("WiFi", true);
        amenities.put("TV", false);
        reservation.setAmenities(amenities);
        assertEquals(amenities, reservation.getAmenities());
    }

    @Test
    public void testIsActiveGetterAndSetter() {
        boolean isActive = true;
        reservation.setActive(isActive);
        assertEquals(isActive, reservation.isActive());
    }

    @Test
    public void testAccessCodeGetterAndSetter() {
        String accessCode = "test-access-code";
        reservation.setAccessCode(accessCode);
        assertEquals(accessCode, reservation.getAccessCode());
    }

    @Test
    public void testIsKeyCardActiveGetterAndSetter() {
        boolean isKeyCardActive = true;
        reservation.setKeyCardActive(isKeyCardActive);
        assertEquals(isKeyCardActive, reservation.isKeyCardActive());
    }

    @Test
    public void testServiceRequestsGetterAndSetter() {
        List<ServiceRequest> serviceRequests = Arrays.asList(new ServiceRequest("test service request", 1, "test"));
        reservation.setServiceRequests(serviceRequests);
        assertEquals(serviceRequests, reservation.getServiceRequests());
    }
}
