package edu.mu.hotel;

import edu.mu.customer.Customer;
/*
 * this class is the check out command
 */
public class CheckOutCommand implements CIOCommand{

	private CIOReceiver cioreceiver;
	/*
	 * the constructor inits the receiver
	 */
	public CheckOutCommand(CIOReceiver cioreceiver) 
	{

		this.cioreceiver = cioreceiver;
	}
	
	/*
	 * this method overwrites execute from the interface and performs the call to go ahead with the checkout
	 */
	@Override
	public void execute(Customer customer, Reservation reservation) {
		cioreceiver.checkout(customer, reservation);		
	}
}