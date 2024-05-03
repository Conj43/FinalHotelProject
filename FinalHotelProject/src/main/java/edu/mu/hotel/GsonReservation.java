package edu.mu.hotel;

import java.util.Map;



//this class was made because gson does not work well with abstract classes, and we are using an abstract class for the room types
//all it does it create a different version of a reservation so we can seamlessly store all our reservation information into a json file using gson

public class GsonReservation {
	
	private int reservationId;
    private int customerId;
    private String checkInDate;
    private String checkOutDate;
    private String roomTypeName;
    private double roomBasePrice;
    private int roomNumber;
    private boolean isRoomOccupied;
    private Map<String, Boolean> amenities;
    private boolean isActive;
	
	

	public GsonReservation() {
		
	}



	public GsonReservation(int reservationId, int customerId, String checkInDate, String checkOutDate,
			String roomTypeName, double roomBasePrice, int roomNumber, boolean isRoomOccupied,
			Map<String, Boolean> amenities, boolean isActive) {
		super();
		this.reservationId = reservationId;
		this.customerId = customerId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomTypeName = roomTypeName;
		this.roomBasePrice = roomBasePrice;
		this.roomNumber = roomNumber;
		this.isRoomOccupied = isRoomOccupied;
		this.amenities = amenities;
		this.isActive = isActive;
	}



	public int getReservationId() {
		return reservationId;
	}



	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}



	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}



	public String getCheckOutDate() {
		return checkOutDate;
	}



	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



	public String getRoomTypeName() {
		return roomTypeName;
	}



	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}



	public double getRoomBasePrice() {
		return roomBasePrice;
	}



	public void setRoomBasePrice(double roomBasePrice) {
		this.roomBasePrice = roomBasePrice;
	}



	public int getRoomNumber() {
		return roomNumber;
	}



	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}



	public boolean isRoomOccupied() {
		return isRoomOccupied;
	}



	public void setRoomOccupied(boolean isRoomOccupied) {
		this.isRoomOccupied = isRoomOccupied;
	}



	public Map<String, Boolean> getAmenities() {
		return amenities;
	}



	public void setAmenities(Map<String, Boolean> amenities) {
		this.amenities = amenities;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

	
	
	
	
	
	

}