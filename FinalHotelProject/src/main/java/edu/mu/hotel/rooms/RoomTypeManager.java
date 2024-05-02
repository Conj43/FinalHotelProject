package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mu.hotel.Reservation;

public class RoomTypeManager {
	
	
	private static RoomTypeManager instance = null; //part of singleton design patters
	private int totalRoomsAvailable;
	private int suiteAvailable;
	private int deluxeAvailable;
	private int standardAvailable;
	private int totalRooms = 0;

	
	private int totalStandardRooms;
	private int totalDeluxeRooms;
	private int totalSuiteRooms;
	
	
	
	private RoomTypeManager() {
		
	}

	
	
	public static RoomTypeManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new RoomTypeManager();
		}
		return instance; //return the instance
		
	}
	
	public void decreaseRoomAvailability(String roomType) {
        switch (roomType) {
            case "Suite":
                suiteAvailable--;
                totalRoomsAvailable--;
                break;
            case "Deluxe":
                deluxeAvailable--;
                totalRoomsAvailable--;
                break;
            case "Standard":
                standardAvailable--;
                totalRoomsAvailable--;
                break;
            default:
                break;
        }
    }
	
	public void increaseRoomAvailability(String roomType) {
        switch (roomType) {
            case "Suite":
                suiteAvailable++;
                totalRoomsAvailable++;
                break;
            case "Deluxe":
                deluxeAvailable++;
                totalRoomsAvailable++;
                break;
            case "Standard":
                standardAvailable++;
                totalRoomsAvailable++;
                break;
            default:
                break;
        }
    }
    
    public int getTotalRooms(String type) {
    	switch (type) {
    	case "Standard":
    		return totalStandardRooms;
		case "Deluxe":
    		return totalDeluxeRooms;
		case "Suite":
    		return totalSuiteRooms;
		default:
    		break;
    	}
    	return -1;
    }
    
    public int getTotalRoomsAvailable() {
    	return totalRoomsAvailable;
    }
    
    public int suiteAvailable() {
    	return suiteAvailable;
    }
    
    public int standardAvailable() {
    	return standardAvailable;
    }
    
    public int deluxeAvailable() {
    	return deluxeAvailable;
    }
    
    
    public RoomType getRoomTypeByName(String roomName) {
        // Implement logic to find RoomType by name and return it
        return null;
    }
    
    public String getRoomName(RoomType roomType) {
        return roomType.getTypeName();
    }


    
    
    
    
    
	

}
