package edu.mu.hotel;

import java.util.HashMap;
import java.util.Map;

public class RoomType {
    private static final Map<String, RoomType> roomTypes = new HashMap<>();
    private String typeName;
    private int totalRooms;
    private int roomsAvailable;
    private double basePrice;
    private Map<String, Boolean> amenities;
    private String roomCategory; // "hotelRoom" or "conferenceRoom"

    

    public RoomType(String typeName, int totalRooms, double basePrice, String roomCategory) {
        this.typeName = typeName;
        this.totalRooms = totalRooms;
        this.roomsAvailable = totalRooms;
        this.basePrice = basePrice;
        this.roomCategory = roomCategory;
        this.amenities = new HashMap<>();
    }
   
   
    
    public static synchronized RoomType createRoomType(String typeName, int totalRooms, double basePrice) {
        RoomType roomType = roomTypes.get(typeName);
        if (roomType == null) {
            roomType = new RoomType(typeName, totalRooms, basePrice);
            roomTypes.put(typeName, roomType);
            
        }
        return roomType;
    }

    //get a room type by name
    public static RoomType getRoomTypeByName(String name) {
        return roomTypes.get(name);
    }

    //adjust room availability
    public void decreaseRoomAvailability() {
        if (roomsAvailable > 0) {
            roomsAvailable--;
        }
    }

    public void increaseRoomAvailability() {
        if (roomsAvailable < totalRooms) {
            roomsAvailable++;
        }
    }

  
    public void addAmenity(String amenity, boolean available) {
        amenities.put(amenity, available);
    }

    //Getters and Setters
    public String getTypeName() {
        return typeName;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Map<String, Boolean> getAmenities() {
        return amenities;
    }
    
    public String getRoomCategory() {
        return roomCategory;
    }
    
    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "typeName='" + typeName + '\'' +
                ", totalRooms=" + totalRooms +
                ", roomsAvailable=" + roomsAvailable +
                ", basePrice=" + basePrice +
                ", amenities=" + amenities +
                '}';
    }
    
    public double getDynamicPrice(LocalDate checkInDate) {
        return PricingManager.calculatePrice(this, checkInDate);
    }
    
    public interface PricingStrategy {
        double calculatePrice(RoomType roomType, LocalDate date);
    }
    
    
    
}
