package edu.mu.hotel;

import edu.mu.customer.Customer;
/*
 * this is the invoker for the check in and check out commands
 */
public class CIOInvoker {

	private CIOCommand checkInCommand;
	private CIOCommand checkOutCommand;
	/*
	 * this constructor is used to initialize both commands 
	 * @param checkInCommand is for checking a customer in
	 * @param checkOutCommand is for checking a customer out
	 */
	public CIOInvoker(CIOCommand checkInCommand, CIOCommand checkOutCommand) {
		super();
		this.checkInCommand = checkInCommand;
		this.checkOutCommand = checkOutCommand;
	}
	
	/*
	 * this will be called when the manager wants to invoke the check in command
	 * @param customer is the guest who is checking in
	 * @parm reservation is the reservation they are checking into
	 */
	
	public void checkIn(Customer customer, Reservation reservation)
	{
		checkInCommand.execute(customer,reservation);
	}
	
	/*
	 * this will be called when the manager wants to invoke the check out command
	 * @param customer is the guest who is checking out
	 * @parm reservation is the reservation they are checking out of
	 */
	
	public void checkOut(Customer customer, Reservation reservation)
	{
		checkOutCommand.execute(customer, reservation);
	}
	
	
}