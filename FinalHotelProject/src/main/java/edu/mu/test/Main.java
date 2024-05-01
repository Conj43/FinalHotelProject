package edu.mu.test;

import edu.mu.*;
import edu.mu.hotel.*;

import java.time.LocalDate;
import java.util.Scanner;




public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static RoomTypeManager roomTypeManager = new RoomTypeManager();

    public static void main(String[] args) {
        //initialize room types at the start
        initializeRoomTypes();

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
                    makeReservation();
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

 //room types I added you can change these to whatever you want. 
    private static void initializeRoomTypes() {
        roomTypeManager.addRoomType(new RoomType("Standard", 10, 100.00));
        roomTypeManager.addRoomType(new RoomType("Deluxe", 5, 150.00));
        roomTypeManager.addRoomType(new RoomType("Suite", 2, 250.00));
        System.out.println("Room types initialized.");
    }
//makes reservation
    private static void makeReservation() {
        System.out.print("Enter room type (Standard, Deluxe, Suite): ");
        String roomType = scanner.next();
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());

        Reservation reservation = roomTypeManager.createReservation(roomType, checkInDate, checkOutDate);
        if (reservation != null) {
            System.out.println("Reservation made successfully: " + reservation);
        } else {
            System.out.println("Failed to make a reservation. No available rooms.");
        }
    }
//cancels reservation
    private static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        if (roomTypeManager.cancelReservation(reservationId)) {
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
        LocalDate checkInDate = LocalDate.parse(scanner.next());
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());

        boolean available = roomTypeManager.checkAvailability(roomType, checkInDate, checkOutDate);
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
        Reservation reservation = roomTypeManager.getReservationById(reservationId);
        if (reservation != null) {
            System.out.println("Reservation details: " + reservation);
        } else {
            System.out.println("Reservation ID not found.");
        }
    }
}

