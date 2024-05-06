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
            System.out.println("5. Check In");
            System.out.println("6. Check Out & Pay Bill");
            System.out.println("7. Generate Report of Past Reservations");
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
                	getReport();
                	break;
                case 8:
                	System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    	}
  




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

        boolean isRewardsMember = askYesNoQuestion("Would you like to become a rewards member? (y/n): ");

        Customer newCustomer = new Customer(firstName, lastName, email, phoneNum, address, birthdate, age, isRewardsMember, 0, cardNum);
        CustomerDBSingleton customerDB = CustomerDBSingleton.getInstance();
        customerDB.addCustomer(newCustomer);
        customerDB.saveDatabase();
        if (isRewardsMember) {
            LoyaltyMemberSignUp.signUp(newCustomer);
        }
        System.out.println("\n\nCongratulations! You have created a profile with us. Your CustomerID is " + newCustomer.getCustomerID() + ". Make sure you remember it if you ever stay with us again!\n\n");

        customer = newCustomer;
        return;
    }


    private static void printIntro() throws InvalidInputException {
        try {
            System.out.println("\n\n-----Welcome to Group AA's Luxury Hotel-----\n--------------------------------------------\n");
            boolean hasStayedBefore = askYesNoQuestion("Have you stayed with us before? (y/n): ");
            if (hasStayedBefore) {
                System.out.println("Please enter your customer ID: ");
                int customerId = scanner.nextInt();
                CustomerDBSingleton customerManager = CustomerDBSingleton.getInstance();
                customer = customerManager.getCustomer(customerId);

                if (customer == null) {
                    throw new InvalidInputException("Customer ID not found.");
                }

                System.out.println("Welcome back, " + customer.getFirstName() + "! We are so happy to see you again.");
            } else {
                registerCustomer();
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
            printIntro();
        }
    }

    
    
    
    //uses customer id and input to make new reservation
    private static void makeReservation(int customerID) {
    	String roomType;
        while (true) {
            System.out.print("Enter room type (Standard, Deluxe, Suite or Conference): ");
            roomType = scanner.next(); // Normalize input
            String lower = roomType.trim().toLowerCase();
            
            
            if (lower.equals("standard") || lower.equals("deluxe") || lower.equals("suite") || lower.equals("conference")){
                break; 
            } else {
                System.out.println("Invalid room type. Please enter Standard, Deluxe, Suite, or Conference.");
            }
        }
        
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.next();
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.next();
        
        
        
        List<ServiceRequest> serviceRequests = collectRequests();
        
        
        if(serviceRequests.isEmpty()) { //if customer didn't have any requests, just add a base request so we don't deal with null
    		emptyServiceRequest(serviceRequests);
    	}

        
        Reservation reservation = ReservationManager.getInstance().createReservation(customerID, roomType, checkInDate, checkOutDate, serviceRequests);
        
        if (reservation != null) {
            System.out.println("Reservation made successfully: " + reservation.toString());
        } else {
            System.out.println("Failed to make a reservation. No available rooms.");
        }
    }
    
    
    private static void emptyServiceRequest(List<ServiceRequest> serviceRequests) {
    	ServiceRequest request = new ServiceRequest("N/A", 0, "N/A");
    	serviceRequests.add(request);
    }
    
    
    public static List<ServiceRequest> collectRequests() {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        boolean hasSpecialRequest = askYesNoQuestion("Do you have any special requests? (y/n)");
        
        if (hasSpecialRequest) {
            collectRequest(serviceRequests);
        }

        return serviceRequests;
    }

    private static void collectRequest(List<ServiceRequest> serviceRequests) {
        while (true) {
            System.out.print("What type of request do you have? ");
            String type = scanner.next();
            
            System.out.print("What is the quantity of your request? ");
            int quantity = scanner.nextInt();
            
            scanner.nextLine(); 
            System.out.print("Do you have any notes to add? ");
            String notes = scanner.nextLine();
            
            serviceRequests.add(new ServiceRequest(type, quantity, notes));
            
            if (!askYesNoQuestion("Do you have any additional requests? (y/n)")) {
                break;
            }
        }
    }

    private static boolean askYesNoQuestion(String question) {
        while (true) {
            System.out.print(question);
            String answer = scanner.next().trim().toLowerCase();
            if (answer.equals("y")) {
                return true;
            } else if (answer.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    
    
    
    
    
//cancels reservation
    private static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        
        Reservation reservation = ReservationManager.getInstance().getReservationById(reservationId);
        if (reservation == null) {
            System.out.println("Failed to cancel reservation. ID not found.");
            return;
        }
        
        //check if the current customer matches the customer who booked the reservation
        if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) {
            if (ReservationManager.getInstance().cancelReservation(reservationId)) {
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Failed to cancel reservation.");
            }
        } else {
            System.out.println("You can't cancel this reservation. You are not the customer who booked it.");
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
            //check if the current customer matches the customer who booked the reservation
            if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) {
                System.out.println("Reservation details: " + reservation.toString());
            } else {
                System.out.println("You are not authorized to view this reservation.");
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    
    
    private static void customerCheckOut() {
        System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt();
       
        Reservation reservation = ReservationManager.getInstance().getReservationById(id);
        
        if (reservation != null) {
            //check if the current customer matches the customer who booked the reservation
            if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) {
                CIOReceiver receiver = new CIOReceiver();
                CIOCommand checkOutCommand = new CheckOutCommand(receiver);
            
                CIOInvoker manager = new CIOInvoker(null, checkOutCommand);
                manager.checkOut(customer, reservation);
                
                payCustomerBill(customer, id);
            } else {
                System.out.println("You are not authorized to check out this reservation.");
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    private static void customerCheckIn() {
        System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt();
       
        Reservation reservation = ReservationManager.getInstance().getReservationById(id);
        
        if (reservation != null) {
            
            if (customer != null && reservation.getCustomerId() == customer.getCustomerID()) {
                CIOReceiver receiver = new CIOReceiver();
                CIOCommand checkInCommand = new CheckInCommand(receiver);
            
                CIOInvoker manager = new CIOInvoker(checkInCommand, null);
                manager.checkIn(customer, reservation);
            } else {
                System.out.println("You are not authorized to check in this reservation.");
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    
    
    
    
    private static void payCustomerBill(Customer customer, int id)
    {
    	 
        
         Reservation reservation = ReservationManager.getInstance().getReservationById(id);
         
        RoomType roomType = reservation.getRoom();    	
    	double amount = roomType.calculateCost(calculateDaysStayed(reservation.getCheckInDate(), reservation.getCheckOutDate()), reservation.getCheckInDate());
    	Bill bill = new Bill(customer, amount);
    	
    	System.out.println("Would you like to redeem rewards points to pay for your stay? (y/n)");
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
    
    
    public static void getReport() {
    	
    	System.out.println("We can generate a report of past reservations. \nGive us a start and end date and we'll give you the"
    			+ " reservations that occured during that time.");
    	
    	
    	System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.next());


        System.out.print("Enter end date (YYYY-MM-DD): ");
        scanner.nextLine();
        LocalDate endDate = LocalDate.parse(scanner.next());

        List<Reservation> pastReservations = ReservationManager.getInstance().generatePastHistory(startDate, endDate);

        if (pastReservations.isEmpty()) {
            System.out.println("No reservations found for the specified date range.");
        } else {
            System.out.println("\nPast reservations for the specified date range:\n");
            for (Reservation reservation : pastReservations) {
                System.out.println(reservation.toString() + "\n\n"); 
            }
        }
    	
    }
    
    
    
    
    
    public static int calculateDaysStayed(LocalDate checkInDate, LocalDate checkOutDate) {
    	
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        
    }
    
    
}

