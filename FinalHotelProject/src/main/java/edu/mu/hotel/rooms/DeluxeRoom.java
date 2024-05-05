package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DeluxeRoom extends RoomType{
	
	private static int roomNumberCounter = 200;

	public DeluxeRoom() {
		super("Deluxe Room",125.0, roomNumberCounter++, false, getDefaultAmenities());


}
	
	
	private static Map<String, Boolean> getDefaultAmenities() {
        Map<String, Boolean> defaultAmenities = new HashMap<>();
        defaultAmenities.put("Wifi", true);
        defaultAmenities.put("TV", true);
        defaultAmenities.put("Breakfast", true);
        defaultAmenities.put("Jacuzzi", true);
        return defaultAmenities;
    }
	

	@Override
	public double calculateCost(int days, LocalDate checkIn) {
		return getDynamicPrice(checkIn) * days;
	}


	@Override
	public String toString() {
		return "DeluxeRoom [typeName=" + typeName + ", basePrice=" + basePrice + ", roomNumber=" + roomNumber
				+ ", isOccupied=" + isOccupied + ", amenities=" + amenities + "]";
	}






	
	
	
	
}
