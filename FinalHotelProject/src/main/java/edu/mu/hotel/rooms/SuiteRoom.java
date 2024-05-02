package edu.mu.hotel.rooms;

import java.util.HashMap;
import java.util.Map;

public class SuiteRoom extends RoomType{


	public SuiteRoom() {
			super("Suite Room", 3, 150.0, getDefaultAmenities());
	
	
	}
		
		
		private static Map<String, Boolean> getDefaultAmenities() {
	        Map<String, Boolean> defaultAmenities = new HashMap<>();
	        defaultAmenities.put("Wifi", true);
	        defaultAmenities.put("TV", true);
	        defaultAmenities.put("Breakfast", true);
	        defaultAmenities.put("Jacuzzi", true);
	        defaultAmenities.put("Free Spa", true);
	        return defaultAmenities;
	    }
		
	
		@Override
		public double calculateCost(int days) {
			return this.basePrice * days;
		}


		@Override
		public String toString() {
			return "SuiteRoom [typeName=" + typeName + ", totalRooms=" + totalRooms + ", basePrice=" + basePrice
					+ ", amenities=" + amenities + "]";
		}



		

}