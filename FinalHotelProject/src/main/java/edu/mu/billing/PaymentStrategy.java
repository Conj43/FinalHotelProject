package edu.mu.billing;

import edu.mu.customer.Customer;

public interface PaymentStrategy {
	/*
	 * this is the interface for payment strategy
	 * each strategy will overwrite this method
	 * this follows the strategy design pattern
	 * @param Customer is the customer who is being billed
	 * @param amount is the total they are being billed for
	 */
	public void pay(Customer customer, double amount);
	
	
}
