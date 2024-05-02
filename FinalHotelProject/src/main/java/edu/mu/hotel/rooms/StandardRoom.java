package edu.mu.hotel.rooms;

import java.util.HashMap;
import java.util.Map;

public class StandardRoom extends RoomType{

	
	
	
	public StandardRoom() {
		super("Standard Room", 20, 100.0, getDefaultAmenities());


}
	
	
	private static Map<String, Boolean> getDefaultAmenities() {
        Map<String, Boolean> defaultAmenities = new HashMap<>();
        defaultAmenities.put("Wifi", true);
        defaultAmenities.put("TV", true);
        defaultAmenities.put("Breakfast", true);
        return defaultAmenities;
    }
	

	@Override
	public double calculateCost(int days) {
		return this.basePrice * days;
	}


	@Override
	public String toString() {
		return "StandardRoom [typeName=" + typeName + ", totalRooms=" + totalRooms + ", basePrice=" + basePrice
				+ ", amenities=" + amenities + "]";
	}



	
	
	

}
