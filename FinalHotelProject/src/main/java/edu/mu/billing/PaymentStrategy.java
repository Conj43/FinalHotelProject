package edu.mu.billing;

import edu.mu.customer.Customer;

public interface PaymentStrategy {
	
	public void pay(Customer customer, double amount);
	
	
}
