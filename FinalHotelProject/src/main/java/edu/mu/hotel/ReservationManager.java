package edu.mu.hotel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mu.hotel.rooms.RoomType;
import edu.mu.hotel.rooms.RoomTypeManager;

/*
 * singelton class that manages reservations and saves to json file
 */
public class ReservationManager {
	
	private static ReservationManager instance = null; //part of singleton design pattern
	private Map<Integer, Reservation> reservations; 
	private Gson gson; //use gson to store data to json file
	private final String filePath = "data/ReservationDatabase.json"; //file path to database json file
	private int lastReservationId; //keeps track of reservation id so we do not repeat id's
	
	
	/*
	 * private constructor called by getInstance method
	 */
	private ReservationManager() {
		reservations = new HashMap<>(); // initialize reservations map
        gson = new GsonBuilder().setPrettyPrinting().create(); // initialize gson with pretty print so json file looks nice
        loadDatabase(); // load data from file
	}
	
	
	
	
	/*
	 * static method to get instance of ReservationManager
	 * @return an instance of reservation manager
	 */
	public static ReservationManager getInstance() {
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new ReservationManager();
		}
		return instance; //return the instance, can only have one instane
	}
	
	
	
	
	
	 /*
	  * method to get a list of all active reservations
	  * @return list of active reservations
	  */
    public List<Reservation> getActiveReservations() {
        List<Reservation> activeReservations = new ArrayList<>(); //make list for active reservations
        
        if(!reservations.isEmpty()) { //make sure reservations isnt empty
        	
        for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
        	Reservation reservation = entry.getValue();
            if (reservation.isActive()) { //if reservation hasnt been cancelled, add it to array list
                activeReservations.add(reservation);
            }
        }
        }
        return activeReservations; //return list of reservations
    }

    
    
    
    
 /*
  * method to get reservation by id
  * @param int reservation id
  * @return reservation object with reservation id, null if doesnt exist
  */
    public Reservation getReservationById(int reservationId) { //returns a reservation by ID
        return reservations.get(reservationId);
    }
    
    
    
    
    /*
     * creates reservation and saves to json file and adds to reservations map
     * @param int customer id
     * @param String room type for reservation
     * @param String check in and check out, which should be in correct date format
     * @param list of service requests
     * @return reservation object that was created, null if failed
     */
    public Reservation createReservation(int customerId, String roomType, String checkIn, String checkOut, List<ServiceRequest> serviceRequests) {

    	
    	
        if (!checkAvailability(roomType, checkIn, checkOut)) { //check availability for room type
            System.out.println("No available rooms for the selected dates.");
            return null; //return null if no availability
        }

        RoomType room = RoomTypeManager.getInstance().findAvailableRooms(roomType, checkIn, checkOut); //returns an available room if there are any
        if (room == null) { //if there isn't an available room
            System.out.println("No available rooms of type: " + roomType);
            return null; //return null if no availability
        }

        Reservation reservation = new Reservation(customerId, checkIn, checkOut, room, serviceRequests);
        addReservationToDatabase(reservation);
        return reservation; //return new reservation if it is created
    }
    

    
    /*
     * method to check availability in date range for given room type
     * @param String room type
     * @param String check in and check out date in correct date format
     * @return true if there is availability, false if no availability
     */
    public boolean checkAvailability(String roomType, String checkIn, String checkOut) {
        try {
            LocalDate checkInDate = LocalDate.parse(checkIn); //parse check-in date
            LocalDate checkOutDate = LocalDate.parse(checkOut); //parse check-out date
            
            if (checkInDate.isAfter(checkOutDate)) {//make sure check out isnt before check in
                System.out.println("Error: Check-in date cannot be after check-out date.");
                return false; //return false if true
            }
            
            RoomTypeManager roomTypeManager = RoomTypeManager.getInstance();
            int totalRoomsOfType = roomTypeManager.getTotalRooms(roomType); //get total number of rooms of given type
            
            //count how many rooms are booked for the given room type during the requested date range
         // Count how many rooms are booked for the given room type during the requested date range
            int bookedRooms = 0;

            for (Reservation reservation : reservations.values()) {
                if (reservation.isActive() &&
                    reservation.getRoomType().equals(roomType) &&
                    (
                        (reservation.getCheckInDate().isBefore(checkOutDate) && reservation.getCheckOutDate().isAfter(checkInDate)) || 
                        (reservation.getCheckInDate().isEqual(checkInDate) && reservation.getCheckOutDate().isEqual(checkOutDate)) || 
                        (reservation.getCheckInDate().isAfter(checkInDate) && reservation.getCheckOutDate().isBefore(checkOutDate))
                    )) {
                    bookedRooms++; //keep track of booked rooms in given range of given type
                }
            }

            return bookedRooms < totalRoomsOfType; //returns false if no availability, true if there is availability
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please enter dates in YYYY-MM-DD format.");
            return false;
        }
    }



    
    
    /*
     * cancels reservation by setting isactive to false
     * @param int reservation id of reservation to cancel
     * @return true if success, false if failure
     */
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

    
    
    
    /*
     * method to confirm booking, prints out info
     * @param reservation object
     * @return string of the reservation, else an error if it is not active
     */
    public String confirmBooking(Reservation reservation) {
        if (!reservation.isActive()) {
            return "Reservation ID " + reservation.getReservationId() + " is cancelled."; //if reservation isn't active, print it out to user
        }
        //display or return reservation details
        return reservation.toString();
    }
    
    
    
    
    
    /*
     * method to load json file data into reservations map
     * 
     */
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

    
    
    /*
     * converts a gson reservation to reservation
     * @param gson reservtion to be converted
     * @return reservation object with same attributes of the param value
     */
