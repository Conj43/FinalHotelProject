package hotel.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.hotel.rooms.StandardRoom;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StandardRoomTest {

    private StandardRoom standardRoom;

    /*
     * create new standard room
     */
    @BeforeEach
    void setUp() {
        standardRoom = new StandardRoom();
    }

    /*
     * test getter for room type
     */
    @Test
    void testRoomType() {
        assertEquals("Standard Room", standardRoom.getTypeName(), "Room type should be 'Standard Room'");
    }

    /*
     * test getter for room type
     */
    @Test
    void testBaseRate() {
        assertEquals(100.0, standardRoom.getBasePrice(), "Base rate should be 100.0");
    }

    /*
     * test room number uniqueness
     */
    @Test
    void testRoomNumberIncrement() {
        StandardRoom anotherRoom = new StandardRoom();
        assertNotEquals(standardRoom.getRoomNumber(), anotherRoom.getRoomNumber(), "Room numbers should be unique and increment");
    }

    /*
     * test get amenities
     */
    @Test
    void testAmenities() {
        Map<String, Boolean> amenities = standardRoom.getAmenities();
        assertTrue(amenities.get("Wifi") && amenities.get("TV") && amenities.get("Breakfast"), "Standard room should include Wifi, TV, and Breakfast");
    }

    /*
     * test calcualting cost
     */
    @Test
    void testCalculateCost() {
        double expectedCost = standardRoom.getBasePrice() * 3; // Assuming getDynamicPrice returns base rate
        assertEquals(expectedCost, standardRoom.calculateCost(3, LocalDate.now()), "Cost calculation should match expected for 3 days");
    }

    /*
     * test to make sure to string returns correct string
     */
    @Test
    void testToString() {
        String expectedDescription = "StandardRoom [typeName=Standard Room, basePrice=100.0, roomNumber=" + standardRoom.getRoomNumber()
                                    + ", isOccupied=false, amenities=" + standardRoom.getAmenities() + "]";
        assertEquals(expectedDescription, standardRoom.toString(), "ToString should return the correct string representation");
    }
}
