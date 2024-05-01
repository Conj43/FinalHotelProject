package edu.mu.hotel;



public class RoomTypeManager {
	
	
	private static RoomTypeManager instance = null; //part of singleton design patters
	private int roomsAvailable = 0;
	private int totalRooms = 0;

	public RoomTypeManager() {
		
	}

	
	
	public static RoomTypeManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new RoomTypeManager();
		}
		return instance; //return the instance
		
	}
	
    //adjust room availability
    public void decreaseRoomAvailability() {
        if (roomsAvailable > 0) {
            roomsAvailable--;
        }
    }
    
    public int getTotalRooms() {
    	return totalRooms;
    }
    
    
    public RoomType getRoomTypeByName(String roomName) {
    	return null;
    }
    
    
    public void addRoomType() {
    	
    }
    
    public void createReservation() {
    	
    }
    
    public void cancelReservation() {
    	
    }
    
    public void checkAvailability() {
    	
    }
    
    public void getReservationById() {
    	
    }
    
    
    
    
    
	

}
