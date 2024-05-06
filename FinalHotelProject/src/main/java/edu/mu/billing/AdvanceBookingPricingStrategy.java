package edu.mu.billing;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import edu.mu.hotel.rooms.RoomType;

/*
 * class is for the advance booking strategy pricing
 */
public class AdvanceBookingPricingStrategy implements PricingStrategy {
	

	/*
	 * This method calculates the price taking into account how far in advance it is booked
	 * @param RoomType is the type of room being booked
	 * @param date allows method to see how far in advance its booked
	 * @return returns base price or discounted price if it is eligible
	 * 
	 */
	
    public double calculatePrice(RoomType roomType, LocalDate date) {
        long daysInAdvance = ChronoUnit.DAYS.between(LocalDate.now(), date);
        if (daysInAdvance > 30) {
            return roomType.getBasePrice() * 0.9;  // 10% discount for early bookings (at leat 1 month early)
        }
        return roomType.getBasePrice();
    }
}
