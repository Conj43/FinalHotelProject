package edu.mu.billing;

import edu.mu.customer.Customer;

public class CreditCardPayment implements PaymentStrategy {

	
//	private Customer customer;
//	private double amount;
//
//	public CreditCardPayment(Customer customer, double amount) 
//	{
//		this.customer = customer;
//		this.amount = amount;
//	}

	@Override
	public void pay(Customer customer, double amount) {
		
		charge(customer.getCardNum(),amount);
		
	}
	

	private void charge(String cardNum, double amount) {
		System.out.println("charging customer $" +amount );
		//this is a blank method, this is where the program would send off the customer card information
		//to a third party for the charge to occur
		
	}

}
