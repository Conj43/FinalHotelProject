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

    private RoomType(String typeName, int totalRooms, double basePrice) {
        this.typeName = typeName;
        this.totalRooms = totalRooms;
        this.roomsAvailable = totalRooms; //initially, all rooms are available.
        this.basePrice = basePrice;
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
}
