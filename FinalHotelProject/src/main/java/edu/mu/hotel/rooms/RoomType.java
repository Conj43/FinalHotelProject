package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mu.billing.PricingManager;
import edu.mu.hotel.Reservation;
import edu.mu.hotel.ReservationManager;


/*
 * abstract class to help us make different room types
 */
public abstract class RoomType {
	
	/*
	 * attributes all rooms will have
	 */
    protected String typeName;
    protected double basePrice;
    protected int roomNumber;
    protected boolean isOccupied;
    protected Map<String, Boolean> amenities;
    
    
    /*
     * default cosntructor
     */
    public RoomType() {
    	 
    }

    /*
     * paramaterized constructor
     */
    protected RoomType(String typeName,  double basePrice, int roomNumber, boolean isOccupied,  Map<String, Boolean> amenities) {
    	this.amenities = new HashMap<>();
        this.typeName = typeName;
        this.basePrice = basePrice;
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.amenities = amenities;
    }

   
/*
 * abstract method to calculate cose
 * @param int num of days stayed
 * @param date of date checked in
 * @return double of total price of stay
 */
    public abstract double calculateCost(int days, LocalDate checkIn);




  /*
   * getters and setters
   */
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
    
    
    /*
     * method to check if a room is occupied during a given period of time
     * @param int room number
     * @param string of check in and check out date with correct format
     * @return boolean true if it is occupied during time range, false if it is not
     */
    public boolean isReserved(int roomNumber, String checkIn, String checkOut) {
        List<Reservation> reservations = ReservationManager.getInstance().getActiveReservations(); //get list of active reservations

        if (!reservations.isEmpty()) { //make sure list isnt empty
            for (Reservation reservation : reservations) { //go thru each reservation
            	if (reservation.isActive() && //check if room is active, room number is correct, and if the reservation falls in the given time period
            		    reservation.getRoom().getRoomNumber() == roomNumber &&
            		    (
            		        (reservation.getCheckInDate().isBefore(LocalDate.parse(checkOut)) && reservation.getCheckOutDate().isAfter(LocalDate.parse(checkIn))) ||
            		        (reservation.getCheckInDate().isBefore(LocalDate.parse(checkOut)) && reservation.getCheckOutDate().isAfter(LocalDate.parse(checkOut))) ||
            		        (reservation.getCheckInDate().isAfter(LocalDate.parse(checkIn)) && reservation.getCheckOutDate().isBefore(LocalDate.parse(checkOut)))
            		    )
            		) {
            		    
            		    return true; // means room is reserved for the requested time period
            		}

                }
            }
        
        
        return false; // if room is not occupied for given time priod
    }


    /*
     * method to get dynamic price 
     * @param date check in date
     * @return double which is dynamic price
     */
    public double getDynamicPrice(LocalDate checkInDate) {
        return PricingManager.calculatePrice(this, checkInDate); //uses pricing manager to chekc strategies for dynamic pricing
    }

    
    
    

}

