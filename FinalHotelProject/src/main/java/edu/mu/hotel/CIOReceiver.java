package edu.mu.hotel;

import edu.mu.customer.Customer;
import edu.mu.hotel.rooms.RoomTypeManager;
/*
 * this is the receiver for the check in and out commands
 * this handles the true logic behind the process
 */
public class CIOReceiver {
	
	RoomTypeManager manager = RoomTypeManager.getInstance(); //gets the instance of the current manager
	
	public void checkin(Customer customer, Reservation reservation)
	{
		if(customer.getCustomerID() == reservation.getCustomerId()) //makes sure it has the correct customer and reservation before proceeding
		{
			//this is sets everything to reflect that the guest has arrived and the room is occupied
			reservation.setActive(true); 
			manager.decreaseRoomAvailability(reservation.getRoom());
			KeyCardManager.activateKeyCard(reservation.getReservationId());
	        reservation.setKeyCardActive(true);
	        reservation.setAccessCode(AccessCodeGenerator.generateCode()); 
	        System.out.println("Access Code for Amenities: " + reservation.getAccessCode());
	        ReservationManager.getInstance().saveDatabase();
			customer.setRewardPoints(customer.getRewardsPoints()+40);
		}
	}
	
	public void checkout(Customer customer, Reservation reservation)
	{
		if(customer.getCustomerID() == reservation.getCustomerId())//makes sure it has the correct customer and reservation before proceeding
		{
			//resets all information to reflect that the guest has left
			reservation.setActive(false);	
			KeyCardManager.deactivateKeyCard(reservation.getReservationId());
	        reservation.setKeyCardActive(false);
	        reservation.setAccessCode(null);
	        ReservationManager.getInstance().saveDatabase();
			manager.decreaseRoomAvailability(reservation.getRoom());
		}
	}
}