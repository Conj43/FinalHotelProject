package edu.mu.test;


import edu.mu.billing.Bill;
import edu.mu.billing.CreditCardPayment;
import edu.mu.billing.PaymentStrategy;
import edu.mu.billing.RedeemLoyaltyPoints;
import edu.mu.customer.Customer;
import edu.mu.customer.CustomerDBSingleton;
import edu.mu.customer.LoyaltyMemberSignUp;
import edu.mu.hotel.*;
import edu.mu.hotel.rooms.RoomType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;




public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer customer;

    public static void main(String[] args) throws InvalidInputException {
        
    	
    	printIntro();
    	
        //menu options
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Check Room Availability");
            System.out.println("4. Confirm a Reservation");
            System.out.println("5. CheckIn");
            System.out.println("5. CheckOut");
            System.out.println("7. Pay Bill");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    makeReservation(customer.getCustomerID());
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    checkAvailability();
                    break;
                case 4:
                    confirmReservation();
                    break;
                case 5:
                	customerCheckIn();
                    break;
                case 6:
                	customerCheckOut();
                    break;
                case 7:
                	payCustomerBill(customer);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    	}
  



	//registers a new customer by taking input and creating new customer and saving it to database
    private static void registerCustomer() {
    	System.out.println("Please enter your information to create a new profile:");
        System.out.println("-----------------------------------------------------");
    	
        System.out.println("First Name: ");
        String firstName = scanner.next();
       
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        
        System.out.println("Email: ");
        String email = scanner.next();
        
        System.out.println("Phone Number: ");
        String phoneNum = scanner.next();
        
        System.out.println("Address: ");
        scanner.nextLine();
        String address = scanner.nextLine();
        
        System.out.println("Birthdate (YYYY-MM-DD): ");
        String birthdate = scanner.next();
        
        System.out.println("Age: ");
        int age = scanner.nextInt();
        
        System.out.println("Credit Card Number: ");
        String cardNum = scanner.next();
        
        
        System.out.println("Would you like to become a rewards member? (y/n): ");
        String memberResponse = scanner.next();
        boolean isRewardsMember = memberResponse.equalsIgnoreCase("y");

        Customer newCustomer = new Customer(firstName, lastName, email, phoneNum, address, birthdate, age, isRewardsMember, 0, cardNum);
        CustomerDBSingleton customerDB = CustomerDBSingleton.getInstance();
        customerDB.addCustomer(newCustomer);
        customerDB.saveDatabase();
        if(isRewardsMember)
        {
        	LoyaltyMemberSignUp.signUp(newCustomer);
        }
        System.out.println("\n\nCongratulations! You have created a profile with us. Your CustomerID is " + newCustomer.getCustomerID() + ". Make sure you remember it if you ever stay with us again!\n\n");

        customer = newCustomer;
        return;
    }
    
    
    
    //prints intro and sees if customer is new or not
    private static void printIntro() throws InvalidInputException {
    	
    	
    	try {
    	
    	System.out.println("\n\n-----Welcome to Group AA's Luxury Hotel-----\n--------------------------------------------\n");
    	System.out.println("Have you stayed with us before? (y/n): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            System.out.println("Please enter your customer ID: ");
            int customerId = scanner.nextInt();
            CustomerDBSingleton customerManager = CustomerDBSingleton.getInstance();
            customer = customerManager.getCustomer(customerId);
            
            if (customer == null) {
                throw new InvalidInputException("Customer ID not found.");
            }
            
             System.out.println("Welcome back, "+ customer.getFirstName() + "! We are so happy to see you again.");
            return;
            
        } if (response.equalsIgnoreCase("n")) {
           registerCustomer();

           
        } else {
        	throw new InvalidInputException("Invalid response. Please enter 'y' or 'n'.");
           
        }
    	} catch(InvalidInputException e){
        	System.out.println("Error: " + e.getMessage());
        	printIntro();
        }
        
    }

    
    
    
    //uses customer id and input to make new reservation
    private static void makeReservation(int customerID) {
    	String roomType;
        while (true) {
            System.out.print("Enter room type (Standard, Deluxe, Suite): ");
            roomType = scanner.next(); // Normalize input
            String lower = roomType.trim().toLowerCase();
            
            
            if (lower.equals("standard") || lower.equals("deluxe") || lower.equals("suite")) {
                break; 
            } else {
                System.out.println("Invalid room type. Please enter Standard, Deluxe, or Suite.");
            }
        }
        
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.next();
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.next();
        
        Reservation reservation = ReservationManager.getInstance().createReservation(customerID, roomType, checkInDate, checkOutDate);
        
        if (reservation != null) {
            System.out.println("Reservation made successfully: " + reservation.toString());
        } else {
            System.out.println("Failed to make a reservation. No available rooms.");
        }
    }
    
    
    
    
    
    
