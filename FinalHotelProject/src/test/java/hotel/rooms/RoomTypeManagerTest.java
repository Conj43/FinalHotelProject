package hotel.rooms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.mu.hotel.rooms.RoomType;
import edu.mu.hotel.rooms.RoomTypeManager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoomTypeManagerTest {

    private static RoomTypeManager roomManager;

    @BeforeAll
    static void setUp() {
        roomManager = RoomTypeManager.getInstance();
    }

    /*
     * tests singleton design pattern
     */
    @Test
    void testSingletonInstance() {
        RoomTypeManager anotherInstance = RoomTypeManager.getInstance();
        assertSame(roomManager, anotherInstance, "There should be only one instance of RoomTypeManager");
    }

    /*
     * test if gey by room number works correctly
     */
    @Test
    void testGetRoomByRoomNum() {
        RoomType room = roomManager.getRoomByRoomNum(101);
        assertNotNull(room, "Room should be found with room number 101");
        assertEquals(101, room.getRoomNumber(), "Room number should match the queried number");
    }

    /*
     * tests get rooms, should return correct corresponding list
     */
    @Test
    void testGetRooms() {
        assertNotNull(roomManager.getRooms("Standard"), "Should return list of standard rooms");
        assertTrue(roomManager.getRooms("Standard").size() > 0, "Should have standard rooms available");
    }

    /*
     * make sure available rooms is correct
     */
    @Test
    void testFindAvailableRooms() {
        RoomType availableRoom = roomManager.findAvailableRooms("Standard", "2024-01-01", "2024-01-05");
        assertNotNull(availableRoom, "Should find an available standard room");
        assertFalse(availableRoom.isOccupied(), "Found room should not be occupied");
    }

    /*
     * make sure increases room availability
     */
    @Test
    void testIncreaseRoomAvailability() {
        RoomType room = roomManager.getRoomByRoomNum(101);
        roomManager.decreaseRoomAvailability(room); //  room check-in
        roomManager.increaseRoomAvailability(room); //  room check-out
        assertFalse(room.isOccupied(), "Room should be marked as unoccupied after check-out");
    }

    /*
     * makes sure decreases room availability
     */
    @Test
    void testDecreaseRoomAvailability() {
        RoomType room = roomManager.getRoomByRoomNum(102);
        roomManager.decreaseRoomAvailability(room); // Simulate room check-in
        assertTrue(room.isOccupied(), "Room should be marked as occupied after check-in");
    }

    /*
     * sees if it gets total rooms
     */
    @Test
    void testGetTotalRooms() {
        assertEquals(30, roomManager.getTotalRooms("Standard"), "Should return the correct total number of standard rooms");
    }

    /*
     * tests total rooms available
     */
    @Test
    void testGetTotalRoomsAvailable() {
        int initialAvailable = roomManager.getTotalRoomsAvailable();
        RoomType room = roomManager.getRoomByRoomNum(103);
        roomManager.decreaseRoomAvailability(room);
        assertEquals(initialAvailable - 1, roomManager.getTotalRoomsAvailable(), "Total available rooms should decrease after a check-in");
    }
}
