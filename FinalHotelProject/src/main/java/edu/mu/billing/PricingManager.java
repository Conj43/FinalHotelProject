package edu.mu.billing;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import edu.mu.hotel.rooms.RoomType;

public class PricingManager {
    private static Map<String, PricingStrategy> strategies = new HashMap<>();
    
    /*
     * 
     * adds strategies to Map
     */

    static {
    	
    	strategies.put("advanceBooking", new AdvanceBookingPricingStrategy());
    	strategies.put("seasonal", new SeasonalPricingStrategy());
        
    }

    
    /*
     * this goes through strategies and returns price using dynamic pricing
     * @param room type gives the base price
     * @param date allows us to see what time the reservation will occue
     * @return returns the dynamic price based on strategies
     * 
     */
    public static double calculatePrice(RoomType roomType, LocalDate date) {
        double price = roomType.getBasePrice(); 

        
        for (PricingStrategy strategy : strategies.values()) {
             price = strategy.calculatePrice(roomType, date);
           if(price!=roomType.getBasePrice()) {
        	   return price;
           }
            
        }
        
        return price;
    }
}
