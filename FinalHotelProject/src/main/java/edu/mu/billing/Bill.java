package edu.mu.billing;
import edu.mu.customer.*;

public class Bill {

	private double amount;
	private Customer customer;
	private PaymentStrategy paymentStrategy;

	public Bill(Customer customer, double amount) {
		this.customer = customer;
		this.amount = amount;
	}

	public void setPaymentStrategy(PaymentStrategy paymentStrategy)
	{
		this.paymentStrategy = paymentStrategy;
	}
	
	public void payBill()
	{
		paymentStrategy.pay(customer, amount);
	}
	
}
