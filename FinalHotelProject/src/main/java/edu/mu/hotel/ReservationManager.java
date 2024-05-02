package edu.mu.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mu.hotel.rooms.RoomTypeManager;


public class ReservationManager {
	
	private static ReservationManager instance = null; //part of singleton design patters
	private static final List<Reservation> reservations = new ArrayList<>();

	private ReservationManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static ReservationManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new ReservationManager();
		}
		return instance; //return the instance
	}
	
	
	 //get all active reservations
    public static List<Reservation> getActiveReservations() {
        List<Reservation> activeReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.isActive()) {
                activeReservations.add(reservation);
            }
        }
        return activeReservations;
    }

    //get specific reservation by ID
    public static Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null; //if no reservation is found with the given ID
    }
    
    
    
    public static Reservation createReservation(int customerId, String roomType, LocalDate checkIn, LocalDate checkOut) {
        if (checkAvailability(roomType, checkIn, checkOut)) {
            // Assuming there is a method to reduce the room availability count
            RoomTypeManager.getInstance().decreaseRoomAvailability(roomType);
            return new Reservation(customerId, roomType, checkIn, checkOut);
        }
        return null; //return null if no availability
    }

    //static method to check room availability
    public static boolean checkAvailability(String roomType, LocalDate checkIn, LocalDate checkOut) {
        // Assuming there is a RoomTypeManager class to handle room types and their counts
        RoomTypeManager roomTypeManager = RoomTypeManager.getInstance();
        int totalRoomsOfType = roomTypeManager.getTotalRooms(roomType);

        //count how many rooms are booked for the given room type during the requested date range
        long bookedRooms = reservations.stream()
            .filter(reservation -> reservation.isActive() &&
                                  reservation.getRoomType().equals(roomType) &&
                                  reservation.getCheckOutDate().isAfter(checkIn) &&
                                  reservation.getCheckInDate().isBefore(checkOut))
            .count();

        //check if there are any rooms left
        return bookedRooms < totalRoomsOfType;
    }


    public static boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId && reservation.isActive()) {
                reservation.setActive(false);
                String roomType = reservation.getRoomType();
                RoomTypeManager roomTypeManager = RoomTypeManager.getInstance();
				roomTypeManager.increaseRoomAvailability(roomType);
                
                return true;
            }
        }
        return false;
    }

    //method to confirm reservation details
    public String confirmBooking(Reservation reservation) {
        if (!reservation.isActive()) {
            return "Reservation ID " + reservation.getReservationId() + " is cancelled.";
        }
        //display or return reservation details
        return "Reservation confirmed: ID " + reservation.getReservationId() +
                "\nCustomer ID: " + reservation.getCustomerId() +
                "\nRoom Type: " + reservation.getRoomType() +
                "\nCheck-In Date: " + reservation.getCheckInDate() +
                "\nCheck-Out Date: " + reservation.getCheckOutDate();
    }
    
}