//cancels reservation
    private static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
       
        if (ReservationManager.getInstance().cancelReservation(reservationId)) {
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Failed to cancel reservation. ID not found.");
        }
    }
    
    
    
    
    
    
//checks availability
    private static void checkAvailability() {
        System.out.print("Enter room type to check (Standard, Deluxe, Suite): ");
        String roomType = scanner.next();
        
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.next();
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.next();
        
        boolean available = ReservationManager.getInstance().checkAvailability(roomType, checkInDate, checkOutDate);
        
        if (available) {
            System.out.println("Rooms are available.");
        } else {
            System.out.println("No rooms available for the selected dates.");
        }
    }

    
    
    
    
    //confirms reservation
    private static void confirmReservation() {
        System.out.print("Enter reservation ID to confirm: ");
        int reservationId = scanner.nextInt();
        
        Reservation reservation = ReservationManager.getInstance().getReservationById(reservationId);
        
        if (reservation != null) {
            System.out.println("Reservation details: " + reservation.toString());
        } else {
            System.out.println("Reservation ID not found.");
        }
    }
    
    
    private static void customerCheckOut() {
    	
    	System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt();
       
        Reservation reservation = ReservationManager.getInstance().getReservationById(id);
        
    	CIOReceiver receiver = new CIOReceiver();
    	CIOCommand checkOutCommand = new CheckOutCommand(receiver);
    	
    			
    	CIOInvoker manager = new CIOInvoker(null, checkOutCommand);
    	manager.checkIn(customer, reservation);
		
	}



	private static void customerCheckIn() {
	
		System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt();
       
        Reservation reservation = ReservationManager.getInstance().getReservationById(id);
        
    	CIOReceiver receiver = new CIOReceiver();
    	CIOCommand checkInCommand = new CheckInCommand(receiver);
    	
    			
    	CIOInvoker manager = new CIOInvoker(checkInCommand, null);
    	manager.checkIn(customer, reservation);
	}
    
    
    
    
    private static void payCustomerBill(Customer customer)
    {
    	 System.out.print("Enter Reservation ID: ");
         int id = scanner.nextInt();
        
         Reservation reservation = ReservationManager.getInstance().getReservationById(id);
         
        RoomType roomType = reservation.getRoom();    	
    	double amount = roomType.calculateCost(calculateDaysStayed(reservation.getCheckInDate(), reservation.getCheckOutDate()));
    	Bill bill = new Bill(customer, amount);
    	
    	System.out.println("Would you like to redeem rewards points to pay for your stay?");
    	String memberResponse = scanner.next();
        boolean useRewards = memberResponse.equalsIgnoreCase("y");
        if(useRewards)
        {
        	PaymentStrategy rewardPaymentStrategy = new RedeemLoyaltyPoints();
        	bill.setPaymentStrategy(rewardPaymentStrategy);
        	bill.payBill();
        }
        else
        {
        	PaymentStrategy creditCardPaymentStrategy = new CreditCardPayment();
        	bill.setPaymentStrategy(creditCardPaymentStrategy);
        	bill.payBill();	
		}
    
    }
    
    
    
    
    
    public static int calculateDaysStayed(LocalDate checkInDate, LocalDate checkOutDate) {
    	
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        
    }
    
    
}

