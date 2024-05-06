package edu.mu.hotel;

import edu.mu.customer.Customer;
/*
 * this is the invoker for the check in and check out commands
 */
public class CIOInvoker {

	private CIOCommand checkInCommand;
	private CIOCommand checkOutCommand;
	
	public CIOInvoker(CIOCommand checkInCommand, CIOCommand checkOutCommand) {
		super();
		this.checkInCommand = checkInCommand;
		this.checkOutCommand = checkOutCommand;
	}
	
	/*
	 * these 2 methods will be called when the manager wants to invoke either the check in or out command
	 */
	
	public void checkIn(Customer customer, Reservation reservation)
	{
		checkInCommand.execute(customer,reservation);
	}
	
	public void checkOut(Customer customer, Reservation reservation)
	{
		checkOutCommand.execute(customer, reservation);
	}
	
	
}