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
	private final String filePath = "data/ReservationDatabase.json"; //file path to database json file
	private int lastReservationId; //keeps track of reservation id so we do not repeat id's
	
	

	private ReservationManager() {
		reservations = new HashMap<>(); // initialize reservations map
        gson = new GsonBuilder().setPrettyPrinting().create(); // initialize gson with pretty print so json file looks nice
        loadDatabase(); // load data from file
	}
	
	
	
	
	
	public static ReservationManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new ReservationManager();
		}
		return instance; //return the instance
	}
	
	
	
	
	
	 //get all active reservations
    public List<Reservation> getActiveReservations() {
        List<Reservation> activeReservations = new ArrayList<>(); //make list for active reservations
        
        if(!reservations.isEmpty()) { //,ake sure reservations isnt empty
        	
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
        	Reservation reservation = entry.getValue();
            if (reservation.isActive()) { //if reservation hasnt been cancelled, add it to array list
                activeReservations.add(reservation);
            }
        }
        }
        return activeReservations; //return list of reservations
    }

    
    
    
    
 
    public Reservation getReservationById(int reservationId) { //returns a reservation by ID
        return reservations.get(reservationId);
    }
    
    
    
    
    //creates a reservation using customer ID, room type, check in and check out
    public Reservation createReservation(int customerId, String roomType, String checkIn, String checkOut) {

        if (!checkAvailability(roomType, checkIn, checkOut)) { //check availability for room type
            System.out.println("No available rooms for the selected dates.");
            return null;
        }

        RoomType room = RoomTypeManager.getInstance().findAvailableRooms(roomType, checkIn, checkOut); //returns an available room if there are any
        if (room == null) { //if there isn't an available room
            System.out.println("No available rooms of type: " + roomType);
            return null; //return null if no availability
        }

        Reservation reservation = new Reservation(customerId, checkIn, checkOut, room);
        addReservationToDatabase(reservation);
        return reservation; //return new reservation if it is created
    }

    
   


    
    
    
    
    
    //method to check availibility
    public boolean checkAvailability(String roomType, String checkIn, String checkOut) {
        
        RoomTypeManager roomTypeManager = RoomTypeManager.getInstance();
        int totalRoomsOfType = roomTypeManager.getTotalRooms(roomType); //get total number of rooms of given type
        LocalDate checkInDate = LocalDate.parse(checkIn); //change to LocalDate type
        LocalDate checkOutDate = LocalDate.parse(checkOut);
        //count how many rooms are booked for the given room type during the requested date range
        long bookedRooms = reservations.values().stream()
            .filter(reservation -> reservation.isActive() &&
                                  reservation.getRoomType().equals(roomType) &&
                                  reservation.getCheckOutDate().isAfter(checkInDate) &&
                                  reservation.getCheckInDate().isBefore(checkOutDate))
            .count();

        //check if there are any rooms left
        return bookedRooms < totalRoomsOfType; //returns false if no availability, true if there is availability
    }


    
    
    
    public boolean cancelReservation(int reservationId) { //calcels reservation by using reservation id
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
        	Reservation reservation = entry.getValue();
            if (reservation.getReservationId() == reservationId && reservation.isActive()) { //find the reservation using id, and make sure it is active
                reservation.setActive(false); //we dont delete reservation, just set it as not active so we still have it on record
                return true; //return true on success
            }
        }
        return false; //return false on failure
    }

    
    
    
    //method to confirm reservation details
    public String confirmBooking(Reservation reservation) {
        if (!reservation.isActive()) {
            return "Reservation ID " + reservation.getReservationId() + " is cancelled."; //if reservation isn't active, print it out to user
        }
        //display or return reservation details
        return "Reservation confirmed: ID " + reservation.getReservationId() +
                "\nCustomer ID: " + reservation.getCustomerId() +
                "\nRoom Type: " + reservation.getRoomType() +
                "\nCheck-In Date: " + reservation.getCheckInDate() +
                "\nCheck-Out Date: " + reservation.getCheckOutDate();
    }
    
    
    
    
    
    
    private void loadDatabase() { //loads reservation database into map
        try (FileReader reader = new FileReader(filePath)) { // read file using file path
            GsonReservation[] gsonReservations = gson.fromJson(reader, GsonReservation[].class); // reads json into GsonReservation array
            if (gsonReservations != null) { //make sure array is not empty
                for (GsonReservation gsonReservation : gsonReservations) {
                    Reservation reservation = convertToReservation(gsonReservation); //convert to correct type, see GsonReservation for the reason we made this class

                        reservations.put(reservation.getReservationId(), reservation); //put value and id into hash map
                        updateLastReservationId(reservation.getReservationId()); //update the last reservation id so we do not repeat when creating new users
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading reservation database: " + e.getMessage()); // on error, we print out the error
        }
    }

    
    
    
public Reservation convertToReservation(GsonReservation temp) { //converts GsonReservation to a reservation
		
		if (temp != null) { //make sure temp is valid
	
		RoomType room = RoomTypeManager.getInstance().getRoomByRoomNum(temp.getRoomNumber()); //get the room 
		
		Reservation reservation = new Reservation(); //create a reservation object
		
		reservation.setReservationId(temp.getReservationId()); //set all values, respectively
		reservation.setCustomerId(temp.getCustomerId());
		reservation.setCheckInDate(temp.getCheckInDate());
		reservation.setCheckOutDate(temp.getCheckOutDate());
		reservation.setRoom(room);
		
		return reservation; //return the reservation
		}
		return null; //return null if temp is null

		
	}
    
    

	 public void addReservationToDatabase(Reservation reservation) {
		 	int newReservationId = generateReservationId(); //get the new reservation id
		 	reservation.setReservationId(newReservationId); //set the new reservation id
	        reservations.put(reservation.getReservationId(), reservation); //adds it to hasmap
	        saveDatabase(); //save changes
	    }

	 
	 //changes a reservation to GsonReservation and returns it
	 private List<GsonReservation> changeToGson() {
		 List<GsonReservation> temp = new ArrayList<>(); //make new arraylist of temp for temp values to put into database
		 if(!reservations.isEmpty()) { //make sure reservations isnt empty
			 for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) { //loop through entries in map
		        	Reservation reservation = entry.getValue();
		        	RoomType room  = reservation.getRoom();
		        	temp.add(new GsonReservation(reservation.getReservationId(), reservation.getCustomerId(), reservation.getCheckInDateString(), reservation.getCheckOutDateString(), 
		        			room.getTypeName(), room.getBasePrice(), room.getRoomNumber(), room.isOccupied(), room.getAmenities(), reservation.isActive())); //add new gson reservation to temp
		 }
			 if (!temp.isEmpty()) {
				 return temp; //if temp isnt empty we return it
			 }
		 }
		 
		return null; //return null if any issues
		 
	 }
	  
	    
	   public void saveDatabase() { //saves database back to json file
		   List<GsonReservation> temp = changeToGson(); //changes reservations to gson reservation
		    
		    
	    	try(FileWriter writer = new FileWriter(filePath)){
	    		gson.toJson(temp, writer); //writes to file
	    	}
	    	catch(IOException e) {
	    		System.err.println("Error saving reservation database: " + e.getMessage()); //print if there are any errors
	    	}
	    }
	   
	   
	   private void updateLastReservationId(int reservationId) { //checks if new reservation id is greater than the last, if so then change it so we can keep track
			if (reservationId > lastReservationId) {
				lastReservationId = reservationId;
			}
		}

		
		private int generateReservationId() { //generates new reservation id just by incrementing
			lastReservationId++;
			return lastReservationId;
		}
		
    
    
    
    
}
