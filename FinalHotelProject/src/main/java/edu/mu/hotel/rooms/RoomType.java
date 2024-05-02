package edu.mu.hotel.rooms;

import java.util.HashMap;
import java.util.Map;

public abstract class RoomType {
	
    protected String typeName;
    protected double basePrice;
    protected int roomNumber;
    protected boolean isOccupied;
    protected Map<String, Boolean> amenities;
    
    
    
    public RoomType() {
    	 
    }

    protected RoomType(String typeName,  double basePrice, int roomNumber, boolean isOccupied, Map<String, Boolean> amenities) {
    	this.amenities = new HashMap<>();
        this.typeName = typeName;
        this.basePrice = basePrice;
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.amenities = amenities;
    }

   

    public abstract double calculateCost(int days);




  
    public void addAmenity(String amenity, boolean available) {
        amenities.put(amenity, available);
    }


    public String getTypeName() {
        return typeName;
    }




    public double getBasePrice() {
        return basePrice;
    }

    public Map<String, Boolean> getAmenities() {
        return amenities;
    }
    
    public boolean isOccupied() {
    	return isOccupied;
    }
    
    public void setOccupied(boolean status) {
    	this.isOccupied = status;
    }
    
    public int getRoomNumber() {
    	return roomNumber;
    }
    
    
    

}

