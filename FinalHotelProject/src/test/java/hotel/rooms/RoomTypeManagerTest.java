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

    @Test
    void testSingletonInstance() {
        RoomTypeManager anotherInstance = RoomTypeManager.getInstance();
        assertSame(roomManager, anotherInstance, "There should be only one instance of RoomTypeManager");
    }

    @Test
    void testGetRoomByRoomNum() {
        // Assuming room numbers are set predictably in the constructor of the rooms
        RoomType room = roomManager.getRoomByRoomNum(101);
        assertNotNull(room, "Room should be found with room number 101");
        assertEquals(101, room.getRoomNumber(), "Room number should match the queried number");
    }

    @Test
    void testGetRooms() {
        assertNotNull(roomManager.getRooms("Standard"), "Should return list of standard rooms");
        assertTrue(roomManager.getRooms("Standard").size() > 0, "Should have standard rooms available");
    }

    @Test
    void testFindAvailableRooms() {
        RoomType availableRoom = roomManager.findAvailableRooms("Standard", "2024-01-01", "2024-01-05");
        assertNotNull(availableRoom, "Should find an available standard room");
        assertFalse(availableRoom.isOccupied(), "Found room should not be occupied");
    }

    @Test
    void testIncreaseRoomAvailability() {
        RoomType room = roomManager.getRoomByRoomNum(101);
        roomManager.decreaseRoomAvailability(room); // Simulate room check-in
        roomManager.increaseRoomAvailability(room); // Simulate room check-out
        assertFalse(room.isOccupied(), "Room should be marked as unoccupied after check-out");
    }

    @Test
    void testDecreaseRoomAvailability() {
        RoomType room = roomManager.getRoomByRoomNum(102);
        roomManager.decreaseRoomAvailability(room); // Simulate room check-in
        assertTrue(room.isOccupied(), "Room should be marked as occupied after check-in");
    }

    @Test
    void testGetTotalRooms() {
        assertEquals(30, roomManager.getTotalRooms("Standard"), "Should return the correct total number of standard rooms");
    }

    @Test
    void testGetTotalRoomsAvailable() {
        int initialAvailable = roomManager.getTotalRoomsAvailable();
        RoomType room = roomManager.getRoomByRoomNum(103);
        roomManager.decreaseRoomAvailability(room);
        assertEquals(initialAvailable - 1, roomManager.getTotalRoomsAvailable(), "Total available rooms should decrease after a check-in");
    }
}
