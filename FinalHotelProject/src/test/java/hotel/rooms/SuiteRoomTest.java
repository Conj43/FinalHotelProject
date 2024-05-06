package edu.mu.hotel.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SuiteRoomTest {

    private SuiteRoom suiteRoom;

    @BeforeEach
    void setUp() {
        suiteRoom = new SuiteRoom();
    }

    @Test
    void testRoomType() {
        assertEquals("Suite Room", suiteRoom.getTypeName(), "Room type should be 'Suite Room'");
    }

    @Test
    void testBaseRate() {
        assertEquals(150.0, suiteRoom.getBasePrice(), "Base rate should be 150.0");
    }

    @Test
    void testRoomNumberIncrement() {
        SuiteRoom anotherRoom = new SuiteRoom();
        assertNotEquals(suiteRoom.getRoomNumber(), anotherRoom.getRoomNumber(), "Room numbers should be unique and increment");
    }

    @Test
    void testAmenities() {
        Map<String, Boolean> amenities = suiteRoom.getAmenities();
        assertTrue(amenities.get("Wifi") && amenities.get("TV") && amenities.get("Breakfast") && amenities.get("Jacuzzi") && amenities.get("Free Spa"), 
                   "Suite room should include Wifi, TV, Breakfast, Jacuzzi, and Free Spa");
    }

    @Test
    void testCalculateCost() {
        double expectedCost = suiteRoom.getBasePrice() * 5; // Assuming getDynamicPrice returns base rate
        assertEquals(expectedCost, suiteRoom.calculateCost(5, LocalDate.now()), "Cost calculation should match expected for 5 days");
    }

    @Test
    void testToString() {
        String expectedDescription = "SuiteRoom [typeName=Suite Room, basePrice=150.0, roomNumber=" + suiteRoom.getRoomNumber()
                                    + ", isOccupied=false, amenities=" + suiteRoom.getAmenities() + "]";
        assertEquals(expectedDescription, suiteRoom.toString(), "ToString should return the correct string representation");
    }
}
