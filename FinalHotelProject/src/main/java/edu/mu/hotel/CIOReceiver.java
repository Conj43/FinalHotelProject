package edu.mu.hotel;

import edu.mu.customer.Customer;
import edu.mu.hotel.rooms.RoomTypeManager;

public class CIOReceiver {
	
	RoomTypeManager manager = RoomTypeManager.getInstance();
	
	public void checkin(Customer customer, Reservation reservation)
	{
		if(customer.getCustomerID() == reservation.getCustomerId())
		{
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
		if(customer.getCustomerID() == reservation.getCustomerId())
		{
			reservation.setActive(false);	
			KeyCardManager.deactivateKeyCard(reservation.getReservationId());
	        reservation.setKeyCardActive(false);
	        ReservationManager.getInstance().saveDatabase();
			manager.decreaseRoomAvailability(reservation.getRoom());
		}
	}
}