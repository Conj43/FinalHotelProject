package edu.mu.hotel.rooms;

import java.util.ArrayList;
import java.util.List;



public class RoomTypeManager {
	
	
	
	private static RoomTypeManager instance = null; //this class uses singleton design pattern

	
	private int totalStandardRooms = 30; //our hotel has 30 standard rooms
	private int totalDeluxeRooms = 15; //15 deluxe rooms
	private int totalSuiteRooms = 8; //8 suite rooms
	private int totalConferenceRooms = 5;
	
	private int totalRooms = totalStandardRooms+totalDeluxeRooms+totalSuiteRooms; //total rooms is those all combined
	
	
	
	private int totalRoomsAvailable = totalRooms;
	
	private List<StandardRoom> standardRooms; //we use a list to hold each of the rooms 
	private List<DeluxeRoom> deluxeRooms;
	private List<SuiteRoom> suiteRooms;
	private List<ConferenceRoom> conferenceRooms;
	
	private List<RoomType> allRooms; //then we have a list with all roooms
	
	
	private RoomTypeManager() { //make our constructor private for singleton pattern
		
		standardRooms = new ArrayList<>(); //insitiante each list as an arraylist
		deluxeRooms = new ArrayList<>();
		suiteRooms = new ArrayList<>();
		conferenceRooms = new ArrayList<>();
		allRooms = new ArrayList<>();
		
		for(int i = 0; i<totalStandardRooms; i++) { //using a loop add all standard rooms to its corresponding list and to the all rooms list
			StandardRoom room = new StandardRoom();
			standardRooms.add(room);
			allRooms.add(room);
			
		}
		
		for(int i = 0; i<totalDeluxeRooms; i++) { //same for each type
			DeluxeRoom room = new DeluxeRoom();
			deluxeRooms.add(room);
			allRooms.add(room);
		}
		
		for(int i = 0; i<totalSuiteRooms; i++) {
			SuiteRoom room = new SuiteRoom();
			suiteRooms.add(room);
			allRooms.add(room);
		}
		
		for(int i = 0; i<totalConferenceRooms; i++) { //conference rooms can have their own list, dont need to be added to other rooms
			ConferenceRoom room = new ConferenceRoom();
			conferenceRooms.add(room);
			
			
		}

		
	}

	
	public static RoomTypeManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new RoomTypeManager();
		}
		return instance; //return the instance
	}
	
	public RoomType getRoomByRoomNum(int num) { //returns a room using the room number to identify it, each room has unique room number
		if(!allRooms.isEmpty()) { //make sure list is not empty
			for(RoomType room : allRooms) { //loop through all rooms
				if(room.getRoomNumber() == num) {
					return room; //return room when we find it
				}
			}
		}
		return null; //return null if not found or there is an error
	}
	
	
	public List<? extends RoomType> getRooms(String roomType){
		switch (roomType) {
		case "Standard":
			return standardRooms;
		case "Deluxe":
			return deluxeRooms;
		case "Suite":
			return suiteRooms;
		case "Conference":
			return conferenceRooms;
		default:
			return null;
			
		}
	}
	
	
	

	
	public RoomType findAvailableRooms(String roomType, String checkIn, String checkOut) {
	    

	    List<? extends RoomType> rooms; //creates list of wildcards that extend RoomType, so it can be whatever type we determine in the switch
	    switch (roomType) {
	        case "Standard":
	            rooms = standardRooms;
	            break;
	        case "Deluxe":
	            rooms = deluxeRooms;
	            break;
	        case "Suite":
	            rooms = suiteRooms;
	            break;
	        case "Conference":
	            rooms = conferenceRooms;
	            break;
	        default:
	            return null;
	    }

	    for (RoomType room : rooms) {
	    	
	        if (!room.isOccupied() && !room.isReserved(room.getRoomNumber(), checkIn,  checkOut)) {
	        	
	            return room;
	        }
	    }

	    return null; //no rooms available
	}


	
	public void increaseRoomAvailability(RoomType room) { //increases availability and sets room to unoccupied
		
		room.setOccupied(false); //used when someone checks out
		totalRoomsAvailable++;
    }
	
	public void decreaseRoomAvailability(RoomType room) { //increases availability and sets room to unoccupied
		
		room.setOccupied(true); //used when someone checks in
		totalRoomsAvailable--;
    }
    
    public int getTotalRooms(String type) {
    	switch (type) {
    	case "Standard":
    		return totalStandardRooms;
		case "Deluxe":
    		return totalDeluxeRooms;
		case "Suite":
    		return totalSuiteRooms;
		case "Conference":
    		return totalConferenceRooms;
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
