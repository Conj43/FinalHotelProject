package hotel;

import edu.mu.hotel.*;
import edu.mu.hotel.rooms.RoomType;
import edu.mu.hotel.rooms.StandardRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    private RoomType roomType;
    private List<ServiceRequest> serviceRequests;

    @BeforeEach
    void setUp() {
        roomType = mock(StandardRoom.class); // Mocking RoomType since it's abstract
        when(roomType.getTypeName()).thenReturn("Standard Room");

        serviceRequests = new ArrayList<>();
        serviceRequests.add(new ServiceRequest("Extra Towels", 5, "Want more towels"));

        reservation = new Reservation(123, "2024-01-01", "2024-01-05", roomType, serviceRequests);
    }

    @Test
    void testReservationInitialization() {
        assertEquals(123, reservation.getCustomerId());
        assertEquals(LocalDate.of(2024, 1, 1), reservation.getCheckInDate());
        assertEquals(LocalDate.of(2024, 1, 5), reservation.getCheckOutDate());
        assertTrue(reservation.isActive());
        assertEquals(roomType, reservation.getRoom());
        assertEquals("Standard Room", reservation.getRoomType());
        assertEquals("Guest has not checked in yet.", reservation.getAccessCode());
        assertFalse(reservation.isKeyCardActive());
        assertEquals(serviceRequests, reservation.getServiceRequests());
    }

    @Test
    void testSettersAndGetters() {
        reservation.setReservationId(999);
        reservation.setCustomerId(321);
        reservation.setCheckInDate("2024-02-01");
        reservation.setCheckOutDate("2024-02-05");
        reservation.setActive(false);
        reservation.setKeyCardActive(true);
        reservation.setAccessCode("XYZ123");

        ServiceRequest newServiceRequest = new ServiceRequest("Room Cleaning", 2, "Would like extra room cleaning");
        List<ServiceRequest> newServiceRequests = new ArrayList<>();
        newServiceRequests.add(newServiceRequest);
        reservation.setServiceRequests(newServiceRequests);

        assertEquals(999, reservation.getReservationId());
        assertEquals(321, reservation.getCustomerId());
        assertEquals(LocalDate.of(2024, 2, 1), reservation.getCheckInDate());
        assertEquals(LocalDate.of(2024, 2, 5), reservation.getCheckOutDate());
        assertFalse(reservation.isActive());
        assertTrue(reservation.isKeyCardActive());
        assertEquals("XYZ123", reservation.getAccessCode());
        assertEquals(newServiceRequests, reservation.getServiceRequests());
    }

    @Test
    void testAddServiceRequest() {
        ServiceRequest newRequest = new ServiceRequest("Room Cleaning", 3, "Would like extra room cleaning");
        reservation.addServiceRequest(newRequest);
        assertTrue(reservation.getServiceRequests().contains(newRequest));
    }

    @Test
    void testToString() {
        String expectedString = "Reservation [reservationId=0, customerId=123, checkInDate=2024-01-01, checkOutDate=2024-01-05,\nroom=Standard Room, \nisActive=true, "
        		+ "accessCode=Guest has not checked in yet., isKeyCardActive=false, serviceRequests=[ServiceRequest [requestType=Extra Towels, quantity=5, notes=Want more towels]]]";
        assertEquals(expectedString, reservation.toString().replace(reservation.getRoom().toString(), reservation.getRoomType()));
    }
}
