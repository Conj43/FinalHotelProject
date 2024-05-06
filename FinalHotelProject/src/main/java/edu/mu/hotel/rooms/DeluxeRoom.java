package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DeluxeRoom extends RoomType{
	/*
	 * start deluxe rooms at 200
	 */
	private static int roomNumberCounter = 200;

	
	/*
	 * use super for the constructor
	 * @param string of name of room
	 * @param doublebase price of room
	 * @param room number plus increment it to keep them unique
	 * @param boolean set is occupied to false at initalization
	 * @param map of amenities
	 */
	public DeluxeRoom() {
		super("Deluxe Room",125.0, roomNumberCounter++, false, getDefaultAmenities());


}
	
	/*
	 * defines default amenities for conference rooms
	 * @return map of default amenities
	 */
	private static Map<String, Boolean> getDefaultAmenities() {
        Map<String, Boolean> defaultAmenities = new HashMap<>();
        defaultAmenities.put("Wifi", true);
        defaultAmenities.put("TV", true);
        defaultAmenities.put("Breakfast", true);
        defaultAmenities.put("Jacuzzi", true);
        return defaultAmenities;
    }
	
	
	/*
	 * abstract method to is used to calculate price
	 * @param int number of days stayed
	 * @param date of check in
	 * @return double value of total cost
	 */
	@Override
	public double calculateCost(int days, LocalDate checkIn) {
		return getDynamicPrice(checkIn) * days;
	}


	/*
	 * to string method to get info of room
	 * @return string with all info of the room
	 */
	@Override
	public String toString() {
		return "DeluxeRoom [typeName=" + typeName + ", basePrice=" + basePrice + ", roomNumber=" + roomNumber
				+ ", isOccupied=" + isOccupied + ", amenities=" + amenities + "]";
	}






	
	
	
	
}
