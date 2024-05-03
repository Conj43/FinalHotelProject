package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mu.hotel.Reservation;
import edu.mu.hotel.ReservationManager;

public abstract class RoomType {
	
    protected String typeName;
    protected double basePrice;
    protected int roomNumber;
    protected boolean isOccupied;
    protected Map<String, Boolean> amenities;
    
    
    
    public RoomType() {
    	 
    }

    protected RoomType(String typeName,  double basePrice, int roomNumber, boolean isOccupied,  Map<String, Boolean> amenities) {
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
    
    public boolean isReserved(int roomNumber, String checkIn, String checkOut) {
    	
    	List<Reservation> reservations = ReservationManager.getInstance().getActiveReservations();
    	
    	if(!reservations.isEmpty()) {
        for (Reservation reservation : reservations) {
            if (reservation.isActive() &&
            	this.roomNumber == roomNumber &&
                reservation.getCheckOutDate().isAfter(LocalDate.parse(checkIn)) &&
                reservation.getCheckInDate().isBefore(LocalDate.parse(checkOut))) {
                return true; // Room is reserved for the requested time period
            }
        }
    	}
        return false; // Room is not reserved for the requested time period
    }


    
    
    

}