public Reservation convertToReservation(GsonReservation temp) { //converts GsonReservation to a reservation
		
		if (temp != null) { //make sure temp is valid
	
		RoomType room = RoomTypeManager.getInstance().getRoomByRoomNum(temp.getRoomNumber()); //get the room 
		
		Reservation reservation = new Reservation(); //create a reservation object
		
		reservation.setReservationId(temp.getReservationId()); //set all values, respectively
		reservation.setCustomerId(temp.getCustomerId());
		reservation.setCheckInDate(temp.getCheckInDate());
		reservation.setCheckOutDate(temp.getCheckOutDate());
		reservation.setRoom(room);
		reservation.setActive(temp.isActive());
		reservation.setAccessCode(temp.getAccessCode());
		reservation.setKeyCardActive(temp.isKeyCardActive());
		reservation.setServiceRequests(temp.getServiceRequests());
		
		return reservation; //return the reservation
		}
		return null; //return null if temp is null

		
	}
    
    
	/*
	 * adds reservation to json database and saves it
	 * @param a reservation object to be added
	 */
	 public void addReservationToDatabase(Reservation reservation) {
		 	int newReservationId = generateReservationId(); //get the new reservation id
		 	reservation.setReservationId(newReservationId); //set the new reservation id
	        reservations.put(reservation.getReservationId(), reservation); //adds it to hasmap
	        saveDatabase(); //save changes
	    }

	 
	 /*
	  * changes the reservations map into a list of gson reservations to be saved to json fule
	  * @return list of Gson reservations
	  */
	 private List<GsonReservation> changeToGson() {
		 List<GsonReservation> temp = new ArrayList<>(); //make new arraylist of temp for temp values to put into database
		 if(!reservations.isEmpty()) { //make sure reservations isnt empty
			 for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) { //loop through entries in map
		        	Reservation reservation = entry.getValue();
		        	RoomType room  = reservation.getRoom();
		        	temp.add(new GsonReservation(reservation.getReservationId(), reservation.getCustomerId(), 
		        			reservation.getCheckInDateString(), reservation.getCheckOutDateString(), 
		        			room.getTypeName(), room.getBasePrice(), room.getRoomNumber(), room.isOccupied(), 
		        			room.getAmenities(), reservation.isActive(), reservation.getAccessCode(), 
		        			reservation.isKeyCardActive(), reservation.getServiceRequests())); //add new gson reservation to temp
		 }
			 if (!temp.isEmpty()) {
				 return temp; //if temp isnt empty we return it
			 }
		 }
		 
		return null; //return null if any issues
		 
	 }
	 
	 
	  
	    /*
	     * method to save reservations to json file
	     */
	   public void saveDatabase() { //saves database back to json file
		   List<GsonReservation> temp = changeToGson(); //changes reservations to gson reservation
		    
		    
	    	try(FileWriter writer = new FileWriter(filePath)){
	    		gson.toJson(temp, writer); //writes to file
	    	}
	    	catch(IOException e) {
	    		System.err.println("Error saving reservation database: " + e.getMessage()); //print if there are any errors
	    	}
	    }
	   
	   
	   /*
	    * method to update last reservation id
	    * @param int reservation id to compare
	    * 
	    */
	   private void updateLastReservationId(int reservationId) { //checks if new reservation id is greater than the last, if so then change it so we can keep track
			if (reservationId > lastReservationId) { //we want to keep max reservation id
				lastReservationId = reservationId;
			}
		}

		/*
		 * method to generate unique reservation id
		 * @return a unique int reservation id
		 */
		private int generateReservationId() { //generates new reservation id just by incrementing
			lastReservationId++;
			return lastReservationId;
		}
		
		
		
		/*
		 * method to geerate history of reservations in a given range
		 * @param Local date of start and end date of range
		 * @return list of reservations that fall within given range, null if none
		 */
		public List<Reservation> generatePastHistory(LocalDate startDate, LocalDate endDate) {
	        List<Reservation> pastHistory = new ArrayList<>(); // list to store report

	       
	        for (Reservation reservation : reservations.values()) { //go thru reservations
	            LocalDate checkInDate = reservation.getCheckInDate();
	            LocalDate checkOutDate = reservation.getCheckOutDate();

	            //if falls within specified range
	            if (checkInDate.isBefore(endDate) && checkOutDate.isAfter(startDate)) {
	                pastHistory.add(reservation); // add to list
	            }
	        }
	        return pastHistory; // return list, will be null if no reservations added
	    }
		
		
		
		/*
		 * method used in testing to clear database so we don't overload it
		 */
		public void clearDatabase() {
		    reservations.clear();
		    saveDatabase(); //be careful, this over rides entire databse
		}
    
    
    
    
}
