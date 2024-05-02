package edu.mu.hotel;

import edu.mu.customer.Customer;

public class CIOReciver {
	public void checkin(Customer customer, Reservation reservation)
	{
		if(customer.getCustomerID() == reservation.getCustomerId())
		{
			reservation.setActive(true);	
		}
	}
	
	public void checkout(Customer customer, Reservation reservation)
	{
		if(customer.getCustomerID() == reservation.getCustomerId())
		{
			reservation.setActive(false);	
		}
	}
}
