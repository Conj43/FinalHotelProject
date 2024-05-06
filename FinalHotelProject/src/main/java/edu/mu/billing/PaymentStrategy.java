package edu.mu.billing;

import edu.mu.customer.Customer;

public interface PaymentStrategy {
	/*
	 * this is the interface for payment strategy
	 * each strategy will overwrite this method
	 * this follows the strategy design pattern
	 */
	public void pay(Customer customer, double amount);
	
	
}
