package edu.mu.billing;

import java.time.LocalDate;

import edu.mu.hotel.rooms.RoomType;


/*
 * interface to implement strategy design pattern for dynamic pricing
 * @param room type gives room type to determine price
 * @param date gives us the check in for reservation
 * @retrun double which is the dynamic price
 */
public interface PricingStrategy {
	double calculatePrice(RoomType roomType, LocalDate date);

}
