package edu.mu.hotel.rooms;

import java.util.ArrayList;
import java.util.List;



public class RoomTypeManager {
	
	
	
	private static RoomTypeManager instance = null;

	
	private int totalStandardRooms = 20;
	private int totalDeluxeRooms = 15;
	private int totalSuiteRooms = 3;
	
	private int totalRooms = totalStandardRooms+totalDeluxeRooms+totalSuiteRooms;
	
	
	
	private int totalRoomsAvailable = totalRooms;
	
	private List<StandardRoom> standardRooms;
	private List<DeluxeRoom> deluxeRooms;
	private List<SuiteRoom> suiteRooms;
	
	private List<RoomType> allRooms;
	
	
	private RoomTypeManager() {
		
		standardRooms = new ArrayList<>();
		deluxeRooms = new ArrayList<>();
		suiteRooms = new ArrayList<>();
		allRooms = new ArrayList<>();
		
		for(int i = 0; i<totalStandardRooms; i++) {
			StandardRoom room = new StandardRoom();
			standardRooms.add(room);
			allRooms.add(room);
			
		}
		
		for(int i = 0; i<totalDeluxeRooms; i++) {
			DeluxeRoom room = new DeluxeRoom();
			deluxeRooms.add(room);
			allRooms.add(room);
		}
		
		for(int i = 0; i<totalSuiteRooms; i++) {
			SuiteRoom room = new SuiteRoom();
			suiteRooms.add(room);
			allRooms.add(room);
		}

		
	}

	
	public static RoomTypeManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new RoomTypeManager();
		}
		return instance; //return the instance
	}
	
	public RoomType getRoomByRoomNum(int num) {
		if(!allRooms.isEmpty()) {
			for(RoomType room : allRooms) {
				if(room.getRoomNumber() == num) {
					return room;
				}
			}
		}
		return null;
	}
	

	
	public RoomType findAvailableRooms(String roomType) {
        switch (roomType) {
            case "Standard":
            	
                for(StandardRoom room : standardRooms) {
                	if(!room.isOccupied()) {
                		return room;
                	}
                }
                
                break;
            case "Deluxe":
            	for(DeluxeRoom room : deluxeRooms) {
                	if(!room.isOccupied()) {
                		return room;
                	}
                }
                break;
            case "Suite":
            	for(SuiteRoom room : suiteRooms) {
                	if(!room.isOccupied()) {
                		return room;
                	}
                }
                break;
            default:
                return null;
        }
        return null;
    }
	
	public void increaseRoomAvailability(RoomType room) {
		
		room.setOccupied(false);
		totalRoomsAvailable++;
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

    
    
    public RoomType getRoomTypeByName(String roomName) {
        // Implement logic to find RoomType by name and return it
        return null;
    }
    
    public String getRoomName(RoomType roomType) {
        return roomType.getTypeName();
    }


    
    
    
    
    
	

}
