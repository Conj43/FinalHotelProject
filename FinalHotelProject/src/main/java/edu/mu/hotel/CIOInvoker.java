package edu.mu.hotel;

import edu.mu.customer.Customer;

public class CIOInvoker {

	private CIOCommand checkInCommand;
	private CIOCommand checkOutCommand;
	
	public CIOInvoker(CIOCommand checkInCommand, CIOCommand checkOutCommand) {
		super();
		this.checkInCommand = checkInCommand;
		this.checkOutCommand = checkOutCommand;
	}
	
	public void checkIn(Customer customer, Reservation reservation)
	{
		checkInCommand.execute(customer,reservation);
	}
	
	public void checkOut(Customer customer, Reservation reservation)
	{
		checkOutCommand.execute(customer, reservation);
	}
	
	
}
