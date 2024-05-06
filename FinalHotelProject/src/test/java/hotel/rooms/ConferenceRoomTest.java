package edu.mu.hotel.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ConferenceRoomTest {

    private ConferenceRoom conferenceRoom;

    @BeforeEach
    void setUp() {
        conferenceRoom = new ConferenceRoom();
    }

    @Test
    void testRoomType() {
        assertEquals("Conference Room", conferenceRoom.getRoomType(), "Room type should be 'Conference Room'");
    }

    @Test
    void testBaseRate() {
        assertEquals(30.0, conferenceRoom.getBaseRate(), "Base rate should be 30.0");
    }

    @Test
    void testRoomNumberIncrement() {
        ConferenceRoom anotherRoom = new ConferenceRoom();
        assertNotEquals(conferenceRoom.getRoomNumber(), anotherRoom.getRoomNumber(), "Room numbers should be unique and increment");
    }

    @Test
    void testAmenitiesContainsWifi() {
        assertTrue(conferenceRoom.getAmenities().containsKey("Wifi") && conferenceRoom.getAmenities().get("Wifi"), "Amenities should include Wifi");
    }

    @Test
    void testAmenitiesContainsTV() {
        assertTrue(conferenceRoom.getAmenities().containsKey("TV") && conferenceRoom.getAmenities().get("TV"), "Amenities should include TV");
    }

    @Test
    void testCalculateCost() {
        double expectedCost = conferenceRoom.getBaseRate() * 5; // Assuming getDynamicPrice returns base rate
        assertEquals(expectedCost, conferenceRoom.calculateCost(5, LocalDate.now()), "Cost calculation should match expected for 5 days");
    }
}
