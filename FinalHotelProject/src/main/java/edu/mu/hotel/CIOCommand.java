package edu.mu.hotel;

import edu.mu.customer.Customer;

public interface CIOCommand {
	void execute(Customer customer, Reservation reservation);
}