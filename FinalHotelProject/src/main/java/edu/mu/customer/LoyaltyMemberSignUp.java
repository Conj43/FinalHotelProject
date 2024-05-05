package edu.mu.customer;

public class LoyaltyMemberSignUp {
	Customer customer;

	public LoyaltyMemberSignUp(Customer customer) {
		this.customer = customer;
	}
	
	public static void signUp(Customer customer) {
		System.out.println("Thank you for joining our rewards program? \n We really value our Cusomers here\n"
				+ "To show our appreciation you will be given 20 rewards points just for signing up today\n"
				+ "Each time you stay with us you will be given 40 rewards points"
				+ "You can redeem 100 points to recieve any hotel stay for free"
				+ "It does not matter what room you reserve or how long the stay is, 100 points will cover the balance");
		customer.setRewardPoints(20);
		customer.setRewardsMember(true);
	}
	
	
	
	
}
