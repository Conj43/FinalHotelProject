package hotel.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.hotel.rooms.SuiteRoom;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SuiteRoomTest {

    private SuiteRoom suiteRoom;

    /*
     * create new suite room
     */
    @BeforeEach
    void setUp() {
        suiteRoom = new SuiteRoom();
    }

    /*
     * create get room type
     */
    @Test
    void testRoomType() {
        assertEquals("Suite Room", suiteRoom.getTypeName(), "Room type should be 'Suite Room'");
    }

    /*
     * test get base rate
     */
    @Test
    void testBaseRate() {
        assertEquals(150.0, suiteRoom.getBasePrice(), "Base rate should be 150.0");
    }

    /*
     * test uniqueness of room number
     */
    @Test
    void testRoomNumberIncrement() {
        SuiteRoom anotherRoom = new SuiteRoom();
        assertNotEquals(suiteRoom.getRoomNumber(), anotherRoom.getRoomNumber(), "Room numbers should be unique and increment");
    }

    /*
     * test get amenities
     */
    @Test
    void testAmenities() {
        Map<String, Boolean> amenities = suiteRoom.getAmenities();
        assertTrue(amenities.get("Wifi") && amenities.get("TV") && amenities.get("Breakfast") && amenities.get("Jacuzzi") && amenities.get("Free Spa"), 
                   "Suite room should include Wifi, TV, Breakfast, Jacuzzi, and Free Spa");
    }

    /*
     * test get calculated cost
     */
    @Test
    void testCalculateCost() {
        double expectedCost = suiteRoom.getBasePrice() * 5; // Assuming getDynamicPrice returns base rate
        assertEquals(expectedCost, suiteRoom.calculateCost(5, LocalDate.now()), "Cost calculation should match expected for 5 days");
    }

    /*
     * test to string method
     */
    @Test
    void testToString() {
        String expectedDescription = "SuiteRoom [typeName=Suite Room, basePrice=150.0, roomNumber=" + suiteRoom.getRoomNumber()
                                    + ", isOccupied=false, amenities=" + suiteRoom.getAmenities() + "]";
        assertEquals(expectedDescription, suiteRoom.toString(), "ToString should return the correct string representation");
    }
}
