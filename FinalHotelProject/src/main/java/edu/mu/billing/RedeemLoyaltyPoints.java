package edu.mu.billing;

import edu.mu.customer.Customer;

public class RedeemLoyaltyPoints implements PaymentStrategy{
	/*
	 * this strategy is for when customers want to redeem rewards points
	 */
	@Override
	public void pay(Customer customer, double amount) {
		payWithPoints(customer, amount);
	}
	
	/*
	 * this method is used to perform the logic behind paying with loyalty points
	 */
	public void payWithPoints(Customer customer,double amount)
	{
		if(customer.getRewardsPoints() >= 100) //checks to make sure the customer has enough points to use it
		{
			System.out.println("Thank you for being a loyal Customer!");
			System.out.println("You have redeemed 100 points to pay for your visit this time");
			customer.setRewardPoints(customer.getRewardsPoints()-100);
		}
		else//if the customer does not have enough points it will automatically charge the card on file
		{
			System.out.println("Sorry you do not have enough points");
			System.out.println("You only have " +customer.getRewardsPoints() +" points");
			System.out.println("You will now be charged to your card on file");
			CreditCardPayment creditCardPayment = new CreditCardPayment();
			creditCardPayment.pay(customer, amount);
		}
	}

}
