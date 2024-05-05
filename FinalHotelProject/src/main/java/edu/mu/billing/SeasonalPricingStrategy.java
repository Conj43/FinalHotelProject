package edu.mu.billing;

import java.time.LocalDate;
import java.time.Month;

import edu.mu.hotel.rooms.RoomType;

public class SeasonalPricingStrategy implements PricingStrategy {
	
	
    public double calculatePrice(RoomType roomType, LocalDate date) {
        if (isPeakSeason(date)) {
            return roomType.getBasePrice() * 1.2;  // 20% increase during peak season
        }
        return roomType.getBasePrice();
    }

    private boolean isPeakSeason(LocalDate date) {
       
        return date.getMonth() == Month.JUNE || date.getMonth() == Month.JULY
                || date.getMonth() == Month.DECEMBER;
    }
}

