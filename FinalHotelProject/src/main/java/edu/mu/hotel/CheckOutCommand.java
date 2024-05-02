package edu.mu.hotel;

import edu.mu.customer.Customer;

public class CheckOutCommand implements CIOCommand{

	private CIOReceiver cioReciver;
	
	public CheckOutCommand(CIOReceiver cioReciver) 
	{

		this.cioReciver = cioReciver;
	}
	
	
	@Override
	public void execute(Customer customer, Reservation reservation) {
		cioReciver.checkout(customer, reservation);		
	}
}
