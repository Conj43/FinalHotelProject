package edu.mu.customer;


public class Customer {
	
	//private attributes of customer
	public static int customerIdCounter = 1;
	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNum; 
	private String address;
	private String birthDate; 
	private int age;
	private int roomNumber;
	private boolean isRewardsMember;
	private int rewardPoints;
	
	
	//default constructor
	public Customer() {
		
	}
	
	
	
	
	//paramateized constructor
	public Customer(String firstName, String lastName, String email, String phoneNum, String address,
			String birthDate, int age, int roomNumber, boolean isRewardsMember, int rewardPoints) {
		super();
		this.customerID = customerIdCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.birthDate = birthDate;
		this.age = age;
		this.roomNumber = roomNumber;
		this.isRewardsMember = isRewardsMember;
		this.rewardPoints = rewardPoints;
	}


	public int getRoomNumber() {
		return roomNumber;
	}




	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}




	public int getRewardPoints() {
		return rewardPoints;
	}




	//getters and setters for each attribute
	//but dont have setter for ID because we dont want to be able to change that
	public int getCustomerID() {
		return customerID;
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



	//generated to string method
	
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNum=" + phoneNum + ", address=" + address + ", birthDate=" + birthDate + ", age="
				+ age + ", isRewardsMember=" + isRewardsMember + ", rewardPoints=" + rewardPoints + "]";
	}



	

}
