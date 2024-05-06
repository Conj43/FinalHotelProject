package edu.mu.billing;
import edu.mu.customer.*;
/*
 * This class is for creating the bill that will be paid
 */

public class Bill {

	//all the information needed to make the bill
	private double amount;
	private Customer customer;
	private PaymentStrategy paymentStrategy;

	/*
	 * the constructor will be used to initialize the bill
	 */
	public Bill(Customer customer, double amount) {
		this.customer = customer;
		this.amount = amount;
	}

	/*
	 * this is used to set the correct payment strategy for paying the bill
	 */
	public void setPaymentStrategy(PaymentStrategy paymentStrategy)
	{
		this.paymentStrategy = paymentStrategy;
	}
	
	/*
	 * passes the bill essentially to the correct payment strategy to pay
	 */
	public boolean payBill()
	{
		if(paymentStrategy != null)
		{
		paymentStrategy.pay(customer, amount);
		return true;
		}
		else {
			return false;
		}
	}
	
}
