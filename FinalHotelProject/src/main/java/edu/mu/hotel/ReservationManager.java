package edu.mu.hotel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mu.hotel.rooms.RoomType;
import edu.mu.hotel.rooms.RoomTypeManager;


public class ReservationManager {
	
	private static ReservationManager instance = null; //part of singleton design patters
	private Map<Integer, Reservation> reservations; 
	private Gson gson; //use gson to store data to json file
	private final String filePath = "data/ReservationDatabase.json";
	private int lastReservationId;

	private ReservationManager() {
		reservations = new HashMap<>(); // Initialize the reservations map
        gson = new GsonBuilder().setPrettyPrinting().create(); // Initialize Gson
        loadDatabase(); // Load data from file
	}
	
	public static ReservationManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new ReservationManager();
		}
		return instance; //return the instance
	}
	
	
	 //get all active reservations
    public List<Reservation> getActiveReservations() {
        List<Reservation> activeReservations = new ArrayList<>();
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
        	Reservation reservation = entry.getValue();
            if (reservation.isActive()) {
                activeReservations.add(reservation);
            }
        }
        return activeReservations;
    }

    //get specific reservation by ID
    public Reservation getReservationById(int reservationId) {
        return reservations.get(reservationId);
    }
    
    
    
    public Reservation createReservation(int customerId, String roomType, String checkIn, String checkOut) {
        if (checkAvailability(roomType, checkIn, checkOut)) {
            // Assuming there is a method to reduce the room availability count
            RoomType room = RoomTypeManager.getInstance().findAvailableRooms(roomType);
            Reservation reservation = new Reservation(customerId, checkIn, checkOut, room);
            addReservationToDatabase(reservation);
            return reservation;
        }
        return null; //return null if no availability
    }

    //static method to check room availability
    public boolean checkAvailability(String roomType, String checkIn, String checkOut) {
        // Assuming there is a RoomTypeManager class to handle room types and their counts
        RoomTypeManager roomTypeManager = RoomTypeManager.getInstance();
        int totalRoomsOfType = roomTypeManager.getTotalRooms(roomType);
        LocalDate checkInDate = LocalDate.parse(checkIn);
        LocalDate checkOutDate = LocalDate.parse(checkOut);
        //count how many rooms are booked for the given room type during the requested date range
        long bookedRooms = reservations.values().stream()
            .filter(reservation -> reservation.isActive() &&
                                  reservation.getRoomType().equals(roomType) &&
                                  reservation.getCheckOutDate().isAfter(checkInDate) &&
                                  reservation.getCheckInDate().isBefore(checkOutDate))
            .count();

        //check if there are any rooms left
        return bookedRooms < totalRoomsOfType;
    }


    public boolean cancelReservation(int reservationId) {
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
        	Reservation reservation = entry.getValue();
            if (reservation.getReservationId() == reservationId && reservation.isActive()) {
                reservation.setActive(false);
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
    
    
    
    
    private void loadDatabase() {
        try (FileReader reader = new FileReader(filePath)) { // read file using file path
            GsonReservation[] gsonReservations = gson.fromJson(reader, GsonReservation[].class); // reads json into GsonReservation array
            if (gsonReservations != null) {
                for (GsonReservation gsonReservation : gsonReservations) {
                    Reservation reservation = convertToReservation(gsonReservation);
                    
                    
                        reservations.put(reservation.getReservationId(), reservation);
                        updateLastReservationId(reservation.getReservationId());
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading reservation database: " + e.getMessage()); // on error, we print out the error
        }
    }

public Reservation convertToReservation(GsonReservation temp) {
		
		
		RoomType room = RoomTypeManager.getInstance().getRoomByRoomNum(temp.getRoomNumber());
		
		Reservation reservation = new Reservation();
		
		reservation.setReservationId(temp.getReservationId());
		reservation.setCustomerId(temp.getCustomerId());
		reservation.setCheckInDate(temp.getCheckInDate());
		reservation.setCheckOutDate(temp.getCheckOutDate());
		reservation.setRoom(room);
		
		return reservation;

		
	}
    
    

	 public void addReservationToDatabase(Reservation reservation) {
		 	int newReservationId = generateReservationId();
		 	reservation.setReservationId(newReservationId);
	        reservations.put(reservation.getReservationId(), reservation); //adds it to hasmap
	        saveDatabase(); //save changes
	    }

	 
	 
	 private List<GsonReservation> changeToGson() {
		 List<GsonReservation> temp = new ArrayList<>();
		 if(!reservations.isEmpty()) {
			 for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
		        	Reservation reservation = entry.getValue();
		        	RoomType room  = reservation.getRoom();
		        	temp.add(new GsonReservation(reservation.getReservationId(), reservation.getCustomerId(), reservation.getCheckInDateString(), reservation.getCheckOutDateString(), 
		        			room.getTypeName(), room.getBasePrice(), room.getRoomNumber(), room.isOccupied(), room.getAmenities(), reservation.isActive()));
		 }
			 if (!temp.isEmpty()) {
				 return temp;
			 }
		 }
		 
		return null;
		 
	 }
	  
	    
	   public void saveDatabase() { //saves database back to json file
		   List<GsonReservation> temp = changeToGson();
		    
		    
	    	try(FileWriter writer = new FileWriter(filePath)){
	    		gson.toJson(temp, writer); //writes to file
	    	}
	    	catch(IOException e) {
	    		System.err.println("Error saving reservation database: " + e.getMessage()); //print if there are any errors
	    	}
	    }
	   
	   
	   private void updateLastReservationId(int reservationId) {
			if (reservationId > reservationId) {
				lastReservationId = reservationId;
			}
		}

		// Generate a new customer ID
		private int generateReservationId() {
			lastReservationId++;
			return lastReservationId;
		}
		
    
    
    
    
}
