package hotel.rooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.mu.hotel.rooms.RoomType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class RoomTypeTest {

    private RoomType roomType;

    /*
     * make a sample room type, basically just checks standard room
     */
    @BeforeEach
    void setUp() {
        roomType = new ConcreteRoomType("Standard Room", 100.0, 101, false, new HashMap<>());
    }

    /*
     * many just test getters and setters
     */
    @Test
    void testAddAmenity() {
        roomType.addAmenity("Pool", true);
        assertTrue(roomType.getAmenities().containsKey("Pool") && roomType.getAmenities().get("Pool"), "Amenity 'Pool' should be added with value true");
    }

    @Test
    void testTypeName() {
        assertEquals("Standard Room", roomType.getTypeName(), "Type name should match 'Standard Room'");
    }

    @Test
    void testBasePrice() {
        assertEquals(100.0, roomType.getBasePrice(), "Base price should be 100.0");
    }

    /*
     * tests occupised status
     */
    @Test
    void testOccupiedStatus() {
        assertFalse(roomType.isOccupied(), "Initially, room should not be occupied");
        roomType.setOccupied(true);
        assertTrue(roomType.isOccupied(), "Room should be occupied after setting to true");
    }

    @Test
    void testRoomNumber() {
        assertEquals(101, roomType.getRoomNumber(), "Room number should be 101");
    }

    @Test
    void testIsReserved() {
       
        assertFalse(roomType.isReserved(101, "2024-01-01", "2024-01-05"), "Room should not be reserved for given dates");
    }

    /*
     * test dynamic pricing
     */
    @Test
    void testGetDynamicPrice() {
        
        assertEquals(100.0, roomType.getDynamicPrice(LocalDate.now()), "Dynamic price should be the base price");
    }

    // implement a concrete subclass for testing purposes
    private static class ConcreteRoomType extends RoomType {
        public ConcreteRoomType(String typeName, double basePrice, int roomNumber, boolean isOccupied, Map<String, Boolean> amenities) {
            super(typeName, basePrice, roomNumber, isOccupied, amenities);
        }

        /*
         * test abstract calculate cost method
         */
        @Override
        public double calculateCost(int days, LocalDate checkIn) {
            return getDynamicPrice(checkIn) * days;
        }
    }
}
