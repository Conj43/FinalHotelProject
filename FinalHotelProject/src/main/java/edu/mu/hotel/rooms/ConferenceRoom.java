package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ConferenceRoom extends RoomType{
	
	private static int roomNumberCounter = 400;

	
	
	
	public ConferenceRoom() {
		super("Conference Room",30.0, roomNumberCounter++, false, getDefaultAmenities());


}
	
	private static Map<String, Boolean> getDefaultAmenities() {
        Map<String, Boolean> defaultAmenities = new HashMap<>();
        defaultAmenities.put("Wifi", true);
        defaultAmenities.put("TV", true);
        
        return defaultAmenities;
    }

	@Override
	public double calculateCost(int days, LocalDate checkIn) {
		return getDynamicPrice(checkIn) * days;
	}

}
