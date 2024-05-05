package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mu.billing.PricingManager;
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

   

    public abstract double calculateCost(int days, LocalDate checkIn);




  
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

        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
            	if (reservation.isActive() &&
            		    reservation.getRoom().getRoomNumber() == roomNumber &&
            		    (
            		        // Check if the requested check-in date falls within the existing reservation period
            		        (reservation.getCheckInDate().isBefore(LocalDate.parse(checkOut)) && reservation.getCheckOutDate().isAfter(LocalDate.parse(checkIn))) ||
            		        // Check if the requested check-out date falls within the existing reservation period
            		        (reservation.getCheckInDate().isBefore(LocalDate.parse(checkOut)) && reservation.getCheckOutDate().isAfter(LocalDate.parse(checkOut))) ||
            		        // Check if the existing reservation completely encompasses the requested time period
            		        (reservation.getCheckInDate().isAfter(LocalDate.parse(checkIn)) && reservation.getCheckOutDate().isBefore(LocalDate.parse(checkOut)))
            		    )
            		) {
            		    
            		    return true; // Room is reserved for the requested time period
            		}

                }
            }
        
        
        return false; // Room is not reserved for the requested time period
    }


    
    public double getDynamicPrice(LocalDate checkInDate) {
        return PricingManager.calculatePrice(this, checkInDate);
    }

    
    
    

}

