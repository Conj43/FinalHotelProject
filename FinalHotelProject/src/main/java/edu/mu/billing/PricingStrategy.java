package edu.mu.billing;

import java.time.LocalDate;

import edu.mu.hotel.rooms.RoomType;

public interface PricingStrategy {
	double calculatePrice(RoomType roomType, LocalDate date);

}
