package edu.mu.hotel;

import edu.mu.customer.Customer;

public class CheckInCommand implements CIOCommand {
/*
 * this is the command for checking in 
 * the constructor inits the command
 */
private CIOReceiver cioReciver;
	
	public CheckInCommand(CIOReceiver cioReciver) 
	{

		this.cioReciver = cioReciver;
	}
	/*
	 * this is overwriting execute from the interface and performs the check in
	 */
	@Override
	public void execute(Customer customer, Reservation reservation) {
		cioReciver.checkin(customer, reservation);		
	}

	
}