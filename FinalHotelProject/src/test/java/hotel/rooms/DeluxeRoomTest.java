package hotel.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.hotel.rooms.DeluxeRoom;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DeluxeRoomTest {

    private DeluxeRoom deluxeRoom;
    /*
     * uses constructor to assign default values
     */
    @BeforeEach
    void setUp() {
        deluxeRoom = new DeluxeRoom();
    }

    /*
     * check to make sure each value was assigned correctly
     */
    @Test
    void testRoomType() {
        assertEquals("Deluxe Room", deluxeRoom.getTypeName(), "Room type should be 'Deluxe Room'");
    }

    @Test
    void testBaseRate() {
        assertEquals(125.0, deluxeRoom.getBasePrice(), "Base rate should be 125.0");
    }

    @Test
    void testRoomNumberIncrement() {
        DeluxeRoom anotherRoom = new DeluxeRoom();
        assertNotEquals(deluxeRoom.getRoomNumber(), anotherRoom.getRoomNumber(), "Room numbers should be unique and increment");
    }

    @Test
    void testAmenitiesContainsWifi() {
        assertTrue(deluxeRoom.getAmenities().containsKey("Wifi") && deluxeRoom.getAmenities().get("Wifi"), "Amenities should include Wifi");
    }

    @Test
    void testAmenitiesContainsTV() {
        assertTrue(deluxeRoom.getAmenities().containsKey("TV") && deluxeRoom.getAmenities().get("TV"), "Amenities should include TV");
    }

    @Test
    void testAmenitiesContainsBreakfast() {
        assertTrue(deluxeRoom.getAmenities().containsKey("Breakfast") && deluxeRoom.getAmenities().get("Breakfast"), "Amenities should include Breakfast");
    }

    @Test
    void testAmenitiesContainsJacuzzi() {
        assertTrue(deluxeRoom.getAmenities().containsKey("Jacuzzi") && deluxeRoom.getAmenities().get("Jacuzzi"), "Amenities should include Jacuzzi");
    }

    @Test
    void testCalculateCost() {
        double expectedCost = deluxeRoom.getBasePrice() * 3; // Assuming getDynamicPrice returns base rate
        assertEquals(expectedCost, deluxeRoom.calculateCost(3, LocalDate.now()), "Cost calculation should match expected for 3 days");
    }

    @Test
    void testToString() {
        String expectedString = "DeluxeRoom [typeName=Deluxe Room, basePrice=125.0, roomNumber=" + deluxeRoom.getRoomNumber() 
                                + ", isOccupied=false, amenities=" + deluxeRoom.getAmenities() + "]";
        assertEquals(expectedString, deluxeRoom.toString(), "ToString should return the correct string representation");
    }
}
