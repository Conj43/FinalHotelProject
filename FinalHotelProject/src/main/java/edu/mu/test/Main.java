package edu.mu.test;


import edu.mu.customer.Customer;
import edu.mu.customer.CustomerDBSingleton;
import edu.mu.hotel.*;


import java.util.Scanner;




public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer customer;

    public static void main(String[] args) {
        
    	
    	printIntro();
    	
        //menu options
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Check Room Availability");
            System.out.println("4. Confirm a Reservation");
            System.out.println("5. Exit");
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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    	}
    


    
    
    
    private static void printIntro() {
    	System.out.println("-----Welcome to Group AA's Luxury Hotel-----\n\n");
    	System.out.println("Have you stayed with us before? (y/n): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            System.out.println("Please enter your customer ID: ");
            int customerId = scanner.nextInt();
            CustomerDBSingleton customerManager = CustomerDBSingleton.getInstance();
            customer = customerManager.getCustomer(customerId);
            System.out.println("Welcome back, "+ customer.getFirstName() + "! We are so happy to see you again.");
            return;
            
        } if (response.equalsIgnoreCase("n")) {
           
            System.out.println("Please enter your first name: ");
            String firstName = scanner.next();
           
            System.out.println("Please enter your last name: ");
            String lastName = scanner.next();
            
            System.out.println("Please enter your email: ");
            String email = scanner.next();
            
            System.out.println("Please enter your phone number: ");
            String phoneNum = scanner.next();
            
            System.out.println("Please enter your address: ");
            scanner.nextLine();
            String address = scanner.nextLine();
            
            System.out.println("Please enter your birthdate: ");
            String birthdate = scanner.next();
            
            System.out.println("Please enter your age: ");
            int age = scanner.nextInt();
            
            System.out.println("Please enter your credit card number: ");
            String cardNum = scanner.next();
            
            
            System.out.println("Would you like to become a rewards member? (yes/no): ");
            String memberResponse = scanner.next();
            boolean isRewardsMember = memberResponse.equalsIgnoreCase("yes");

            Customer newCustomer = new Customer(firstName, lastName, email, phoneNum, address, birthdate, age, isRewardsMember, 0, cardNum);
            CustomerDBSingleton customerDB = CustomerDBSingleton.getInstance();
            customerDB.addCustomer(newCustomer);
            customerDB.saveDatabase();
            System.out.println("\n\nCongratulations! You have created a profile with us. Your CustomerID is " + newCustomer.getCustomerID() + ". Make sure you remember it if you ever stay with us again!");

            customer = newCustomer;
            return;
            
           
        } else {
            System.out.println("Invalid response. Please try again.");
            printIntro(); 
        }
        
    }
//makes reservation
    private static void makeReservation(int customerID) {
        System.out.print("Enter room type (Standard, Deluxe, Suite): ");
        String roomType = scanner.next();
        
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
}

