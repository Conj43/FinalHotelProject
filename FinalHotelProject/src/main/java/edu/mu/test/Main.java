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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/*
 * main class for our hotel system, where everything happens
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in); //scanner we use throughout
    private static Customer customer; //only one customer is used when we are running, need to restart to get a different customer

    
    /*
     * this is our main where we run our methods
     */
    public static void main(String[] args) throws InvalidInputException {
        
    	
    	printIntro();
    	menuOptions();
        
       
    	}
    
    /*
     * method to print menu options
     */
    private static void menuOptions() throws InvalidInputException {
    	 while (true) {
             System.out.println("\nHotel Reservation System");
             System.out.println("1. Make a Reservation");
             System.out.println("2. Cancel a Reservation");
             System.out.println("3. Check Room Availability");
             System.out.println("4. Confirm a Reservation");
             System.out.println("5. Check In");
             System.out.println("6. Check Out & Pay Bill");
             System.out.println("7. Generate Report of Past Reservations");
             System.out.println("8. Exit");
             System.out.print("Select an option: ");

             int option = scanner.nextInt(); //take user input to see what option they pick
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
                 	getReport();
                 	break;
                 case 8:
                 	System.out.println("Exiting..."); //exits
                     return;
                 default:
                     System.out.println("Invalid option. Please try again."); //if no option picked
             }
         }
    }
  



    /*
     * method to register a customer
     * has them create a new profile
     */
    private static void registerCustomer() {
        System.out.println("Please enter your information to create a new profile:");
        System.out.println("-----------------------------------------------------");

        System.out.println("First Name: "); //scans for each attribute we need to create a customer
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

        boolean isRewardsMember = askYesNoQuestion("Would you like to become a rewards member? (y/n): ");

        Customer newCustomer = new Customer(firstName, lastName, email, phoneNum, address, birthdate, age, isRewardsMember, 0, cardNum);
        CustomerDBSingleton customerDB = CustomerDBSingleton.getInstance(); //get instance of customer db singleton
        customerDB.addCustomer(newCustomer); //use it to add customer and save database
        customerDB.saveDatabase();
        if (isRewardsMember) { //if they chose to be rewards member, tell them how it works
            LoyaltyMemberSignUp.signUp(newCustomer);
        }
        //make sure they remember their customer id
        System.out.println("\n\nCongratulations! You have created a profile with us. Your CustomerID is " + newCustomer.getCustomerID() + ". Make sure you remember it if you ever stay with us again!\n\n");
        //set our customer to the new customer so we can use it in other methods
        customer = newCustomer;
        return;
    }

    /*
     * method to print introduction to system
     */
    private static void printIntro() throws InvalidInputException {
        try {
            System.out.println("\n\n-----Welcome to Group AA's Luxury Hotel-----\n--------------------------------------------\n");
            boolean hasStayedBefore = askYesNoQuestion("Have you stayed with us before? (y/n): ");
            if (hasStayedBefore) { //if they have a profile with us
                System.out.println("Please enter your customer ID: ");
                int customerId = scanner.nextInt();
                CustomerDBSingleton customerManager = CustomerDBSingleton.getInstance();
                customer = customerManager.getCustomer(customerId); //set customer to corresponding customer

                if (customer == null) {//make sure customer id is valid
                    throw new InvalidInputException("Customer ID not found.");
                }

                System.out.println("Welcome back, " + customer.getFirstName() + "! We are so happy to see you again."); //welcome them back if they have been here 
            } else { //if they dont have a profile with is
                registerCustomer();
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
            printIntro(); //start again if we get error
        }
    }

    
    
    
    /*
     * uses customer id to make a new reservation
     * @param int customer id
     */
    private static void makeReservation(int customerID) {
    	String roomType;
        while (true) {
            System.out.print("Enter room type (Standard, Deluxe, Suite or Conference): ");
            roomType = scanner.next(); //get room type they want
            String lower = roomType.trim().toLowerCase(); //make it so it isnt case sensitive
            
            
            if (lower.equals("standard") || lower.equals("deluxe") || lower.equals("suite") || lower.equals("conference")){
                break; 
            } else {
                System.out.println("Invalid room type. Please enter Standard, Deluxe, Suite, or Conference."); //if invalid, ask for input again
            }
        }
        
        System.out.print("Enter check-in date (YYYY-MM-DD): "); //enter check in date
        String checkInDate = scanner.next();
        
        System.out.print("Enter check-out date (YYYY-MM-DD): "); //enter check out date
        String checkOutDate = scanner.next();
        
        
        
        List<ServiceRequest> serviceRequests = collectRequests(); //initialize service requests
        
        
        if(serviceRequests.isEmpty()) { //if customer didn't have any requests, just add a base request so we don't deal with null
    		emptyServiceRequest(serviceRequests);
    	}

        //create new reservation with ingo
        Reservation reservation = ReservationManager.getInstance().createReservation(customerID, roomType, checkInDate, checkOutDate, serviceRequests);
        
        if (reservation != null) {
            System.out.println("Reservation made successfully: " + reservation.toString()); //prints info if reservation was made right
        } else {
            System.out.println("Failed to make a reservation. No available rooms."); //print error if we couldnt make reservation
        }
    }
    
    /*
     * method to make base service request
     * @param list of service requests
     */
    private static void emptyServiceRequest(List<ServiceRequest> serviceRequests) {
    	ServiceRequest request = new ServiceRequest("N/A", 0, "N/A"); //default values
    	serviceRequests.add(request); //add to list
    }
    
    /*
     * collect requests from customer
     * @return list of requests
     */
    public static List<ServiceRequest> collectRequests() {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        boolean hasSpecialRequest = askYesNoQuestion("Do you have any special requests? (y/n)");
        
        if (hasSpecialRequest) { //if they want, ask them for info
            collectRequest(serviceRequests);
        }

        return serviceRequests; //else return empty list
    }

    
    /*
     * method to get more info on requests
     * @param list of service requests
     */
    private static void collectRequest(List<ServiceRequest> serviceRequests) {
        while (true) {
            System.out.print("What type of request do you have? "); //ask for details and save it to variables
            String type = scanner.next();
            
            System.out.print("What is the quantity of your request? ");
            int quantity = scanner.nextInt();
            
            scanner.nextLine(); 
            System.out.print("Do you have any notes to add? ");
            String notes = scanner.nextLine();
            
            serviceRequests.add(new ServiceRequest(type, quantity, notes)); //create a new request and add it to list
            
            if (!askYesNoQuestion("Do you have any additional requests? (y/n)")) {
                break; //make sure they don't have any more requests, if theyre done then break
            }
        }
    }

    
    /*
     * helps us ask yes or no question 
     * @param string question to be asked
     * @return true for yes, false for no
     */
    private static boolean askYesNoQuestion(String question) {
        while (true) {
            System.out.print(question);
            String answer = scanner.next().trim().toLowerCase(); //make so it isnt case sensitive
            if (answer.equals("y")) { //y for yes
                return true;
            } else if (answer.equals("n")) { //n for no
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'."); //if input was not valid
            }
        }
    }

    
    
    
    
    
/*
 * methid to cancel reservation
 */
    private static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationId = scanner.nextInt(); //get reservation id
        
        Reservation reservation = ReservationManager.getInstance().getReservationById(reservationId); //get reservation using id
        if (reservation == null) { //make sure it exists
            System.out.println("Failed to cancel reservation. ID not found.");
            return;
        }
        
        //check if the current customer matches the customer who booked the reservation
        if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) {
            if (ReservationManager.getInstance().cancelReservation(reservationId)) { //returns true on success, false on failure
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Failed to cancel reservation.");
            }
        } else {
            System.out.println("You can't cancel this reservation. You are not the customer who booked it."); //if customer id doesnt match, tell user
        }
    }

    
    
    
    
    
    
