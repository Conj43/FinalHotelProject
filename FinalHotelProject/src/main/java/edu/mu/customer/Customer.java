package edu.mu.customer;

/*
 * class that is a customer that allows people to make reservations
 */
public class Customer {
	
	/*
	 * attributes for customer
	 */
	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNum; 
	private String address;
	private String birthDate; 
	private int age;
	private boolean isRewardsMember;
	private int rewardPoints;
	private String cardNum;
	
	
	/*
     * default constructor
     * 
     */
	public Customer() {
		
	}
	
	
	
	
	/*
     * 
     * paramaterized constructor
     * 
     */
	public Customer(String firstName, String lastName, String email, String phoneNum, String address,
			String birthDate, int age, boolean isRewardsMember, int rewardPoints, String cardNum) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.birthDate = birthDate;
		this.age = age;
		this.isRewardsMember = isRewardsMember;
		this.rewardPoints = rewardPoints;
		this.cardNum = cardNum;
	}


	/*
	 * getters and setters for all attributes
	 */

	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int id) {
		this.customerID = id;
	}

	public void setCardNum(String num) {
		this.cardNum = num;
	}
	
	public String getCardNum() {
	return cardNum;
	}

	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNum() {
		return phoneNum;
	}



	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public boolean isRewardsMember() {
		return isRewardsMember;
	}



	public void setRewardsMember(boolean isRewardsMember) {
		this.isRewardsMember = isRewardsMember;
	}
	
	
	public void setRewardPoints(int points) {
		this.rewardPoints = points;
	}
	
	public int getRewardsPoints() {
		return this.rewardPoints;
	}



	/*
	 * to string method that returns a string of all customer attributes
	 */
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNum=" + phoneNum + ", address=" + address + ", birthDate=" + birthDate + ", age="
				+ age + ", isRewardsMember=" + isRewardsMember + ", rewardPoints=" + rewardPoints + ", cardNum="
				+ cardNum + "]";
	}



	
	
	
	



	

}
