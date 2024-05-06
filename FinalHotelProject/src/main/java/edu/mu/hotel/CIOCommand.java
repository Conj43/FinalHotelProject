package edu.mu.hotel;

import edu.mu.customer.Customer;
/*
 * this is the interface for creating the commands for checking in and out
 * each command will overwrite the execute method 
 * this follows the command design pattern
 */
public interface CIOCommand {
	void execute(Customer customer, Reservation reservation);
}