/*
 * prompts user for date range and tells them if there is availability or not
 */
    private static void checkAvailability() {
        System.out.print("Enter room type to check (Standard, Deluxe, Suite, Conference): "); //get room type
        String roomType = scanner.next();
        
        System.out.print("Enter check-in date (YYYY-MM-DD): "); //get check in and out date
        String checkInDate = scanner.next();
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.next();
        
        boolean available = ReservationManager.getInstance().checkAvailability(roomType, checkInDate, checkOutDate);  //check availability, true if available false if not
        
        if (available) {
            System.out.println("Rooms are available."); //prints if there is availability
        } else {
            System.out.println("No rooms available for the selected dates."); //prints if no availaility
        }
    }

    
    
    
    
    /*
     * method to give user info on their reservation
     */
    private static void confirmReservation() {
        System.out.print("Enter reservation ID to confirm: ");
        int reservationId = scanner.nextInt(); //get reservation id
        
        Reservation reservation = ReservationManager.getInstance().getReservationById(reservationId); //get reservation from id
        
        if (reservation != null) { //make sure it exists
            //check if the current customer matches the customer who booked the reservation
            if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) {
                System.out.println("Reservation details: " + reservation.toString()); //if we find it, print out info
            } else {
                System.out.println("You are not authorized to view this reservation."); //if customer id's dont match, tell user
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    
    /*
     * checks out customer and has them pay their bill
     */
    private static void customerCheckOut() {
        System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt(); //get reservation id
       
        Reservation reservation = ReservationManager.getInstance().getReservationById(id); //get reservation from id
        
        if (reservation != null) { //if it exists
            //check if the current customer matches the customer who booked the reservation
            if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) { //check if customer ids match
                CIOReceiver receiver = new CIOReceiver();
                CIOCommand checkOutCommand = new CheckOutCommand(receiver);
            
                CIOInvoker manager = new CIOInvoker(null, checkOutCommand); //access invoker
                manager.checkOut(customer, reservation); //use design patter to get check out
                
                payCustomerBill(customer, id); //pay bill after you check out
            } else {
                System.out.println("You are not authorized to check out this reservation."); //if id does not match
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }
    
    /*
     * checks customer into their rservation
     */
    private static void customerCheckIn() {
        System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt(); //get id
       
        Reservation reservation = ReservationManager.getInstance().getReservationById(id); //using reseveration id, get reservation
        
        if (reservation != null) { //make sure it exists
            
            if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) { //check if customer ids match
                CIOReceiver receiver = new CIOReceiver();
                CIOCommand checkInCommand = new CheckInCommand(receiver);
            
                CIOInvoker manager = new CIOInvoker(checkInCommand, null); //access invoker
                manager.checkIn(customer, reservation);
            } else {
                System.out.println("You are not authorized to check in this reservation."); //if customer id doesnt match
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    
    
    
    /*
     * method to pay bill
     * @param customer object
     * @param reservation id int
     */
    private static void payCustomerBill(Customer customer, int id)
    {
    	 
        
         Reservation reservation = ReservationManager.getInstance().getReservationById(id); //get reservation from id
         
        RoomType roomType = reservation.getRoom();    	
    	double amount = roomType.calculateCost(calculateDaysStayed(reservation.getCheckInDate(), reservation.getCheckOutDate()), reservation.getCheckInDate()); //use method to get days stayed
    	Bill bill = new Bill(customer, amount); //create the bill
    	
    	System.out.println("Would you like to redeem rewards points to pay for your stay? (y/n)");
    	String memberResponse = scanner.next(); //ask if they want to use reward points
        boolean useRewards = memberResponse.equalsIgnoreCase("y");
        if(useRewards) //if they want to use rewards
        {
        	PaymentStrategy rewardPaymentStrategy = new RedeemLoyaltyPoints(); //use loyalty points to pay
        	bill.setPaymentStrategy(rewardPaymentStrategy);
        	bill.payBill();
        }
        else //if they dont, then use their card number on file
        {
        	PaymentStrategy creditCardPaymentStrategy = new CreditCardPayment();
        	bill.setPaymentStrategy(creditCardPaymentStrategy);
        	bill.payBill();	
		}
    
    }
    
    /*
     * method to print a report of reservations
     */
    public static void getReport() {
    	
    	System.out.println("We can generate a report of past reservations. \nGive us a start and end date and we'll give you the"
    			+ " reservations that occured during that time.");
    	
    	
    	System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.next()); //give start and end of date range to get reservations


        System.out.print("Enter end date (YYYY-MM-DD): ");
        scanner.nextLine();
        LocalDate endDate = LocalDate.parse(scanner.next());

        List<Reservation> pastReservations = ReservationManager.getInstance().generatePastHistory(startDate, endDate); //call method to return list of reservations in range

        if (pastReservations.isEmpty()) { //if empty, tell user
            System.out.println("No reservations found for the specified date range.");
        } else {
            System.out.println("\nPast reservations for the specified date range:\n");
            for (Reservation reservation : pastReservations) { //else print out all reservations in range
                System.out.println(reservation.toString() + "\n\n"); 
            }
        }
    	
    }
    
    
    
    
    /*
     * method to calculate days stayed using check in and out dates
     * @param date of check in and check out
     * @retun int of num of days stayed
     */
    public static int calculateDaysStayed(LocalDate checkInDate, LocalDate checkOutDate) {
    	
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate); //use number of days between check in and check out
        
    }
    
    
}

