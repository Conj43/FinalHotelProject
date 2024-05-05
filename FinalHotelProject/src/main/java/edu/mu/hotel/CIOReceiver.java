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
			customer.setRewardPoints(customer.getRewardsPoints()+40);
		}
	}
	
	public void checkout(Customer customer, Reservation reservation)
	{
		if(customer.getCustomerID() == reservation.getCustomerId())
		{
			reservation.setActive(false);	
			manager.decreaseRoomAvailability(reservation.getRoom());
		}
	}
}