package edu.mu.hotel;

import edu.mu.customer.Customer;
/*
 * this is the interface for creating the commands for checking in and out
 * each command will overwrite the execute method 
 * this follows the command design pattern
 */
public interface CIOCommand {
	/*
	 * This is the method that will be overwritten by each command
	 * @param customer is the guest who is checking in or out
	 * @parm reservation is the reservation they are checking out of
	 */
	void execute(Customer customer, Reservation reservation);
}