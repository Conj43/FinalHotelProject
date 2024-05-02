package edu.mu.hotel.rooms;

import java.util.HashMap;
import java.util.Map;

public class DeluxeRoom extends RoomType{

	public DeluxeRoom() {
		super("Deluxe Room", 10, 125.0, getDefaultAmenities());


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
	public double calculateCost(int days) {
		return this.basePrice * days;
	}


	@Override
	public String toString() {
		return "DeluxeRoom [typeName=" + typeName + ", totalRooms=" + totalRooms + ", basePrice=" + basePrice
				+ ", amenities=" + amenities + "]";
	}



	
	
	
	
}
