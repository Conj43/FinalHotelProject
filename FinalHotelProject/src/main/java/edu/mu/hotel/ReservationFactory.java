package edu.mu.hotel;

import java.time.LocalDate;

import edu.mu.hotel.rooms.*;


public class ReservationFactory {
	
	private int customerIdCounter = 1;
	

	public ReservationFactory() {
			}
	
	public Reservation createReservation(String roomType, LocalDate checkInDate, LocalDate checkOutDate, int customerID) {
		RoomType room;
		switch(roomType) {
		case "Standard":
			room = new StandardRoom();
			break;
		case "Deluxe":
			room = new DeluxeRoom();
			break;
		case "Suite":
			room = new SuiteRoom();
			break;
		default:
			return null;
		}
		
		Reservation reservation = new Reservation(customerID, roomType, checkInDate, checkOutDate);
		
		return reservation;
	}

}
