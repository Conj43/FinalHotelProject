package edu.mu.hotel.rooms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StandardRoom extends RoomType{

	
	private static int roomNumberCounter = 100;
	
	public StandardRoom() {
		super("Standard Room", 100.0, roomNumberCounter++, false, getDefaultAmenities());


}
	
	
	private static Map<String, Boolean> getDefaultAmenities() {
        Map<String, Boolean> defaultAmenities = new HashMap<>();
        defaultAmenities.put("Wifi", true);
        defaultAmenities.put("TV", true);
        defaultAmenities.put("Breakfast", true);
        return defaultAmenities;
    }
	

	@Override
	public double calculateCost(int days, LocalDate checkIn) {
		return getDynamicPrice(checkIn) * days;
	}


	@Override
	public String toString() {
		return "StandardRoom [typeName=" + typeName + ", basePrice=" + basePrice + ", roomNumber=" + roomNumber
				+ ", isOccupied=" + isOccupied + ", amenities=" + amenities + "]";
	}


	



	
	
	

}
