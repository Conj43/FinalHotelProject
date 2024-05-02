package edu.mu.hotel;

import edu.mu.customer.Customer;

public class CheckInCommand implements CIOCommand {

private CIOReciver cioReciver;
	
	public CheckInCommand(CIOReciver cioReciver) 
	{

		this.cioReciver = cioReciver;
	}
	
	@Override
	public void execute(Customer customer, Reservation reservation) {
		cioReciver.checkin(customer, reservation);		
	}

	
}
