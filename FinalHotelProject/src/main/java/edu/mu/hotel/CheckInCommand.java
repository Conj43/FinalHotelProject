package edu.mu.hotel;

import edu.mu.customer.Customer;

public class CheckInCommand implements CIOCommand {

private CIOReceiver cioReciver;

/*
 * this is the command for checking in 
 * the constructor inits the command
 * @param cioReceiver is where the command is sent
 */
	public CheckInCommand(CIOReceiver cioReciver) 
	{

		this.cioReciver = cioReciver;
	}
	/*
	 * this is overwriting execute from the interface and performs the check in
	 * @param customer is the guest who is checking in
	 * @parm reservation is the reservation they are checking into
	 */
	@Override
	public void execute(Customer customer, Reservation reservation) {
		cioReciver.checkin(customer, reservation);		
	}

	
}