package edu.mu.billing;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import edu.mu.hotel.rooms.RoomType;

public class AdvanceBookingPricingStrategy implements PricingStrategy {
	

    public double calculatePrice(RoomType roomType, LocalDate date) {
        long daysInAdvance = ChronoUnit.DAYS.between(LocalDate.now(), date);
        if (daysInAdvance > 30) {
            return roomType.getBasePrice() * 0.9;  // 10% discount for early bookings
        }
        return roomType.getBasePrice();
    }
}
