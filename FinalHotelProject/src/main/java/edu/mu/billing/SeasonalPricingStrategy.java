package edu.mu.billing;

import java.time.LocalDate;
import java.time.Month;

import edu.mu.hotel.rooms.RoomType;


/*
 * class for seasonal pricing strategy
 */
public class SeasonalPricingStrategy implements PricingStrategy {
	
	
	/*
     * this method uses seasonal pricing strategy to give dynamic price
     * @param room type allows us to get base room price
     * @param date allows us to determine dynamic price
     * @return returns double which is price
     * 
     */
	
    public double calculatePrice(RoomType roomType, LocalDate date) {
        if (isPeakSeason(date)) {
            return roomType.getBasePrice() * 1.2;  // 20% increase during peak season 
        }
        return roomType.getBasePrice(); //else return base price
    }

    
    /*
     * checks if reservation is during peak season
     * @param date the reservation starts
     * @return true or false if its in peak season or not
     * 
     */
    private boolean isPeakSeason(LocalDate date) {
       
        return date.getMonth() == Month.JUNE || date.getMonth() == Month.JULY //we say peak season is main summer months and december
                || date.getMonth() == Month.DECEMBER;
    }
}

