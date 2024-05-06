package edu.mu.billing;

import edu.mu.customer.Customer;
/*
 * this class is a strategy design pattern
 */

public class CreditCardPayment implements PaymentStrategy {

/*
 * this method is overriding the pay method from PaymentStrategy
 */
	@Override
	public void pay(Customer customer, double amount) {
		
		charge(customer.getCardNum(),amount);
		
	}
	
	/*
	 * this method hold the place of where the credit card charge would take place
	 */
	private void charge(String cardNum, double amount) {
		System.out.println("charging customer $" +amount );
		//this is a blank method, this is where the program would send off the customer card information
		//to a third party for the charge to occur
		
	}

}
