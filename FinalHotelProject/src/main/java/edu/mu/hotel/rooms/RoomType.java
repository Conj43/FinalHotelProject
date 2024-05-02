package edu.mu.hotel.rooms;

import java.util.HashMap;
import java.util.Map;

public abstract class RoomType {
    protected String typeName;
    protected int totalRooms;
    protected double basePrice;
    protected Map<String, Boolean> amenities;
    
    public RoomType() {
    	 this.amenities = new HashMap<>();
    }

    protected RoomType(String typeName, int totalRooms, double basePrice, Map<String, Boolean> amenities) {
    	this();
        this.typeName = typeName;
        this.totalRooms = totalRooms;
        this.basePrice = basePrice;
        this.amenities = amenities;
    }

   

    public abstract double calculateCost(int days);




  
    public void addAmenity(String amenity, boolean available) {
        amenities.put(amenity, available);
    }


    public String getTypeName() {
        return typeName;
    }

    public int getTotalRooms() {
        return totalRooms;
    }



    public double getBasePrice() {
        return basePrice;
    }

    public Map<String, Boolean> getAmenities() {
        return amenities;
    }
    
    
    

}

